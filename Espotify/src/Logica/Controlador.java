
package Logica;

import Datatypes.DTAlbum;
import Datatypes.DTArtista;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.tree.TreeModel;


public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    //
    //
    
    // creamos cliente y recibe datos de GUI
    public void crearUsuario(DTUsuario user) throws Exception {

        Usuario nuevoUsuario;

        if (user instanceof DTArtista) {
            DTArtista artista = (DTArtista) user;
            nuevoUsuario = new Artista(
                    artista.getNickname(),
                    artista.getNombre(),
                    artista.getApellido(),
                    artista.getContrasenia(),
                    artista.getFechaNac(),
                    artista.getCorreo(),
                    artista.getBiografia(),
                    artista.getSitioWeb()
            );
            controlpersis.AddArtista((Artista) nuevoUsuario);
        } else {
            nuevoUsuario = new Cliente(
                    user.getNickname(),
                    user.getNombre(),
                    user.getApellido(),
                    user.getContrasenia(),
                    user.getCorreo(),
                    user.getFechaNac()
            );
            controlpersis.AddCliente((Cliente) nuevoUsuario);
        }
    }
    
    public boolean verificarExistenciaArtista(String correo) throws Exception {

    return controlpersis.findArtista(correo);
}
    
    public void CrearGenero(String nombre,String nombrePadre) throws Exception{
        // Buscar el género padre en la base de datos
    Genero padre = null;
    if (nombrePadre != null && !nombrePadre.isEmpty()) {
        padre = controlpersis.findGenerobynombre(nombrePadre);
        if (padre == null) {
            throw new Exception("El género padre no existe: " + nombrePadre);
        }
    }

    // Crear el nuevo género
    Genero nuevoGenero = new Genero(nombre, padre);

    // Añadir el nuevo género a la base de datos
    controlpersis.AddGenero(nuevoGenero);
    }

    public TreeModel buildGeneroTree() {
        return controlpersis.buildGeneroTree();
    }

    public void guardarAlbum(String correoArtista, DTAlbum nuevoAlbum, List<DTTema> listaTemas) throws Exception {
    Artista artista = buscarArtistaPorCorreo(correoArtista);
    if (artista == null) {
        throw new Exception("Artista no encontrado con el correo proporcionado.");
    }

    // Convertir DTAlbum a Album
    Album album = new Album();
    album.setNombre(nuevoAlbum.getNombre());
    album.setAnioCreacion(nuevoAlbum.getAnioCreacion());
    album.setImagen(nuevoAlbum.getImagen());
    album.setArtista(artista); // Asociar el álbum con el artista

    // Convertir los géneros desde DTAlbum a la lista de Genero en Album
    List<Genero> generos = nuevoAlbum.getListaGeneros().stream()
        .map(nombreGenero -> buscarGeneroPorNombre(nombreGenero))
        .collect(Collectors.toList());
    album.setListaGeneros(generos);

    // Persistir el álbum primero
    controlpersis.crearAlbum(album);

    // Contador para el orden de los temas
    final int[] maxOrden = {0};

    // Ahora convertir y asociar los temas al álbum
    List<Tema> temas = listaTemas.stream().map(dtTema -> {
        Tema tema = new Tema();
        tema.setNombre(dtTema.getNombre());
        tema.setDuracion(Duration.ofMinutes(dtTema.getMinutos()).plusSeconds(dtTema.getSegundos()));
        tema.setDireccion(dtTema.getDirectorio());
        tema.setAlbum(album); // Establecer la relación con el álbum
        tema.setOrden(++maxOrden[0]); // Asignar el orden e incrementar maxOrden
        return tema;
    }).collect(Collectors.toList());

    // Persistir los temas
    for (Tema tema : temas) {
        controlpersis.crearTema(tema);
    }

    // Actualizar el álbum con la lista de temas
    album.setListaTemas(temas);

    // Persistir el álbum con los temas asociados (si es necesario)
    controlpersis.actualizarAlbum(album);
}


private Artista buscarArtistaPorCorreo(String correo) throws Exception {
    // Implementa la lógica para buscar al artista por correo en tu base de datos.
    // Aquí se muestra un ejemplo, pero deberías usar tu controlador de persistencia.
    return controlpersis.findArtistaByCorreo(correo);
}

    
    @SuppressWarnings("empty-statement")
    private Genero buscarGeneroPorNombre(String nombreGenero) {
    
    Genero genero = controlpersis.findGenerobynombre(nombreGenero);;
    return genero;
}
    
   
}
