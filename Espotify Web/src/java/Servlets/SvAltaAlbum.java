package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 * Servlet para manejar el alta de álbum sin cargar archivos.
 */
@WebServlet(name = "SvAltaAlbum", urlPatterns = {"/SvAltaAlbum"})
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

        // Mostrar en consola los valores recibidos
        System.out.println("correo artist: " + correoArtista);
        System.out.println("Nombre del álbum: " + nombreAlbum);
        System.out.println("Año del álbum: " + anioAlbumStr);
        System.out.println("Géneros seleccionados: " + (generosSeleccionados != null ? String.join(", ", generosSeleccionados) : "Ninguno"));
        System.out.println("Temas en formato JSON: " + temasJson);

        // Convertir el año del álbum
        int anioAlbum = 2023;  // Valor predeterminado
        if (anioAlbumStr != null && !anioAlbumStr.isEmpty()) {
            anioAlbum = Integer.parseInt(anioAlbumStr);
        }

        // Convertir los géneros seleccionados a una lista
        List<String> generos = new ArrayList<>();
        if (generosSeleccionados != null) {
            for (String genero : generosSeleccionados) {
                generos.add(genero);
            }
        }

        // Convertir el JSON de los temas a una lista de DTTema
        List<DTTema> listaTemas = new ArrayList<>();
        if (temasJson != null && !temasJson.isEmpty()) {
            Gson gson = new Gson();
            DTTema[] temasArray = gson.fromJson(temasJson, DTTema[].class);
            for (DTTema tema : temasArray) {
                listaTemas.add(tema);
            }
        }

        // Obtener el correo del artista logueado
        //String correoArtista = ((DTUsuario) session.getAttribute("usuario")).getCorreo();

        try {
            // Crear el álbum con los datos recibidos
            DTAlbum nuevoAlbum = new DTAlbum(nombreAlbum, anioAlbum, "ruta archivo", generos);
            System.out.println("Creando álbum: " + nombreAlbum + " (Año: " + anioAlbum + ")");

            // Crear el álbum en la lógica de negocio
            control.CrearAlbum(correoArtista, nuevoAlbum, listaTemas);
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al crear el álbum: " + e.getMessage());
            request.getRequestDispatcher("altaAlbum.jsp").forward(request, response);
        }
    }
}
