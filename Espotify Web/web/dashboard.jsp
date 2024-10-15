<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Datatypes.DTUsuario"%>
<%
    session = request.getSession(false);
    DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
    if (dtUsuario == null) {
        response.sendRedirect("index.jsp");    
        return;
    }
%>
<jsp:include page="header.jsp" /> <!-- haciendo este include en el principio del jsp es que traes el archivo header -->
    <!-- Main Content -->
    <div class="main-content container">
        <!-- Sidebar -->
        <div class="sidebar">
            <button id="btnGeneros">Generos</button>
            <button id="btnArtistas">Artistas</button>
        </div>

        <!-- Dynamic Content -->
        <div id="dynamicContent">
            <iframe src="" width="50%" height="100px" frameborder="0" scrolling="auto" sandbox="allow-same-origin allow-scripts">></iframe>
        </div>
            <iframe src="" id="dynamicIframe" width="100%" height="400px" frameborder="0" scrolling="auto"></iframe>

        
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
        const dynamicContent = document.getElementById('dynamicContent');
        const playPauseBtn = document.getElementById('playPauseBtn');
        const volumeSlider = document.getElementById('volumeSlider');

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
        console.log(`Album: `,albumName);

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

     
    </script>

</body>
</html>
