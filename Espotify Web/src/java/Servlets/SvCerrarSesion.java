package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import Datatypes.DTAlbum;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCerrarSesion", urlPatterns = {"/SvCerrarSesion"})
public class SvCerrarSesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
