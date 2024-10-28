package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.annotation.MultipartConfig;
import java.nio.file.Files;
/**
 *
 * @author ivan
 */
@MultipartConfig
@WebServlet(name = "SvAgregarListaRep", urlPatterns = {"/SvAgregarListaRep"})
public class SvAgregarListaRep extends HttpServlet {
Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  try {
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");

        // Obtener el nombre de la lista desde el formulario
        String listName = request.getParameter("nombreLista");

        Part imagenPart = request.getPart("imagenLista");
        String rutaImagen = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            File archivoTemporal = File.createTempFile("album_image_", ".jpg");
            imagenPart.write(archivoTemporal.getAbsolutePath());
            byte[] archivoImagen = Files.readAllBytes(archivoTemporal.toPath());
            rutaImagen = control.guardarImagenesLista(archivoImagen, listName);
            
            System.out.println(rutaImagen);
            archivoTemporal.delete();
        }
        control.CrearListaRepParticular(listName, dtUsuario.getCorreo(), rutaImagen, true);

        // Responder con éxito en formato JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"success\": true, \"message\": \"Lista creada exitosamente.\"}");

    } catch (Exception e) {
        // Manejar errores con código de estado 400 y respuesta en formato JSON
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        String errorMessage = e.getMessage().replace("\"", "\\\""); // Escapar comillas dobles
        response.getWriter().write("{\"success\": false, \"message\": \"Error al crear la lista: " + errorMessage + "\"}");
    }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
