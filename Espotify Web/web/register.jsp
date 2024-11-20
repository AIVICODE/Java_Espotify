<%-- 
    Document   : registro
    Created on : Oct 3, 2024, 1:34:28 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse - Espotify</title>
        <link rel="stylesheet" href="css/register.css"> <!-- Asegúrate de que la ruta sea correcta -->
                    <link rel="icon" type="image/png" href="https://storage.googleapis.com/pr-newsroom-wp/1/2023/05/Spotify_Primary_Logo_RGB_Green.png"> <!-- Ruta del logo de Spotify -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
    <nav class="navbar">
        <div class="container">
            <a href="index.jsp" class="logo">
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 496 512">
                    <path fill="currentColor" d="M248 8C111.1 8 0 119.1 0 256s111.1 248 248 248 248-111.1 248-248S384.9 8 248 8zm100.7 364.9c-4.2 0-6.8-1.3-10.7-3.6-62.4-37.6-135-39.2-206.7-24.5-3.9 1-9 2.6-11.9 2.6-9.7 0-15.8-7.7-15.8-15.8 0-10.3 6.1-15.2 13.6-16.8 81.9-18.1 165.6-16.5 237 26.2 6.1 3.9 9.7 7.4 9.7 16.5s-7.1 15.4-15.2 15.4zm26.9-65.6c-5.2 0-8.7-2.3-12.3-4.2-62.5-37-155.7-51.9-238.6-29.4-4.8 1.3-7.4 2.6-11.9 2.6-10.7 0-19.4-8.7-19.4-19.4s5.2-17.8 15.5-20.7c27.8-7.8 56.2-13.6 97.8-13.6 64.9 0 127.6 16.1 177 45.5 8.1 4.8 11.3 11 11.3 19.7-.1 10.8-8.5 19.5-19.4 19.5zm31-76.2c-5.2 0-8.4-1.3-12.9-3.9-71.2-42.5-198.5-52.7-280.9-29.7-3.6 1-8.1 2.6-12.9 2.6-13.2 0-23.3-10.3-23.3-23.6 0-13.6 8.4-21.3 17.4-23.9 35.2-10.3 74.6-15.2 117.5-15.2 73 0 149.5 15.2 205.4 47.8 7.8 4.5 12.9 10.7 12.9 22.6 0 13.6-11 23.3-23.2 23.3z"/>
                </svg>
                Espotify
            </a>
        </div>
    </nav>
 <% //Mensaje de exito al registrar
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<% if (successMessage != null) { %>
    <div class="success-message"> <span> ✔ </span><%= successMessage %></div>
<% } %>

<% if (errorMessage != null) { %>
    <div class="error-message"><%= errorMessage %></div>
<% } %>

    <main>
        <div class="register-form">
            <h2>Registrarse</h2>
            <form onsubmit="return validar()" action="SvRegistrarUsuario" method="POST" id="registerForm" enctype="multipart/form-data"> 
    <div class="form-group">
        <label for="nickname">Nickname</label>
        <input type="text" id="nickname" name="nickname" required>
        <span id="existeNickMensaje" class="existeNickMensaje"></span> <!-- Mensaje de disponibilidad -->
    </div>
    <div class="form-group">
        <label for="email">Correo electrónico</label>
        <input type="email" id="email" name="email" required>
        <span id="existeMailMensaje" class="existeMailMensaje"></span> <!-- Mensaje de disponibilidad -->
    </div>
    <div class="form-group">
        <label for="name">Nombre</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div class="form-group">
        <label for="surname">Apellido</label>
        <input type="text" id="surname" name="surname" required>
    </div>
    <div class="form-group">
        <label for="password">Contraseña</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirmar Contraseña</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div class="form-group">
        <label for="dob">Fecha de Nacimiento</label>
        <input type="date" id="dob" name="dob" required>
    </div>
    <div class="form-group">
        <label for="image">Imagen (opcional)</label>
        <input type="file" id="image" name="image" accept="image/*">
    </div>
    <div class="form-group">
        <label for="userType">Tipo de Usuario</label>
        <select id="userType" name="userType" required>
            <option value="Cliente">Cliente</option>
            <option value="Artista">Artista</option>
        </select>
    </div>
    <div class="form-group" id="artistBioContainer" style="display: none;">
        <label for="bio">Biografía (opcional)</label>
        <textarea id="bio" name="bio"></textarea>
    </div>
    <div class="form-group" id="artistLinkContainer" style="display: none;">
        <label for="website">Sitio web (opcional)</label>
        <input type="url" id="website" name="website" placeholder="https://ejemplo.com">
    </div>
    <button type="submit" class="submit-btn">Registrarse</button>
