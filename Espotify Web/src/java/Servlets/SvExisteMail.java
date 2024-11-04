
package Servlets;
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

@WebServlet(name = "SvExisteMail", urlPatterns = {"/SvExisteMail"})
public class SvExisteMail extends HttpServlet {
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
            out.println("<title>Servlet SvExisteMail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvExisteMail at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        
        try {
            // Llama a tu método que verifica si el email está en uso
            boolean exists = control.existeMail(email); // Método que retorna true o false
            
            if (exists) {
                response.getWriter().write("<span class='unavailable'>✘ El correo electrónico ya está en uso.</span>");
            } else {
                response.getWriter().write("<span class='available'>✔ Correo disponible.</span>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<span class='unavailable'>Error al verificar.</span>");
        }

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
