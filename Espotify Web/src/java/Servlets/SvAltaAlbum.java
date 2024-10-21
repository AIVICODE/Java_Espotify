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
    // Deserializar los temas desde el JSON
    TemaData[] temasArray = gson.fromJson(temasJson, TemaData[].class);
    for (TemaData temaData : temasArray) {
        // Crear el objeto DTTema usando los minutos y segundos
        DTTema nuevoTema = new DTTema(temaData.getNombre(), temaData.getMinutos(), temaData.getSegundos(), temaData.getUrl());
        listaTemas.add(nuevoTema);
    }
}
        try {
            // Crear el álbum con los datos recibidos
            DTAlbum nuevoAlbum = new DTAlbum(nombreAlbum, anioAlbum, "ruta archivo", generos);
            System.out.println("Creando álbum: " + nombreAlbum + " (Año: " + anioAlbum + ")");

            // Crear el álbum en la lógica de negocio
            for (DTTema tema : listaTemas) {
            System.out.println("Nombre: " + tema.getNombre());
        System.out.println("Minutos: " + tema.getMinutos());
        System.out.println("Segundos: " + tema.getSegundos());
        System.out.println("URL: " + tema.getDirectorio());
            System.out.println("-----------");
        }
            control.CrearAlbum(correoArtista, nuevoAlbum, listaTemas);
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al crear el álbum: " + e.getMessage());
            request.getRequestDispatcher("altaAlbum.jsp").forward(request, response);
        }
    }
}
