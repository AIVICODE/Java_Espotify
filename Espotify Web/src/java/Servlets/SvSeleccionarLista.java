package Servlets;

import Datatypes.DTListaRep;
import Datatypes.DTTema;
import Logica.Fabrica;
import Logica.IControlador;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvSeleccionarLista", urlPatterns = {"/SvSeleccionarLista"})
public class SvSeleccionarLista extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            String nombreLista = request.getParameter("nombreLista");
            System.out.println("aca lista: " + nombreLista);
            String nombreCliente = request.getParameter("nombreCliente"); // Puede ser null para listas generales
            
            // Lógica para obtener la lista de reproducción por nombre y cliente
            DTListaRep listaSeleccionada = null;
            
            // Comprobar si el nombreCliente es nulo para distinguir entre listas generales y particulares
            if (nombreCliente == null || nombreCliente.isEmpty()) {
                try {
                    
                    // Buscar lista de reproducción por nombre (listas generales)
                    listaSeleccionada = control.obtenerDatosDeLista_Por_Defecto(nombreLista);
                } catch (Exception ex) {
                    Logger.getLogger(SvSeleccionarLista.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String correo= control.ConvierteNick_A_Correo(nombreCliente);
                // Buscar lista de reproducción por nombre y cliente (listas particulares)
                listaSeleccionada = control.obtenerDatosDeLista_Por_Cliente(correo, nombreLista);
            }
            
            if (listaSeleccionada != null) {
                // Obtener los temas de la lista seleccionada
                List<DTTema> temas = listaSeleccionada.getTemas();

                request.setAttribute("listaSeleccionada", listaSeleccionada);
                request.setAttribute("temas", temas);
                // Redirigir a la página de detalles de la lista
                request.getRequestDispatcher("ConsultaListaRep.jsp").forward(request, response);
            } else {
                // Manejar el caso donde no se encuentra la lista
                request.setAttribute("error", "Lista no encontrada.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(SvSeleccionarLista.class.getName()).log(Level.SEVERE, null, ex);
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
