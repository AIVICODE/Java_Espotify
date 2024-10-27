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
    <link rel="stylesheet" href="css/altaAlbum.css">
</head>
<jsp:include page="header.jsp" />
<body>
    <div class="container mt-5">
        <div class="content-container">
            <h1 class="text-center mb-4">Alta álbum</h1>

            <!-- Formulario único para datos del álbum, imagen y temas -->
            <form id="altaAlbumForm" action="SvAltaAlbum" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="nombreAlbum">Nombre del álbum</label>
                    <input type="text" class="form-control" id="nombreAlbum" name="nombreAlbum" placeholder="Ingrese el nombre" required>
                </div>
                <div class="form-group">
                    <label for="anioAlbum">Año de creación</label>
                    <input type="number" class="form-control" id="anioAlbum" name="anioAlbum" min="1900" max="2099" value="2023" required>
                </div>
                <div class="form-group">
                    <label for="imagenAlbum">Seleccionar Imagen del Álbum</label>
                    <input type="file" class="form-control" id="imagenAlbum" name="imagenAlbum" accept="image/*">
                </div>
                <div class="form-group">
                    <label>Género</label>
                    <div id="generosContainer" class="form-check">
                        <!-- Los géneros se cargarán dinámicamente aquí -->
                    </div>
                </div>

                <!-- Sección para agregar temas -->
                <div id="temasContainer" class="form-group">
                    <label>Agregar Temas</label>
                    <div class="form-group">
                        <input type="text" class="form-control mb-2" id="nombreTema" placeholder="Nombre del Tema">
                        <input type="text" class="form-control mb-2" id="duracionTema" placeholder="Duración (mm:ss)">
                        <input type="text" class="form-control mb-2" id="urlTema" placeholder="URL del Tema (opcional)">
                        <label for="archivoTema">O seleccionar un archivo MP3</label>
                        <input type="file" class="form-control mb-2" id="archivoTema" accept="audio/*">
                    </div>
                    <button type="button" id="agregarTemaBtn" class="btn btn-info">Agregar Tema</button>
                    <!-- Lista de temas -->
                    <ul id="listaTemas" class="mt-3"></ul>
                </div>

                <!-- Campo oculto para temas en formato JSON -->
                <input type="hidden" id="temasInput" name="temas" value="">

                <!-- Botón para enviar el formulario -->
                <button type="submit" class="btn btn-success w-100" id="submitBtn">Crear Álbum</button>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
    let temas = [];

    document.getElementById('agregarTemaBtn').addEventListener('click', function() {
        const nombreTema = document.getElementById('nombreTema').value;
        const duracionTema = document.getElementById('duracionTema').value;
        const urlTema = document.getElementById('urlTema').value;
        const archivoTema = document.getElementById('archivoTema').files[0];

        // Validación de campos
        if (!nombreTema || !duracionTema) {
            alert("Por favor, complete todos los campos del tema.");
            return;
        }

        const duracionRegex = /^([0-5]?[0-9]):([0-5][0-9])$/;
        const duracionMatch = duracionTema.match(duracionRegex);
        if (!duracionMatch) {
            alert("Por favor, ingrese la duración en formato mm:ss.");
            return;
        }

        const minutos = parseInt(duracionMatch[1], 10);
        const segundos = parseInt(duracionMatch[2], 10);
        
        let archivo;
        if (archivoTema) {
            archivo = archivoTema.name;
        } else {
            archivo = null;
        }


        const tema = {
            nombre: nombreTema,
            minutos: minutos,
            segundos: segundos,
            url: urlTema,
            archivo: archivo
        };

        temas.push(tema);
        
        if (archivoTema) {
            const archivoInput = document.createElement("input");
            archivoInput.type = "file";
            archivoInput.name = "archivoTema_" + nombreTema; // Nombre único basado en el nombre del tema
            archivoInput.style.display = "none"; // Oculta el campo
            archivoInput.files = document.getElementById('archivoTema').files;
            document.getElementById('altaAlbumForm').appendChild(archivoInput);
        }

        actualizarListaTemas();
        limpiarCamposTema();
    });

    function actualizarListaTemas() {
        const listaTemas = document.getElementById('listaTemas');
        listaTemas.innerHTML = ''; 

        for (let i = 0; i < temas.length; i++) {
            const tema = temas[i];
            const li = document.createElement('li');

            // Usa paréntesis para separar la expresión condicional de archivo o URL
            li.innerHTML = tema.nombre + " (" + tema.minutos + ":" + tema.segundos + ") - " + 
                        (tema.archivo ? "Archivo MP3: " + tema.archivo : "URL: " + tema.url) + 
                        '<button type="button" class="delete-button btn btn-danger btn-sm" onclick="eliminarTema(' + i + ')">Eliminar</button>';

            listaTemas.appendChild(li);
        }
    }


    function limpiarCamposTema() {
        document.getElementById('nombreTema').value = '';
        document.getElementById('duracionTema').value = '';
        document.getElementById('urlTema').value = '';
        document.getElementById('archivoTema').value = '';
    }

    function eliminarTema(index) {
        temas.splice(index, 1);
        actualizarListaTemas();
    }

document.getElementById('altaAlbumForm').addEventListener('submit', function(event) {
        document.getElementById('temasInput').value = JSON.stringify(temas);
    });


    $(document).ready(function() {
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
    });
    </script>
</body>
</html>