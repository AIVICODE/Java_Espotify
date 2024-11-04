package Servlets;

import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvObtenerListasParticulares", urlPatterns = {"/SvObtenerListaRepCliente"})
public class SvObtenerListaRepCliente extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica principal aquí
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 HttpSession session = request.getSession(false);
    DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");

    if (dtUsuario != null && !dtUsuario.getNickname().isEmpty()) {
        try {
            // Obtener las listas particulares del cliente
            List<DTListaRep> listasParticulares = control.obtenerDTListaPorCliente(dtUsuario.getCorreo());

            // Convertir la lista a JSON
            Gson gson = new Gson();
            String listasJson = gson.toJson(listasParticulares);

            // Configurar la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(listasJson);

        } catch (Exception e) {
            Logger.getLogger(SvObtenerListaRepCliente.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener listas particulares: " + e.getMessage());
        }
    } else {
        // Si no hay usuario en sesión, devolver error
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre del cliente no proporcionado.");
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet que obtiene las listas particulares de un cliente específico";
    }
}