package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Clase interna para procesar los datos del tema
class TemaData {

    private String nombre;
    private int minutos;
    private int segundos;
    private String url;

    public String getNombre() {
        return nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public String getUrl() {
        return url;
    }
    
}

@WebServlet(name = "SvAltaAlbum", urlPatterns = {"/SvAltaAlbum"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SvAltaAlbum extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validar la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Obtener los parámetros del formulario
        String nombreAlbum = request.getParameter("nombreAlbum");
        String anioAlbumStr = request.getParameter("anioAlbum");
        String[] generosSeleccionados = request.getParameterValues("genero[]");
        String temasJson = request.getParameter("temas");
        String correoArtista = ((DTUsuario) session.getAttribute("usuario")).getCorreo();

        // Procesar la imagen del álbum
        Part imagenPart = request.getPart("imagenAlbum");
        String rutaImagen = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            File archivoTemporal = File.createTempFile("album_image_", ".jpg");
            imagenPart.write(archivoTemporal.getAbsolutePath());
            byte[] archivoImagen = Files.readAllBytes(archivoTemporal.toPath());
            rutaImagen = control.guardarImagenesAlbum(archivoImagen, nombreAlbum, correoArtista);
            archivoTemporal.delete();
        }

        // Convertir el año del álbum
        int anioAlbum = anioAlbumStr != null && !anioAlbumStr.isEmpty() ? Integer.parseInt(anioAlbumStr) : 2023;

        // Convertir los géneros seleccionados a una lista
        List<String> generos = new ArrayList<>();
        if (generosSeleccionados != null) {
            for (String genero : generosSeleccionados) {
                generos.add(genero);
            }
        }

        // Procesar cada tema desde JSON y verificar si tiene archivo MP3
        List<DTTema> listaTemas = new ArrayList<>();
        if (temasJson != null && !temasJson.isEmpty()) {
            Gson gson = new Gson();
            TemaData[] temasArray = gson.fromJson(temasJson, TemaData[].class);

            for (TemaData temaData : temasArray) {
                String rutaTema = null;

                // Recorre todas las partes y busca las que coincidan con archivoTema
                for (Part part : request.getParts()) {
                    if (part.getName().equals("archivoTema") && part.getSize() > 0) {
                        File archivoMp3Temporal = File.createTempFile("tema_", ".mp3");
                        part.write(archivoMp3Temporal.getAbsolutePath());
                        byte[] archivoMp3 = Files.readAllBytes(archivoMp3Temporal.toPath());
                        rutaTema = control.guardarTemaEnCarpeta(archivoMp3, temaData.getNombre());
                        archivoMp3Temporal.delete();
                        break; // Rompe después de encontrar el archivo del tema actual
                    }
                }

                if (rutaTema == null) {
                    // Usa la URL si no hay archivo
                    rutaTema = temaData.getUrl();
                }

                DTTema nuevoTema = new DTTema(temaData.getNombre(), temaData.getMinutos(), temaData.getSegundos(), rutaTema);
                listaTemas.add(nuevoTema);
            }
        }

        try {
            DTAlbum nuevoAlbum = new DTAlbum(nombreAlbum, anioAlbum, rutaImagen, generos);
            control.CrearAlbum(correoArtista, nuevoAlbum, listaTemas);
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al crear el álbum: " + e.getMessage());
            request.getRequestDispatcher("altaAlbum.jsp").forward(request, response);
        }
    }
}