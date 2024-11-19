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
                </div>
<%
            } else {
                // Si no tiene género, mostrar el cliente
%>
                <div class="alert alert-secondary">
                    <strong>Lista de Reproducción de clientes:</strong> <%= listaRep.getNombreListaRep() %>
                    <strong>Cliente:</strong> <%= listaRep.getNombreCliente() %>
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
</div>

</body>
</html>
