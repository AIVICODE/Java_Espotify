/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import webservices.DtAlbum;
import webservices.DtCliente;
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


@WebServlet(name = "SvObtenerTemaDeUnAlbum", urlPatterns = {"/SvObtenerTemaDeUnAlbum"})
public class SvObtenerTemaDeUnAlbum extends HttpServlet {
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
        
        
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
        // Obtener parámetros
        String nombreAlbum = request.getParameter("nombreAlbum");
        String nombreArtista = request.getParameter("nombreArtista");

        // Buscar el álbum usando los parámetros
        String correoartista= control.convierteNickACorreo(nombreArtista);//control.ConvierteNick_A_Correo(nombreArtista);
        DtAlbum album = control.findAlbumxNombreDT(nombreAlbum, correoartista);

        // Generar el HTML de las opciones del ComboBox
        for (DtTema tema : album.getListaTemas()) {
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
        
        String nombreLista = request.getParameter("nombreLista");
        String nombreAlbum = request.getParameter("nombreAlbum");
        String nombreArtista = request.getParameter("nombreArtista");
        String nombreTema = request.getParameter("nombreTema");
        
        // Llamar al método del controlador con los valores
        control.agregarTemaDeAlbumALista(dtcliente.getNickname(), nombreLista, nombreAlbum, nombreArtista, nombreTema);//control.AgregarTema_De_Album_A_Lista(dtcliente.getNickname(),nombreLista, nombreAlbum, nombreArtista, nombreTema);
        
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
