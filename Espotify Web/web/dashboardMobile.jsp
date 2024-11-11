<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Aplicación Musical</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

    <style>
        /* Estilos personalizados para botones de navegación */
        .btn-group {
            margin-bottom: 20px;
        }
        
        .btn-group .btn {
            background-color: #1DB954; /* Verde de Spotify */
            color: white;
            border: 1px solid #1DB954;
            border-radius: 20px;
            font-weight: bold;
            padding: 10px 20px;
            transition: background-color 0.3s, transform 0.2s;
             
        }

        .btn-group .btn:hover {
            background-color: #1ed760; /* Efecto hover */
            transform: scale(1.05);
        }

     .btn-group .btn:focus, .btn-group .btn.active {
    outline: none !important; /* Elimina el borde azul predeterminado */
    box-shadow: 0 0 0 0.2rem rgba(29, 185, 84, 0.5) !important; /* Efecto de sombra verde */}

        /* Estilo para la lista de géneros */
        .genre-list, .artist-list {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            padding: 10px;
        }

        .genre-item, .artist-item {
            background-color: #1DB954;
            color: white;
            padding: 12px 20px;
            border-radius: 30px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
            display: table-cell;
            text-align: center;
        }

        .genre-item:hover, .artist-item:hover {
            background-color: #1ed760;
            transform: scale(1.1);
        }

        /* Estilo para las tablas */
        .table {
            background-color: #121212; /* Fondo oscuro */
            color: white;
            border-radius: 10px;
        }

        .table th, .table td {
            text-align: center;
            padding: 15px;
            border: none;
        }

        .table th {
            background-color: #1DB954; /* Color verde de Spotify */
        }

        .table td {
            background-color: #181818; /* Fondo más oscuro para celdas */
        }

        .table thead {
            border-bottom: 2px solid #1DB954;
        }

        /* Estilo para los iframes */
        iframe {
            border-radius: 10px;
            border: none;
        }

        /* Aseguramos que dynamicContent sea visible por defecto */
        #dynamicContent {
            display: block;
        }
        
        body {
            background-color: #121212;
            flex-wrap:wrap !important;
            display:flex !important;
            height:auto !important;
            flex-direction:column;
        }
        .mt-4 {
    margin-top: 1.0rem !important;
}
    </style>
