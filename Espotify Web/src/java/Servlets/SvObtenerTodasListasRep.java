package Servlets;

import webservices.DtAlbum;
import webservices.DtCliente;
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
import webservices.DtUsuario;
import com.google.gson.Gson;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTListaRep;


@WebServlet(name = "SvObtenerTodasListasRep", urlPatterns = {"/SvObtenerTodasListasRep"})
public class SvObtenerTodasListasRep extends HttpServlet {
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
    
        HttpSession session = request.getSession(false);
        DtCliente dtUsuario = (DtCliente) session.getAttribute("usuario");

        try {
            ListaDTListaRep lisLisRep = control.obtenerDTListaPorCliente(dtUsuario.getCorreo());
            List<DtListaRep> listaListasRep = lisLisRep.getLista();//control.obtenerDTListaPorCliente(dtUsuario.getCorreo());
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