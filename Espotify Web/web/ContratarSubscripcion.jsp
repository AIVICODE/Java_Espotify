<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/ContratarSubscripcion.css"> 
    <title>Suscripción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="icon" type="image/png" href="https://storage.googleapis.com/pr-newsroom-wp/1/2023/05/Spotify_Primary_Logo_RGB_Green.png"> <!-- Ruta del logo de Spotify -->

    <style>
        :root {
            --spotify-green: #1DB954;
            --spotify-black: #191414;
            --spotify-white: #FFFFFF;
            --spotify-grey: #535353;
        }

        body {
            background-color: var(--spotify-black);
            color: var(--spotify-white);
            font-family: 'Circular', Arial, sans-serif;
            min-height: 100vh;
            display: flex; /* Flex para centrar todo el body */
            align-items: center; /* Centra verticalmente */
            justify-content: center; /* Centra horizontalmente */
        }

        .card {
            background-color: #121212; /* Cambiado a un color más oscuro para asemejar el anterior */
            border-radius: 8px; /* Bordes redondeados */
            padding: 24px; /* Espaciado interior */
            box-shadow: 0 4px 60px rgba(0, 0, 0, 0.5); /* Sombra similar a la tarjeta de Spotify */
        }

        .card-body {
            display: flex; /* Utiliza flexbox para el contenido del card */
            flex-direction: column; /* Coloca los elementos en columna */
            justify-content: center; /* Centra verticalmente */
            align-items: center; /* Centra horizontalmente */
            min-height: 300px; /* Ajusta la altura mínima según sea necesario */
        }

        .card-title {
            color: var(--spotify-white);
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 24px;
            text-align: center; /* Centramos el título */
        }

        .form-check-input {
    appearance: none; /* Eliminar el estilo predeterminado del input */
    width: 20px; /* Ajustar el tamaño del círculo */
    height: 20px; /* Ajustar el tamaño del círculo */
    border: 2px solid var(--spotify-white); /* Borde blanco */
    border-radius: 50%; /* Hacer que el input sea un círculo */
    background-color: transparent; /* Fondo transparente */
    cursor: pointer; /* Cambiar el cursor al pasar sobre el input */
}

.form-check-input:checked {
    background-color: var(--spotify-white); /* Fondo blanco cuando está seleccionado */
    box-shadow: 0 0 0 4px var(--spotify-green); /* Sombra para dar un efecto de "resaltado" */
}

.form-check-label {
    color: var(--spotify-white); /* Color de las etiquetas */
}
        .btn-success {
            background-color: var(--spotify-green);
            border-color: var(--spotify-green);
            color: var(--spotify-black);
            border-radius: 30px; /* Bordes redondeados */
            padding: 12px 24px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background-color: #1ed760; /* Color al pasar el ratón */
            transform: scale(1.05);
        }

        #confirmationArea {
            text-align: center; /* Centramos el contenido */
            margin-top: 20px; /* Espacio superior */
        }

        #successMessage {
            text-align: center; /* Centramos el mensaje de éxito */
            margin-top: 20px; /* Espacio superior */
            color: #22c55e; /* Color verde para el mensaje de éxito */
        }
    </style>
</head>
<jsp:include page="header.jsp" />
<body>

    <div class="container d-flex justify-content-center">
        <div class="row justify-content-center">
            <div class="card text-white">
                <div class="card-body">
                    <h5 class="card-title">Elige tu Suscripción</h5>
                    <form id="subscriptionForm">
                        <div class="mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="subscriptionOption" id="weekly" value="semanal">
                                <label class="form-check-label" for="weekly">
                                    Semanal - $9.99
                                </label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="subscriptionOption" id="monthly" value="mensual">
                                <label class="form-check-label" for="monthly">
                                    Mensual - $29.99
                                </label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="subscriptionOption" id="yearly" value="anual">
                                <label class="form-check-label" for="yearly">
                                    Anual - $299.99
                                </label>
                            </div>
                        </div>
                    </form>
                    <div id="confirmationArea" style="display: none;">
                        <p>Monto total: $<span id="totalAmount"></span></p>
                        <button id="confirmButton" class="btn btn-success">Confirmar Suscripción</button>
                    </div>
                    <div id="successMessage" style="display: none;">
                        <p>¡Suscripción confirmada! Tu suscripción está ahora en estado "Pendiente".</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('subscriptionForm');
            const confirmationArea = document.getElementById('confirmationArea');
            const totalAmountSpan = document.getElementById('totalAmount');
            const confirmButton = document.getElementById('confirmButton');
            const successMessage = document.getElementById('successMessage');

            const prices = {
                semanal: 9.99,
                mensual: 29.99,
                anual: 299.99
            };

            form.addEventListener('change', function() {
                const selectedOption = form.querySelector('input[name="subscriptionOption"]:checked');
                if (selectedOption) {
                    const price = prices[selectedOption.value];
                    totalAmountSpan.textContent = price.toFixed(2);
                    confirmationArea.style.display = 'block';
                    successMessage.style.display = 'none';
                }
            });

            confirmButton.addEventListener('click', function() {
                const selectedOption = form.querySelector('input[name="subscriptionOption"]:checked');
                if (selectedOption) {
                    confirmationArea.style.display = 'none';
                    successMessage.style.display = 'block';

                    // Crear un objeto de datos para enviar
                    const params = new URLSearchParams();
                    params.append("subscriptionType", selectedOption.value); // Enviar el valor en minúsculas

                    // Enviar la solicitud fetch
                    fetch("SvContratarSubscripcion", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: params
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data.message); // Manejar la respuesta del servidor
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                }
            });
        });
    </script>
</body>
</html>
