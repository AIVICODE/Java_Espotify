/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servlets;

import webservices.DtAlbum;
import webservices.DtCliente;
import webservices.DtListaRep;
import webservices.DtTema;
//import Logica.Fabrica;
//import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;

/**
 *
 * @author camil
 */

@WebServlet(name = "SvObtenerTemaDeUnaListaP", urlPatterns = {"/SvObtenerTemaDeUnaListaP"})
public class SvObtenerTemaDeUnaListaP extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
        // Obtener parámetros
        String nombreLista = request.getParameter("nombreLista");
        String nombreCliente = request.getParameter("nombreCliente");

        // Buscar el álbum usando los parámetros
        String correocliente= control.convierteNickACorreo(nombreCliente);//control.ConvierteNick_A_Correo(nombreCliente);
        DtListaRep ListaP = control.obtenerDatosDeListaPorCliente(correocliente, nombreLista);//control.obtenerDatosDeLista_Por_Cliente(correocliente, nombreLista);

        // Generar el HTML de las opciones del ComboBox
        for (DtTema tema : ListaP.getTemas()) {
            out.println("<option value=\"" + tema.getNombre() + "\">" + tema.getNombre() + "</option>");
        }
    } catch (Exception ex) {
        Logger.getLogger(SvObtenerTemaDeUnAlbum.class.getName()).log(Level.SEVERE, null, ex);
        out.println("<option>Error al cargar temas</option>"); // Manejo básico de errores
    }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    try {
        HttpSession session = request.getSession();
        DtCliente dtcliente = (DtCliente) session.getAttribute("usuario");
        
        //A donde voy a agregar el tema
        String nombreListaA_Agregar = request.getParameter("nombreLista");
        //De donde saco el tema
        String nombreListaA_Sacar = request.getParameter("nombreListaParticular");
        //Cliente de la lista de donde saco el tema
        String nombreClienteA_Sacar = request.getParameter("nombreCliente_LP");
        
        String nombreTema = request.getParameter("nombreTema_LP");
     
       System.out.println("antes de agregar el tema:");
        System.out.println("Cliente:" + dtcliente.getNickname());
        System.out.println("Lista a Agregar:" + nombreListaA_Agregar);
        System.out.println("Lista a Sacar:" + nombreListaA_Sacar);
         System.out.println("Cliente a sacar:" + nombreClienteA_Sacar);
         System.out.println("Nombre tema:" + nombreTema);
        
        
        // Llamar al método del controlador con los valores
        control.agregarTemaDeListaPartALista(dtcliente.getNickname(),nombreListaA_Agregar ,nombreListaA_Sacar,nombreClienteA_Sacar, nombreTema);
        //control.AgregarTema_De_ListaPart_A_Lista(dtcliente.getNickname(),nombreListaA_Agregar ,nombreListaA_Sacar,nombreClienteA_Sacar, nombreTema);
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Datos recibidos y procesados correctamente");
    } catch (Exception ex) {
        Logger.getLogger(SvObtenerTemaDeUnAlbum.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

  
}


