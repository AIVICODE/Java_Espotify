<%-- 
    Document   : index
    Created on : Oct 1, 2024, 12:19:34 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espotify - Dashboard</title>
    <style>
        /* Reset and base styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #18181b;
            color: white;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* Navbar styles */
        .navbar {
            background-color: #22c55e;
            padding: 1rem;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            display: flex;
            align-items: center;
            font-size: 1.5rem;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        .logo svg {
            height: 2rem;
            width: 2rem;
            margin-right: 0.5rem;
        }

        .search-container {
            flex: 1;
            margin: 0 1rem;
            position: relative;
        }

        .search-input {
            width: 100%;
            padding: 0.5rem 1rem 0.5rem 2.5rem;
            border-radius: 9999px;
            border: none;
            outline: none;
        }

        .search-icon {
            position: absolute;
            left: 0.75rem;
            top: 50%;
            transform: translateY(-50%);
            color: #6b7280;
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

        /* Main content styles */
        .main-content {
            display: flex;
            flex: 1;
            margin-top: 2rem;
        }

        .sidebar {
            width: 16rem;
            background-color: #27272a;
            padding: 1rem;
            border-radius: 0.5rem;
            margin-right: 2rem;
        }

        .sidebar button {
            width: 100%;
            text-align: left;
            padding: 0.5rem 1rem;
            margin-bottom: 0.5rem;
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 0.25rem;
        }

        .sidebar button:hover {
            background-color: #3f3f46;
        }

        .sidebar button.active {
            background-color: #3f3f46;
        }

        .content {
            flex: 1;
            background-color: #27272a;
            padding: 1.5rem;
            border-radius: 0.5rem;
        }

        .content h3 {
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 1rem;
        }

        /* Footer (Player) styles */
        .footer {
            background-color: #1a1a1a;
            padding: 1rem;
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
        }

        .player-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .track-info {
            display: flex;
            align-items: center;
        }

        .album-cover {
            width: 2.5rem;
            height: 2.5rem;
            margin-right: 1rem;
        }

        .track-details p {
            margin: 0;
        }

        .track-details .artist {
            font-size: 0.875rem;
            color: #9ca3af;
        }

        .player-controls {
            display: flex;
            align-items: center;
        }

        .player-controls button {
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            margin: 0 0.5rem;
        }

        .player-controls button:hover {
            color: #22c55e;
        }

        .volume-control {
            display: flex;
            align-items: center;
        }

        .volume-slider {
            -webkit-appearance: none;
            width: 100px;
            height: 4px;
            background: #4a4a4a;
            outline: none;
            opacity: 0.7;
            transition: opacity .2s;
            margin-left: 0.5rem;
        }

        .volume-slider:hover {
            opacity: 1;
        }

        .volume-slider::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 12px;
            height: 12px;
            background: #22c55e;
            cursor: pointer;
            border-radius: 50%;
        }

        .volume-slider::-moz-range-thumb {
            width: 12px;
            height: 12px;
            background: #22c55e;
            cursor: pointer;
            border-radius: 50%;
        }
    </style>
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
            <div class="profile-section">
                <img src="https://via.placeholder.com/40" alt="Profile" class="profile-img">
                <div class="profile-info">
                    <p>Nickname</p>
                    <button>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" style="margin-right: 4px;">
                            <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
                        </svg>
                        Favoritos
                    </button>
                    <button>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" style="margin-right: 4px;">
                            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                            <polyline points="16 17 21 12 16 7"></polyline>
                            <line x1="21" y1="12" x2="9" y2="12"></line>
                        </svg>
                        Cerrar sesión
                    </button>
                </div>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-content container">
        <!-- Sidebar -->
        <div class="sidebar">
            <button id="btnGeneros">Géneros</button>
            <button id="btnArtistas">Artistas</button>
        </div>

        <!-- Dynamic Content -->
        <div id="dynamicContent" class="content">
            <h3>Dashboard</h3>
            <p>Contenido dinámico al seleccionar género, artista o favoritos</p>
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
            dynamicContent.innerHTML = '<h3>Géneros</h3><p>Grid con géneros musicales</p>';
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
            // Here you would typically set the audio volume
            console.log('Volume set to:', e.target.value);
        });
    </script>
</body>
</html>
