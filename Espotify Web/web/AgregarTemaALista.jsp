<%@page import="Datatypes.DTListaRep"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agregar Tema a Lista de Reproducción - Espotify</title>
  <link rel="stylesheet" href="css/AgregarTemaALista.css?v=1.2">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #18181b;
    }
    .card {
      color: #ddd;
      background-color: #121212;
      margin: 20px;
      padding: 20px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }
    .form-label {
      font-weight: bold;
    }
    .btn-primary {
      background-color: #1DB954;
      border-color: #1DB954;
    }
    .titulo {
      color: #ddd;
    }
    .playlist-img {
      width: 150px;
      height: 150px;
      border: 1px solid black;
      margin-right: 10px;
    }
    .add-favorite {
      background-color: transparent;
      border: none;
      color: #1DB954;
      font-size: 20px;
    }
  </style>
</head>

<body>
  <jsp:include page="header.jsp" />

  <div class="container">
    <div class="titulo">
      <h1 class="my-4 text-center">Agregar Tema a Lista de Reproducción</h1>
    </div>

    <!-- Selección de la lista de reproducción -->
    <div class="card">
      <h2>1. Selecciona una lista de reproducción</h2>

      <%
        // Mostrar listas particulares si están disponibles
        List<DTListaRep> listasParticulares = (List<DTListaRep>) request.getAttribute("listasdeRepparticular");
        if (listasParticulares != null && !listasParticulares.isEmpty()) {
      %>
      <h3>Listas Particulares</h3>
        <%
          for (DTListaRep lista : listasParticulares) {
        %>
                    <%= lista.getNombreListaRep() %>
            <%
              }
            %>
            <%
                }
           %>
    </div>

    <!-- Aquí continúan las otras tarjetas para seleccionar tipo de contenido, álbum, tema, etc. -->

    <!-- Selección del tipo de contenido -->
    <div class="card">
      <h2>2. Selecciona el tipo de contenido</h2>
      <form>
        <div class="mb-3">
          <label for="tipoContenido" class="form-label">Tipo de Contenido</label>
          <select class="form-select" id="tipoContenido" required>
            <option selected disabled>Elige un tipo de contenido...</option>
            <option value="album">Álbum</option>
            <option value="lista_particular">Lista Particular</option>
            <option value="lista_defecto">Lista por Defecto</option>
          </select>
        </div>
      </form>
    </div>

    <!-- Selección del álbum o lista -->
    <div class="card">
      <h2>3. Selecciona un álbum o una lista</h2>
      <form>
        <div class="mb-3">
          <label for="seleccionContenido" class="form-label">Álbum o Lista</label>
          <select class="form-select" id="seleccionContenido" required>
            <option selected disabled>Elige un álbum o lista...</option>
            <option value="album_1">Álbum 1</option>
            <option value="album_2">Álbum 2</option>
            <option value="lista_particular_1">Lista Particular 1</option>
          </select>
        </div>
      </form>
    </div>

    <!-- Selección del tema -->
    <div class="card">
      <h2>4. Selecciona un tema</h2>
      <form>
        <div class="mb-3">
          <label for="seleccionTema" class="form-label">Tema</label>
          <select class="form-select" id="seleccionTema" required>
            <option selected disabled>Elige un tema...</option>
            <option value="tema_1">Tema 1</option>
            <option value="tema_2">Tema 2</option>
            <option value="tema_3">Tema 3</option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Tema</button>
      </form>
    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
