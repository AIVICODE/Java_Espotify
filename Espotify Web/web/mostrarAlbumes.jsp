<%@page import="Datatypes.DTUsuario"%>
<%@page import="Datatypes.DTAlbum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Datatypes.DTTema"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Albumes</title>
<link rel="stylesheet" href="css/mostrarAlbumes.css?v=1.3">
    <style>
        .album-temas {
            
            display: none; /* Inicialmente oculta la lista de temas */
            margin-top: 10px;
        }
    </style>

</head>
<body>
    
    
    <%
        // Obtiene la lista de Ã¡lbumes desde la solicitud
        List<DTAlbum> albumes = (List<DTAlbum>) request.getAttribute("listaAlbumes");
        
        if (albumes != null && !albumes.isEmpty()) {
    %>
        <div class="album-list">
            
            <ul>
                <h1>Albumes</h1>
    <%
        // Itera sobre los álbumes y los muestra
        for (DTAlbum album : albumes) {
            // Generar un ID único para cada álbum basado en su nombre y artista
            String albumId = album.getNombre().replaceAll(" ", "_") + "_" + album.getArtista().getNickname().replaceAll(" ", "_");
    %>
        <li class="album-item" onclick="toggleTemas('<%= album.getNombre() %>', '<%= album.getArtista().getNickname() %>')">
            <img src="<%= album.getImagen() %>" alt="Portada de <%= album.getNombre() %>" />
            <div class="album-name"><%= album.getNombre() %></div>
            <div><%= album.getAnioCreacion()%></div>
            <div><%= album.getListaGeneros()%></div>
            <div class="album-artist"><%= album.getArtista().getNickname() %></div>
            <%
    session = request.getSession(false);
    DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
    if (dtUsuario != null) {
    %>
<button class="add-favorite" onclick="verificarYAgregarFavorito('<%= album.getNombre() %>', '<%= album.getArtista().getCorreo() %>')">+</button>
        <%
        return;
    }
    
%>
                        

            
        </li>
    <%
        }
    %>
</ul>
        </div>
    <%
        } else {
    %>
        <p>No se encontraron Albumes para el genero seleccionado.</p>
    <%
        }
    %>
    

</body>
    <script>
  

        
    </script>
</html>