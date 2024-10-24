
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Publicar Lista de Reproducción - Estilo Spotify</title>
        
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
                background-color: #121212; /* Cambiado a un color más oscuro para asemejar el anterior */
            border-radius: 8px; /* Bordes redondeados */
            padding: 24px; /* Espaciado interior */
            box-shadow: 0 4px 60px rgba(0, 0, 0, 0.5); /* Sombra similar a la tarjeta de Spotify */
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
            
        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        
        <div class="container d-flex justify-content-center">
                <div class="spotify-card">
                    <form action="SvPublicarListaPrivada" method="post" class="formi">
                        <h1>Publicar lista de Reproducción</h1>
                        <div class="mb-3">                       
                            <label for="opciones">Seleccione el nombre de la lista</label>
                            <!--  <a href="SvObtenerListasPrivadas">Cargar Listas de Reproducción</a>-->
                            <select id="listas" name="nombreLista"><!-- IMPORTANTE PARA PROXIMOS FORMULARIOS -->
                                <option value="">...</option>
                            </select>
                        </div>
                    <script>
                    // Función para cargar las listas de reproducción
                    function cargarListas() {
                        fetch('SvObtenerListasPrivadas')
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Error en la red');
                                }
                                return response.json();
                            })
                            .then(data => {
                                const select = document.getElementById('listas');
                                data.forEach(lista => {
                                    const option = document.createElement('option');
                                    option.value = lista;
                                    option.textContent = lista;
                                    select.appendChild(option);
                                });
                            })
                            .catch(error => {
                                console.error('Error al cargar las listas:', error);
                                alert('No se pudieron cargar las listas de reproducción.');
                            });
                    }

                    // Cargar las listas al cargar la página
                    window.onload = cargarListas;
                    </script>                  
                        <button type="submit" class="boton">Publicar Lista</button>
                    </form>
                </div>
            
    </div>
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





