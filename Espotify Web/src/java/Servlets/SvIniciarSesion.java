/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Datatypes.DTUsuario;
import jakarta.servlet.http.HttpSession;


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
            String rutaImagen = dtUsuario.getImagen();
            System.out.println("ACA IMAGEN "+rutaImagen);
            HttpSession session = request.getSession();
            byte[] imagenBytes = control.obtenerImagenComoBytes(rutaImagen);
            session.setAttribute("usuario", dtUsuario);
            session.setAttribute("imagenUsuario", imagenBytes);
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
