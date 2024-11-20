package Servlets;


import webservices.DtContenido;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.Exception_Exception;
import webservices.ListaDTContenido;


@WebServlet(name = "SvObtenerBusqueda", urlPatterns = {"/SvObtenerBusqueda"})
public class SvObtenerBusqueda extends HttpServlet {
    //Fabrica fabrica = Fabrica.getInstance();
    //private IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String filtro = request.getParameter("filtro");
        String sortBy = request.getParameter("sortBy");

        try {
            ListaDTContenido favLis = control.buscador(filtro, sortBy);
            List<DtContenido> favoritosList = favLis.getLista();//control.Buscador(filtro, sortBy);
            // Coloca la lista de favoritos como atributo en la solicitud
            request.setAttribute("dtContenidoList", favoritosList);

            // Redirige a la JSP para mostrar los resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Buscador.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | Exception_Exception e) {
            // En caso de error, redirige a una página de error
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
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
