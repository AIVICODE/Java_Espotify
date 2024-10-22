package Servlets;

import Datatypes.DTAlbum;
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
import Datatypes.DTUsuario;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
/**
 *
 * @author ivan
 */
@WebServlet(name = "SvGetImagen", urlPatterns = {"/SvGetImagen"})
public class SvGetImagen extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String nombreImagen = request.getParameter("nombre");

        // Ruta donde se guardan las imágenes
        String path = getServletContext().getRealPath("/images") + File.separator + nombreImagen;
        File imageFile = new File(path);

        if (imageFile.exists() && imageFile.isFile()) {
            // Cargar la imagen en un array de bytes
            byte[] imagenBytes = Files.readAllBytes(imageFile.toPath());

            // Configurar la respuesta
            response.setContentType("image/jpeg"); // o "image/png", según el formato
            response.setContentLength(imagenBytes.length);

            // Escribir los bytes de la imagen en la respuesta
            response.getOutputStream().write(imagenBytes);
            response.getOutputStream().flush(); // Asegurarse de que se envían todos los bytes
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Si la imagen no existe
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
