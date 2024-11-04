/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Datatypes.DTUsuario;
import com.google.gson.Gson;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author camil
 */
@WebServlet(name = "SvObtenerAlbumTodos", urlPatterns = {"/SvObtenerAlbumTodos"})
public class SvObtenerAlbumTodos extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 try {
        List<DTAlbum> albumes = control.findDTAlbumTodos(); // Obtener todos los álbumes
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Generar opciones HTML para el ComboBox de álbumes, identificando por nombre de álbum y nombre de artista
       for (DTAlbum album : albumes) {
    out.println("<option value='" + album.getNombre() + " - " + album.getArtista().getNickname() + "'>" + album.getNombre() + " - " + album.getArtista().getNickname() + "</option>");
}
    } catch (Exception ex) {
        Logger.getLogger(SvObtenerAlbumTodos.class.getName()).log(Level.SEVERE, null, ex);
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
