<%@page import="Datatypes.DTListaRep"%>
<%@page import="Datatypes.DTTema"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@page import="Datatypes.DTUsuario"%>



<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Seleccionar Género</title>
        <link rel="stylesheet" href="css/ConsultaListaRep.css?v=1.8">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Obtener los géneros desde el servlet
                fetch('SvObtenerGeneros')
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error al obtener géneros: ' + response.statusText);
                            }
                            return response.json();
                        })
                        .then(data => {
                            const genreSelect = document.getElementById('genero');
                            genreSelect.innerHTML = ''; // Limpiar contenido previo

                            if (data.length > 0) {
                                data.forEach(genero => {
                                    const option = document.createElement('option');
                                    option.value = genero;
                                    option.textContent = genero;
                                    genreSelect.appendChild(option);
                                });
                            } else {
                                const option = document.createElement('option');
                                option.value = '';
                                option.textContent = 'No hay géneros disponibles';
                                option.disabled = true;
                                genreSelect.appendChild(option);
                            }
                        })
                        .catch(error => {
                            console.error('Error en la solicitud:', error);
                        });
            });
        </script>
    </head>


        <%
            session = request.getSession(false);
            DTUsuario dtUsuario = (DTUsuario) session.getAttribute("usuario");
            if (dtUsuario == null) {
        %>
        <jsp:include page="headerunlogged.jsp" />
        <%
        } else {
        %>
        <jsp:include page="header.jsp" />
        <%
            }
        %>


    <body class="main-body">
