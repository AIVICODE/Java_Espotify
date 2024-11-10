<%@page import="webservices.DtTema"%>
<%@page import="webservices.DtAlbum"%>
<%@page import="java.util.List"%>
<%@page import="webservices.DtListaRep"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Listas de Reproducción</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        body {
            background-color: #18181b;
            color: #ddd;
        }
        .card {
            background-color: #121212;
            border: none;
            color: #ddd;
            margin: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.3);
            height: 100%; /* Asegura que todas las tarjetas tengan la misma altura */
        }
        .card-header {
            background-color: #1DB954;
            color: #fff;
            font-weight: bold;
            text-align: center;
        }
        .form-control, .form-label, .btn-primary {
            background-color: #333;
            border-color: #1DB954;
            color: #ddd;
        }
        .btn-primary {
            background-color: #1DB954;
        }
        h3 {
            color: #1DB954;
            text-align: center;
            margin-top: 20px;
        }
        #resultadoSeleccion, #resultadoTipo, #resultadoSeleccionOtrosClientes, #resultadoTemaListaParticular, #resultadoSeleccionPorDefecto, #resultadoTemaListaPorDefecto, #resultadoAlbum, #resultadoTema {
            margin-top: 20px;
            color: #bbb;
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
    </style>
</head>
<jsp:include page="header.jsp" />
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">Selecciona una Lista</div>
                    <div class="card-body">
                        <select id="comboboxListas" class="form-control mb-3" onchange="listaSeleccionada(this.value)">
                            <option value="">-- Selecciona una lista --</option>
                        </select>
                        <div id="resultadoSeleccion"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">Selecciona el Tipo</div>
                    <div class="card-body">
                        <select id="tipoSeleccionado" class="form-control mb-3" onchange="tipoSeleccionado(this.value)">
                            <option value="">-- Selecciona un tipo --</option>
                            <option value="album">Álbum</option>
                            <option value="listaParticular">Lista Particular</option>
                            <option value="listaPorDefecto">Lista por Defecto</option>
                        </select>
                        <div id="resultadoTipo"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4" id="contenedorListasOtrosClientes" style="display: none;">
                <div class="card">
                    <div class="card-header">Listas de Reproducción de Otros Clientes</div>
                    <div class="card-body">
                        <select id="comboboxListasOtrosClientes" class="form-control mb-3" onchange="listaOtrosClientesSeleccionada(this.value)">
                            <option value="">-- Selecciona una lista de otro cliente --</option>
                        </select>
                        <div id="resultadoSeleccionOtrosClientes"></div>
                        <h5 class="mt-4">Selecciona un Tema de la Lista Particular:</h5>
                        <select id="comboboxTemasListaParticular" class="form-control mb-3">
                            <option value="">-- Selecciona un tema --</option>
                        </select>
                        <div id="resultadoTemaListaParticular"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4" id="contenedorListasPorDefecto" style="display: none;">
                <div class="card">
                    <div class="card-header">Listas por Defecto</div>
                    <div class="card-body">
                        <select id="comboboxListasPorDefecto" class="form-control mb-3" onchange="listaPorDefectoSeleccionada(this.value)">
                            <option value="">-- Selecciona una lista por defecto --</option>
                        </select>
                        <div id="resultadoSeleccionPorDefecto"></div>
                        <h5 class="mt-4">Selecciona un Tema de la Lista por Defecto:</h5>
                        <select id="comboboxTemasListaPorDefecto" class="form-control mb-3">
                            <option value="">-- Selecciona un tema --</option>
                        </select>
                        <div id="resultadoTemaListaPorDefecto"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4" id="albumTemaContainer" style="display: none;">
                <div class="card">
                    <div class="card-header">Álbum y Temas</div>
                    <div class="card-body">
                        <h5>Selecciona un Álbum:</h5>
                        <select id="comboboxAlbumes" class="form-control mb-3" onchange="albumSeleccionado(this.value)">
                            <option value="">-- Selecciona un álbum --</option>
                        </select>
                        <div id="resultadoAlbum"></div>
                        <h5 class="mt-4">Selecciona un Tema del Álbum:</h5>
                        <select id="comboboxTemas" class="form-control mb-3">
                            <option value="">-- Selecciona un tema --</option>
                        </select>
                        <div id="resultadoTema"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center mt-5">
            <button onclick="verificarYAgregarFavorito()" class="btn btn-primary">Enviar datos al servlet</button>
        </div>
    </div>

