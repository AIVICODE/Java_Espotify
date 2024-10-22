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
    }
%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espotify</title>
    <link rel="stylesheet" href="css/index.css"> <!-- Asegúrate de que la ruta sea correcta -->
</head>

<jsp:include page="header.jsp" />
<body>


    <!-- Main Content -->
    <div class="main-content container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h2>Opciones</h2>
            <button id="btnActualizarSubscripcion">Actualizar Subscripcion</button>
            <button id="btnAgregarSubscripcion">Activar Premium</button>
            <button id="btnAgregarLista">Crear Lista de Reproduccion</button>
            <h2>Biblioteca</h2>
            <button id="btnGeneros">Generos</button>
            <button id="btnArtistas">Artistas</button>
            <button id="btnListas">Listas de reproduccion</button>
            <% if (dtUsuario instanceof DTArtista) { %>
            <button id="btnAltaAlbum">Alta Album</button>
            <% }%>
            
        </div>

        <!-- Dynamic Content -->
        <div id="dynamicContent">
        </div>

    </div>

    <!-- Footer (Player) -->


    <script>
        function toggleTemas(albumId) {
            const temasDiv = document.getElementById(albumId);
            if (temasDiv.style.display === "none" || temasDiv.style.display === "") {
                temasDiv.style.display = "block"; // Muestra los temas
            } else {
                temasDiv.style.display = "none"; // Oculta los temas
            }
        }

        const btnGeneros = document.getElementById('btnGeneros');
        const btnArtistas = document.getElementById('btnArtistas');
        const btnListas = document.getElementById('btnListas');
        const dynamicContent = document.getElementById('dynamicContent');
        const playPauseBtn = document.getElementById('playPauseBtn');
        const volumeSlider = document.getElementById('volumeSlider');


        btnListas.addEventListener('click', () => {
            window.location.href = 'SvObtenerClientes'; // Redirige a la nueva página
        });

        function setActiveButton(button) {
            [btnGeneros, btnArtistas].forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        }

        btnGeneros.addEventListener('click', () => {
            setActiveButton(btnGeneros);
            fetch('SvObtenerGeneros')
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Error al obtener géneros: ' + response.statusText);
                        }
                        return response.json();
                    })
                    .then(data => {
                        dynamicContent.innerHTML = '<div class="genre-list"></div>';
                        const genreList = document.querySelector('.genre-list');

                        data.forEach(genre => {
                            const genreItem = document.createElement('div');
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
                                            const dynamicContent = document.getElementById('dynamicContent');
                                            dynamicContent.innerHTML = html; // Incrusta el nuevo HTML

                                            // Vuelve a asociar el evento de clic a cada elemento de álbum
                                            const albumItems = dynamicContent.querySelectorAll('.album-item');
                                            albumItems.forEach(item => {

                                                // Obtener el nombre del álbum y del artista directamente del DOM
                                                const albumName = item.querySelector('.album-name').textContent.trim();
                                                const artistName = item.querySelector('.album-artist').textContent.trim();
                                                console.log('Género seleccionado:', genre);
                                                // Verificar que se obtienen correctamente los nombres
                                                console.log(`Album: `, albumName);

                                                item.onclick = () => {
                                                    const encodedAlbumName = encodeURIComponent(albumName);
                                                    const encodedArtistName = encodeURIComponent(artistName);
                                                    const servletUrl = "SvObtenerTemas?album=" + encodedAlbumName + "&artista=" + encodedArtistName;
                                                    // Log de la URL generada
                                                    console.log(servletUrl);

                                                    const iframe = document.getElementById('dynamicIframe');
                                                    iframe.src = servletUrl; // Establece la URL del iframe
                                                };
                                            });
                                        })
                                        .catch(error => console.error('Error al obtener álbumes:', error));
                            });

                            genreList.appendChild(genreItem);

                        });
                    })
                    .catch(error => console.error('Error al obtener géneros:', error));
        });


        btnArtistas.addEventListener('click', () => {
            setActiveButton(btnArtistas);

            // Realizar la solicitud AJAX para obtener los artistas
            fetch('SvObtenerArtistas') // Asegúrate de que esta ruta sea correcta
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Error al obtener artistas: ' + response.statusText);
                        }
                        return response.json(); // Parsear la respuesta como JSON
                    })
                    .then(data => {
                        // Limpiar el contenido dinámico y crear una nueva lista de artistas
                        dynamicContent.innerHTML = '<div class="artist-list"></div>';
                        const artistList = document.querySelector('.artist-list');

                        // Recorrer los artistas obtenidos y agregarlos a la lista
                        data.forEach(artist => {
                            const artistItem = document.createElement('div');
                            artistItem.className = 'artist-item';
                            artistItem.textContent = artist;

                            // Agregar el artista a la lista
                            artistList.appendChild(artistItem);

                            // Agregar el evento de clic para obtener álbumes del artista
                            artistItem.addEventListener('click', () => {
                                console.log('Artista seleccionado:', artist);
                                const variableUrl = encodeURIComponent(artist);
                                const url = "SvObtenerAlbumxArtista?artista=" + variableUrl;

                                // Realiza la solicitud AJAX al servlet para obtener álbumes del artista
                                fetch(url)
                                        .then(response => {
                                            if (!response.ok) {
                                                throw new Error('Error al obtener álbumes: ' + response.statusText);
                                            }
                                            return response.text(); // Cambia a .text() para obtener el HTML
                                        })
                                        .then(html => {
                                            const dynamicContent = document.getElementById('dynamicContent');
                                            dynamicContent.innerHTML = html; // Incrusta el nuevo HTML

                                            // Vuelve a asociar el evento de clic a cada elemento de álbum
                                            const albumItems = dynamicContent.querySelectorAll('.album-item');
                                            albumItems.forEach(item => {
                                                const albumName = item.querySelector('.album-name').textContent.trim();
                                                const artistName = item.querySelector('.album-artist').textContent.trim();
                                                console.log(`Album: `, albumName);

                                                item.onclick = () => {
                                                    const encodedAlbumName = encodeURIComponent(albumName);
                                                    const encodedArtistName = encodeURIComponent(artistName);
                                                    const servletUrl = "SvObtenerTemas?album=" + encodedAlbumName + "&artista=" + encodedArtistName;
                                                    console.log(servletUrl);

                                                    const iframe = document.getElementById('dynamicIframe');
                                                    iframe.src = servletUrl; // Establece la URL del iframe
                                                };
                                            });
                                        })
                                        .catch(error => console.error('Error al obtener álbumes:', error));
                            });
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
        

    document.getElementById('btnAgregarLista').addEventListener('click', function() {
        // Redirigir a la página AgregarListaRep.jsp
        window.location.href = 'AgregarListaRep.jsp';
    });
document.getElementById('btnAgregarSubscripcion').addEventListener('click', function() {
        // Redirigir a la página AgregarListaRep.jsp
        window.location.href = 'ContratarSubscripcion.jsp';
    });
    document.getElementById('btnActualizarSubscripcion').addEventListener('click', function() {
        // Redirigir a la página AgregarListaRep.jsp
        window.location.href = 'SvActualizarSubscripcion';
    });
    <% if (dtUsuario instanceof DTArtista) { %>
    document.getElementById('btnAltaAlbum').addEventListener('click', function() {
        // Redirigir a la página altaAlbum .jsp
        window.location.href = 'altaAlbum.jsp';
    });
    <% }%>

    </script>

</body>
<footer>
    <iframe src="" id="dynamicIframe" width="100%" height="400px" frameborder="0" scrolling="auto"></iframe>

</footer>