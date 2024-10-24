package Servlets;

import Datatypes.DTCliente;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import Logica.Fabrica;
import Logica.IControlador;
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


@WebServlet(name = "SvPublicarListaPrivada", urlPatterns = {"/SvPublicarListaPrivada"})
public class SvPublicarListaPrivada extends HttpServlet {
Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        
        String nombreLista = request.getParameter("nombreLista");
        //que no mande nada al controlador si no se selecciona lista
        if (nombreLista.equals("")){
            request.setAttribute("mensajeExito", "Debe seleccionar una lista valida");
            request.getRequestDispatcher("PublicarLista.jsp").forward(request, response);
        }
        try {
            control.publicarListaPrivada(dtUsuario.getNickname(), nombreLista);
            //Mensaje de exito en jsp
            request.setAttribute("mensajeExito", "La lista '" + nombreLista + "' ha sido publicada con éxito.");

            // Redirigir al JSP donde se mostrará el mensaje
            request.getRequestDispatcher("PublicarLista.jsp").forward(request, response);
                        
        } catch (Exception ex) {
            Logger.getLogger(SvPublicarListaPrivada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
