<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consulta Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/mostrarAlbumes.css?v=1.2">
        <style>
            body{
                background-color: #191414;
            }
            .boton {
                width: 100%;
                padding: 0.75rem;
                background-color: #22c55e;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
            }
            .boton:hover {
                background-color: #16a34a;
            }       
            /* barra de arriba*/
            .user-section {
                display: flex;
                align-items: center;
            }
            .user-avatar {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 0.5rem;
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
            .user-info {
                display: flex;
                flex-direction: column;
                margin-right: 1rem;
            }
            .user-nickname {
                font-weight: bold;
            }
            .user-favorites {
                display: flex;
                align-items: center;
                font-size: 0.8rem;
                color: #ffff00;
            }
            .star-icon {
                margin-right: 0.25rem;
            }
            .logout-button {
                background-color: transparent;
                border: 1px solid white;
                color: white;
                padding: 0.5rem 1rem;
                border-radius: 2rem;
                cursor: pointer;
                transition: background-color 0.3s, color 0.3s;
            }
            .logout-button:hover {
                background-color: white;
                color: #191414;
            }
            header {
                width: 100%; /* Header ocupa todo el ancho */
                position: fixed; /* Fijarlo en la parte superior */
                top: 0;
                z-index: 1000;
            }
            h1{
                font-family: 'Circular', Arial, sans-serif;
                color: white;
            }
            .formi {
                max-width: 400px;
                margin: 6rem auto; /* Margen de arriba para que aparezca en la pantalla */
                padding: 2rem;
                background-color: #27272a;
                border-radius: 8px;
            }
            label{
                font-family: 'Circular', Arial, sans-serif;
                color: white;
            }
            
            select {
            width: 100%; /* Ancho completo del combobox */
            padding: 10px; /* Espaciado interno del combobox */
            border-radius: 5px; /* Bordes redondeados del combobox */
            border: none; /* Sin borde */
            background-color: #333333; /* Color de fondo del combobox */
            color: white; /* Color del texto en el combobox */
            }
            .mensajeExito {
            color: white; /* Color del texto */
            background-color: #22c55e; /* Fondo verde */
            padding: 10px; /* Espaciado interno */
            border-radius: 8px; /* Bordes redondeados */
            text-align: center; /* Centrar texto */
            margin-top: 15px; /* Espacio arriba */
            margin-bottom: 20px; /* Espacio inferior */
            width: 30%; /* Ancho del rectángulo */
            margin-left: auto; /* Centrar horizontalmente */
            margin-right: auto; /* Centrar horizontalmente */
        }
        
        #userInfo {
                background-color: #27272a;
                border-radius: 8px;
                padding: 20px;
                margin-top: 20px;
                color: white;
            }
        </style>
    </head>
    <jsp:include page="headerunlogged.jsp" />
    <body>
        <div class="container d-flex justify-content-center">
                <div class="spotify-card">
                    <form id="userForm" class="formi"><!-- SERVLET CAMBIAR --> <!-- NUEVO GPT -->
                        <h1 id="borrar">Consulta de Perfil de Usuario</h1>
                        <div class="mb-3">                       
                            <label for="opciones">Seleccione el usuario</label>
                            <select id="listaUsuarios" name="nickUsuario"><!-- IMPORTANTE PARA PROXIMOS FORMULARIOS -->
                                <option value="">...</option>
                            </select>
                        </div>
                    </form>
                    
                    <div id="userInfo" style="display: none;">
                        <h2>Información del Usuario</h2>
                        <p><strong>Nickname:</strong> <span id="nickname"></span></p>
                        
                        <p>
    <img id="imagen" 
         src="" 
         alt="Imagen de Usuario" 
                  style="width: 150px; height: 150px; border-radius: 50%; border: 2px solid #ccc; margin-top: 10px;">