<div class="content-container">
        <h1>Selecciona un Género</h1>

        <form action="SvObtenerListasDeRep" method="GET"> <!-- Se envía al servlet SvObtenerListasDeRep -->
            <label for="genero">Género:</label>
            <select name="genero" id="genero" required>
                <!-- Las opciones se llenarán dinámicamente con JavaScript -->
            </select>

            <button type="submit">Ver Listas de Reproducción</button>
        </form>

        <div id="playlist-container">
            <!-- Aquí se llenarán las listas de reproducción cuando se seleccione un género -->
            <%
                // Mostrar listas de reproducción por género
                List<DTListaRep> listasRep = (List<DTListaRep>) request.getAttribute("listasdeRep");

                if (listasRep != null && !listasRep.isEmpty()) {
            %>
            <h2>Listas de Reproducción para el Género Seleccionado</h2>
            <ul>
                <%
                    for (DTListaRep lista : listasRep) {
                %>
                <li>
                    <!-- Formulario para seleccionar lista -->
                    <form action="SvSeleccionarLista" method="GET">
                        <input type="hidden" name="nombreLista" value="<%= lista.getNombreListaRep()%>">
                        <button type="submit"><%= lista.getNombreListaRep()%></button>
                    </form>
                </li>
                <%
                    }
                %>
            </ul>
            <%
            } else if (request.getParameter("genero") != null) { // Si se seleccionó un género pero no hay listas
            %>
            <p>No hay listas de reproducción disponibles para este género.</p>
            <%
                }

                // Mostrar el botón para regresar a las listas particulares, siempre después de intentar cargar un género
                if (request.getParameter("genero") != null) { // Si se ha seleccionado un género
            %>
            <form action="SvObtenerClientes" method="GET">
                <button type="submit">Ver Listas Particulares</button>
            </form>
            <%
                }
            %>

            <%
                // Mostrar listas particulares si están disponibles
                List<DTListaRep> listasParticulares = (List<DTListaRep>) request.getAttribute("listasdeRepparticular");

                if (listasParticulares != null && !listasParticulares.isEmpty()) {
            %>
            <h2>Listas Particulares</h2>
            <ul>
                <%
                    for (DTListaRep lista : listasParticulares) {
                %>
                <li>
                    <!-- Formulario para seleccionar lista particular -->
                    <form action="SvSeleccionarLista" method="GET">
                        <input type="hidden" name="nombreLista" value="<%= lista.getNombreListaRep()%>">
                        <input type="hidden" name="nombreCliente" value="<%= lista.getNombreCliente()%>">
                        <div class="list-content"> <!-- Contenedor para la imagen y texto -->
                            <img src="<%= lista.getImagen()%>" alt="<%= lista.getNombreListaRep()%>" class="list-image">
                            <button type="submit"><%= lista.getNombreListaRep()%> (por <%= lista.getNombreCliente()%>)</button>
                    </form>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
            <%
            } else if (request.getParameter("listasdeRepparticular") != null) {
            %>
            <p>No hay listas particulares disponibles.</p>
            <%
                }
            %>
        </div>

        <div id="temas-container">
            <%
                // Mostrar los temas de la lista seleccionada si están disponibles
                List<DTTema> temas = (List<DTTema>) request.getAttribute("temas");
                DTListaRep listaSeleccionada = (DTListaRep) request.getAttribute("listaSeleccionada");

                if (temas != null && !temas.isEmpty() && listaSeleccionada != null) {
            %>
            <h2>Temas en la Lista: <%= listaSeleccionada.getNombreListaRep()%></h2>
            <ul>
                <%
                    int contador = 0;
                    for (DTTema tema : temas) {
                %>
                <li>
                    <a href="javascript:void(0);" class="tema-enlace" onclick="seleccionarTema('<%= tema.getNombre()%>', '<%= tema.getDirectorio()%>', <%= contador%>);">
                        <span ><%= tema.getNombre()%></span> 
                        <span class="duracion" ><%= tema.getMinutos() + ":" + tema.getSegundos()%></span>

                    </a>
                </li>
                <%
                        contador++;
                    }
                %>
            </ul>
            <%
            } else if (listaSeleccionada != null) {
            %>
            <p>No hay temas disponibles en esta lista.</p>
            <%
                }
            %>
        </div>
        </div>
    </body>

    <%
        // Verificar si hay temas cargados y mostrar el reproductor solo si los hay
        if (temas != null && !temas.isEmpty()) {
    %>
    <footer>
        <div class="container player-container">
            <div class="track-info">
                <div class="track-details">
                    <p id="currentSongName"></p>
                    <p class="artist" id="currentArtistName"></p>
                </div>
            </div>
            <div class="player-controls">
                <button id="prevBtn" onclick="prevTema()">
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
                <button id="nextBtn" onclick="nextTema()">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <polygon points="5 4 15 12 5 20 5 4"></polygon>
                    <line x1="19" y1="5" x2="19" y2="19"></line>
                    </svg>
                </button>
            </div>
            <div class="volume-control">
                <input type="range" min="0" max="100" value="50" class="volume-slider" id="volumeSlider">
            </div>
            <audio id="audioPlayer">
                <source id="audioSource" src="" type="audio/mpeg">
                Your browser does not support the audio element.
            </audio>
            <a id="downloadLink" href="" download>Descargar</a>
        </div>
    </footer>
    <%
        } // fin de la verificación de temas
    %>

    <script>
        let temas = [];
        <%
        if (temas != null && !temas.isEmpty()) {
            int contador = 0; // Variable contador para asignar orden secuencial
            for (DTTema tema : temas) {
        %>
        temas.push({
            nombre: "<%= tema.getNombre()%>",
            directorio: "<%= tema.getDirectorio()%>",
            orden: <%= contador%> // Asignamos un orden secuencial basado en el índice del for
        });
        <%
            contador++; // Incrementamos el contador después de cada iteración
        }
    } else {
        %>
        console.log("No hay temas disponibles.");
        <% }%>

        let currentIndex = -1;

        function seleccionarTema(nombreTema, directorio, orden) {
            document.getElementById("currentSongName").textContent = nombreTema;

            if (directorio.startsWith("bit.ly")) {
                nextTema();
                return;
            }

            var audioSource = document.getElementById("audioSource");
            audioSource.src = directorio;
            var audioPlayer = document.getElementById("audioPlayer");
            audioPlayer.load();
            audioPlayer.play();

            let downloadLink = document.getElementById("downloadLink");
            downloadLink.href = directorio;
            downloadLink.download = nombreTema;

            currentIndex = temas.findIndex(tema => tema.orden === orden);
        }

        function prevTema() {
            if (temas.length === 0)
                return;

            currentIndex = (currentIndex - 1 + temas.length) % temas.length;
            playTemaActual();
        }

        function nextTema() {
            if (temas.length === 0)
                return;

            currentIndex = (currentIndex + 1) % temas.length;
            playTemaActual();
        }

        function playTemaActual() {
            if (currentIndex >= 0 && currentIndex < temas.length) {
                let temaActual = temas[currentIndex];
                seleccionarTema(temaActual.nombre, temaActual.directorio, temaActual.orden);
            }
        }

        playPauseBtn.addEventListener("click", function () {
            if (audioPlayer.paused) {
                audioPlayer.play();
            } else {
                audioPlayer.pause();
            }
            updatePlayPauseButton();
        });

        function updatePlayPauseButton() {
            const playIcon = playPauseBtn.querySelector("svg");
            if (audioPlayer.paused) {
                playIcon.innerHTML = `
                <circle cx="12" cy="12" r="10"></circle>
                <polygon points="10 8 16 12 10 16 10 8"></polygon>`;
            } else {
                playIcon.innerHTML = `
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="10" y1="15" x2="10" y2="9"></line>
                <line x1="14" y1="15" x2="14" y2="9"></line>`;
            }
        }

        volumeSlider.addEventListener("input", function () {
            audioPlayer.volume = this.value / 100;
        });
    </script>

</html>