</body>
</html>


        
        <script>
            // Cargar las listas al ingresar a la página
            $(document).ready(function() {
            obtenerListasParticulares();
            obtenerListasParticularesDeOtrosClientes();
            obtenerListasPorDefecto();
            });
            function obtenerListasParticulares() {
            fetch('SvObtenerListaRepCliente')
                    .then(response => {
                    if (!response.ok) {
                    throw new Error("Error al obtener listas: " + response.statusText);
                    }
                    return response.json();
                    })
                    .then(data => {
                    let combobox = document.getElementById('comboboxListas');
                    data.forEach(lista => {
                    let option = document.createElement('option');
                    option.value = lista.nombreListaRep; // Almacena el nombre
                    option.textContent = lista.nombreListaRep; // Muestra el nombre en el ComboBox
                    combobox.appendChild(option);
                    });
                    })
                    .catch(error => {
                    console.error(error);
                    alert('Error al cargar listas de reproducción.');
                    });
            }

function obtenerListasParticularesDeOtrosClientes() {
    fetch('SvObtenerListaParticular') 
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener listas particulares de otros clientes: " + response.statusText);
            }
            return response.text(); // Cambiamos a 'text' para recibir HTML en lugar de JSON
        })
        .then(html => {
            let combobox = document.getElementById('comboboxListasOtrosClientes');
            combobox.innerHTML = '<option value="">-- Selecciona una lista de otro cliente --</option>'; // Limpiar opciones previas
            combobox.innerHTML += html; // Insertar el HTML recibido directamente en el ComboBox
        })
        .catch(error => {
            console.error("Error al obtener listas particulares de otros clientes:", error);
            alert('Error al cargar listas particulares de otros clientes.');
        });
}

            // Función para manejar la selección de una lista
            function listaSeleccionada(nombreLista) {
                console.log("Lista seleccionada:", nombreLista);
                document.getElementById('resultadoSeleccion').innerText = "Has seleccionado la lista: " + nombreLista;
            }

function listaOtrosClientesSeleccionada(listaInfo) {
    // Separar nombre de la lista y nombre del cliente
    let [nombreLista, nombreCliente] = listaInfo.split(" - "); // Usar " - " como separador
    console.log("Lista de otros clientes seleccionada:", nombreLista, "Cliente:", nombreCliente);

    // Actualizar el contenido en el div
    document.getElementById('resultadoSeleccionOtrosClientes').innerText = 
        "Has seleccionado la lista: " + nombreLista + " del cliente: " + nombreCliente;

    // Llamar a obtenerTemasDeListaParticular pasando nombreLista y nombreCliente
    obtenerTemasDeListaParticular(nombreLista, nombreCliente);
}


function obtenerListasPorDefecto() {
    fetch('SvObtenerListaPorDefecto') 
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener listas por defecto de otros clientes: " + response.statusText);
            }
            return response.text(); // Cambiamos a 'text' para recibir HTML en lugar de JSON
        })
        .then(html => {
            let combobox = document.getElementById('comboboxListasPorDefecto');
            combobox.innerHTML = '<option value="">-- Selecciona una lista por defecto de otro cliente --</option>'; // Limpiar opciones previas
            combobox.innerHTML += html; // Insertar el HTML recibido directamente en el ComboBox
        })
        .catch(error => {
            console.error("Error al obtener listas por defecto de otros clientes:", error);
            alert('Error al cargar listas por defecto de otros clientes.');
        });
}

