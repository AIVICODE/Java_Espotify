
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<%
    session = request.getSession(false);
    DtUsuario dtUsuario = (DtUsuario) session.getAttribute("usuario");
    if (dtUsuario == null) {
        response.sendRedirect("index.jsp");    
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Baja de Artista - Estilo Spotify</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body{
                background-color: #191414;
            }
            .boton {
                width: 100%;
                padding: 0.75rem;
                background-color: #22c55e;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
            }
            .boton:hover {
                background-color: #16a34a;
            }       
            /* barra de arriba*/
            .user-section {
                display: flex;
                align-items: center;
            }
            .user-avatar {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 0.5rem;
            }
            .profile-section {
                display: flex;
                align-items: center;
            }

            .profile-img {
                width: 2.5rem;
                height: 2.5rem;
                border-radius: 50%;
                margin-right: 1rem;
            }

            .profile-info button {
                background: none;
                border: none;
                color: white;
                cursor: pointer;
                font-size: 0.875rem;
                display: inline-flex;
                align-items: center;
                margin-right: 0.5rem;
            }

            .profile-info button:hover {
                text-decoration: underline;
            }
            .user-info {
                display: flex;
                flex-direction: column;
                margin-right: 1rem;
            }
            .user-nickname {
                font-weight: bold;
            }
            .user-favorites {
                display: flex;
                align-items: center;
                font-size: 0.8rem;
                color: #ffff00;
            }
            .star-icon {
                margin-right: 0.25rem;
            }
            .logout-button {
                background-color: transparent;
                border: 1px solid white;
                color: white;
                padding: 0.5rem 1rem;
                border-radius: 2rem;
                cursor: pointer;
                transition: background-color 0.3s, color 0.3s;
            }
            .logout-button:hover {
                background-color: white;
                color: #191414;
            }
            header {
                width: 100%; /* Header ocupa todo el ancho */
                position: fixed; /* Fijarlo en la parte superior */
                top: 0;
                z-index: 1000;
            }
            h1{
                font-family: 'Circular', Arial, sans-serif;
                color: white;
            }
            .formi {
                max-width: 400px;
                margin: 6rem auto; /* Margen de arriba para que aparezca en la pantalla */
                padding: 2rem;
                background-color: #27272a;
                border-radius: 8px;
            }
            label{
                font-family: 'Circular', Arial, sans-serif;
                color: white;
            }
            
            select {
            width: 100%; /* Ancho completo del combobox */
            padding: 10px; /* Espaciado interno del combobox */
            border-radius: 5px; /* Bordes redondeados del combobox */
            border: none; /* Sin borde */
            background-color: #333333; /* Color de fondo del combobox */
            color: white; /* Color del texto en el combobox */
            }
            .mensajeExito {
            color: white; /* Color del texto */
            background-color: #22c55e; /* Fondo verde */
            padding: 10px; /* Espaciado interno */
            border-radius: 8px; /* Bordes redondeados */
            text-align: center; /* Centrar texto */
            margin-top: 15px; /* Espacio arriba */
            margin-bottom: 20px; /* Espacio inferior */
            width: 30%; /* Ancho del rectángulo */
            margin-left: auto; /* Centrar horizontalmente */
            margin-right: auto; /* Centrar horizontalmente */
        }
        
        /* Estilos del modal */
        .modal {
            display: none; /* Oculto por defecto */
            position: fixed;
            z-index: 1; /* Por encima de otros elementos */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto; /* Habilitar desplazamiento si es necesario */
            background-color: rgba(0,0,0,0.4); /* Fondo negro con opacidad */
        }
        .modal-content {
            background-color: #191414; /* Fondo verde claro */
            margin: 12% auto; /* 15% desde arriba y centrado */
            padding: 20px;
            border-radius: 10px; /* Bordes redondeados del modal */
            width: 300px; /* Ancho del modal */
            color: white;
        }
        .modal-body {
            font-size: 20px; /* Tamaño de fuente más pequeño para el mensaje */
            color: white;
        }
        .modal-footer {
            display: flex;
            justify-content: space-between;
        }
        .modal-footer button {
            width: 100%;
            padding: 0.75rem;
            background-color: #22c55e;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
        }
            
      
        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <div class="container d-flex justify-content-center">
            <div class="spotify-card">
                <form id="bajaForm" action="SvBajaDeArtista" method="post" class="formi">
                    <h1 align="center">Baja de Artista</h1>
                    <label align="left" >Se eliminará toda la información relacionada al artista.</label>
                    <button type="button" class="boton" onclick="confirmBaja()">Darse de Baja</button>
                </form>
            </div>
        </div>
      <!-- Modal de confirmación -->
    <!-- Modal de confirmación -->
    <div id="confirmModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Confirmación</h2>
            </div>
            <div class="modal-body">
                <p>¿Desea darse de baja?</p>
            </div>
            <div class="modal-footer">
                <button onclick="closeModal()">Cancelar</button>
                <button id="confirmButton">Confirmar</button>
            </div>
        </div>
    </div>
      
    <script>
        function confirmBaja() {
            // Mostrar el modal
            document.getElementById('confirmModal').style.display = 'block';
        }

        function closeModal() {
            // Ocultar el modal
            document.getElementById('confirmModal').style.display = 'none';
        }

        document.getElementById('confirmButton').onclick = function() {
            // Obtener el nickname del usuario
            const nickname = '<%= dtUsuario.getNickname() %>';

            // Crear un campo oculto en el formulario para enviar el nickname
            const form = document.getElementById('bajaForm');

            // Crear un input oculto
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'nickname'; // Nombre del parámetro que espera el servlet
            input.value = nickname;

            // Agregar el input al formulario
            form.appendChild(input);

            // Enviar el formulario
            form.submit();
        };
    </script>
      
        <!-- Bootstrap JS y Font Awesome -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
    <script>
    // Mostrar un mensaje emergente si hay un mensaje de éxito
    <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
    <% if (mensajeExito != null) { %>
        alert("<%= mensajeExito %>");
    <% } %>
</script>
    </body>
</html>





