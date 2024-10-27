/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import com.google.gson.Gson;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camil
 */
@WebServlet(name = "SvObtenerTodasListasRep", urlPatterns = {"/SvObtenerTodasListasRep"})
public class SvObtenerTodasListasRep extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");

        try {
            List<DTListaRep> listaListasRep = control.obtenerDTListaPorCliente(dtUsuario.getCorreo());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            out.print(gson.toJson(listaListasRep)); // Convertir a JSON
            out.flush();
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las listas de reproducción");
        }
}
}