// Función para manejar la selección de una lista por defecto
function listaPorDefectoSeleccionada(listaInfo) {
    // Separar nombre de la lista y nombre del cliente
    let [nombreListaG, nombreGenero] = listaInfo.split(" - "); // Usar " - " como separador
    console.log("Lista por defecto seleccionada:", nombreListaG, "Genero:", nombreGenero);

    // Actualizar el contenido en el div
    document.getElementById('resultadoSeleccionPorDefecto').innerText = 
        "Has seleccionado la lista por defecto: " + nombreListaG + " del cliente: " + nombreGenero;

    obtenerTemasDeListaPorDefecto(nombreListaG);
    // Llamar a obtenerTemasDeListaPorDefecto pasando nombreLista y nombreCliente (si es necesario)
  //  obtenerTemasDeListaPorDefecto(nombreLista, nombreCliente);
}















            // Variable para almacenar el tipo seleccionado
            let tipoSeleccionadoValor = '';
            // Función para manejar la selección del tipo
function tipoSeleccionado(valor) {
    tipoSeleccionadoValor = valor;
    console.log("Tipo seleccionado:", tipoSeleccionadoValor);
    document.getElementById('resultadoTipo').innerText = "Has seleccionado el tipo: " + valor;

    // Ocultar o mostrar contenedores según la selección
    if (valor === "album") {
        document.getElementById('albumTemaContainer').style.display = 'block'; // Mostrar contenedor de álbumes y temas
        obtenerAlbumes(); // Asegúrate de que esto esté aquí para cargar álbumes
        document.getElementById('contenedorListasOtrosClientes').style.display = 'none';
    } else {
        document.getElementById('albumTemaContainer').style.display = 'none'; // Ocultar si no es "album"
    }

    if (valor === "listaParticular") {
        document.getElementById('contenedorListasOtrosClientes').style.display = 'block';
    } else {
        document.getElementById('contenedorListasOtrosClientes').style.display = 'none';
    }
    
    if (valor === "listaPorDefecto"){
        document.getElementById('contenedorListasPorDefecto').style.display = 'block';
       
    } else {
        document.getElementById('contenedorListasPorDefecto').style.display = 'none';
    }
    
}


            function obtenerAlbumes() {
            fetch('SvObtenerAlbumTodos')
                    .then(response => {
                    if (!response.ok) {
                    throw new Error("Error al obtener álbumes: " + response.statusText);
                    }
                    return response.text(); // Recibir HTML directamente
                    })
                    .then(html => {
                    let combobox = document.getElementById('comboboxAlbumes');
                    combobox.innerHTML = '<option value="">-- Selecciona un álbum --</option>'; // Limpiar opciones anteriores
                    combobox.innerHTML += html; // Agregar el HTML recibido del servlet
                    })
                    .catch(error => {
                    console.error("Error al obtener álbumes:", error);
                    alert('Error al cargar álbumes.');
                    });
            }

            // Función para manejar la selección de un álbum
            function albumSeleccionado(albumInfo) {
            let [nombreAlbum, nombreArtista] = albumInfo.split(" - "); // Usar " - " como separador
            console.log("Álbum seleccionado:", nombreAlbum, "Artista:", nombreArtista);
            // Actualizar el contenido en el div
            document.getElementById('resultadoAlbum').innerText =
                    "Has seleccionado el álbum: " + nombreAlbum + " del artista: " + nombreArtista;
            // Llamar a obtenerTemasDeAlbum pasando nombreAlbum y nombreArtista
            obtenerTemasDeAlbum(nombreAlbum, nombreArtista);
            }



