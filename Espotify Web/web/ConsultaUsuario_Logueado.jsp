<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Datatypes.DTUsuario"%>
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
    <jsp:include page="header.jsp" />
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
                        <p><strong>Correo electrónico:</strong> <span id="correo"></span></p>
                        <p><strong>Nombre:</strong> <span id="nombre"></span></p>
                        <p><strong>Apellido:</strong> <span id="apellido"></span></p>
                        <p><strong>Fecha de nacimiento:</strong> <span id="fechaNac"></span></p>
                        <p id="bioT"><strong>Biografía:</strong> <span id="bio"></span></p>
                        <p id="webT"><strong>Sitio Web:</strong> <span id="web"></span></p>
                        <div id="seguidoresDiv"><h3>Seguidores:</h3>
                        <ul id="seguidores"></ul>
                        </div>
                        <div id="seguidosDiv"> <h3>Seguidos:</h3>
                        <ul id="seguidos"></ul>
                        </div>
                        <div id="listasCreadasDiv"> <h3>Listas Creadas:</h3>
                        <ul id="listasCreadas"></ul>
                        </div>
                        <div id="favoritosDiv"> <h3>Favoritos:</h3>
                        <ul id="favoritos"></ul>
                        </div>
                        <div> <h3 id="albTexto">Albumes del Artista:</h3> </div>
                        <div id="albumesArtistaDiv"> <h3>Albumes del Artista:</h3>
                        <ul id="albumesArtista"></ul>
                        </div>
                        <div id="albumesFavoritosDiv"> <h3>Albumes favoritos del Cliente:</h3>
                        <ul id="albumesFavoritos"></ul>
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
                    const albumesFavoritos = data.albumesFavoritos;
                    // Muestra los datos en el HTML
                    document.getElementById('nickname').textContent = usuario.nickname;
                    document.getElementById('correo').textContent = usuario.correo;
                    document.getElementById('nombre').textContent = usuario.nombre;
                    document.getElementById('apellido').textContent = usuario.apellido;
                    document.getElementById('fechaNac').textContent = usuario.fechaNac;
                    document.getElementById('bio').textContent = usuario.biografia;
                    document.getElementById('web').textContent = usuario.sitioWeb;
                    // Hace visible el div con la información del usuario    
                    document.getElementById('userInfo').style.display = 'block';
                    //si es A se muestra sino no
                    if ((usuario.esCliente)==="s"){
                        document.getElementById('albTexto').style.display = 'none';
                        document.getElementById('bio').style.display = 'none';
                        document.getElementById('web').style.display = 'none';
                        document.getElementById('bioT').style.display = 'none';
                        document.getElementById('webT').style.display = 'none';
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
                        } else {
                            // Si no hay seguidores, puedes ocultar el contenedor
                            document.getElementById('seguidoresDiv').style.display = 'none';
                        }
                        //Seguidos cliente 
                        const seguidosDiv = document.getElementById('seguidos');
                        seguidosDiv.innerHTML = '';
                        if (seguidos && seguidos.length > 0) {
                            seguidos.forEach(seguido => {
                                const li = document.createElement('li');
                                li.textContent = seguido;
                                seguidosDiv.appendChild(li);
                            });
                            document.getElementById('seguidosDiv').style.display = 'block'; // Asegúrate de que el contenedor esté visible
                            console.log(seguidos);//MOSTRAR CLIENTES Y ARTISTAS
                        } else {
                            document.getElementById('seguidosDiv').style.display = 'none'; // Asegúrate de que el contenedor esté visible
                        }
                        //Listas creadas del cliente
                        const listasCreadasDiv = document.getElementById('listasCreadas');
                        listasCreadasDiv.innerHTML = '';
                        if (listasCreadas && listasCreadas.length > 0) {
                            listasCreadas.forEach(lis => {
                                const li = document.createElement('li');
                                li.textContent = lis;
                                listasCreadasDiv.appendChild(li);
                            });
                            document.getElementById('listasCreadasDiv').style.display = 'block'; // Asegúrate de que el contenedor esté visible
                            console.log(listasCreadas);//MOSTRAR CLIENTES Y ARTISTAS
                        } else {
                            document.getElementById('listasCreadasDiv').style.display = 'none'; // Asegúrate de que el contenedor esté visible
                        }
                        
                        //Favoritos del cliente
                        const favoritosDiv = document.getElementById('favoritos');
                        favoritosDiv.innerHTML = '';
                        if (favoritos && favoritos.length > 0) {
                            favoritos.forEach(lis => {
                                const li = document.createElement('li');
                                li.textContent = lis;
                                favoritosDiv.appendChild(li);
                            });
                            document.getElementById('favoritosDiv').style.display = 'block'; // Asegúrate de que el contenedor esté visible
                            console.log(favoritos);//MOSTRAR CLIENTES Y ARTISTAS
                        } else {
                            document.getElementById('favoritosDiv').style.display = 'none'; // Asegúrate de que el contenedor esté visible
                        }
                        
                        //Albumes del artista no se meustra porq es cliente
                        const albumesArtistaDiv = document.getElementById('albumesArtista');
                        dynamicContent.innerHTML =  '';//limpio para que no quede guardado lo del cliente anterior
                        //albumesArtistaDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior DA ERROR
                        document.getElementById('albumesArtistaDiv').style.display = 'none';
                        
                        //------------------------------------------------------
                        
                        //Albumes favoritos del cliente
                        const albumesFavoritosDiv = document.getElementById('albumesFavoritos');
                        albumesFavoritosDiv.innerHTML = ''; // Limpiar el contenido previo
                        if (albumesFavoritos && albumesFavoritos.length > 0) {
                            albumesFavoritos.forEach(textoArreglo => {
                                // Dividir el texto en partes
                                const partes = textoArreglo.split('-');
                                const nombreAlbum = partes[0];
                                const nombreArtista = partes[1];

                                // Crear el enlace
                                const link = document.createElement('a');
                                link.textContent = nombreAlbum; // Muestra solo el nombre del álbum
                                link.href = "#"; // Evita que el enlace navegue
                                link.style.textDecoration = 'underline'; // Opcional: subraya para resaltar que es un enlace

                                // Asociar el evento de clic para ejecutar fetchAlbum
                                link.onclick = (event) => {
                                    event.preventDefault(); // Evita el comportamiento de navegación
                                    fetchAlbum(nombreAlbum, nombreArtista); // Llama a la función con álbum y artista
                                };

                                // Crear el elemento de lista y agregar el enlace dentro
                                const li = document.createElement('li');
                                li.appendChild(link);
                                albumesFavoritosDiv.appendChild(li);
                            });

                            document.getElementById('albumesFavoritosDiv').style.display = 'block'; // Mostrar el contenedor
                        } else {
                            document.getElementById('albumesFavoritosDiv').style.display = 'none'; // Ocultar el contenedor si no hay favoritos
                        }
                        //------------------------------------------------------
                        
                        
                    }else {//           ARTISTA
                        document.getElementById('albTexto').style.display = 'block';
                        document.getElementById('bio').style.display = 'block';
                        document.getElementById('web').style.display = 'block';
                        document.getElementById('bioT').style.display = 'block';
                        document.getElementById('webT').style.display = 'block';
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
                        } else {// Si no hay seguidores, ocultar el contenedor
                            document.getElementById('seguidoresDiv').style.display = 'none';
                        }
                        //Seguidos aca no se muestra
                        const seguidosDiv = document.getElementById('seguidos');
                        seguidosDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior
                        document.getElementById('seguidosDiv').style.display = 'none';
                        //Listas creadas aca no se muestra
                        const listasCreadasDiv = document.getElementById('listasCreadas');
                        listasCreadasDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior
                        document.getElementById('listasCreadasDiv').style.display = 'none';
                        //Favoritos aca no se muestra
                        const favoritosDiv = document.getElementById('favoritos');
                        favoritosDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior
                        document.getElementById('favoritosDiv').style.display = 'none';
                        
                        //albumes fav cliente no mostrar
                        const albumesFavoritosDiv = document.getElementById('albumesFavoritos');
                        dynamicContent.innerHTML =  '';
                        //albumesFavoritosDiv.innerHTML = '';//limpio para que no quede guardado lo del cliente anterior
                        document.getElementById('albumesFavoritosDiv').style.display = 'none';
                    
                    
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
                        document.getElementById('albTexto').style.display = 'block';
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
