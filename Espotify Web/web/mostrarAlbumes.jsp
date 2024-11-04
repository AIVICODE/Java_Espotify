<%@page import="Datatypes.DTUsuario"%>
<%@page import="Datatypes.DTAlbum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
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
    List<DTAlbum> albumes = (List<DTAlbum>) request.getAttribute("listaAlbumes");
    List<byte[]> listaImgAlbumes = (List<byte[]>) request.getAttribute("listaImgAlbumes");

    if (albumes != null && !albumes.isEmpty() && listaImgAlbumes != null && !listaImgAlbumes.isEmpty()) {
%>
    <div class="album-list">
<ul>
    <h1>Álbumes</h1>
    <%
        for (int i = 0; i < albumes.size(); i++) {
            DTAlbum album = albumes.get(i);
            byte[] imgAlbumBytes = listaImgAlbumes.get(i);

            // Convierte los bytes en una URL base64
            String base64Image = Base64.getEncoder().encodeToString(imgAlbumBytes);
            String imgSrc = "data:image/jpeg;base64," + base64Image;

            String albumId = album.getNombre().replaceAll(" ", "_") + "_" + album.getArtista().getNickname().replaceAll(" ", "_");
    %>
    <li class="album-item" 
        onclick="toggleTemas('<%= album.getNombre() %>', '<%= album.getArtista().getNickname() %>')">
        <img src="<%= imgSrc %>" alt="Portada de <%= album.getNombre() %>" />
        <div class="album-name"><%= album.getNombre() %></div>
        <div><%= album.getAnioCreacion() %></div>
        <div><%= album.getListaGeneros() %></div>
        <div class="album-artist"><%= album.getArtista().getNickname() %></div>

        <% session = request.getSession(false);
        DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
        if (dtUsuario != null) { %>
            <button class="add-favorite" 
                    onclick=" verificarYAgregarFavorito('<%= album.getNombre() %>', '<%= album.getArtista().getCorreo() %>')"
                    style="margin-left: 10px;">+</button>
        <% } %>
    </li>
    <%
        }
    %>
</ul>

    </div>
<%
    } else {
%>
    <p>No se encontraron Álbumes para el artista seleccionado.</p>
<%
    }
%>

</body>
</html>