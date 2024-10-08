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
<link rel="stylesheet" href="css/mostrarAlbumes.css?v=1.1">
    <style>
        .album-temas {
            display: none; /* Inicialmente oculta la lista de temas */
            margin-top: 10px;
        }
    </style>
    <script>
        function toggleTemas(albumId) {
            const temasDiv = document.getElementById(albumId);
            if (temasDiv.style.display === "none") {
                temasDiv.style.display = "block"; // Muestra los temas
            } else {
                temasDiv.style.display = "none"; // Oculta los temas
            }
        }
    </script>
</head>
<body>
    <h1>Albumes</h1>
    
    <%
        // Obtiene la lista de Ã¡lbumes desde la solicitud
        List<DTAlbum> albumes = (List<DTAlbum>) request.getAttribute("listaAlbumes");
        
        if (albumes != null && !albumes.isEmpty()) {
    %>
        <div class="album-list">
            <ul>
                <%
                    // Itera sobre los Ã¡lbumes y los muestra
                    for (DTAlbum album : albumes) {
                        // Generar un ID Ãºnico para cada Ã¡lbum basado en su nombre y artista
                        String albumId = album.getNombre().replaceAll(" ", "_") + "_" + album.getArtista().getNickname().replaceAll(" ", "_");
                %>
                    <li class="album-item" onclick="toggleTemas('<%= albumId %>')">
                        <img src="<%= album.getImagen() %>" alt="Portada de <%= album.getNombre() %>" />
                        <div class="album-name"><%= album.getNombre() %></div>
                        <div class="album-artist"><%= album.getArtista().getNickname() %></div>
                        
                        <div class="album-temas" id="<%= albumId %>">
                            <strong>Temas:</strong>
                            <ul>
                                <%
                                    // Obtiene la lista de temas del Ã¡lbum
                                    List<DTTema> temas = album.getListaTemas();
                                    if (temas != null && !temas.isEmpty()) {
                                        for (DTTema tema : temas) {
                                %>
                                            <li><%= tema.getNombre() %></li>
                                <%
                                        }
                                    } else {
                                %>
                                        <li>No hay temas disponibles.</li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                    </li>
                <%
                    }
                %>
            </ul>
        </div>
    <%
        } else {
    %>
        <p>No se encontraron Ã¡lbumes para el gÃ©nero seleccionado.</p>
    <%
        }
    %>
    

</body>
</html>