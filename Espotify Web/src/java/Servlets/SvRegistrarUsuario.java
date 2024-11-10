
package Servlets;

import webservices.DtArtista;
import webservices.DtCliente;
import webservices.DtUsuario;
import java.io.IOException;
import java.io.PrintWriter;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import jakarta.servlet.http.Part;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.IOException_Exception;
import Logica.DateUtils;
import javax.xml.datatype.XMLGregorianCalendar;

@WebServlet(name = "SvRegistrarUsuario", urlPatterns = {"/SvRegistrarUsuario"})
@MultipartConfig
public class SvRegistrarUsuario extends HttpServlet {
//    Fabrica fabrica = Fabrica.getInstance();
//    IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
    String nickname = request.getParameter("nickname");
    String correo = request.getParameter("email");
    String nombre = request.getParameter("name");
    String apellido = request.getParameter("surname");
    String contrasenia = request.getParameter("password");
    String contraseniaC = request.getParameter("confirmPassword");
    String tipoUsuario = request.getParameter("userType");
    String nacimiento = request.getParameter("dob");
        System.out.println("nickname recibido: " + nickname);
        System.out.println("Fecha de nacimiento recibida: " + nacimiento);

        Date fecha = convertirStringADate(nacimiento);
        
        // Manejo de la imagen
        Part imagenPart = request.getPart("image"); // Obtener la parte del archivo
        String imagen = null;

        if (imagenPart != null && imagenPart.getSize() > 0) {
            byte[] archivoImagen = new byte[(int) imagenPart.getSize()];
            imagenPart.getInputStream().read(archivoImagen); // Leer el archivo como bytes
        try {
            imagen = control.guardarImagenesEnCarpeta(archivoImagen, nickname); // Guardar la imagen
        } catch (IOException_Exception ex) {
            Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            imagen = "/home/usuario/imagenes_usuarios/generico.jpg"; // Ruta por defecto
        }

        
        //Artista
        String biografia = request.getParameter("bio");
        String web = request.getParameter("website");
        XMLGregorianCalendar xmlFecha = DateUtils.convertDateToXMLGregorianCalendar(fecha);
        
        //DTUsuario user = new DTArtista o cliente
        DtUsuario usuario = new DtUsuario();
        if(tipoUsuario.equals("Cliente")){
            usuario = new DtCliente();//(nickname, nombre, apellido, correo, fecha, contrasenia, contraseniaC, imagen);
            usuario.setNombre(nombre);
            usuario.setNickname(nickname);
            usuario.setApellido(apellido);
            usuario.setCorreo(web);
            usuario.setFechaNac(xmlFecha);
            usuario.setContrasenia(contrasenia);
            usuario.setConfirmacion(contraseniaC);
            usuario.setImagen(imagen);
            
        }else{
            usuario = new DtArtista();//(nickname, nombre, apellido, contrasenia, contraseniaC, imagen, fecha, correo, biografia, web);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setNickname(nickname);
            usuario.setCorreo(web);
            usuario.setContrasenia(contrasenia);
            usuario.setConfirmacion(contraseniaC);
            usuario.setImagen(imagen);
            usuario.setFechaNac(xmlFecha);
            
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
