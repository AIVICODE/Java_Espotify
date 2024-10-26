/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Datatypes.DTAlbum;
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

import com.google.gson.Gson;
import java.util.List;




/**
 *
 * @author ivan
 */
@WebServlet(name = "SvObtenerGeneros", urlPatterns = {"/SvObtenerGeneros"})
public class SvObtenerGeneros extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> nombreGeneros = control.MostrarNombreGeneros();
        
        // Debug: imprimir la lista en la consola
        System.out.println("Géneros obtenidos: " + nombreGeneros);

        // Convertir la lista a formato JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(nombreGeneros);

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
