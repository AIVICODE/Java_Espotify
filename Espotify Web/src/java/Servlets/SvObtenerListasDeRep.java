package Servlets;

import webservices.DtAlbum;
import webservices.DtListaRep;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTListaRep;
import webservices.ListaString;


@WebServlet(name = "SvObtenerListasDeRep", urlPatterns = {"/SvObtenerListasDeRep"})
public class SvObtenerListasDeRep extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 
 
 
    String genero = request.getParameter("genero");
    System.out.println("Género recibido: " + genero);
    
    if (genero != null && !genero.isEmpty()) {
        try {
            ListaDTListaRep lisRe = control.obtenerDTListaPorGenero(genero);
            List<DtListaRep> listasRep = lisRe.getLista();//control.obtenerDTListaPorGenero(genero);
            request.setAttribute("listasdeRep", listasRep);
            
         
            
            // Obtener nuevamente la lista de géneros para mantener el contexto en la JSP
            ListaString nomGe = control.mostrarNombreGeneros();
            List<String> nombreGeneros = nomGe.getLista();//control.MostrarNombreGeneros();
            request.setAttribute("nombreGeneros", nombreGeneros);
            
            // Redirigir a la página JSP para mostrar las listas de reproducción
            RequestDispatcher dispatcher = request.getRequestDispatcher("ConsultaListaRep.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            // Manejo de errores al obtener las listas de reproducción
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener listas de reproducción: " + e.getMessage());
        }
    } else {
        // Si no hay género seleccionado, redirigir para obtener la lista de géneros
        doGet(request, response);
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