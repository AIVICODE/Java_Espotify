package Servlets;

import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
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

import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "SvVerificarSubscripcion", urlPatterns = {"/SvVerificarSubscripcion"})
public class SvVerificarSubscripcion extends HttpServlet {

Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               HttpSession session = request.getSession();
        DTCliente dtcliente = (DTCliente) session.getAttribute("usuario");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
if (dtcliente == null) {
    // Usuario no autenticado
    String jsonResponse = "{\"hasSubscription\": false, \"error\": \"Usuario no autenticado\"}";
    out.write(jsonResponse);
    out.flush();
    return;
}
        // Verificar si el usuario tiene una suscripción activa
        boolean hasSubscription = control.verificarSubscripcion(dtcliente.getNickname());

        // Crear la respuesta en formato JSON
        String jsonResponse = "{\"hasSubscription\": " + hasSubscription + "}";
        out.write(jsonResponse);
        out.flush();
        
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
