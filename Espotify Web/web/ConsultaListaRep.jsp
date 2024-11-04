<%@page import="Datatypes.DTCliente"%>
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
        <link rel="stylesheet" href="css/ConsultaListaRep.css?v=1.5">
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
            DTCliente dtUsuario = (DTCliente) session.getAttribute("usuario");
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
        <%
            // Si `temaBytes` no es nulo, construye el `temaDataUrl` en base64.
            byte[] temaBytes = (byte[]) request.getAttribute("temaBytes");
            String temaDataUrl = temaBytes != null
                    ? "data:audio/mpeg;base64," + java.util.Base64.getEncoder().encodeToString(temaBytes)
                    : "";
        %>
 
<div class="content-container">
        <h1>Selecciona un Género:</h1>

        <form action="SvObtenerListasDeRep" method="GET"> <!-- Se envía al servlet SvObtenerListasDeRep -->
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
                    
                    <div style="display: flex; align-items: center;">
                        
                     <%
    session = request.getSession(false);
    if (dtUsuario != null) {
    %>
<button class="add-favorite" onclick="verificarYAgregarListaDefFavorito('<%= lista.getNombreListaRep() %>')">+</button>
 <%
        
    }
    
%>
                <div style="width: 150px; height: 150px; border: 1px solid black;">
<%
                        byte[] imagenAlbum = (byte[]) lista.getImagenBytes();
                        if (imagenAlbum != null) {
                            String base64Image = java.util.Base64.getEncoder().encodeToString(imagenAlbum);
                    %>
                <img src="data:image/jpeg;base64,<%= base64Image %>" style="width: 100%; height: 100%; object-fit: cover;">
                    <%
                        }
                    %>                    
</div> 
    
                    <form action="SvSeleccionarLista" method="GET" class="playlist-form">
                        <input type="hidden" name="nombreLista" value="<%= lista.getNombreListaRep()%>">
                        <button type="submit"><%= lista.getNombreListaRep()%></button>
                    </form>
                      </div>
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
              <div style="display: flex; align-items: center;">
        <!-- Botón de agregar a favoritos -->
          <%
    session = request.getSession(false);
    if (dtUsuario != null) {
    %>
        <button class="add-favorite" style="margin-right: 10px;" 
                onclick="verificarYAgregarListaPartFavorito('<%= lista.getNombreListaRep() %>', '<%= lista.getNombreCliente() %>')">+</button>
        <%
        
    }
    
%>
       <div style="width: 150px; height: 150px; border: 1px solid black; margin-right: 10px;">
    <%
        
                        byte[] imagenAlbum = (byte[]) lista.getImagenBytes();
                        if (imagenAlbum != null) {
                            String base64Image = java.util.Base64.getEncoder().encodeToString(imagenAlbum);
                    %>
                <img src="data:image/jpeg;base64,<%= base64Image %>" style="width: 100%; height: 100%; object-fit: cover;">
                    <%
                        }
                    %>     
</div>
        
        <!-- Formulario para seleccionar lista particular -->
        <form action="SvSeleccionarLista" method="GET" class="playlist-form" style="display: inline;">
            <input type="hidden" name="nombreLista" value="<%= lista.getNombreListaRep() %>">
            <input type="hidden" name="nombreCliente" value="<%= lista.getNombreCliente() %>">
            
            <!-- Botón de submit para seleccionar la lista -->
            <button type="submit" class="playlist-button"><%= lista.getNombreListaRep() %> (por <%= lista.getNombreCliente() %>)</button>
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
        <a href="javascript:void(0);" class="tema-enlace" 
           onclick="seleccionarTema('<%= tema.getNombre() %>', '<%= tema.getDirectorio() %>', '<%= tema.getNombreartista() %>', <%= contador %>);">
            <% if (dtUsuario != null) { %>
                <button class="add-favorite-tema" 
                        onclick="event.stopPropagation(); verificarYAgregarTemaFavorito('<%= tema.getNombre() %>', '<%= tema.getNombrealbum() %>', '<%= tema.getNombreartista() %>')">+</button>
            <% } %>
            <span><%= tema.getNombre() %></span> 
            <span class="duracion"><%= tema.getMinutos() + ":" + tema.getSegundos() %></span>
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
            </audio>
                        <%
            session = request.getSession(false);
            if (dtUsuario != null) {
        %>
            <a id="downloadLink" href="" download >Descargar</a>
            <%
            }
        %>
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
            artista: "<%= tema.getNombreartista() %>",
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

