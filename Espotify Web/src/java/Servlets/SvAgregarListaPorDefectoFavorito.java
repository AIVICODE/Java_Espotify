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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;


@WebServlet(name = "SvAgregarListaPorDefectoFavorito", urlPatterns = {"/SvAgregarListaPorDefectoFavorito"})
public class SvAgregarListaPorDefectoFavorito extends HttpServlet {
//Fabrica fabrica = Fabrica.getInstance();
//IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        DtCliente dtUsuario = (DtCliente) session.getAttribute("usuario");
        
        String listaName = request.getParameter("lista");
        
        try {
            //control.GuardarLista_Por_Defecto_Favorito(dtUsuario.getCorreo(), listaName);
            control.guardarListaPorDefectoFavorito(dtUsuario.getCorreo(), listaName);
            
            // Enviar respuesta exitosa en formato JSON
            response.getWriter().write("{\"success\": true, \"message\": \"Lista agregada a favoritos exitosamente.\"}");
        } catch (Exception ex) {
            // Enviar respuesta de error en formato JSON
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
            Logger.getLogger(SvAgregarListaParticularFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
    } catch (Exception ex) {
        Logger.getLogger(SvAgregarListaParticularFavorito.class.getName()).log(Level.SEVERE, null, ex);
    }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
