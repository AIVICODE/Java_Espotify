<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtCliente"%>
<%@page import="webservices.DtArtista"%>

<%
    session = request.getSession(false);
    DtUsuario dtUsuario = (DtUsuario) session.getAttribute("usuario");
    if (dtUsuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espotify</title>
    <link rel="stylesheet" href="css/index.css?v1.5"> <!-- Asegúrate de que la ruta sea correcta -->
</head>

<jsp:include page="header.jsp" />
<body>


    <!-- Main Content -->
    <div class="containerprincipal">
        <!-- Sidebar -->
        <div class="sidebar">
            <h2>Opciones</h2>
            <button id="btnAltaAlbum">Alta Album</button>

            
        </div>


</body>
<script>
    document.getElementById('btnAltaAlbum').addEventListener('click', function() {
        // Redirigir a la página altaAlbum .jsp
        console.log('Button clicked');
        window.location.href = 'altaAlbum.jsp';
    });
    </script>