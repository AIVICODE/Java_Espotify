package Servlets;

import Datatypes.DTListaRep;
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
import java.util.logging.Level;
import java.util.logging.Logger;
@WebServlet(name = "SvObtenerClientes", urlPatterns = {"/SvObtenerClientes"})
public class SvObtenerClientes extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            // Obtener los nicks de los clientes
            List<String> nicks = control.nicksClientes();
            List<DTListaRep> todasListasRepparticulares = new ArrayList<>();

            // Recorrer cada nick para obtener el correo y luego las listas de reproducción
            for (String nick : nicks) {
                try {
                    // Convertir el nick a correo
                    String correo = control.ConvierteNick_A_Correo(nick);
                    List<DTListaRep> list = control.obtenerDTListaPorClientepublica(correo);
                    todasListasRepparticulares.addAll(list);
                } catch (Exception ex) {
                    Logger.getLogger(SvObtenerListasDeRep.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Establecer la lista total de listas de reproducción en el request
            request.setAttribute("listasdeRepparticular", todasListasRepparticulares);
        
        
        System.out.println("Entra-------------------------------");
            // Obtener la lista de clientes desde el controlador
    List<String> clientes = control.nicksClientes();
    


    // Verificar que la lista no esté vacía o nula
    if (clientes != null && !clientes.isEmpty()) {
        // Agregar la lista de clientes como atributo en la solicitud (request)
        request.setAttribute("listaClientes", clientes);

        // Redirigir al JSP donde se mostrará la lista
        RequestDispatcher dispatcher = request.getRequestDispatcher("ConsultaListaRep.jsp");
        dispatcher.forward(request, response);
    } else {
        // En caso de que no haya clientes, puedes redirigir a una página de error o mostrar un mensaje
        response.sendError(HttpServletResponse.SC_NO_CONTENT, "No se encontraron clientes.");
    }
        
        processRequest(request, response);
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
