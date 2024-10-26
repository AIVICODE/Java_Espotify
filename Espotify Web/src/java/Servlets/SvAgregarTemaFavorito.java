package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */

@WebServlet(name = "SvAgregarTemaFavorito", urlPatterns = {"/SvAgregarTemaFavorito"})
public class SvAgregarTemaFavorito extends HttpServlet {
Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();

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

    try {
        // Código para agregar el tema favorito
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");

        String albumName = request.getParameter("album");
        String artistName = request.getParameter("artista");
        String temaName = request.getParameter("tema");

        String correoArtist = control.ConvierteNick_A_Correo(artistName);
        control.GuardarTemaFavorito(dtUsuario.getCorreo(), correoArtist, albumName, temaName);

        // Si todo va bien, enviamos respuesta de éxito
        response.getWriter().write("{\"success\": true, \"message\": \"Tema agregado a favoritos exitosamente.\"}");
    } catch (Exception ex) {
        // Si hay un error, enviamos respuesta de error
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Código de error 400
        response.getWriter().write("{\"success\": false, \"message\": \"Error al agregar el tema a favoritos: " + ex.getMessage() + "\"}");
    }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
