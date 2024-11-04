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
import java.io.OutputStream;
import java.nio.file.Files;
/**
 *
 * @author ivan
 */
@WebServlet(name = "SvGetImagen", urlPatterns = {"/SvGetImagen"})
public class SvGetImagen extends HttpServlet {
private final Fabrica fabrica = Fabrica.getInstance();
    private final IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 String nombreImagen = request.getParameter("nombre");
        
        // Obtener la imagen como arreglo de bytes
        byte[] imagenBytes = control.obtenerImagenComoBytes(nombreImagen);

        if (imagenBytes != null) {
            // Configura el tipo de contenido de la respuesta como imagen
            response.setContentType("image/jpeg");
            response.setContentLength(imagenBytes.length);
            
            // Escribe los bytes de la imagen en el OutputStream de la respuesta
            try (OutputStream out = response.getOutputStream()) {
                out.write(imagenBytes);
            }
        } else {
            // Si no se encuentra la imagen, enviar un error 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
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
