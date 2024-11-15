
package Servlets;

import webservices.DtTema;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SvActualizarConteo", urlPatterns = {"/SvActualizarConteo"})
public class SvActualizarConteo extends HttpServlet {
//Fabrica fabrica = Fabrica.getInstance();
//IControlador control = fabrica.getIControlador();

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
        response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    try {


        // Obtener datos de los parámetros de solicitud
        String temaName = request.getParameter("temaName");
        String albumName = request.getParameter("albumName");
        String artistName = request.getParameter("artistName");
        String tipo = request.getParameter("tipo"); // "reproduccion" o "descarga"

        System.out.println("Nombre del tema: " + temaName);
        System.out.println("Nombre del álbum: " + albumName);
        System.out.println("Nombre del artista: " + artistName);
        System.out.println("Tipo de acción: " + tipo);

        // Crear DTTema y actualizar conteo en la lógica de negocio
        DtTema ntema=new DtTema();
        ntema.setNombre(temaName);
        ntema.setNombrealbum(albumName);
        ntema.setNombreartista(artistName);
        //DtTema tema = new DtTema(temaName, albumName, artistName);
        
        if ("reproduccion".equalsIgnoreCase(tipo)) {
           // control.AgregaReproduccionTema(ntema);
           control.agregaReproduccionTema(ntema);
        } else if ("descarga".equalsIgnoreCase(tipo)) {
           control.agregaDescargaTema(ntema);
        } else {
            throw new Exception("Tipo de acción no válido.");
        }

        // Respuesta JSON de éxito
        response.getWriter().write("{\"success\": true, \"message\": \"Conteo de " + tipo + " actualizado exitosamente.\"}");

    } catch (Exception ex) {
        // Respuesta JSON de error
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Código de error 400
        response.getWriter().write("{\"success\": false, \"message\": \"Error al actualizar el conteo: " + ex.getMessage() + "\"}");
    }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
