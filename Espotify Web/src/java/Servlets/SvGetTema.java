package Servlets;
import webservices.DtAlbum;
import webservices.DtListaRep;
import webservices.DtTema;
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

import com.google.gson.Gson;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.IOException_Exception;

@WebServlet(name = "SvGetTema", urlPatterns = {"/SvGetTema"})
public class SvGetTema extends HttpServlet {

    //private final Fabrica fabrica = Fabrica.getInstance();
    //private final IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            
            // Obtener la ruta del tema desde el parámetro
            String rutaTema = request.getParameter("rutaTema");
            System.out.println("ruta llega de jsp obtenidos: " + rutaTema);
            if (rutaTema == null || rutaTema.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La ruta del tema es requerida.");
                return;
            }
            
            // Llama al método obtenerTemaComoBytes para obtener el archivo en bytes
            byte[] temaBytes = control.obtenerTemaComoBytes(rutaTema);
            if (0<=temaBytes.length){
                System.out.println(" bytes no es vacio: ");
            }
            System.out.println("fuera de if  ");
            
            // Configura la respuesta para enviar el archivo como audio
            response.setContentType("audio/mpeg");
            response.setContentLength(temaBytes.length);
            try (OutputStream os = response.getOutputStream()) {
                os.write(temaBytes);
            }
            
        }   catch (IOException_Exception ex) {
            Logger.getLogger(SvGetTema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
