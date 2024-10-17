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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */
@WebServlet(name = "SvAgregarListaRep", urlPatterns = {"/SvAgregarListaRep"})
public class SvAgregarListaRep extends HttpServlet {
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
    try {
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        
        String listName = request.getParameter("lista");
        String imagendir = request.getParameter("imagen");

        System.out.println("Nombre del álbum recibido: " + listName);
        System.out.println("Nombre del artista recibido: " + imagendir);
            
        control.CrearListaRepParticular(listName, dtUsuario.getCorreo(), imagendir, true);
        response.getWriter().write("{\"success\": true, \"message\": \"Lista creada exitosamente.\"}");

    } catch (Exception e) {
       response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Código de error 400
        response.getWriter().write("{\"success\": false, \"message\": \"Error al agregar al crear la lista: " + e.getMessage() + "\"}");
    }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
