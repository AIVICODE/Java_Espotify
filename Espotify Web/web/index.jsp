<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espotify - Dashboard</title>
    <link rel="stylesheet" href="css/index.css?v=1.1"> <!-- Asegúrate de que la ruta sea correcta -->
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="container">
            <a href="#" class="logo">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 496 512">
                <path fill="currentColor" d="M248 8C111.1 8 0 119.1 0 256s111.1 248 248 248 248-111.1 248-248S384.9 8 248 8zm100.7 364.9c-4.2 0-6.8-1.3-10.7-3.6-62.4-37.6-135-39.2-206.7-24.5-3.9 1-9 2.6-11.9 2.6-9.7 0-15.8-7.7-15.8-15.8 0-10.3 6.1-15.2 13.6-16.8 81.9-18.1 165.6-16.5 237 26.2 6.1 3.9 9.7 7.4 9.7 16.5s-7.1 15.4-15.2 15.4zm26.9-65.6c-5.2 0-8.7-2.3-12.3-4.2-62.5-37-155.7-51.9-238.6-29.4-4.8 1.3-7.4 2.6-11.9 2.6-10.7 0-19.4-8.7-19.4-19.4s5.2-17.8 15.5-20.7c27.8-7.8 56.2-13.6 97.8-13.6 64.9 0 127.6 16.1 177 45.5 8.1 4.8 11.3 11 11.3 19.7-.1 10.8-8.5 19.5-19.4 19.5zm31-76.2c-5.2 0-8.4-1.3-12.9-3.9-71.2-42.5-198.5-52.7-280.9-29.7-3.6 1-8.1 2.6-12.9 2.6-13.2 0-23.3-10.3-23.3-23.6 0-13.6 8.4-21.3 17.4-23.9 35.2-10.3 74.6-15.2 117.5-15.2 73 0 149.5 15.2 205.4 47.8 7.8 4.5 12.9 10.7 12.9 22.6 0 13.6-11 23.3-23.2 23.3z"/>
                </svg>
                Espotify
            </a>
            <div class="search-container">
                <input type="text" class="search-input" placeholder="Buscar tema, album o lista">
                <svg xmlns="http://www.w3.org/2000/svg" class="search-icon" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="11" cy="11" r="8"></circle>
                <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                </svg>
            </div>
            <div class="auth-links">
                <a href="login.jsp">Iniciar sesión</a>
                <a href="register.jsp">Registrarse</a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-content container">
        <!-- Sidebar -->
        <div class="sidebar">
            <button id="btnGeneros">Generos</button>
            <button id="btnArtistas">Artistas</button>
        </div>

        <!-- Dynamic Content -->
        <div id="dynamicContent">
            <iframe src="" width="100%" height="500px" frameborder="0"></iframe>
        </div>
    </div>

    <!-- Footer (Player) -->
    <footer class="footer">
        <div class="container player-container">
            <div class="track-info">
                <img src="https://via.placeholder.com/40" alt="Album cover" class="album-cover">
                <div class="track-details">
                    <p>Song Name</p>
                    <p class="artist">Artist Name</p>
                </div>
            </div>
            <div class="player-controls">
                <button id="prevBtn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <polygon points="19 20 9 12 19 4 19 20"></polygon>
                    <line x1="5" y1="19" x2="5" y2="5"></line>
                    </svg>
                </button>
                <button id="playPauseBtn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="12" cy="12" r="10"></circle>
                    <polygon points="10 8 16 12 10 16 10 8"></polygon>
                    </svg>
                </button>
                <button id="nextBtn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <polygon points="5 4 15 12 5 20 5 4"></polygon>
                    <line x1="19" y1="5" x2="19" y2="19"></line>
                    </svg>
                </button>
            </div>
            <div class="volume-control">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <polygon points="11 5 6 9 2 9 2 15 6 15 11 19 11 5"></polygon>
                <path d="M19.07 4.93a10 10 0 0 1 0 14.14M15.54 8.46a5 5 0 0 1 0 7.07"></path>
                </svg>
                <input type="range" min="0" max="100" value="50" class="volume-slider" id="volumeSlider">
            </div>
        </div>
    </footer>

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
        iframe.src = url;
        albumItems.forEach(item => {
            const albumId = item.id; // Suponiendo que el ID está presente en el elemento
            item.onclick = () => toggleTemas(albumId); // Asociar la función
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
            dynamicContent.innerHTML = '<h3>Artistas</h3><p>Grid con artistas populares</p>';
        });

        let isPlaying = false;
        playPauseBtn.addEventListener('click', () => {
            isPlaying = !isPlaying;
            if (isPlaying) {
                playPauseBtn.innerHTML = `
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="10" y1="15" x2="10" y2="9"></line>
                    <line x1="14" y1="15" x2="14" y2="9"></line>
                </svg>
            `;
            } else {
                playPauseBtn.innerHTML = `
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="12" cy="12" r="10"></circle>
                    <polygon points="10 8 16 12 10 16 10 8"></polygon>
                </svg>
            `;
            }
        });

        volumeSlider.addEventListener('input', (e) => {
            // Aquí normalmente establecerías el volumen del audio
            console.log('Volume set to:', e.target.value);
        });
    </script>

</body>
</html>
