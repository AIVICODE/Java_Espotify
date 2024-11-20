<%@page import="webservices.DtCliente"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtTema"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/temas.css?v=1.2">

<%
    String album = (String) request.getAttribute("album");
    String artista = (String) request.getAttribute("artista");
    List<DtTema> temas = (List<DtTema>) request.getAttribute("temas");
    byte[][] temasBytes = (byte[][]) request.getAttribute("temasBytes");
%>

<h2>Temas de <%= album %></h2>
<ul>
    <% for (int i = 0; i < temas.size(); i++) { 
        DtTema tema = temas.get(i);
        String temaDataUrl = "data:audio/mpeg;base64," + java.util.Base64.getEncoder().encodeToString(temasBytes[i]);
    %>
    <% session = request.getSession(false); 
        DtUsuario dtUsuario = (DtUsuario) session.getAttribute("usuario"); 
    %>
        <li style="display: flex; align-items: center; justify-content: space-between;" 
            onclick='seleccionarTema("<%= tema.getNombre() %>", "<%= tema.getDirectorio() %>", "<%= artista %>",<%= tema.getOrden() %>)'>
            
            <% if (dtUsuario != null) { %>
                <button class="add-favorite-tema" 
                        onclick="event.stopPropagation(); verificarYAgregarFavorito('<%= tema.getNombre() %>', '<%= album %>', '<%= artista %>')"
                        style="margin-right: 10px;">+</button>
            <% } %>

            <span class="orden"><%= tema.getOrden() %></span>
            <span><%= tema.getNombre() %></span>
            <span class="duracion"><%= tema.getMinutos() + ":" + tema.getSegundos() %></span>
        </li>
    <% } %>
</ul>


<footer>
    <div class="container player-container">
        <div class="track-info">
            <div class="track-details">
                <p id="currentSongName"></p>
                <p class="artist" id="currentArtistName"><%= artista %></p>
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

            <a id="downloadLink" href="" download style="display: inline-flex; align-items: center; justify-content: center; text-decoration: none;">
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32" fill="#1DB954">
    <path d="M12 16.5l4.5-4.5H13V3h-2v9H7.5l4.5 4.5zm-8 2h16v2H4v-2z"/>
  </svg>
</a>


    </div>
</footer>

<script>
// Lista de temas con datos del servidor
let temas = [];
<% for (int i = 0; i < temas.size(); i++) { 
    DtTema tema = temas.get(i);
    String temaDataUrl = "data:audio/mpeg;base64," + java.util.Base64.getEncoder().encodeToString(temasBytes[i]);
%>
    temas.push({
        nombre: "<%= tema.getNombre() %>",
        directorio: "<%= temaDataUrl %>",
        orden: <%= tema.getOrden() %>
    });
<% } %>

let currentIndex = -1;
let audioPlayer = document.getElementById("audioPlayer");
let audioSource = document.getElementById("audioSource");

function seleccionarTema(nombreTema, directorio, artista, orden) {
    document.getElementById("currentSongName").textContent = nombreTema;
    document.getElementById("currentArtistName").textContent = artista;

    // Si `directorio` es una URL, redirige y termina la función
    if (directorio.startsWith("bit.ly") || directorio.startsWith("http")) {
        
        AumentarReproducciones(nombreTema, "<%= album %>", artista);
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
            AumentarReproducciones(nombreTema, "<%= album %>", artista);
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
                // Si el tipo es correcto, proceder a descargar
                const fileName = downloadLink.download; // Obtener el nombre del archivo desde el atributo download

                // Crear un nuevo elemento de anclaje para forzar la descarga
                const a = document.createElement('a');
                a.href = downloadLink.href; // Usar el href original
                a.download = fileName+ '.mp3'; // Asegúrate de que el nombre del archivo se mantenga
                document.body.appendChild(a); // Agregar al DOM
                a.click(); // Simular clic para iniciar descarga
                document.body.removeChild(a); // Eliminar el elemento del DOM
                
                AumentarDescargas(fileName, "<%= album %>", "<%= artista %>");
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
    
    function verificarYAgregarFavorito(temaName, albumName, artistName) {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                AgregarTemaFavorito(temaName, albumName, artistName);
            } else {
                alert('No tienes una suscripción activa para agregar este tema a favoritos.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}
</script>