/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Datatypes.DTAlbum;
import Datatypes.DTTema;
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
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author ivan
 */
@WebServlet(name = "SvObtenerTemas", urlPatterns = {"/SvObtenerTemas"})
public class SvObtenerTemas extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 try {
        // Obtiene los parámetros de la solicitud
        String album = request.getParameter("album");
        String artista = request.getParameter("artista");

        // Verifica que los parámetros no sean nulos
        if (album == null || artista == null) {
            request.setAttribute("error", "Los parámetros 'album' y 'artista' son requeridos.");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
            return;
        }

        // Busca el álbum utilizando la lógica de negocio
        String correo= control.ConvierteNick_A_Correo(artista);
        DTAlbum dtalbum = control.findAlbumxNombreDT(album, correo);

        if (dtalbum != null) {
            List<DTTema> temas = dtalbum.getListaTemas();

            if (temas != null && !temas.isEmpty()) {
                // Pasa los datos del álbum y los temas a la página JSP
                request.setAttribute("album", album);
                request.setAttribute("artista", artista);
                request.setAttribute("temas", temas);

                // Redirige a temas.jsp
                RequestDispatcher rd = request.getRequestDispatcher("temas.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "No se encontraron temas para el álbum seleccionado.");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error", "No se encontró el álbum seleccionado.");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }

    } catch (Exception ex) {
        request.setAttribute("error", "Error al procesar la solicitud: " + ex.getMessage());
        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
        rd.forward(request, response);
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