</form>
            <a href="index.jsp" class="back-link">Volver a la página de inicio</a>
        </div>
    </main>
    
    
    <script>
        //VERIFICACION DE NICK Y MAIL
        var nickLibre = true; // Variable para verificar disponibilidad del nickname
        var mailLibre = true; // Variable para verificar disponibilidad del email
        
        $(document).ready(function() {
            $("#nickname").on("keyup", function() {
                var nickname = $(this).val();
                if (nickname.length > 0) {
                    $.ajax({
                        type: "POST",
                        url: "SvExisteNickname", // URL del servlet que verifica el nickname
                        data: { nickname: nickname },
                        success: function(response) {
                            $("#existeNickMensaje").html(response);//aca setea el mensaje con lo que le mando el servlet
                            
                            nickLibre = response.includes("disponible");
                            //si el servlet envia el mensaje con la palabra disponible, cambia a true, si el mensaje no tiene la palabra disponible, lo pone false
                        },
                        error: function() {
                            $("#existeNickMensaje").html("<span class='unavailable'>Error al verificar.</span>");
                        }
                    });
                } else {
                    $("#existeNickMensaje").html(""); // Limpiar mensaje si no hay entrada
                }
            });
        });
        $(document).ready(function() {
            $("#email").on("keyup", function() {
                var email = $(this).val();
                if (email.length > 0) {
                    $.ajax({
                        type: "POST",
                        url: "SvExisteMail", // URL del servlet que verifica el email
                        data: { email: email },
                        success: function(response) {
                            $("#existeMailMensaje").html(response);
                            
                            mailLibre = response.includes("disponible"); // Actualiza a true o false dependiendo de si está disponible
                        },
                        error: function() {
                            $("#existeMailMensaje").html("<span class='unavailable'>Error al verificar.</span>");
                        }
                    });
                } else {
                    $("#existeMailMensaje").html(""); // Limpiar mensaje si no hay entrada
                }                
            });
        });
    //Si se selecciona artista
        const userTypeSelect = document.getElementById('userType');
        const artistBioContainer = document.getElementById('artistBioContainer');
        const artistLinkContainer = document.getElementById('artistLinkContainer');
        //Esto muestra los cuadros de info de artista
        userTypeSelect.addEventListener('change', function() {
            if (this.value === 'Artista') {
                artistBioContainer.style.display = 'block';
                artistLinkContainer.style.display = 'block';
            } else {
                artistBioContainer.style.display = 'none';
                artistLinkContainer.style.display = 'none';
            }
        });
        //Validacion de contraseñas, nickname y mail y control de envio de formulario
    function validar() {
            // Obtener los valores de las contraseñas
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            // Comparar las contraseñas
            if (password !== confirmPassword) {
                // Mostrar un mensaje emergente
                alert("Las contraseñas no coinciden.");
                // Limpiar el campo de confirmación
                document.getElementById("confirmPassword").value = '';
                // Enfocar el campo de confirmación
                document.getElementById("confirmPassword").focus();
                return false; // Evitar el envío del formulario
            }
            if ((!nickLibre) || (!mailLibre)){//Variables declaradas arriba
                alert("Asegurate de que tanto el nickname como el correo estén disponibles");
                return false;
            }      
            return true; // Permitir el envío del formulario 
        }    
    </script>
    
</body>
</html>