</p>
                        <p id="cor"><strong>Correo electrónico:</strong> <span id="correo"></span></p>
                        <p id="nom"><strong>Nombre:</strong> <span id="nombre"></span></p>
                        <p id="ap"><strong>Apellido:</strong> <span id="apellido"></span></p>
                        <p id="fec"><strong>Fecha de nacimiento:</strong> <span id="fechaNac"></span></p>
                        <p id="bioT"><strong>Biografía:</strong> <span id="bio"></span></p>
                        <p id="webT"><strong>Sitio Web:</strong> <span id="web"></span></p>
                        <div id="seguidoresDiv"><h3>Seguidores:</h3>
                        <ul id="seguidores"></ul>
                        </div>
                        <div> <h3 id="albTexto">Albumes del Artista:</h3> </div>
                        <div id="albumesArtistaDiv"> <h3>Albumes del Artista:</h3>
                        <ul id="albumesArtista"></ul>
                        </div>
                        <div id="listasPublicasDiv"> <h3>Listas Publicas del Cliente:</h3>
                        <ul id="listasPublicas"></ul>
                        </div>
                        <!-- Dynamic Content para albumes-->
                        <div id="dynamicContent"> </div>
                    </div>
                </div>
            
    </div>
        <!-- Bootstrap JS y Font Awesome -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
      
    <script>
        function cargarUsuarios() {
            fetch('SvCargarUsuarios')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error en la red');
                    }
                    return response.json();
                })
                .then(data => {
                    const select = document.getElementById('listaUsuarios');
                    data.forEach(usu => {
                        const option = document.createElement('option');
                        option.value = usu;
                        option.textContent = usu;
                        select.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error('Error al cargar los usuarios:', error);
                    alert('No se pudieron cargar los usuarios.');
                });
        }
        document.getElementById('listaUsuarios').addEventListener('change', cargarDatosUsuario);
        
        function cargarDatosUsuario() {
            const nickname = document.getElementById('listaUsuarios').value;

            if (nickname) {
                fetch('SvObtenerDatosUsuario', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ nickUsuario: nickname })//manda el nick al servlet pa buscar los datos
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error en la red');
                    }
                    return response.json();
                })
                .then(data => {
                    const usuario = data.usuario; 
                    const seguidores = data.seguidores; // Accede a la lista de seguidores
                    const seguidos = data.seguidos;
                    const listasCreadas = data.listasCreadas;
                    const favoritos = data.favoritos;
                    const albumesArtista = data.albumesArtista;
                    const listasPublicas = data.listasPublicas;
                    const albumesFavoritos = data.albumesFavoritos;
                    // Muestra los datos en el HTML
                    document.getElementById('nickname').textContent = usuario.nickname;
                    document.getElementById('correo').textContent = usuario.correo;
                    document.getElementById('nombre').textContent = usuario.nombre;
                    document.getElementById('apellido').textContent = usuario.apellido;
                    document.getElementById('fechaNac').textContent = usuario.fechaNac;
                    document.getElementById('bio').textContent = usuario.biografia;
                    document.getElementById('web').textContent = usuario.sitioWeb;
                    
                    document.getElementById('imagen').textContent = usuario.imagen;
                    
  const nombreImagen = usuario.imagen; 
        console.log("Nombre de la imagen:", nombreImagen);
            document.getElementById('imagen').src = "/SvGetImagen?nombre=" + nombreImagen;
                    
                    // Hace visible el div con la información del usuario    
                    document.getElementById('userInfo').style.display = 'block';
                    //si es A se muestra sino no
                    if ((usuario.esCliente)==="s"){
                        document.getElementById('albTexto').style.display = 'none';
                        document.getElementById('correo').style.display = 'none';
                        document.getElementById('nombre').style.display = 'none';
                        document.getElementById('apellido').style.display = 'none';
                        document.getElementById('fechaNac').style.display = 'none';
                        document.getElementById('fec').style.display = 'none';
                        document.getElementById('ap').style.display = 'none';
                        document.getElementById('cor').style.display = 'none';
                        document.getElementById('nom').style.display = 'none';
                        document.getElementById('bio').style.display = 'none';
                        document.getElementById('web').style.display = 'none';
                        document.getElementById('bioT').style.display = 'none';
                        document.getElementById('webT').style.display = 'none';
                        
                        //Listas publicas creadas del cliente
                        const listasPublicasDiv = document.getElementById('listasPublicas');
                        listasPublicasDiv.innerHTML = '';
                        if (listasPublicas && listasPublicas.length > 0) {
                            listasPublicas.forEach(lis => {
                                const li = document.createElement('li');
                                li.textContent = lis;
                                listasPublicasDiv.appendChild(li);
                            });
                            document.getElementById('listasPublicasDiv').style.display = 'block'; // Asegúrate de que el contenedor esté visible
                            console.log(listasPublicas);//MOSTRAR CLIENTES Y ARTISTAS
                        } else {
                            document.getElementById('listasPublicasDiv').style.display = 'none'; // Asegúrate de que el contenedor esté visible
                        }
                        
                        //Albumes del artista no se meustra porq es cliente
                        //const albumesArtistaDiv = document.getElementById('albumesArtista');
                        dynamicContent.innerHTML =  '';//limpio para que no quede guardado lo del cliente anterior
                        //albumesArtistaDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior DA ERROR
                        document.getElementById('albumesArtistaDiv').style.display = 'none';
                        document.getElementById('seguidoresDiv').style.display = 'none';
                       
                    }else {//           ARTISTA
                        document.getElementById('albTexto').style.display = 'block';
                        document.getElementById('bio').style.display = 'block';
                        document.getElementById('web').style.display = 'block';
                        document.getElementById('bioT').style.display = 'block';
                        document.getElementById('webT').style.display = 'block';
                        document.getElementById('correo').style.display = 'block';
                        document.getElementById('nombre').style.display = 'block';
                        document.getElementById('apellido').style.display = 'block';
                        document.getElementById('fechaNac').style.display = 'block';
                        document.getElementById('fec').style.display = 'block';
                        document.getElementById('ap').style.display = 'block';
                        document.getElementById('cor').style.display = 'block';
                        document.getElementById('nom').style.display = 'block';
                        
                        // Muestra la lista de seguidores del cliente
                        const seguidoresDiv = document.getElementById('seguidores');
                        seguidoresDiv.innerHTML = ''; // Limpia la lista de seguidores previa
                        if (seguidores && seguidores.length > 0) {
                            seguidores.forEach(segu => { // este for recorre la lista de seguidores
                                const li = document.createElement('li'); // crea un elemento <li> para cada seguidor
                                li.textContent = segu;
                                seguidoresDiv.appendChild(li);
                            });
                            // Muestra el contenedor de seguidores
                            document.getElementById('seguidoresDiv').style.display = 'block'; // Asegúrate de que el contenedor esté visible
                            document.getElementById('albTexto').style.display = 'block';
                        } else {// Si no hay seguidores, ocultar el contenedor
                            document.getElementById('seguidoresDiv').style.display = 'none';
                        }
                        //Listas publicas del cliente aca no se muestra
                        const listasPublicasDiv = document.getElementById('listasPublicas');
                        listasPublicasDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior
                        document.getElementById('listasPublicasDiv').style.display = 'none';
                        
//--------------------------------------------------------------
                        
                        albumesArtistaDiv.innerHTML = '';//NO BORRAR LINEA
                        dynamicContent.innerHTML =  '';
                         if (albumesArtista && albumesArtista.length > 0) {
                            albumesArtista.forEach(nombreAlbum => {
                                // Crear el enlace (hipervínculo)
                                const link = document.createElement('a');
                                link.textContent = nombreAlbum;
                                link.href = "#"; // Para evitar que recargue la página
                                link.style.textDecoration = 'underline'; // Estilo opcional para enfatizar que es un enlace

                                // Agregar evento onclick para ejecutar la función fetchAlbum cuando se haga clic
                                link.onclick = (event) => {
                                    event.preventDefault(); // Evita que el enlace navegue
                                    fetchAlbum(nombreAlbum, usuario.nickname); // Llama a la función pasando el nombre del álbum
                                };

                                // Crear un elemento de lista y agregar el enlace dentro de él
                                const li = document.createElement('li');
                                li.appendChild(link);
                                albumesArtistaDiv.appendChild(li);
                        });
                        document.getElementById('albumesArtistaDiv').style.display = 'block';
                        }else{
                            document.getElementById('albumesArtistaDiv').style.display = 'none';
                            document.getElementById('dynamicIframe').style.display = 'none';
                        }
                       
                    //--------------------------------------------------------------------------------
                        
                    }
                })
                .catch(error => {
                    console.error('Error al cargar los datos del usuario:', error);
                    alert('No se pudieron cargar los datos del usuario.');
                }); //LO COMENTE POR AHORA PORQUE DA ERROR POR LOS SEGUIDORES
            } else {
                document.getElementById('userInfo').style.display = 'none';
            }
        }
        // Cargar las listas al cargar la página
        window.onload = cargarUsuarios;
        
        //-----------------------------------------------------------------------------
        // Función para realizar el fetch cuando se hace clic en un álbum
        function fetchAlbum(nombreAlbum, nombreArtista) {
            const variableUrl = encodeURIComponent(nombreArtista);
            const url = "SvObtenerAlbumxArtista?artista=" + variableUrl;
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
                        const albumName = nombreAlbum//item.querySelector('.album-name').textContent.trim();
                        const artistName = item.querySelector('.album-artist').textContent.trim();
                        console.log(`Nombre albumpasado: `, nombreAlbum, `, Nombre Artistapasado: `, nombreArtista);
                        console.log(`Nombre album: `, albumName, `, Nombre Artista: `, artistName);

                        item.onclick = () => {
                            console.log(`todo ok `, albumName);
                            const encodedAlbumName = encodeURIComponent(albumName);
                            const encodedArtistName = encodeURIComponent(artistName);
                            const servletUrl = "SvObtenerTemas?album=" + encodedAlbumName + "&artista=" + encodedArtistName;
                            console.log(servletUrl);
                            console.log(`Nombre album: `, albumName, `, Nombre Artista: `, artistName);
                            const iframe = document.getElementById('dynamicIframe');
                            iframe.src = servletUrl; // Establece la URL del iframe 
                            console.log(`Album: `, albumName);
                        };
                    });
                })
                .catch(error => console.error('Error al obtener álbumes:', error));
        }
        
        //-----------------------------------------------------------------------------
        </script>
  
    
    
    <script>
    // Mostrar un mensaje emergente si hay un mensaje de éxito
    <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
    <% if (mensajeExito != null) { %>
        alert("<%= mensajeExito %>");
    <% } %>
    </script>
        
    </body>
    <footer> 
    <iframe src="" id="dynamicIframe" width="100%" height="400px" frameborder="0" scrolling="auto"></iframe> </footer>
</html>
