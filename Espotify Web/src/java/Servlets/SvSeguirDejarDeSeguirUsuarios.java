package Servlets;

import Datatypes.DTUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SvSeguirDejarDeSeguirUsuarios", urlPatterns = {"/SvSeguirDejarDeSeguirUsuarios"})
public class SvSeguirDejarDeSeguirUsuarios extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
        if (dtUsuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String correoSeguidor = dtUsuario.getCorreo();
        String nicknameSeguido = request.getParameter("nicknameSeguido");

        try {
            boolean yaSigue = control.estaSiguiendoUsuario(correoSeguidor, nicknameSeguido);
            if (yaSigue) {
                control.dejarSeguirUsuario(correoSeguidor, nicknameSeguido);
                response.getWriter().write("Dejaste de seguir a " + nicknameSeguido);
            } else {
                control.seguirUsuario(correoSeguidor, nicknameSeguido);
                response.getWriter().write("Ahora sigues a " + nicknameSeguido);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
