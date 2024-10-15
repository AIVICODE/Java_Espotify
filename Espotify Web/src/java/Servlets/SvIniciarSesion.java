package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logica.IControlador;
import Logica.Fabrica;
import Datatypes.DTUsuario;


@WebServlet(name = "SvIniciarSesion", urlPatterns = {"/SvIniciarSesion"})
public class SvIniciarSesion extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String usuario = request.getParameter("usuario");  // puede ser nickname o email
        String password = request.getParameter("password");

        try {
            DTUsuario dtUsuario = control.login(usuario, password);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", dtUsuario);
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
