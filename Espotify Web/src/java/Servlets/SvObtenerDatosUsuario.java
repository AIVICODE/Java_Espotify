package Servlets;
import webservices.DtArtista;
import webservices.DtCliente;
import webservices.DtUsuario;
//import Logica.Fabrica;
//import Logica.IControlador;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
//import Logica.Fabrica;
//import Logica.IControlador;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import webservices.ControladorSoap;
import webservices.ControladorSoapService;
import webservices.ListaString;


@WebServlet(name = "SvObtenerDatosUsuario", urlPatterns = {"/SvObtenerDatosUsuario"})
public class SvObtenerDatosUsuario extends HttpServlet {
//Fabrica fabrica = Fabrica.getInstance();
//IControlador control = fabrica.getIControlador();
    ControladorSoapService service = new ControladorSoapService();
    ControladorSoap control = service.getControladorSoapPort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvObtenerDatosUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvObtenerDatosUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nickname = request.getParameter("nickname");//obtiene el nick seleccionado del jsp
        if (nickname == null || nickname.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Nickname no proporcionado");
            return;
        }
        DtUsuario usuario = null;    
        usuario = control.encontrarClientePorNickname(nickname);//dt cliente
        if (usuario == null){
            usuario = control.encontrarDTArtistaPorNickname(nickname);
        }        
        if (usuario != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            Gson gson = new Gson();
            String jsonUsuario = gson.toJson(usuario);
            
            PrintWriter out = response.getWriter();
            out.print(jsonUsuario);
            out.flush();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Usuario no encontrado");
        }  
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//paso la fecha pa q quede linda, se usa en jsons
        // Lee el cuerpo de la solicitud JSON manualmente
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String jsonBody = sb.toString();
        System.out.println("JSON recibido: " + jsonBody);

