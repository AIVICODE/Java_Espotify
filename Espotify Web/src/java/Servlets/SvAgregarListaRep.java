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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.annotation.MultipartConfig;
/**
 *
 * @author ivan
 */
@MultipartConfig
@WebServlet(name = "SvAgregarListaRep", urlPatterns = {"/SvAgregarListaRep"})
public class SvAgregarListaRep extends HttpServlet {
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
  try {
        HttpSession session = request.getSession(false);
        DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");

        // Obtener el nombre de la lista desde el formulario
        String listName = request.getParameter("nombreLista");

        // Obtener la parte del archivo (si fue subido)
        Part filePart = request.getPart("imagenLista"); // Aquí debe coincidir con el "name" del campo del archivo en el formulario
        String fileName = null;

        // Si hay un archivo cargado
        if (filePart != null && filePart.getSize() > 0) {
            // Obtener el nombre del archivo
            fileName = filePart.getSubmittedFileName();

            // Ruta donde se guardará el archivo (en la carpeta 'images')
            String imagesDir = getServletContext().getRealPath("/images");
            File imageDirFile = new File(imagesDir);

            // Crear la carpeta si no existe
            if (!imageDirFile.exists()) {
                imageDirFile.mkdirs(); // Crea los directorios necesarios
            }

            // Ruta completa del archivo a guardar
            String path = imagesDir + File.separator + fileName;

            // Guardar el archivo en la carpeta 'images'
            filePart.write(path);
        }

        // Llamar al método de negocio para crear la lista de reproducción
        String imagendir = (fileName != null) ? "images/" + fileName : null; // Si no hay imagen, imagendir será null

        System.out.println("Nombre de la lista: " + listName);
        System.out.println("Nombre de la url: " + imagendir);
        control.CrearListaRepParticular(listName, dtUsuario.getCorreo(), imagendir, true);

        // Responder con éxito en formato JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"success\": true, \"message\": \"Lista creada exitosamente.\"}");

    } catch (Exception e) {
        // Manejar errores con código de estado 400 y respuesta en formato JSON
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        String errorMessage = e.getMessage().replace("\"", "\\\""); // Escapar comillas dobles
        response.getWriter().write("{\"success\": false, \"message\": \"Error al crear la lista: " + errorMessage + "\"}");
    }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
