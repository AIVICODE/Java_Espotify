/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Datatypes.DTInfoTema;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvObtenerInfoTema", urlPatterns = {"/SvObtenerInfoTema"})
public class SvObtenerInfoTema extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String nombreTema = request.getParameter("nombreTema");
        String nombreArtista = request.getParameter("nombreArtista");
        String nombreAlbum = request.getParameter("nombreAlbum");
System.out.println("ENTRA AL SERVLET");
        try {
            // Obtener la información del tema
            DTInfoTema infoTema = control.ObtenerInfoTema(nombreTema, nombreArtista, nombreAlbum);

            // Construir la respuesta JSON manualmente
            String jsonResponse = "{"
                    + "\"cantidadDescargas\": " + infoTema.getCantidadDescargas() + ","
                    + "\"cantidadReproducciones\": " + infoTema.getCantidadReproducciones() + ","
                    + "\"cantidadFavoritos\": " + infoTema.getCantidadfavoritos()+ ","
                    + "\"cantidadListas\": " + infoTema.getCantidadlistas()
                    + "}";

            // Establecer el tipo de contenido como JSON
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
System.out.println("SALE DEL SERVLET");
        } catch (Exception ex) {
            System.out.println("ERROR");
Logger.getLogger(SvObtenerInfoTema.class.getName()).log(Level.SEVERE, null, ex);
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    response.setContentType("application/json");
    response.getWriter().write("{\"error\": \"Ha ocurrido un error al procesar la solicitud.\"}");        }
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
