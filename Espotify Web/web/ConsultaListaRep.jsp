<%@page import="webservices.DtCliente"%>
<%@page import="webservices.DtListaRep"%>
<%@page import="webservices.DtTema"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<%@page import="webservices.DtUsuario"%>
<%
                // Mostrar los temas de la lista seleccionada si están disponibles
                List<DtTema> temas = (List<DtTema>) request.getAttribute("temas");
                DtListaRep listaSeleccionada = (DtListaRep) request.getAttribute("listaSeleccionada");

           
            %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Seleccionar Género</title>
        <link rel="stylesheet" href="css/ConsultaListaRep.css?v=1.7">
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
            DtCliente dtUsuario = (DtCliente) session.getAttribute("usuario");
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

<input type="hidden" id="nombreListaRep" value="<%= listaSeleccionada != null ? listaSeleccionada.getNombreListaRep() : "" %>">

    <body class="main-body">
        <%
            // Si `temaBytes` no es nulo, construye el `temaDataUrl` en base64.
            byte[] temaBytes = (byte[]) request.getAttribute("temaBytes");
            String temaDataUrl = temaBytes != null
                    ? "data:audio/mpeg;base64," + java.util.Base64.getEncoder().encodeToString(temaBytes)
                    : "";
        %>
 
<div class="content-container">
        <% if (listaSeleccionada == null || !"Sugerencias".equals(listaSeleccionada.getNombreListaRep())) { %>
    <h1>Selecciona un Género:</h1>
    <form action="SvObtenerListasDeRep" method="GET">
        <select name="genero" id="genero" required>
            <!-- Las opciones se llenarán dinámicamente con JavaScript -->
        </select>
        <button type="submit">Ver Listas de Reproducción</button>
    </form>
<% } %>

        <div id="playlist-container">
            <!-- Aquí se llenarán las listas de reproducción cuando se seleccione un género -->
            <%
                // Mostrar listas de reproducción por género
                List<DtListaRep> listasRep = (List<DtListaRep>) request.getAttribute("listasdeRep");

                if (listasRep != null && !listasRep.isEmpty()) {
            %>
            <h2>Listas de Reproducción para el Género Seleccionado</h2>
            <ul>
                <%
                    for (DtListaRep lista : listasRep) {
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
                List<DtListaRep> listasParticulares = (List<DtListaRep>) request.getAttribute("listasdeRepparticular");

                if (listasParticulares != null && !listasParticulares.isEmpty()) {
            %>
            <h2>Listas Particulares</h2>
            <ul>
                <%
                    for (DtListaRep lista : listasParticulares) {
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

  <div id="temas-container" style="position: relative;"> <!-- Añadido position: relative para el contenedor -->
    <% 
        if (temas != null && !temas.isEmpty() && listaSeleccionada != null) {
    %>
    <h2><%= listaSeleccionada.getNombreListaRep()%></h2>
    <ul>
    <%
        int contador = 0;
        for (DtTema tema : temas) {
    %>
    <li>
        <a href="javascript:void(0);" class="tema-enlace" 
           onclick="seleccionarTema('<%= tema.getNombre() %>', '<%= tema.getDirectorio() %>', '<%= tema.getNombreartista() %>', <%= contador %>,'<%= tema.getNombrealbum() %>');">
            <% if (dtUsuario != null) { %>
                <button class="add-favorite-tema" 
                        onclick="event.stopPropagation(); verificarYAgregarTemaFavorito('<%= tema.getNombre() %>', '<%= tema.getNombrealbum() %>', '<%= tema.getNombreartista() %>')">+</button>
            <% } %>
            <span><%= tema.getNombre() %></span> 
            <span class="duracion"><%= tema.getMinutos() + ":" + tema.getSegundos() %></span>
        </a>
        <!-- Nuevo botón para abrir el pop-up -->
<button class="info-button" onclick="abrirPopup('<%= tema.getNombre() %>', '<%= tema.getNombreartista() %>', '<%= tema.getNombrealbum() %>');">
    <i class="fas fa-chevron-down"></i>
</button>    </li>
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

<div id="popupModal" style="display:none; position:absolute; background-color: #404040; color: white; padding: 15px; border-radius: 8px; box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.5); width: 260px; font-family: Arial, sans-serif;">
 
    <p id="popupDownloads" style="margin: 8px 0; font-size: 14px;"></p>
    <p id="popupPlays" style="margin: 8px 0; font-size: 14px;"></p>
    <p id="popupFavorites" style="margin: 8px 0; font-size: 14px;"></p>
    <p id="popupLists" style="margin: 8px 0; font-size: 14px;"></p>
</div>



<!-- JavaScript para manejar el pop-up -->
<script>
function abrirPopup(nombreTema, nombreArtista, nombreAlbum) {
    // Obtener el botón que activó el popup
    var boton = event.currentTarget;
    
    // Crear la solicitud AJAX
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "SvObtenerInfoTema?nombreTema=" + encodeURIComponent(nombreTema) + 
                      "&nombreArtista=" + encodeURIComponent(nombreArtista) + 
                      "&nombreAlbum=" + encodeURIComponent(nombreAlbum), true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            try {
                var data = JSON.parse(xhr.responseText);


                document.getElementById("popupDownloads").textContent = "Descargas: " + (data.cantidadDescargas || 0);
                document.getElementById("popupPlays").textContent = "Reproducciones: " + (data.cantidadReproducciones || 0);
                document.getElementById("popupFavorites").textContent = "Favoritos: " + (data.cantidadFavoritos || 0);
                document.getElementById("popupLists").textContent = "Listas: " + (data.cantidadListas || 0);

                // Posicionar el modal junto al botón
                var botonRect = boton.getBoundingClientRect();
                var modal = document.getElementById("popupModal");
                modal.style.left = (botonRect.left + 30) + "px";
                modal.style.top = (botonRect.top + 30 + window.scrollY) + "px";

                // Mostrar el modal
                modal.style.display = "block";
            } catch (e) {
                console.error("Error al parsear la respuesta JSON:", e);
            }
        }
    };

    xhr.onerror = function() {
        console.error("Error de red al realizar la solicitud AJAX.");
    };

    xhr.send();
}

// Para ocultar el popup cuando se hace clic fuera de él
window.onclick = function(event) {
    var modal = document.getElementById("popupModal");
    if (event.target !== modal && !modal.contains(event.target)) {
        modal.style.display = "none";
    }
};




function cerrarPopup() {
    // Ocultar el modal
    document.getElementById("popupModal").style.display = "none";
}
</script>

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
                    <p class="artist" id="currentAlbumName" ></p>
                    
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
            for (DtTema tema : temas) {
        %>
        temas.push({
            nombre: "<%= tema.getNombre()%>",
            directorio: "<%= tema.getDirectorio()%>",
            artista: "<%= tema.getNombreartista() %>",
            orden: <%= contador%>,
            album: "<%= tema.getNombrealbum() %>"// Asignamos un orden secuencial basado en el índice del for
        });
        <%
            contador++; // Incrementamos el contador después de cada iteración
        }
    } else {
        %>
        console.log("No hay temas disponibles.");
        <% }%>
      
        let currentIndex = -1;

function seleccionarTema(nombreTema, directorio, artista, orden,album) {
    document.getElementById("currentSongName").textContent = nombreTema;
    document.getElementById("currentArtistName").textContent = artista;
    document.getElementById("currentAlbumName").textContent = album;

    // Si `directorio` es una URL, redirige y termina la función
    if (directorio.startsWith("bit.ly") || directorio.startsWith("http")) {
        AumentarReproducciones(nombreTema, album, artista);
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
AumentarReproducciones(nombreTema, album, artista);
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
 const downloadLink = event.target;

    // Verifica la suscripción del usuario primero
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, realizar la solicitud para obtener el archivo
                return fetch(downloadLink.href, { method: 'GET', headers: { 'Content-Type': 'application/json' } });
            } else {
                throw new Error('No tienes una suscripción activa para descargar este archivo.');
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al intentar descargar el archivo.');
            }
            // Verifica el tipo de contenido
            const contentType = response.headers.get('Content-Type');
            if (contentType === 'audio/mpeg') {
                const temaActual = temas[currentIndex]; // Obtén el tema actual
                            AumentarDescargas(temaActual.nombre, temaActual.album, temaActual.artista);
                // Si el tipo es correcto, proceder a descargar
                const fileName = downloadLink.download; // Obtener el nombre del archivo desde el atributo download

                // Crear un nuevo elemento de anclaje para forzar la descarga
                const a = document.createElement('a');
                a.href = downloadLink.href; // Usar el href original
                a.download = fileName+ '.mp3'; // Asegúrate de que el nombre del archivo se mantenga
                document.body.appendChild(a); // Agregar al DOM
                a.click(); // Simular clic para iniciar descarga
                document.body.removeChild(a); // Eliminar el elemento del DOM
                
                
            } else {
                alert('El archivo que intentas descargar no es un archivo de audio válido.');
            }
        })
        .catch(error => {
            alert('Error: ' + error.message);
        });
    });
    
    
    function AumentarReproducciones(temaName, albumName, artistName) {
    const encodedTemaName = encodeURIComponent(temaName);
    const encodedAlbumName = encodeURIComponent(albumName);
    const encodedArtistName = encodeURIComponent(artistName);
    
    fetch('SvActualizarConteo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: "temaName=" + encodedTemaName + "&albumName=" + encodedAlbumName + "&artistName=" + encodedArtistName +"&tipo=reproduccion"
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
        }
    })
    .catch(error => {
        alert("Error: " + error.message); // Mostrar el mensaje de error
    });
    
}

function AumentarDescargas(temaName, albumName, artistName) {
    const encodedTemaName = encodeURIComponent(temaName);
    const encodedAlbumName = encodeURIComponent(albumName);
    const encodedArtistName = encodeURIComponent(artistName);
    
    fetch('SvActualizarConteo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: "temaName=" + encodedTemaName + "&albumName=" + encodedAlbumName + "&artistName=" + encodedArtistName +"&tipo=descarga"
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
        }
    })
    .catch(error => {
        alert("Error: " + error.message); // Mostrar el mensaje de error
    });
}
    
    
        
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