</head>
<jsp:include page="headerMobile.jsp" />
<body>
    <div class="container mt-4">
        <!-- Menú de navegación -->
        <div class="container mt-4 d-flex justify-content-center">
            <div class="btn-group" role="group" aria-label="Botones de navegación">
                <button type="button" id="btnGeneros" class="btn btn-outline-primary">Géneros</button>
                <button type="button" id="btnArtistas" class="btn btn-outline-primary">Artistas</button>
                <button type="button" id="btnListas" class="btn btn-outline-primary">Listas</button>
            </div>
        </div>

        <!-- Contenido dinámico -->
        <div id="dynamicContent" class="mt-3"></div>

        <!-- Iframe para cargar los álbumes -->
        <iframe id="dynamicIframeAlbum" style="width: 100%; height: 400px; display: none;"></iframe>

        <!-- Iframe para cargar los temas -->
        <iframe id="dynamicIframeTemas" style="width: 100%; height: 400px; display: none;"></iframe>
    </div>

    <script>
        // Función para ocultar todos los iframes
        function hideAllIframes() {
            document.getElementById('dynamicIframeAlbum').style.display = "none";
            document.getElementById('dynamicIframeTemas').style.display = "none";
            document.getElementById('dynamicContent').style.display = "block"; // Aseguramos que el contenido siempre esté visible
        }

        // Función para mostrar los temas en el iframe
        function showTemasInIframe(albumName, artistName) {
            const encodedAlbumName = encodeURIComponent(albumName);
            const encodedArtistName = encodeURIComponent(artistName);
            const servletUrl = "SvObtenerTemas?album=" + encodedAlbumName + "&artista=" + encodedArtistName;

            // Cierra todos los iframes
            hideAllIframes();

            // Muestra el iframe de los temas
            const dynamicIframeTemas = document.getElementById('dynamicIframeTemas');
            dynamicIframeTemas.style.display = "block";
            dynamicIframeTemas.src = servletUrl; // Establece la URL del iframe
        }

        const btnGeneros = document.getElementById('btnGeneros');
        const btnArtistas = document.getElementById('btnArtistas');
        const btnListas = document.getElementById('btnListas');
        const dynamicContent = document.getElementById('dynamicContent');
        const dynamicIframeAlbum = document.getElementById('dynamicIframeAlbum');
        const dynamicIframeTemas = document.getElementById('dynamicIframeTemas');

        btnListas.addEventListener('click', () => {
            // Cierra todos los iframes
            hideAllIframes();
            window.location.href = 'SvObtenerClientes'; // Redirige a la nueva página
        });

        function setActiveButton(button) {
            [btnGeneros, btnArtistas].forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        }

        btnGeneros.addEventListener('click', () => {
            setActiveButton(btnGeneros);
            // Cierra todos los iframes
            hideAllIframes();
            
            fetch('SvObtenerGeneros')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al obtener géneros: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    dynamicContent.innerHTML = '<table class="table"><thead><tr><th>Género</th></tr></thead><tbody class="genre-list"></tbody></table>';
                    const genreList = document.querySelector('.genre-list');

                    data.forEach(genre => {
                        const genreRow = document.createElement('tr');
                        const genreItem = document.createElement('td');
                        genreItem.className = 'genre-item';
                        genreItem.textContent = genre;

                        genreItem.addEventListener('click', () => {
                            console.log('Género seleccionado:', genre);
                            const variableurl = encodeURIComponent(genre);
                            const url = "SvObtenerAlbumxGenero?genero=" + variableurl;

                            fetch(url)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Error al obtener álbumes: ' + response.statusText);
                                    }
                                    return response.text(); // Cambia a .text() para obtener el HTML
                                })
                                .then(html => {
                                    dynamicContent.innerHTML = html; // Incrusta el nuevo HTML

                                    // Vuelve a asociar el evento de clic a cada elemento de álbum
                                    const albumItems = dynamicContent.querySelectorAll('.album-item');
                                    albumItems.forEach(item => {
                                        const albumName = item.querySelector('.album-name').textContent.trim();
                                        const artistName = item.querySelector('.album-artist').textContent.trim();
                                        console.log('Género seleccionado:', genre);
                                        console.log('Album: ', albumName);

                                        item.onclick = () => {
                                            // Llamar a la función para mostrar los temas
                                            showTemasInIframe(albumName, artistName);
                                        };
                                    });
                                })
                                .catch(error => console.error('Error al obtener álbumes:', error));
                        });

                        genreRow.appendChild(genreItem);
                        genreList.appendChild(genreRow);
                    });
                })
                .catch(error => console.error('Error al obtener géneros:', error));
        });

        btnArtistas.addEventListener('click', () => {
            setActiveButton(btnArtistas);
            // Cierra todos los iframes
            hideAllIframes();

            // Realizar la solicitud AJAX para obtener los artistas
            fetch('SvObtenerArtistas') // Asegúrate de que esta ruta sea correcta
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al obtener artistas: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    dynamicContent.innerHTML = '<table class="table"><thead><tr><th>Artista</th></tr></thead><tbody class="artist-list"></tbody></table>';
                    const artistList = document.querySelector('.artist-list');

                    data.forEach(artist => {
                        const artistRow = document.createElement('tr');
                        const artistItem = document.createElement('td');
                        artistItem.className = 'artist-item';
                        artistItem.textContent = artist;

                        artistItem.addEventListener('click', () => {
                            console.log('Artista seleccionado:', artist);
                            const variableurl = encodeURIComponent(artist);
                            const url = "SvObtenerAlbumxArtista?artista=" + variableurl;

                            fetch(url)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Error al obtener álbumes: ' + response.statusText);
                                    }
                                    return response.text();
                                })
                                .then(html => {
                                    dynamicContent.innerHTML = html;

                                    // Vuelve a asociar el evento de clic a cada elemento de álbum
                                    const albumItems = dynamicContent.querySelectorAll('.album-item');
                                    albumItems.forEach(item => {
                                        const albumName = item.querySelector('.album-name').textContent.trim();
                                        const artistName = item.querySelector('.album-artist').textContent.trim();

                                        item.onclick = () => {
                                            showTemasInIframe(albumName, artistName);
                                        };
                                    });
                                })
                                .catch(error => console.error('Error al obtener álbumes:', error));
                        });

                        artistRow.appendChild(artistItem);
                        artistList.appendChild(artistRow);
                    });
                })
                .catch(error => console.error('Error al obtener artistas:', error));
        });
        
        function AgregarAlbumFavorito(albumName, artistName) {
            const encodedAlbumName = encodeURIComponent(albumName);
            const encodedArtistName = encodeURIComponent(artistName);

            fetch('SvAgregarAlbumFavorito', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: "album=" + encodedAlbumName + "&artista=" + encodedArtistName
            })
                    .then(response => {
                        // Capturamos el error si el servidor respondió con un estado HTTP no exitoso
                        if (!response.ok) {
                            return response.json().then(errorData => {
                                throw new Error(errorData.message);
                            });
                        }
                        return response.json(); // Parseamos la respuesta JSON
                    })
                    .then(data => {
                        if (data.success) {
                            alert(data.message); // Mostrar mensaje de éxito
                        }
                    })
                    .catch(error => {
                        // Manejo de errores y mostrar el mensaje capturado
                        alert("Error: " + error.message);
                    });
        }
        function verificarYAgregarFavorito(nombreAlbum, correoArtista) {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                AgregarAlbumFavorito(nombreAlbum, correoArtista);
            } else {
                alert('No tienes una suscripción activa para agregar este álbum a favoritos.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}
    </script>
</body>

</html>
