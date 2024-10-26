package Servlets;


import Datatypes.DTContenido;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "SvObtenerBusqueda", urlPatterns = {"/SvObtenerBusqueda"})
public class SvObtenerBusqueda extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String filtro = request.getParameter("filtro");
        String sortBy = request.getParameter("sortBy");

        try {
            List<DTContenido> favoritosList = control.Buscador(filtro, sortBy);
            // Coloca la lista de favoritos como atributo en la solicitud
            request.setAttribute("dtContenidoList", favoritosList);

            // Redirige a la JSP para mostrar los resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Buscador.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
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
