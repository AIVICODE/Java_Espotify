
package Servlets;
import Datatypes.DTArtista;
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

@WebServlet(name = "SvCargarUsuarios", urlPatterns = {"/SvCargarUsuarios"})
public class SvCargarUsuarios extends HttpServlet {
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
            out.println("<title>Servlet SvCargarUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCargarUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        
        List<String> listaUsuarios = control.nicknamesDeTodosLosArtistas(); //el combo va a tener primero los artistas y luego los cientes
        for (DTCliente c:control.listaClientesDT()){
            listaUsuarios.add(c.getNickname());
        }
        // Configurar la respuesta para JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Convertir la lista a formato JSON
        out.print("[");
        for (int i = 0; i < listaUsuarios.size(); i++) {
            out.print("\"" + listaUsuarios.get(i) + "\"");
            if (i < listaUsuarios.size() - 1) {
                out.print(", ");
            }
        }
        out.print("]");
        
        out.flush();
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