function seleccionarTema(nombreTema, directorio, artista, orden) {
    document.getElementById("currentSongName").textContent = nombreTema;
    document.getElementById("currentArtistName").textContent = artista;

    // Si `directorio` es una URL, redirige y termina la función
    if (directorio.startsWith("bit.ly") || directorio.startsWith("http")) {
        window.open(directorio.startsWith("http") ? directorio : "https://" + directorio, '_blank');
        return;
    }

    // Si no es una URL, realiza una solicitud al servlet `SvGetTema` para obtener el archivo en bytes
    fetch("SvGetTema?rutaTema="+ encodeURIComponent(directorio))
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el tema');
            }
            return response.arrayBuffer(); // Recibe el audio en formato ArrayBuffer
        })
        .then(arrayBuffer => {
            // Convierte el ArrayBuffer a un Blob y establece la fuente del reproductor
            const audioBlob = new Blob([arrayBuffer], { type: "audio/mpeg" });
            const audioUrl = URL.createObjectURL(audioBlob);
            var audioSource = document.getElementById("audioSource");
            audioSource.src = audioUrl;
            var audioPlayer = document.getElementById("audioPlayer");
            audioPlayer.load();
            audioPlayer.play();

            // Configuración para la descarga del tema
            let downloadLink = document.getElementById("downloadLink");
            downloadLink.href = audioUrl;
            downloadLink.download = nombreTema;
        })
        .catch(error => {
            console.error("Error al cargar el tema:", error);
        });

    // Actualizar índice actual
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
                seleccionarTema(temaActual.nombre, temaActual.directorio,temaActual.artista, temaActual.orden);
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
        
        
     // Lógica de descarga y verificación de suscripción
    document.getElementById('downloadLink').addEventListener('click', function(event) {
        event.preventDefault(); // Prevenir la acción predeterminada de descarga

        // Realiza la verificación de suscripción
        fetch('SvVerificarSubscripcion', { method: 'GET' })
            .then(response => response.json())
            .then(data => {
                if (data.hasSubscription) {
                    // Si tiene suscripción, permitir la descarga
                    const downloadLink = event.target;

                    // Aquí estamos haciendo que el enlace descargue el archivo
                    const href = downloadLink.href; 
                    
                    // Crear un nuevo elemento de anclaje para forzar la descarga
                    const a = document.createElement('a');
                    a.href = href;
                    a.download = downloadLink.download; // Asegúrate de que el nombre del archivo se mantenga
                    document.body.appendChild(a); // Agregar al DOM
                    a.click(); // Simular clic para iniciar descarga
                    document.body.removeChild(a); // Eliminar el elemento del DOM
                } else {
                    alert('No tienes una suscripción activa para descargar este archivo.');
                }
            })
            .catch(error => {
                console.error('Error al verificar la suscripción:', error);
            });
    });
        
      function AgregarListaPartFavorito(listaName, clienteName) {
    const encodedListaName = encodeURIComponent(listaName);
    const encodedClienteName = encodeURIComponent(clienteName);

    fetch('SvAgregarListaParticularFavorito', {
        
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: "lista=" + encodedListaName + "&cliente=" + encodedClienteName
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(error => {
                throw new Error(error.message || 'Error al agregar la lista a favoritos');
            });
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
            alert(data.message); // Notificación de éxito
        } else {
            throw new Error(data.message); // Si no es exitoso, lanza el error
        }
    })
    .catch(error => {
        alert('Error: ' + error.message); // Notificación de error
        console.error('Error al agregar la lista:', error);
    });
}





      function AgregarListaDefFavorito(listaName) {
    const encodedListaName = encodeURIComponent(listaName);

    fetch('SvAgregarListaPorDefectoFavorito', {
        
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: "lista=" + encodedListaName
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(error => {
                throw new Error(error.message || 'Error al agregar la lista a favoritos');
            });
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
            alert(data.message); // Notificación de éxito
        } else {
            throw new Error(data.message); // Si no es exitoso, lanza el error
        }
    })
    .catch(error => {
        alert('Error: ' + error.message); // Notificación de error
        console.error('Error al agregar la lista:', error);
    });
}

 function AgregarTemaFavorito(temaName, albumName, artistName) {
    const encodedTemaName = encodeURIComponent(temaName);
    const encodedAlbumName = encodeURIComponent(albumName);
    const encodedArtistName = encodeURIComponent(artistName);

    fetch('SvAgregarTemaFavorito', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: "tema=" + encodedTemaName + "&album=" + encodedAlbumName + "&artista=" + encodedArtistName
    })
    .then(response => {
        // Capturamos el error si el servidor respondió con un estado HTTP no exitoso
        if (!response.ok) {
            return response.json().then(errorData => { throw new Error(errorData.message); });
        }
        return response.json(); // Parseamos la respuesta JSON
    })
    .then(data => {
        if (data.success) {
            alert(data.message); // Mostrar mensaje de éxito
        }
    })
    .catch(error => {
        alert("Error: " + error.message); // Mostrar el mensaje de error
    });
}



function verificarYAgregarListaPartFavorito(listaName, clienteName) {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                AgregarListaPartFavorito(listaName, clienteName);
            } else {
                alert('No tienes una suscripción activa para agregar esta lista a favoritos.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}

function verificarYAgregarTemaFavorito(temaName, albumName, artistName) {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                AgregarTemaFavorito(temaName, albumName, artistName);
            } else {
                alert('No tienes una suscripción activa para agregar esta tema a favoritos.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}
function verificarYAgregarListaDefFavorito(listaName) {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                AgregarListaDefFavorito(listaName);
            } else {
                alert('No tienes una suscripción activa para agregar esta lista a favoritos.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}

    </script>

    
    
    
    
</html>
