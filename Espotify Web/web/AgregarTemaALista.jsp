<%@page import="Datatypes.DTTema"%>
<%@page import="Datatypes.DTAlbum"%>
<%@page import="java.util.List"%>
<%@page import="Datatypes.DTListaRep"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Aplicación</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function obtenerAlbumes() {
            $.ajax({
                url: 'SvObtenerAlbumTodos',
                type: 'GET',
                success: function(data) {
                    var html = '<h3>Álbumes:</h3><ul class="list-group">';
                    for (var i = 0; i < data.length; i++) {
                        html += '<li class="list-group-item">' + data[i].nombre + '</li>'; // Suponiendo que DTAlbum tiene un campo 'nombre'
                    }
                    html += '</ul>';
                    $('#resultadosAlbumes').html(html);
                },
                error: function() {
                    $('#resultadosAlbumes').html('<div class="alert alert-danger">Error al obtener los álbumes.</div>');
                }
            });
        }

        function obtenerListasRep() {
            $.ajax({
                url: 'SvObtenerTodasListasRep',
                type: 'GET',
                success: function(data) {
                    var html = '<h3>Listas de Reproducción:</h3><ul class="list-group">';
                    for (var i = 0; i < data.length; i++) {
                        html += '<li class="list-group-item">' + data[i].nombreListaRep + '</li>'; // Suponiendo que DTListaRep tiene un campo 'nombre'
                    }
                    html += '</ul>';
                    $('#resultadosListasRep').html(html);
                },
                error: function() {
                    $('#resultadosListasRep').html('<div class="alert alert-danger">Error al obtener las listas de reproducción.</div>');
                }
            });
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Bienvenido a la Aplicación</h1>
        
        <div class="text-center mb-4">
            <button class="btn btn-primary" onclick="obtenerAlbumes()">Obtener Álbumes</button>
        </div>
        <div id="resultadosAlbumes"></div>

        <div class="text-center mb-4">
            <button class="btn btn-primary" onclick="obtenerListasRep()">Obtener Listas de Reproducción</button>
        </div>
        <div id="resultadosListasRep"></div>
    </div>
</body>
</html>