        // Usar Gson para convertir el JSON en un objeto Java y extraer el nickname
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonBody, JsonObject.class);
        String nickname = jsonObject.get("nickUsuario").getAsString();
        System.out.println("Nickname recibido en el servlet POST: " + nickname);

        if (nickname == null || nickname.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Nickname no proporcionado o es nulo\"}");
            return;
        }  
        //Operaciones para mandar la info al jsp en forma de json
        DtArtista artista = new DtArtista();
        DtCliente cliente = new DtCliente();
        artista = null;
        cliente = null;
        artista = control.encontrarDTArtistaPorNickname(nickname);
        if(artista == null){//si no encontro al artista busca por clientes
            cliente = control.encontrarClientePorNickname(nickname);//dt cliente
        }
        if (artista != null){//si es artista mandarlo
            DtArtista usuario = artista;
            StringBuilder jsonUsuario = new StringBuilder();
            //Convierto a JSON
            jsonUsuario.append("{");
            jsonUsuario.append("\"nickname\": \"").append(usuario.getNickname()).append("\",");
            
            jsonUsuario.append("\"imagen\": \"").append(usuario.getImagen()).append("\",");
            jsonUsuario.append("\"nombre\": \"").append(usuario.getNombre()).append("\",");
            jsonUsuario.append("\"apellido\": \"").append(usuario.getApellido()).append("\",");
            jsonUsuario.append("\"correo\": \"").append(usuario.getCorreo()).append("\",");
            jsonUsuario.append("\"biografia\": \"").append(usuario.getBiografia()).append("\",");
            jsonUsuario.append("\"sitioWeb\": \"").append(usuario.getSitioWeb()).append("\",");
            jsonUsuario.append("\"esCliente\": \"n\","); // Campo esCliente con valor "n" si no es cliente es artista
            // Convierte la fecha a String y agrega al JSON y Formatea la fecha de nacimiento
            String fechaNacStr = (usuario.getFechaNac() != null) ? dateFormat.format(usuario.getFechaNac()) : "";
            jsonUsuario.append("\"fechaNac\": \"").append(fechaNacStr).append("\"");
            jsonUsuario.append("}");
            
            // Crear el JSON de seguidores
            ListaString segui = control.nicksClientesSiguenArtista(nickname);
            List<String> seguidores = segui.getLista();//control.nicksClientesSiguenArtista(nickname);
            StringBuilder jsonSeguidores = new StringBuilder();
            jsonSeguidores.append("["); // Comienza el array
            for (int i = 0; i < seguidores.size(); i++) {
                jsonSeguidores.append("\"").append(seguidores.get(i)).append("\"");
                if (i < seguidores.size() - 1) {
                    jsonSeguidores.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonSeguidores.append("]"); // Cierra el array
            //Seguidos pero vacio porque son artistas
            StringBuilder jsonSeguidos = new StringBuilder();
            jsonSeguidos.append("[\"n\"]"); // Crea un array con solo "n"
            
            //Mostrar listas de reproduccion del cliente creadas por el
            StringBuilder jsonListasCreadas = new StringBuilder();
            jsonListasCreadas.append("[\"n\"]"); // Crea un array con solo "n"
            
            //Favoritos pero vacio porque son artistas
            StringBuilder jsonFavoritos = new StringBuilder();
            jsonFavoritos.append("[\"n\"]"); // Crea un array con solo "n"
            
            // NUEVO Crear el JSON de Albumes del artista
            List<String> albumesArtista;
            StringBuilder jsonAlbumesArtista = new StringBuilder();
            try {
                ListaString alArt = control.listaAlbumesArtistaNick(nickname);
                albumesArtista = alArt.getLista();//control.listaAlbumesArtistaNick(nickname);
                jsonAlbumesArtista.append("["); // Comienza el array
                for (int i = 0; i < albumesArtista.size(); i++) {
                    jsonAlbumesArtista.append("\"").append(albumesArtista.get(i)).append("\"");
                    if (i < albumesArtista.size() - 1) {
                        jsonAlbumesArtista.append(","); // Agregar coma si no es el último elemento
                    }
                }
            jsonAlbumesArtista.append("]"); // Cierra el array
            System.out.println("Contenido de jsonAlbumesArtista: " + jsonAlbumesArtista.toString());
            } catch (Exception ex) {//VER SI ESTO NO DA ERROR
                Logger.getLogger(SvObtenerDatosUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Listas publicas del cliente vacio porque son artistas
            StringBuilder jsonListasPublicas = new StringBuilder();
            jsonListasPublicas.append("[\"n\"]"); // Crea un array con solo "n"
            
            //albumes fav cliente vacio porque son artistas
            StringBuilder jsonalbumesFavoritos = new StringBuilder();
            jsonalbumesFavoritos.append("[\"n\"]"); // Crea un array con solo "n"
            
            // Combinar ambos JSON en uno solo
            StringBuilder jsonRespuesta = new StringBuilder();
            jsonRespuesta.append("{");
            jsonRespuesta.append("\"usuario\": ").append(jsonUsuario.toString()).append(",");
            jsonRespuesta.append("\"seguidores\": ").append(jsonSeguidores.toString()).append(","); // Agrega el JSON de seguidores
            jsonRespuesta.append("\"listasCreadas\": ").append(jsonListasCreadas.toString()).append(",");
            jsonRespuesta.append("\"favoritos\": ").append(jsonFavoritos.toString()).append(",");
            jsonRespuesta.append("\"albumesArtista\": ").append(jsonAlbumesArtista.toString()).append(",");
            jsonRespuesta.append("\"listasPublicas\": ").append(jsonListasPublicas.toString()).append(",");
            //
            jsonRespuesta.append("\"albumesFavoritos\": ").append(jsonalbumesFavoritos.toString()).append(",");
            //            
            jsonRespuesta.append("\"seguidos\": ").append(jsonSeguidos.toString()); // Agrega el JSON de seguidos
            jsonRespuesta.append("}");

            // Enviar la respuesta combinada
            response.getWriter().print(jsonRespuesta.toString());
            response.getWriter().flush();
        }
        if(cliente != null){//si es null es cliente
            DtCliente usuario = cliente;
            StringBuilder jsonUsuario = new StringBuilder();
            //Convierto a JSON
            jsonUsuario.append("{");
            jsonUsuario.append("\"nickname\": \"").append(usuario.getNickname()).append("\",");
            jsonUsuario.append("\"nombre\": \"").append(usuario.getNombre()).append("\",");
             jsonUsuario.append("\"imagen\": \"").append(usuario.getImagen()).append("\",");
            jsonUsuario.append("\"apellido\": \"").append(usuario.getApellido()).append("\",");
            jsonUsuario.append("\"correo\": \"").append(usuario.getCorreo()).append("\",");
            jsonUsuario.append("\"biografia\": \"\",");
            jsonUsuario.append("\"sitioWeb\": \"\",");
            jsonUsuario.append("\"esCliente\": \"s\","); // Campo esCliente con valor "s" para controles
            // Convierte la fecha a String y agrega al JSON en formato lindo
            String fechaNacStr = (usuario.getFechaNac() != null) ? dateFormat.format(usuario.getFechaNac()) : "";
            jsonUsuario.append("\"fechaNac\": \"").append(fechaNacStr).append("\"");
            jsonUsuario.append("}");
            
            // Crear el JSON de seguidores
            ListaString seg = control.seguidoresDelCliente(nickname);
            List<String> seguidores = seg.getLista();//control.seguidoresDelCliente(nickname);
            StringBuilder jsonSeguidores = new StringBuilder();
            jsonSeguidores.append("["); // Comienza el array
            for (int i = 0; i < seguidores.size(); i++) {
                jsonSeguidores.append("\"").append(seguidores.get(i)).append("\"");
                if (i < seguidores.size() - 1) {
                    jsonSeguidores.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonSeguidores.append("]"); // Cierra el array
            System.out.println("Contenido de jsonSeguidores: " + jsonSeguidores.toString());
            // Crear el JSON de los seguidos de clientes 
            ListaString segd = control.clientesSeguidosDelCliente(nickname);
            List<String> seguidos = segd.getLista();//control.clientesSeguidosDelCliente(nickname);
            StringBuilder jsonSeguidos = new StringBuilder();
            jsonSeguidos.append("[");
            if (!seguidos.isEmpty()) {
                jsonSeguidos.append("\"Clientes:\"");// Agrega el texto "Clientes:" al inicio 
                jsonSeguidos.append(","); // Añade una coma después de "Clientes:" solo si la lista no está vacía
            }
            for (int i = 0; i < seguidos.size(); i++) {
                jsonSeguidos.append("\"").append(seguidos.get(i)).append("\"");
                if (i < seguidos.size() - 1) {
                    jsonSeguidos.append(","); // Agregar coma si no es el último elemento
                }
            }
            ListaString segA = control.artistasSeguidosDelCliente(nickname);
            List<String> seguidosA = segA.getLista();//control.artistasSeguidosDelCliente(nickname);
            if (!seguidosA.isEmpty()) {
                jsonSeguidos.append(",\"Artistas:\"");// Agrega el texto "Artistas:"
                jsonSeguidos.append(","); // Añade una coma después de "Artistas:" solo si la lista no está vacía
            }
            // Agrega la lista de artistas seguidos
            for (int i = 0; i < seguidosA.size(); i++) {
                jsonSeguidos.append("\"").append(seguidosA.get(i)).append("\"");
                if (i < seguidosA.size() - 1) {
                    jsonSeguidos.append(","); // Agregar coma si no es el último elemento de `seguidosA`
                }
            }
            jsonSeguidos.append("]"); // Cierra el array
           
            // Crear el JSON de listasCreadas por cliente
            ListaString lisCre = control.nombresListaRepDeCliente(nickname);
            List<String> listasCreadas = lisCre.getLista();//control.nombresListaRepDeCliente(nickname);
            StringBuilder jsonListasCreadas = new StringBuilder();
            jsonListasCreadas.append("["); // Comienza el array
            for (int i = 0; i < listasCreadas.size(); i++) {
                jsonListasCreadas.append("\"").append(listasCreadas.get(i)).append("\"");
                if (i < listasCreadas.size() - 1) {
                    jsonListasCreadas.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonListasCreadas.append("]"); // Cierra el array
            System.out.println("Contenido de jsonListasCreadas: " + jsonListasCreadas.toString());
            
            // Crear el JSON de favoritos del cliente
            ListaString favo = control.listaFavoritosDeCliente(nickname);
            List<String> favoritos = favo.getLista();//control.listaFavoritosDeCliente(nickname);
            StringBuilder jsonFavoritos = new StringBuilder();
            jsonFavoritos.append("["); // Comienza el array
            for (int i = 0; i < favoritos.size(); i++) {
                jsonFavoritos.append("\"").append(favoritos.get(i)).append("\"");// OJO
                if (i < favoritos.size() - 1) {
                    jsonFavoritos.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonFavoritos.append("]"); // Cierra el array
            System.out.println("Contenido de jsonFavoritos: " + jsonFavoritos.toString());
            
            //albumes del artista pero vacio porque son clientes
            StringBuilder jsonAlbumesArtista = new StringBuilder();
            jsonAlbumesArtista.append("[\"n\"]"); // Crea un array con solo "n"
            
            // NUEVO Crear el JSON de listas publicas del cliente
            String c = usuario.getCorreo();//agarra correo de cliente
            
            ListaString lisPub = control.listasPublicasDeCliente(c);
            List<String> listasPublicas = lisPub.getLista();//control.listasPublicasDeCliente(c); //RECIBE CORREO 
            StringBuilder jsonListasPublicas = new StringBuilder();
            jsonListasPublicas.append("["); // Comienza el array
            for (int i = 0; i < listasPublicas.size(); i++) {
                jsonListasPublicas.append("\"").append(listasPublicas.get(i)).append("\"");
                if (i < listasPublicas.size() - 1) {
                    jsonListasPublicas.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonListasPublicas.append("]"); // Cierra el array
            System.out.println("Contenido de jsonListasPublicas: " + jsonListasPublicas.toString());

            //NUEVO ALBUMES MOSTRAR
            
            ListaString albFav = control.nombreAlbumyNombreArtistaFavoritosCliente(nickname);
            List<String> albumesFavoritos = albFav.getLista();//control.nombreAlbumyNombreArtistaFavoritosCliente(nickname);
            StringBuilder jsonalbumesFavoritos = new StringBuilder();
            jsonalbumesFavoritos.append("["); // Comienza el array
            for (int i = 0; i < albumesFavoritos.size(); i++) {
                jsonalbumesFavoritos.append("\"").append(albumesFavoritos.get(i)).append("\"");
                if (i < albumesFavoritos.size() - 1) {
                    jsonalbumesFavoritos.append(","); // Agregar coma si no es el último elemento
                }
            }
            jsonalbumesFavoritos.append("]"); // Cierra el array
            System.out.println("Contenido de jsonalbumesFavoritos: " + jsonalbumesFavoritos.toString());
                        
            //FIN NUEVO ALBUMES MOSTRAR
            
            // Combinar ambos JSON en uno solo
            StringBuilder jsonRespuesta = new StringBuilder();
            jsonRespuesta.append("{");
            jsonRespuesta.append("\"usuario\": ").append(jsonUsuario.toString()).append(",");
            jsonRespuesta.append("\"seguidores\": ").append(jsonSeguidores.toString()).append(","); // Agrega el JSON de seguidores
            jsonRespuesta.append("\"listasCreadas\": ").append(jsonListasCreadas.toString()).append(",");
            jsonRespuesta.append("\"favoritos\": ").append(jsonFavoritos.toString()).append(",");
            jsonRespuesta.append("\"albumesArtista\": ").append(jsonAlbumesArtista.toString()).append(",");
            jsonRespuesta.append("\"listasPublicas\": ").append(jsonListasPublicas.toString()).append(",");
            //
            jsonRespuesta.append("\"albumesFavoritos\": ").append(jsonalbumesFavoritos.toString()).append(",");
            //
            jsonRespuesta.append("\"seguidos\": ").append(jsonSeguidos.toString()); // Agrega el JSON de seguidos
            jsonRespuesta.append("}");
            System.out.println("Contenido de jsonTOTAL: " + jsonRespuesta.toString());
            
            // Enviar la respuesta combinada al jsp
            response.getWriter().print(jsonRespuesta.toString());
            response.getWriter().flush();
            
        }     
    }

/*
public List<String> listaAlbumesArtistaNick(String nick) throws Exception {//devuelve string de albumes de artista en

    */   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

