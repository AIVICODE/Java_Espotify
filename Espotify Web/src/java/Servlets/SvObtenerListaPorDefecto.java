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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtUsuario;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import java.util.ArrayList;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaDTListaRep;

/**
 *
 * @author camil
 */
@WebServlet(name = "SvObtenerListaPorDefecto", urlPatterns = {"/SvObtenerListaPorDefecto"})
public class SvObtenerListaPorDefecto extends HttpServlet {

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

            try {
                ListaDTListaRep lisRepPorDef = control.listasPorDefecto();
                List<DtListaRep> listasPorDefecto = lisRepPorDef.getLista();//control.ListasPorDefecto(); // Obtener todas las listas de reproducción por defecto
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                // Generar opciones HTML para el ComboBox, identificando por nombre de lista y nombre de cliente
                for (DtListaRep listaRep : listasPorDefecto) {
                    out.println("<option value='" + listaRep.getNombreListaRep() + " - " + listaRep.getGenero() + "'>"                                        
                            + listaRep.getNombreListaRep() + " - " + listaRep.getGenero() + "</option>");
                }
            } catch (Exception ex) {
                Logger.getLogger(SvObtenerTodasListasRep.class.getName()).log(Level.SEVERE, null, ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las listas de reproducción por defecto");
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
        }

}