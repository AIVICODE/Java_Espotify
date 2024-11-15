package Servlets;

import webservices.DtCliente;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
//import Logica.Fabrica;
//import Logica.IControlador;
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


@WebServlet(name = "SvPublicarListaPrivada", urlPatterns = {"/SvPublicarListaPrivada"})
public class SvPublicarListaPrivada extends HttpServlet {
//Fabrica fabrica = Fabrica.getInstance();
//IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvPublicarListaPrivada</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvPublicarListaPrivada at " + request.getContextPath() + "</h1>");
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
        HttpSession session = (HttpSession) request.getSession(false);
    DtCliente dtUsuario = (DtCliente) session.getAttribute("usuario");

    String nombreLista = request.getParameter("nombreLista");

    response.setContentType("application/json"); // Set response type to JSON

    try {
        // Check if the list name is valid
        if (nombreLista == null || nombreLista.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Debe seleccionar una lista válida\"}");
            return;
        }

        // Publish the playlist
        control.publicarListaPrivada(dtUsuario.getNickname(), nombreLista);

        // On success
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"success\": true, \"message\": \"La lista '" + nombreLista + "' ha sido publicada con éxito.\"}");
    } catch (Exception ex) {
        Logger.getLogger(SvPublicarListaPrivada.class.getName()).log(Level.SEVERE, null, ex);

        // Send JSON error message for frontend handling
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("{\"success\": false, \"message\": \"No se pudo publicar la lista. Intente nuevamente.\"}");
    }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
