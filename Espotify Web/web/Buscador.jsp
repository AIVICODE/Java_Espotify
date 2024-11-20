<%@page import="webservices.DtCliente"%>
<%@page import="webservices.DtListaRep"%>
<%@page import="webservices.DtAlbum"%>
<%@page import="webservices.DtTema"%>
<%@page import="webservices.DtContenido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Buscador.css"> <!-- Ruta a tu archivo CSS -->
                <link rel="icon" type="image/png" href="https://storage.googleapis.com/pr-newsroom-wp/1/2023/05/Spotify_Primary_Logo_RGB_Green.png"> <!-- Ruta del logo de Spotify -->

    <title>Resultados de Búsqueda</title>
</head>

<%
            session = request.getSession(false);
            DtCliente dtUsuario = (DtCliente) session.getAttribute("usuario");
            if (dtUsuario == null) {
        %>
        <jsp:include page="headerunlogged.jsp" /> 
        <%
        } else {
        %>
        <jsp:include page="header.jsp" />
        <%
            }
        %>

<body>

<div class="container3 mt-3">
    <!-- Formulario para realizar la búsqueda -->
    <h2 class="mb-4">Buscar Favoritos</h2>
    <form action="SvObtenerBusqueda" method="get" class="form-inline mb-4">
        <input type="hidden" id="hidden" name="filtro" value="<%= request.getParameter("filtro") %>" required>
        
        <div class="form-group mx-2">
            <label for="filtro" class="mr-2">Coincidencias de: <%= request.getParameter("filtro") %></label>
            <label for="sortBy" class="mr-2">Ordenar por:</label>
            <select id="sortBy" name="sortBy" class="form-control bg-dark text-white border-0" onchange="this.form.submit()">
                            <option value="-" <%= (request.getParameter("sortBy") == null || "-".equals(request.getParameter("sortBy"))) ? "selected" : "" %>>-</option>

                <option value="nombre" <%= "nombre".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Nombre</option>
            <option value="fecha" <%= "fecha".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Fecha de Creación</option>
            </select>
        </div>
        
    </form>

    <hr class="my-4">

    <!-- Sección para mostrar los resultados de la búsqueda -->
    <div class="results">
        <%
            List<DtContenido> dtContenidoList = (List<DtContenido>) request.getAttribute("dtContenidoList");
            String mensajeError = (String) request.getAttribute("mensajeError");

            if (mensajeError != null) {
        %>
                <div class="alert alert-danger" role="alert">
                    <%= mensajeError %>
                </div>
        <%
            } else if (dtContenidoList != null && !dtContenidoList.isEmpty()) {
                for (DtContenido contenido : dtContenidoList) {
                    if (contenido instanceof DtTema) {
                        DtTema tema = (DtTema) contenido;
        %>
                        <div class="alert alert-info">
                            <strong>Tema:</strong> <%= tema.getNombre() %>, 
                            <strong>Artista:</strong> <%= tema.getNombreartista()%>, 
                            <strong>Album:</strong> <%= tema.getNombrealbum()%>,
                            <strong>Año de Creación:</strong> <%= tema.getAnioCreacion() %>
                        </div>
        <%
                    } else if (contenido instanceof DtAlbum) {
                        DtAlbum album = (DtAlbum) contenido;
        %>
                        <div class="alert alert-light">
                            <strong>Album:</strong> <%= album.getNombre() %>, 
                            <strong>Artista:</strong> <%= album.getArtista().getCorreo() %>, 
                            <strong>Género:</strong> <%= album.getListaGeneros()%>, 
                            <strong>Año de Creación:</strong> <%= album.getAnioCreacion() %>
                            
                            
                        </div>
        <%
                    } else if (contenido instanceof DtListaRep) {
                        DtListaRep listaRep = (DtListaRep) contenido;
            if (listaRep.getGenero() != null && !listaRep.getGenero().isEmpty()) {

        %>
                        <div class="alert alert-secondary">
                    <strong>Lista de Reproducción:</strong> <%= listaRep.getNombreListaRep() %>
                    <strong>Género:</strong> <%= listaRep.getGenero() %>
                    
                        <a href="javascript:void(0);" 
               onclick="fetchLista('<%= listaRep.getNombreListaRep() %>', null);">
               Ver
            </a>
                </div>
<%
            } else {
                // Si no tiene género, mostrar el cliente
%>
                <div class="alert alert-secondary">
                    <strong>Lista de Reproducción de clientes:</strong> <%= listaRep.getNombreListaRep() %>
                    <strong>Cliente:</strong> <%= listaRep.getNombreCliente() %>
                    <a href="javascript:void(0);" 
               onclick="fetchLista('<%= listaRep.getNombreListaRep() %>','<%= listaRep.getNombreCliente() %>');">
               Ver
            </a>
                </div>
        <%
                    }
                }
}
            } else {

 if( request.getParameter("filtro").isEmpty() ){
        %>
        
                <div class="alert alert-info">
                    No se encontraron resultados.
                </div>
        <%
            }
            }


        %>
    </div>
   <iframe id="dynamicIframe" style="width: 100%; height: 400px; border: none;"></iframe>
</div>

</body>

<script>
           
        function fetchLista(nombreLista, nombreCliente) {
    const encodedNombreLista = encodeURIComponent(nombreLista);
    const encodedNombreCliente = nombreCliente ? encodeURIComponent(nombreCliente) : null;

    // Construye la URL del servlet según si hay cliente o no
    let servletUrl;
    if (encodedNombreCliente === null) {
        servletUrl = "SvSeleccionarLista?nombreLista=" + encodedNombreLista;
    } else {
        servletUrl = "SvSeleccionarLista?nombreLista=" + encodedNombreLista + "&nombreCliente=" + encodedNombreCliente;
    }
    // Obtiene el iframe existente y actualiza su fuente
    if (servletUrl) {
    // Abrir en una nueva pestaña o ventana
    window.open(servletUrl, '_blank'); // '_blank' abre en una nueva pestaña
    // O usa `window.location.href` si quieres redirigir en la misma página
    // window.location.href = servletUrl;
} else {
    console.error("No se pudo generar la URL para abrir la página.");
}
}
    
</script>
</html>
