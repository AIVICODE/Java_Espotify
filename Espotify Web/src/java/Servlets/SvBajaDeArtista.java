
package Servlets;


import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTCliente;
import webservices.ListaString;

@WebServlet(name = "SvBajaDeArtista", urlPatterns = {"/SvBajaDeArtista"})
public class SvBajaDeArtista extends HttpServlet {
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvBajaDeArtista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvBajaDeArtista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el nickname del artista a dar de baja
        String nickname = request.getParameter("nickname");
        
        HttpSession session = (HttpSession) request.getSession(false); //cierro sesion
        if (session != null) {
            session.invalidate();
        }
        
        System.out.println("Dando de baja al artista con nickname: " + nickname);
        control.eliminarArt(nickname); //borro artista
        System.out.println("Se elimino: " + nickname);
        
        response.sendRedirect("BajaRespuesta.jsp");//redirijo a pantalla exito
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
