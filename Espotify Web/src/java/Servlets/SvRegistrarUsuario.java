
package Servlets;

import Datatypes.DTArtista;
import Datatypes.DTCliente;
import Datatypes.DTUsuario;
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


@WebServlet(name = "SvRegistrarUsuario", urlPatterns = {"/SvRegistrarUsuario"})
public class SvRegistrarUsuario extends HttpServlet {
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
            out.println("<title>Servlet SvRegistrarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvRegistrarUsuario at " + request.getContextPath() + "</h1>");
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
        //Logica de agregar usuario
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("email");
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("surname");
        String contrasenia = request.getParameter("password");
        String contraseniaC = request.getParameter("confirmPassword");     
        String tipoUsuario = request.getParameter("userType");
        String nacimiento = request.getParameter("dob");//ver esto que onda
        Date fecha = convertirStringADate(nacimiento);
        String imagen = request.getParameter("image");
        
        //Artista
        String biografia = request.getParameter("bio");
        String web = request.getParameter("website");
        
        //DTUsuario user = new DTArtista o cliente
        DTUsuario usuario = new DTUsuario();
        if(tipoUsuario.equals("Cliente")){
            usuario = new DTCliente(nickname, nombre, apellido, correo, fecha, contrasenia, contraseniaC, imagen);
        }else{
            usuario = new DTArtista(nickname, nombre, apellido, contrasenia, contraseniaC, imagen, fecha, correo, biografia, web);
        }
        try {
            control.crearUsuario(usuario);
        } catch (Exception e) {
            Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, e);
        }     
        
        //logica para ver si el usuario quedo registrado, que redireccione a la pag del formulario con mensaje de exito
        if(control.existeNickname(nickname)){//si quedo registrado deberia dar true esta operacion 
            request.setAttribute("successMessage", "Registro exitoso.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");//el formulario
            dispatcher.forward(request, response);
        } else {//false si no se registró
            request.setAttribute("errorMessage", "Error al registrar. Intenta nuevamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }  
    }

    
    public static Date convertirStringADate(String fechaString) {
        // Definir el formato esperado
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            // Convertir el String a Date
            fecha = formato.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return fecha; // Retornar el objeto Date
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
