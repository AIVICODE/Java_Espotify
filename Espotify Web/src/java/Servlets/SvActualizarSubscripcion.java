package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Datatypes.DTSub;
import Logica.Fabrica;
import Logica.IControlador;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Datatypes.DTUsuario;
import javax.servlet.http.HttpSession;
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
        
        try {
            // Obtener parámetros del formulario
            Long id = Long.valueOf(request.getParameter("id"));
            String nuevoEstado = request.getParameter("estado");

            // Llamar al método para modificar el estado de la suscripción
            try {
                control.ClienteModificaEstadoSuscripcion(id, nuevoEstado);
                request.setAttribute("mensajeExito", "Suscripción actualizada correctamente.");
            } catch (Exception e) {
                request.setAttribute("mensajeError", e.getMessage());
            }

            // Volver a cargar las suscripciones para mostrar después de la actualización
            doGet(request, response);

        } catch (NumberFormatException e) {
            Logger.getLogger(SvActualizarSubscripcion.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("mensajeError", "ID de suscripción inválido.");
            doGet(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
