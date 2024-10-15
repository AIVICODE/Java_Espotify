<%@page import="Datatypes.DTTema"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/temas.css?v=1.2">
<% 
    String album = (String) request.getAttribute("album");
    String artista = (String) request.getAttribute("artista");
    List<DTTema> temas = (List<DTTema>) request.getAttribute("temas");
%>

<h2>Temas de <%= album %></h2>
<ul>
    <% for (DTTema tema : temas) { %>
        <li onclick='seleccionarTema("<%= tema.getNombre() %>", "<%= tema.getDirectorio() %>", <%= tema.getOrden() %>)'>
            <span class="orden"><%= tema.getOrden() %></span>
            <span ><%= tema.getNombre()%></span>
            <span class="duracion"><%= tema.getMinutos()+":"+tema.getSegundos()%></span>
            
            
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
            <a id="downloadLink" href="" download>Descargar</a>

            
    </div>
</footer>

<script>
let temas = []; // Se llenará con los datos del servidor
<% for (DTTema tema : temas) { %>
  temas.push({
    nombre: "<%= tema.getNombre() %>",
    directorio: "<%= tema.getDirectorio() %>",
    orden: <%= tema.getOrden() %>
  });
<% } %>

let currentIndex = -1;

function seleccionarTema(nombreTema, directorio, orden) {
  document.getElementById("currentSongName").textContent = nombreTema;

  // Manejo de bit.ly (Mantengo este fragmento ya que lo tienes en tu código original)
  if (directorio.startsWith("bit.ly")) {
    nextTema();
    return;
  }

  var audioSource = document.getElementById("audioSource");
  audioSource.src = directorio;
  var audioPlayer = document.getElementById("audioPlayer");
  audioPlayer.load();
  audioPlayer.play();
  
  //LA OPCION DE DESCARGA SOLO DISONIBLE PARA USUARIOS REGISTRADOS (PRECISO SESION PARA PODER CONFIGURAR)
  let downloadLink = document.getElementById("downloadLink");
    downloadLink.href = directorio; // URL del archivo
    downloadLink.download = nombreTema; // Nombre del archivo al descargar
  

  // Encuentra el índice real en el array 'temas' basado en el 'orden'
  currentIndex = temas.findIndex(tema => tema.orden === orden); 
}


function prevTema() {
  if (temas.length === 0) return;

  currentIndex = (currentIndex - 1 + temas.length) % temas.length; // Ajusta el índice
  playTemaActual();
}

function nextTema() {
  if (temas.length === 0) return;

  currentIndex = (currentIndex + 1) % temas.length; // Ajusta el índice
  playTemaActual();
  
}

function playTemaActual() {
  if (currentIndex >= 0 && currentIndex < temas.length) {
    let temaActual = temas[currentIndex];
    seleccionarTema(temaActual.nombre, temaActual.directorio, temaActual.orden);
  }
}



// Función de reproducción/pausa
playPauseBtn.addEventListener("click", function() {
    if (audioPlayer.paused) {
        audioPlayer.play();
    } else {
        audioPlayer.pause();
    }
    updatePlayPauseButton();
});

// Función para actualizar el ícono de play/pausa
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

// Función para cambiar el volumen
volumeSlider.addEventListener("input", function() {
    audioPlayer.volume = this.value / 100;
    
});
</script>
