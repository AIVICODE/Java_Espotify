<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Datatypes.DTUsuario"%>
<%@page import="Datatypes.DTCliente"%>
<%@page import="Datatypes.DTArtista"%>
<%
    session = request.getSession(false);
    DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
    if (dtUsuario == null) {
        response.sendRedirect("index.jsp");
        return;
    } else if (dtUsuario instanceof DTCliente) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta álbum - Espotify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/index.css">
    
</head>
<jsp:include page="header.jsp" />
<body>
    <div class="sidebar">
        <h2 class="mt-4">Opciones</h2>
        <button id="btnAltaAlbum" class="btn btn-primary w-100 mb-2">Alta Album</button>
    </div>

    <div class="content-container">
        <h1 class="text-center mb-4">Alta álbum</h1>

        <!-- Formulario para datos del álbum, imagen y temas dinámicos -->
        <form id="altaAlbumForm" action="SvAltaAlbum" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="nombreAlbum">Nombre del álbum</label>
                <input type="text" class="form-control" id="nombreAlbum" name="nombreAlbum" placeholder="Ingrese el nombre" required>
            </div>
            <div class="section-divider"></div>
            <div class="form-group">
                <label for="anioAlbum">Año de creación</label>
                <input type="number" class="form-control" id="anioAlbum" name="anioAlbum" min="1900" max="2099" value="2023" required>
            </div>
            <div class="section-divider"></div>
            <div class="form-group">
                <label for="imagenAlbum">Seleccionar Imagen del Álbum</label>
                <input type="file" class="form-control" id="imagenAlbum" name="imagenAlbum" accept="image/*">
            </div>
            <div class="section-divider"></div>
            <div class="form-group">
                <label>Género</label>
                <div id="generosContainer" class="form-check">
                    <!-- Los géneros se cargarán dinámicamente aquí -->
                </div>
            </div>


            
            <!-- Contenedor para los temas -->
            <div id="temasContainer">
                <!-- Tema inicial -->
                <div class="temaContainer form-group">
                    <div class="section-divider"></div>
                    <label>Tema</label>
                    <div class="form-group">
                        <input type="text" class="form-control mb-2" name="nombreTema_0" placeholder="Nombre del Tema" required>
                        <input type="text" class="form-control mb-2" name="duracionTema_0" placeholder="Duración (mm:ss)" required>
                        <input type="text" class="form-control mb-2" name="urlTema_0" placeholder="URL del Tema (opcional)">
                        <label for="archivoTema">O seleccionar un archivo MP3</label>
                        <input type="file" class="form-control mb-2" name="archivoTema_0" accept="audio/*">
                    </div>
                </div>
            </div>
            <button id="agregarTemaBtn" class="btn btn-primary mb-2">Agregar Tema</button>
            <!-- Botón para enviar el formulario -->
            <div class="section-divider"></div>
            <button type="submit" class="boton" id="submitBtn">Crear Álbum</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
    let temaIndex = 1;  // Inicia el contador en 1 para las copias de temas

    $(document).ready(function() {
        // Cargar géneros de manera dinámica
        function cargarGeneros() {
            $.ajax({
                url: 'SvObtenerGeneros',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    const generosContainer = document.getElementById('generosContainer');
                    generosContainer.innerHTML = ''; 
                    data.forEach(function(genero) {
                        const div = document.createElement('div');
                        div.classList.add('form-check');

                        const input = document.createElement('input');
                        input.type = 'checkbox';
                        input.classList.add('form-check-input');
                        input.name = 'genero[]';
                        input.value = genero;
                        input.id = genero;

                        const label = document.createElement('label');
                        label.classList.add('form-check-label');
                        label.setAttribute('for', genero);
                        label.textContent = genero;

                        div.appendChild(input);
                        div.appendChild(label);
                        generosContainer.appendChild(div);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Error al obtener géneros:", error);
                }
            });
        }
        cargarGeneros();

        // Agregar un nuevo tema al hacer clic en el botón "Agregar Tema"
        $('#agregarTemaBtn').click(function() {
            const temaContainer = $('.temaContainer').first().clone();
            temaContainer.find('input').val(''); // Limpiar los campos

            // Actualizar los nombres de los campos en el nuevo tema
            temaContainer.find('input[name="nombreTema_0"]').attr('name', 'nombreTema_' + temaIndex);
            temaContainer.find('input[name="duracionTema_0"]').attr('name', 'duracionTema_' + temaIndex);
            temaContainer.find('input[name="urlTema_0"]').attr('name', 'urlTema_' + temaIndex);
            temaContainer.find('input[name="archivoTema_0"]').attr('name', 'archivoTema_' + temaIndex);

            $('#temasContainer').append(temaContainer);
            temaIndex++; // Incrementar el índice para el próximo tema
        });
    });

    document.getElementById('btnAltaAlbum').addEventListener('click', function () {
        // Redirigir a la página altaAlbum .jsp
        console.log('Button clicked');
        window.location.href = 'altaAlbum.jsp';
    });
    </script>
</body>
</html>