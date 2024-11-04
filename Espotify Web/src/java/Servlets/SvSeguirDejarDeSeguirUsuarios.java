package Servlets;

import Datatypes.DTUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SvSeguirDejarDeSeguirUsuarios", urlPatterns = {"/SvSeguirDejarDeSeguirUsuarios"})
public class SvSeguirDejarDeSeguirUsuarios extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
        System.out.println("llea el user "+ dtUsuario.getNickname());
        if (dtUsuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String correoSeguidor = dtUsuario.getCorreo();
        String nickNameSeguidor= dtUsuario.getNickname();
        String nicknameSeguido = request.getParameter("nicknameSeguido");
        String correoSeguido = request.getParameter("correoSeguido");
        System.out.println("llea el seguidor "+ nicknameSeguido );
        System.out.println("llea el seguidor "+ correoSeguido );
        try {
            boolean yaSigue = control.estaSiguiendoUsuario(nickNameSeguidor, nicknameSeguido);
            if (yaSigue) {
                System.out.println("if ya lo sigue");
                control.dejarSeguirUsuario(correoSeguidor, correoSeguido);
                response.getWriter().write("Dejaste de seguir a " + nicknameSeguido);
            } else {
                System.out.println("Else no lo sigue");
                control.seguirUsuario(correoSeguidor, correoSeguido);
                response.getWriter().write("Ahora sigues a " + nicknameSeguido);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}