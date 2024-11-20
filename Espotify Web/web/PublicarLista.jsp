
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Publicar Lista de Reproducción - Estilo Spotify</title>
        <link rel="stylesheet" href="css/PublicarLista.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
                    <link rel="icon" type="image/png" href="https://storage.googleapis.com/pr-newsroom-wp/1/2023/05/Spotify_Primary_Logo_RGB_Green.png"> <!-- Ruta del logo de Spotify -->

        <style>body {
                background-color: #191414;
            }
            h1{
                font-family: 'Circular', Arial, sans-serif;
                color: white;
            }</style>
    </head>
    <jsp:include page="header.jsp" />
    <body>


        <div class="container d-flex justify-content-center">
            <div class="spotify-card">
                <div class="mensajeExito" id="mensajeExito">
                    ¡Lista publicada con éxito!
                </div>
                <div class="mensajeError" id="mensajeErrorsub">
                    No tienes una suscripción activa para publicar esta lista.
                </div>
                <div class="mensajeError" id="mensajeErrorlistanovalida">
                    No se pudo publicar la lista. Es posible que la lista ya sea publica
                </div>
                <form action="SvPublicarListaPrivada" method="post" class="formi" onsubmit="return verificarSuscripcion(event)">
                    <h1>Publicar lista de Reproducción</h1>
                    <div class="mb-3">                       
                        <label for="opciones">Seleccione el nombre de la lista</label>
                        <select id="listas" name="nombreLista">
                            <option value="">...</option>
                        </select>
                    </div>
                    <button type="submit" class="boton">Publicar Lista</button>
                </form>
            </div>
        </div>

        <script>
            function cargarListas() {
                fetch('SvObtenerListasPrivadas')
                        .then(response => response.json())
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

            async function verificarSuscripcion(event) {
                event.preventDefault(); // Evita el envío normal del formulario
                try {
                    const response = await fetch('SvVerificarSubscripcion', {method: 'GET'});
                    const data = await response.json();

                    if (data.hasSubscription) {
                        // Realiza el fetch para publicar la lista
                        const publishResponse = await fetch('SvPublicarListaPrivada', {
                            method: 'POST',
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                            body: new URLSearchParams(new FormData(event.target))
                        });

                        const publishData = await publishResponse.json();

                        if (publishData.success) {
                            mostrarMensajeExito(publishData.message);

                        } else {
                            mostrarMensajeErrorlista(publishData.message || "No se pudo publicar la lista.");
                        }
                    } else {
                        mostrarMensajeError("No tienes una suscripción activa para publicar esta lista.");
                    }
                } catch (error) {
                    console.error('Error al verificar la suscripción:', error);
                    mostrarMensajeError("Error al verificar la suscripción.");
                }
            }


            function mostrarMensajeExito() {
                const mensajeExito = document.getElementById('mensajeExito');
                mensajeExito.style.display = 'block';
                setTimeout(() => {
                    mensajeExito.style.display = 'none';
                }, 3000);

            }

            function mostrarMensajeError(errorMessage) {
                const mensajeError = document.getElementById('mensajeErrorsub');
                mensajeError.textContent = errorMessage;
                mensajeError.style.display = 'block';
                setTimeout(() => {
                    mensajeError.style.display = 'none';
                }, 3000);
            }

            function mostrarMensajeErrorlista(errorMessage) {
                const mensajeError = document.getElementById('mensajeErrorlistanovalida');
                mensajeError.textContent = errorMessage;
                mensajeError.style.display = 'block';
                setTimeout(() => {
                    mensajeError.style.display = 'none';
                }, 3000);
            }
            window.onload = cargarListas;

            // Mostrar mensaje de éxito temporalmente
            <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
            <% if (mensajeExito != null) { %>
            document.addEventListener('DOMContentLoaded', function () {
                const mensajeExito = document.getElementById('mensajeExito');
                mensajeExito.style.display = 'block';
                setTimeout(() => {
                    mensajeExito.style.display = 'none';
                }, 3000); // Ocultar el mensaje después de 3 segundos
            });
            <% }%>
        </script>
    </body>
</html>






