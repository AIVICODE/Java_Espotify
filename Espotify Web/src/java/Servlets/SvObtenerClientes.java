package Servlets;

import webservices.DtListaRep;
//import Logica.Fabrica;
//import Logica.IControlador;
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
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTListaRep;
import webservices.ListaString;
@WebServlet(name = "SvObtenerClientes", urlPatterns = {"/SvObtenerClientes"})
public class SvObtenerClientes extends HttpServlet {
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
        
            // Obtener los nicks de los clientes
            ListaString lnicks = control.nicksClientes();
            List<String> nicks = lnicks.getLista();
            List<DtListaRep> todasListasRepparticulares = new ArrayList<>();

            // Recorrer cada nick para obtener el correo y luego las listas de reproducción
            for (String nick : nicks) {
                try {
                    // Convertir el nick a correo
                    String correo = control.convierteNickACorreo(nick);//control.ConvierteNick_A_Correo(nick);
                    ListaDTListaRep l = control.obtenerDTListaPorClientepublica(correo);
                    List<DtListaRep> list = l.getLista(); //control.obtenerDTListaPorClientepublica(correo);
                    todasListasRepparticulares.addAll(list);
                } catch (Exception ex) {
                    Logger.getLogger(SvObtenerListasDeRep.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Establecer la lista total de listas de reproducción en el request
            request.setAttribute("listasdeRepparticular", todasListasRepparticulares);
        
        
        System.out.println("Entra-------------------------------");
            // Obtener la lista de clientes desde el controlador
            ListaString clie = control.nicksClientes();
            List<String> clientes = clie.getLista();//control.nicksClientes();
    


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