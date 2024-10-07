/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import Datatypes.DTAlbum;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvObtenerAlbumxGenero", urlPatterns = {"/SvObtenerAlbumxGenero"})
public class SvObtenerAlbumxGenero extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 String genero = request.getParameter("genero");
    System.out.println("Valor de genero recibido: " + genero);
    
    if (genero != null && !genero.isEmpty()) {
        try {
            // Llamar al método del controlador para obtener la lista de DTAlbum
            List<DTAlbum> nombresAlbumes = control.findDTAlbum(genero);
            System.out.println("Número de álbumes encontrados: " + nombresAlbumes.size());
            
            // Configurar la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            // Convertir la lista de DTAlbum a JSON
            Gson gson = new Gson();
            String jsonNombresAlbumes = gson.toJson(nombresAlbumes);
            
            // Escribir el JSON en la respuesta
            response.getWriter().write(jsonNombresAlbumes);
        } catch (Exception e) {
            // Manejo de errores al obtener los álbumes
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los álbumes: " + e.getMessage());
        }
    } else {
        // Si no se proporcionó el parámetro de género, enviar un error
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'genero' es requerido.");
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
