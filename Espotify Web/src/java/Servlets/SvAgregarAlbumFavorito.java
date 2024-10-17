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
HttpSession session = request.getSession(false); // Esto devuelve null si no hay sesión.
            DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
        String albumName = request.getParameter("album"); // Obtiene el valor del parámetro 'album'
        String artistName = request.getParameter("artista"); // Obtiene el valor del parámetro 'album'
System.out.println("Nombre del álbum recibido: " + albumName); // Para depurar

System.out.println("Nombre del artista recibido: " + artistName); // Para depurar
        try {
            control.GuardarAlbumFavorito(dtUsuario.getCorreo(), artistName, albumName);
        } catch (Exception ex) {
            Logger.getLogger(SvAgregarAlbumFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }

        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
