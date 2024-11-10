package Servlets;

import webservices.DtAlbum;
import webservices.DtTema;
import webservices.DtUsuario;
//import Logica.Fabrica;
//import Logica.IControlador;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.IOException_Exception;

@WebServlet(name = "SvAltaAlbum", urlPatterns = {"/SvAltaAlbum"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 50,      // 50MB
    maxRequestSize = 1024 * 1024 * 200   // 200MB
)
public class SvAltaAlbum extends HttpServlet {

    //private final Fabrica fabrica = Fabrica.getInstance();
    //private final IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validar la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Obtener los parámetros del álbum
        String nombreAlbum = request.getParameter("nombreAlbum");
        String anioAlbumStr = request.getParameter("anioAlbum");
        String[] generosSeleccionados = request.getParameterValues("genero[]");
        String correoArtista = ((DtUsuario) session.getAttribute("usuario")).getCorreo();

        // Procesar la imagen del álbum
        Part imagenPart = request.getPart("imagenAlbum");
        String rutaImagen = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            File archivoTemporal = File.createTempFile("album_image_", ".jpg");
            imagenPart.write(archivoTemporal.getAbsolutePath());
            byte[] archivoImagen = Files.readAllBytes(archivoTemporal.toPath());
            try {
                rutaImagen = control.guardarImagenesAlbum(archivoImagen, nombreAlbum, correoArtista);
            } catch (IOException_Exception ex) {
                Logger.getLogger(SvAltaAlbum.class.getName()).log(Level.SEVERE, null, ex);
            }
            archivoTemporal.delete();
        }

        // Convertir el año del álbum
        int anioAlbum = (anioAlbumStr != null && !anioAlbumStr.isEmpty()) ? Integer.parseInt(anioAlbumStr) : 2023;

        // Convertir los géneros seleccionados a una lista
        List<String> generos = new ArrayList<>();
        if (generosSeleccionados != null) {
            for (String genero : generosSeleccionados) {
                generos.add(genero);
            }
        }

        // Procesar múltiples temas iterando por índice
        List<DtTema> listaTemas = new ArrayList<>();
        int temaIndex = 0;

        while (true) {
            // Obtener los parámetros del tema en el índice actual
            String nombreTema = request.getParameter("nombreTema_" + temaIndex);
            String duracionTema = request.getParameter("duracionTema_" + temaIndex);
            String urlTema = request.getParameter("urlTema_" + temaIndex);
            Part archivoTemaPart = request.getPart("archivoTema_" + temaIndex);

            // Salir del bucle si no hay más temas
            if (nombreTema == null || duracionTema == null) {
                break;
            }

            // Separar la duración en minutos y segundos
            String[] duracionSplit = duracionTema.split(":");
            int minutos = Integer.parseInt(duracionSplit[0]);
            int segundos = Integer.parseInt(duracionSplit[1]);

            // Determinar la ruta del archivo de tema o URL
            String rutaTema = null;
            if (archivoTemaPart != null && archivoTemaPart.getSize() > 0) {
                File archivoMp3Temporal = File.createTempFile("tema_", ".mp3");
                archivoTemaPart.write(archivoMp3Temporal.getAbsolutePath());
                byte[] archivoMp3 = Files.readAllBytes(archivoMp3Temporal.toPath());
                try {
                    rutaTema = control.guardarTemaEnCarpeta(archivoMp3, nombreTema+"_"+nombreAlbum+"_"+((DtUsuario) session.getAttribute("usuario")).getNickname());
                } catch (IOException_Exception ex) {
                    Logger.getLogger(SvAltaAlbum.class.getName()).log(Level.SEVERE, null, ex);
                }
                //rutaTema = control.guardarTemaEnCarpeta(archivoMp3, nombreTema+"_"+nombreAlbum+"_"+((DtUsuario) session.getAttribute("usuario")).getNickname());
                archivoMp3Temporal.delete();
            } else if (urlTema != null && !urlTema.isEmpty()) {
                rutaTema = urlTema;
            }

            // Crear el objeto DTTema y añadirlo a la lista de temas
            //DtTema nuevoTema = new DtTema(nombreTema, minutos, segundos, rutaTema);
            DtTema nuevoTema = new DtTema(); 
            nuevoTema.setNombre(nombreTema);
            nuevoTema.setMinutos(minutos);
            nuevoTema.setSegundos(segundos);
            nuevoTema.setDirectorio(rutaTema);
            listaTemas.add(nuevoTema);

            // Incrementar el índice para el próximo tema
            temaIndex++;
        }

        try {
            // Crear el objeto DTAlbum y guardarlo usando el controlador
            DtAlbum nuevoAlbum = new DtAlbum();
            //DtAlbum nuevoAlbum = new DtAlbum(nombreAlbum, anioAlbum, rutaImagen, generos);
            nuevoAlbum.setNombre(nombreAlbum);
            nuevoAlbum.setAnioCreacion(anioAlbum);
            nuevoAlbum.setImagen(rutaImagen);
            nuevoAlbum.getListaGeneros().addAll(generos);
            control.crearAlbum(correoArtista, nuevoAlbum, (webservices.ArrayList) listaTemas);//control.CrearAlbum(correoArtista, nuevoAlbum, listaTemas);
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al crear el álbum: " + e.getMessage());
            request.getRequestDispatcher("altaAlbum.jsp").forward(request, response);
        }
    }
}
