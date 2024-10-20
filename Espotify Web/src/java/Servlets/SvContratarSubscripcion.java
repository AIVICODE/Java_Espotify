package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
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
import java.io.File;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ivan
 */
@WebServlet(name = "SvContratarSubscripcion", urlPatterns = {"/SvContratarSubscripcion"})
public class SvContratarSubscripcion extends HttpServlet {

Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
   
 processRequest(request, response);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session = request.getSession();
            DTCliente dtcliente = (DTCliente) session.getAttribute("usuario");

            if (dtcliente == null) {
                out.println("{\"status\":\"error\",\"message\":\"No hay usuario en sesión\"}");
                return; // No continuar si no hay usuario
            }

            String subscription = request.getParameter("subscriptionType");
            System.out.println("Valor de sub: " + subscription);

            control.crearSubscripcion(dtcliente.getNickname(), subscription);

            out.println("{\"status\":\"success\",\"message\":\"Suscripción creada exitosamente\"}");
        } catch (Exception ex) {
            Logger.getLogger(SvContratarSubscripcion.class.getName()).log(Level.SEVERE, null, ex);
            out.println("{\"status\":\"error\",\"message\":\"Error al crear la suscripción\"}");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
