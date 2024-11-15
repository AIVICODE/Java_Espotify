package Servlets;

import webservices.DtAlbum;
import webservices.DtListaRep;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtUsuario;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.IOException_Exception;

@WebServlet(name = "SvGetImagen", urlPatterns = {"/SvGetImagen"})
public class SvGetImagen extends HttpServlet {
    //private final Fabrica fabrica = Fabrica.getInstance();
    //private final IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
        } catch (IOException_Exception ex) {
            Logger.getLogger(SvGetImagen.class.getName()).log(Level.SEVERE, null, ex);
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