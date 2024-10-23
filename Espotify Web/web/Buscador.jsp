<%@page import="Datatypes.DTCliente"%>
<%@page import="Datatypes.DTListaRep"%>
<%@page import="Datatypes.DTAlbum"%>
<%@page import="Datatypes.DTTema"%>
<%@page import="Datatypes.DTContenido"%>
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
            DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
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
            <select id="sortBy" name="sortBy" class="form-control bg-dark text-white border-0">
                <option value="nombre">Nombre</option>
                <option value="fecha">Fecha de Creación</option>
            </select>
        </div>
        
        <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Buscar</button>
    </form>

    <hr class="my-4">

    <!-- Sección para mostrar los resultados de la búsqueda -->
    <div class="results">
        <%
            List<DTContenido> dtContenidoList = (List<DTContenido>) request.getAttribute("dtContenidoList");
            String mensajeError = (String) request.getAttribute("mensajeError");

            if (mensajeError != null) {
        %>
                <div class="alert alert-danger" role="alert">
                    <%= mensajeError %>
                </div>
        <%
            } else if (dtContenidoList != null && !dtContenidoList.isEmpty()) {
                for (DTContenido contenido : dtContenidoList) {
                    if (contenido instanceof DTTema) {
                        DTTema tema = (DTTema) contenido;
        %>
                        <div class="alert alert-info">
                            <strong>Tema:</strong> <%= tema.getNombre() %>, 
                            <strong>Artista:</strong> <%= tema.getNombreartista()%>, 
                            <strong>Album:</strong> <%= tema.getNombrealbum()%>,
                            <strong>Año de Creación:</strong> <%= tema.getAnioCreacion() %>
                        </div>
        <%
                    } else if (contenido instanceof DTAlbum) {
                        DTAlbum album = (DTAlbum) contenido;
        %>
                        <div class="alert alert-light">
                            <strong>Album:</strong> <%= album.getNombre() %>, 
                            <strong>Género:</strong> <%= album.getListaGeneros()%>, 
                            <strong>Año de Creación:</strong> <%= album.getAnioCreacion() %>
                        </div>
        <%
                    } else if (contenido instanceof DTListaRep) {
                        DTListaRep listaRep = (DTListaRep) contenido;
        %>
                        <div class="alert alert-secondary">
                            <strong>Lista de Reproducción:</strong> <%= listaRep.getNombreListaRep()%>
                        </div>
        <%
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