//const encodedAlbumName = encodeURIComponent(albumName);
//    const encodedArtistName = encodeURIComponent(artistName);
//     const servletUrl = "SvObtenerTemas?album=" + encodedAlbumName + "&artista=" + encodedArtistName;
     
            function obtenerTemasDeAlbum(nombreAlbum, nombreArtista) {
                const encodedAlbumName = encodeURIComponent(nombreAlbum);
    const encodedArtistName = encodeURIComponent(nombreArtista);

            fetch("SvObtenerTemaDeUnAlbum?nombreAlbum=" + encodedAlbumName+"&nombreArtista=" +encodedArtistName)
                    .then(response => {
                    if (!response.ok) {
                    throw new Error("Error al obtener temas del álbum: " + response.statusText);
                    }
                    return response.text(); // Obtener el HTML de los temas
                    })
                    .then(html => {
                    let comboboxTemas = document.getElementById('comboboxTemas');
                    comboboxTemas.innerHTML = '<option value="">-- Selecciona un tema --</option>'; // Limpiar opciones previas
                    comboboxTemas.innerHTML += html; // Insertar las opciones recibidas del servlet
                    })
                    .catch(error => {
                    console.error("Error al obtener temas:", error);
                    alert('Error al cargar temas del álbum.');
                    });
            }
            
            function obtenerTemasDeListaParticular(nombreLista, nombreCliente) {
    // Codificar parámetros
    const encodedNombreLista = encodeURIComponent(nombreLista);
    const encodedNombreCliente = encodeURIComponent(nombreCliente);

    // Realizar la solicitud fetch con los parámetros
    fetch("SvObtenerTemaDeUnaListaP?nombreLista=" + encodedNombreLista + "&nombreCliente=" + encodedNombreCliente)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener temas de la lista particular: " + response.statusText);
            }
            return response.text(); // Obtener el HTML de los temas
        })
        .then(html => {
            let comboboxTemasListaParticular = document.getElementById('comboboxTemasListaParticular');
            comboboxTemasListaParticular.innerHTML = '<option value="">-- Selecciona un tema --</option>'; // Limpiar opciones previas
            comboboxTemasListaParticular.innerHTML += html; // Insertar las opciones recibidas del servlet
        })
        .catch(error => {
            console.error("Error al obtener temas de la lista particular:", error);
            alert('Error al cargar temas de la lista particular.');
        });
}
            
                        function obtenerTemasDeListaPorDefecto(nombreLista) {
    // Codificar parámetros
    const encodedNombreLista = encodeURIComponent(nombreLista);
 

    // Realizar la solicitud fetch con los parámetros
    fetch("SvObtenerTemaDeUnaListaD?nombreLista=" + encodedNombreLista)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener temas de la lista por defecto: " + response.statusText);
            }
            return response.text(); // Obtener el HTML de los temas
        })
        .then(html => {
            let comboboxTemasListaPorDefecto = document.getElementById('comboboxTemasListaPorDefecto');
            comboboxTemasListaPorDefecto.innerHTML = '<option value="">-- Selecciona un tema --</option>'; // Limpiar opciones previas
            comboboxTemasListaPorDefecto.innerHTML += html; // Insertar las opciones recibidas del servlet
        })
        .catch(error => {
            console.error("Error al obtener temas de la lista particular:", error);
            alert('Error al cargar temas de la lista particular.');
        });
}
                   
            
          function enviarDatosAlServlet() {
    // Obtener los valores seleccionados
    console.log("Antes del if");
    const tipoSeleccionadoValor = document.getElementById('tipoSeleccionado').value;
    if (tipoSeleccionadoValor === "album"){
    console.log("Despues del if");
    const nombreLista = document.getElementById('comboboxListas').value;
    const albumInfo = document.getElementById('comboboxAlbumes').value;
    const nombreTema = document.getElementById('comboboxTemas').value;

    if (!nombreLista || !albumInfo || !nombreTema) {
        alert("Por favor selecciona una lista, un álbum y un tema.");
        return;
    }

    // Separar nombre del álbum y del artista
    const [nombreAlbum, nombreArtista] = albumInfo.split(" - ");

    // Codificar los valores para URL
    const encodedNombreLista = encodeURIComponent(nombreLista);
    const encodedNombreAlbum = encodeURIComponent(nombreAlbum);
    const encodedNombreArtista = encodeURIComponent(nombreArtista);
    const encodedNombreTema = encodeURIComponent(nombreTema);

    // Cuerpo de la solicitud
    const bodyData = "nombreLista=" + encodedNombreLista+"&nombreAlbum="+encodedNombreAlbum+"&nombreArtista="+encodedNombreArtista+"&nombreTema="+encodedNombreTema;

    // Realizar la solicitud fetch con método POST
    fetch('SvObtenerTemaDeUnAlbum', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: bodyData // Enviar los datos en formato x-www-form-urlencoded
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error al enviar los datos al servlet");
        }
        return response.text();
    })
    .then(data => {
        console.log("Respuesta del servlet:", data);
        alert("Datos enviados exitosamente");
    })
    .catch(error => {
        console.error("Error al enviar los datos:", error);
        alert("Error al enviar los datos al servlet");
    });
  } else if (tipoSeleccionadoValor === "listaParticular"){
      console.log("Despues del if de la lista particular");
    const nombreLista = document.getElementById('comboboxListas').value;
    const ListaP_Info= document.getElementById('comboboxListasOtrosClientes').value;
    const nombreTema_LP = document.getElementById('comboboxTemasListaParticular').value;
    
   
    if (!nombreLista || !ListaP_Info || !nombreTema_LP) {
        alert("Por favor selecciona una lista, una lista particular y un tema.");
        return;
    }

    // Separar nombre de la lista particular y del cliente
    const [nombreListaParticular, nombreCliente_LP] = ListaP_Info.split(" - ");

    // Codificar los valores para URL
    const encodedNombreLista = encodeURIComponent(nombreLista);
    const encodedNombreListaParticular = encodeURIComponent(nombreListaParticular);
    const encodedNombreClienteLP= encodeURIComponent(nombreCliente_LP);
    const encodedNombreTema = encodeURIComponent(nombreTema_LP);

    // Cuerpo de la solicitud
    const bodyData = "nombreLista=" + encodedNombreLista+"&nombreListaParticular="+encodedNombreListaParticular+"&nombreCliente_LP="+encodedNombreClienteLP+"&nombreTema_LP="+encodedNombreTema;

          fetch('SvObtenerTemaDeUnaListaP', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: bodyData // Enviar los datos en formato x-www-form-urlencoded
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error al enviar los datos al servlet");
        }
        return response.text();
    })
    .then(data => {
        console.log("Respuesta del servlet:", data);
        alert("Datos enviados exitosamente");
    })
    .catch(error => {
        console.error("Error al enviar los datos:", error);
        alert("Error al enviar los datos al servlet");
    });
      
      console.log("salio todo ok");
      
      
        
  } else if (tipoSeleccionadoValor === "listaPorDefecto"){
         console.log("Despues del if de la lista por defecto");
    const nombreLista = document.getElementById('comboboxListas').value;
    const ListaD_Info= document.getElementById('comboboxListasPorDefecto').value;
    const nombreTema = document.getElementById('comboboxTemasListaPorDefecto').value;
    
      
          if (!nombreLista || !ListaD_Info || !nombreTema) {
        alert("Por favor selecciona una lista, una lista por defecto y un tema.");
        return;
    }
      
      
    const [nombreListaDefecto, nombreGenero] = ListaD_Info.split(" - ");

    const encodedNombreLista = encodeURIComponent(nombreLista);
    const encodedNombreListaDefecto = encodeURIComponent(nombreListaDefecto);
    const encodedNombreTema = encodeURIComponent(nombreTema);

     
    const bodyData = "nombreLista=" + encodedNombreLista+"&nombreListaDefecto="+encodedNombreListaDefecto+"&nombreTema="+encodedNombreTema;
      
      
              fetch('SvObtenerTemaDeUnaListaD', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: bodyData // Enviar los datos en formato x-www-form-urlencoded
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error al enviar los datos al servlet");
        }
        return response.text();
    })
    .then(data => {
        console.log("Respuesta del servlet:", data);
        alert("Datos enviados exitosamente");
    })
    .catch(error => {
        console.error("Error al enviar los datos:", error);
        alert("Error al enviar los datos al servlet");
    });
      
      console.log("salio todo ok de lista por defecto");
      
      
      
      
  }
  
          }
          
          function verificarYAgregarFavorito() {
    // Verificación de suscripción antes de agregar a favoritos
    fetch('SvVerificarSubscripcion', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            if (data.hasSubscription) {
                // Si tiene suscripción, agregar el álbum a favoritos
                enviarDatosAlServlet();
            } else {
                alert('No tienes una suscripción activa para poder agregar temas.');
            }
        })
        .catch(error => {
            console.error('Error al verificar la suscripción:', error);
        });
}
        </script>
    </body>
</html>
