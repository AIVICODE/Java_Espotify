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
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ivan
 */
@WebServlet(name = "SvAgregarAlbumFavorito", urlPatterns = {"/SvAgregarAlbumFavorito"})
public class SvAgregarAlbumFavorito extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    private IControlador control = fabrica.getIControlador();
    
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
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("usuario") == null) {
            throw new Exception("Usuario no autenticado.");
        }

        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        String albumName = request.getParameter("album");
        String artistName = request.getParameter("artista");

        System.out.println("Nombre del álbum recibido: " + albumName);
        System.out.println("Nombre del artista recibido: " + artistName);

        control.GuardarAlbumFavorito(dtUsuario.getCorreo(), artistName, albumName);

        // Enviamos una respuesta de éxito en formato JSON
        response.getWriter().write("{\"success\": true, \"message\": \"Álbum agregado a favoritos exitosamente.\"}");
    } catch (Exception ex) {
        // Si ocurre un error, enviamos una respuesta de error
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Código de error 400
        response.getWriter().write("{\"success\": false, \"message\": \"Error al agregar el álbum a favoritos: " + ex.getMessage() + "\"}");
    }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
