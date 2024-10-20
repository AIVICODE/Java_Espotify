package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Datatypes.DTSub;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */
@WebServlet(name = "SvActualizarSubscripcion", urlPatterns = {"/SvActualizarSubscripcion"})
public class SvActualizarSubscripcion extends HttpServlet {
Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        
        // Obtener la lista de suscripciones del cliente
        List<DTSub> subscripciones = control.listarSubdeCliente(dtUsuario.getNickname());
        
        if (subscripciones != null && !subscripciones.isEmpty()) {
            // Si hay suscripciones, mostrar los IDs en consola o log
            for (DTSub sub : subscripciones) {
                System.out.println("Subscripción encontrada: ID = " + sub.getId());
            }
        } else {
            // Si no hay suscripciones, mostrar mensaje en consola o log
            System.out.println("No se encontraron suscripciones para el usuario: " + dtUsuario.getNickname());
        }
        
        // Pasar la lista de suscripciones como atributo a tu JSP
        request.setAttribute("subscripciones", subscripciones);
        
        // Redirigir al JSP que muestra las suscripciones
        RequestDispatcher dispatcher = request.getRequestDispatcher("ActualizarSubscripcion.jsp"); // Cambia esto por el nombre de tu JSP
        dispatcher.forward(request, response);
        
    } catch (Exception ex) {
        Logger.getLogger(SvActualizarSubscripcion.class.getName()).log(Level.SEVERE, null, ex);
    }
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
