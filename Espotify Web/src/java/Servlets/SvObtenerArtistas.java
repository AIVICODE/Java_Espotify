/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import java.util.List;


@WebServlet(name = "SvObtenerArtistas", urlPatterns = {"/SvObtenerArtistas"})
public class SvObtenerArtistas extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                List<String> nombreArtistas = control.nicknamesDeTodosLosArtistas();
      
        // Debug: imprimir la lista en la consola
        System.out.println("Géneros obtenidos: " + nombreArtistas);

        // Convertir la lista a formato JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(nombreArtistas);

        // Establecer el tipo de contenido como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Enviar la respuesta JSON
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
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
