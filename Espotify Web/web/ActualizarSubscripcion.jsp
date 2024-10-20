<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Datatypes.DTSub"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/ContratarSubscripcion.css"> 
    <title>Actualizar Suscripción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            margin-top: 100px;
            background-color: #121212;
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 4px 60px rgba(0, 0, 0, 0.5);
        }

        .card-title {
            color: var(--spotify-white);
            font-size: 28px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 24px;
        }

        .form-check-label {
            color: var(--spotify-white);
        }

        .btn-success {
            background-color: var(--spotify-green);
            border-color: var(--spotify-green);
            color: var(--spotify-black);
            border-radius: 30px;
            padding: 12px 24px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background-color: #1ed760;
            transform: scale(1.05);
        }
    </style>
</head>
<jsp:include page="header.jsp" />
<body>

    <div class="container d-flex justify-content-center ">
        <div class="row justify-content-center">
            <div class="card text-white">
                <div class="card-body">
                    <h5 class="card-title">Actualizar Estado de Suscripciones</h5>

                    <%
                        // Obtener la lista de suscripciones desde el request
                        List<DTSub> subscripciones = (List<DTSub>) request.getAttribute("subscripciones");
                        if (subscripciones != null && !subscripciones.isEmpty()) {
                            for (DTSub subscripcion : subscripciones) {
                    %>
                        <form action="SvActualizarSubscripcion" method="POST" class="subs-form">
    <input type="hidden" name="id" value="<%= subscripcion.getId() %>">
    <input type="hidden" name="tipo" value="<%= subscripcion.getTipo() %>">
    <div class="mb-3">
        <label for="estado_<%= subscripcion.getId() %>" class="form-label">
            Selecciona el nuevo estado para <%= subscripcion.getTipo() %> (<%= subscripcion.getEstado()%>) activada el <%= subscripcion.getFechaActivacion() %> (ID: <%= subscripcion.getId() %>)
        </label>
        <select name="estado" id="estado_<%= subscripcion.getId() %>" class="form-select">
            <%
                // Verificar el estado actual de la suscripción y mostrar opciones según corresponda
                String estadoActual = subscripcion.getEstado();
                if (estadoActual.equals("PENDIENTE")) {
            %>
                <option value="Cancelada">Cancelada</option>
            <%
                } else if (estadoActual.equals("VENCIDA")) {
            %>
                <option value="Cancelada">Cancelada</option>
                <option value="Vigente">Vigente</option>
            <%
                } else {
                    // No mostrar opciones si el estado no permite cambios
            %>
                <option value="" disabled>No se permite modificar el estado</option>
            <%
                }
            %>
        </select>
    </div>
    <button type="submit" class="btn btn-success"
        <%= estadoActual.equals("PENDIENTE") || estadoActual.equals("VENCIDA") ? "" : "disabled" %>>Actualizar Suscripción</button>
</form>

                    <%
                            }
                        } else {
                    %>
                    <p>No hay suscripciones disponibles para actualizar.</p>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
