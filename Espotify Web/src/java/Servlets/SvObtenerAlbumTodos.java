package Servlets;

import webservices.DtAlbum;
import webservices.DtCliente;
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
import com.google.gson.Gson;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTAlbum;



@WebServlet(name = "SvObtenerAlbumTodos", urlPatterns = {"/SvObtenerAlbumTodos"})
public class SvObtenerAlbumTodos extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 try {
        ListaDTAlbum alb = control.findDTAlbumTodos();
        List<DtAlbum> albumes = alb.getLista(); // Obtener todos los álbumes
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Generar opciones HTML para el ComboBox de álbumes, identificando por nombre de álbum y nombre de artista
       for (DtAlbum album : albumes) {
    out.println("<option value='" + album.getNombre() + " - " + album.getArtista().getNickname() + "'>" + album.getNombre() + " - " + album.getArtista().getNickname() + "</option>");
}
    } catch (Exception ex) {
        Logger.getLogger(SvObtenerAlbumTodos.class.getName()).log(Level.SEVERE, null, ex);
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
