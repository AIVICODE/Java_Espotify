package Logica;

import Datatypes.DTAlbum;
import Datatypes.DTArtista;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import javax.swing.tree.TreeModel;

public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();

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

    public void CrearGenero(String nombre, String nombrePadre) throws Exception {
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

        try {
            Artista artista = buscarArtistaPorCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo proporcionado.");
            }
            if(controlpersis.findAlbumByNombre(nuevoAlbum.getNombre())!=null){
                throw new Exception("El album seleccionado ya existe");
            } else {
            }
            // Convertir DTAlbum a Album
            Album album = new Album();
            album.setNombre(nuevoAlbum.getNombre());
            album.setAnioCreacion(nuevoAlbum.getAnioCreacion());
            album.setImagen(nuevoAlbum.getImagen());
            album.setArtista(artista); // Asociar el álbum con el artista

     List<Genero> generos = new ArrayList<>();  // Lista para almacenar los géneros encontrados

for (String nombreGenero : nuevoAlbum.getListaGeneros()) {
    try {
        Genero genero = buscarGeneroPorNombre(nombreGenero);
        generos.add(genero);  // Agrega el género a la lista si se encuentra correctamente
    } catch (Exception e) {
        
        throw new Exception(e.getMessage());
    }
}

album.setListaGeneros(generos);  // Asigna la lista de géneros al álbum


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

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Artista buscarArtistaPorCorreo(String correo) throws Exception {
        // Implementa la lógica para buscar al artista por correo en tu base de datos.
        // Aquí se muestra un ejemplo, pero deberías usar tu controlador de persistencia.
        return controlpersis.findArtistaByCorreo(correo);
    }

    @SuppressWarnings("empty-statement")
private Genero buscarGeneroPorNombre(String nombreGenero) throws Exception {
    try {
        Genero genero = controlpersis.findGenerobynombre(nombreGenero);
        if(genero==null){
            throw new Exception();
        }
        return genero;
    } catch (Exception e) {
        throw new Exception("No se encontró el género con nombre: " + nombreGenero, e);
    }
}


    public void CrearListaRepGeneral(String nombre, String imagen, boolean privada) {

    }

    public void CrearListaRepParticular(String nombreLista, String correoCliente, String imagen, boolean privada) throws Exception {
        // Encuentra al cliente por su correo
        try {
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);

            if (cliente != null) {
                // Crear una nueva instancia de ListaRep
                ListaRep nuevaLista = new ListaRep();
                nuevaLista.setNombre(nombreLista);  // Asigna el nombre de la lista
                nuevaLista.setPrivada(privada);  // Configura si la lista es privada o no
                nuevaLista.setImagen(imagen);  // Asigna la imagen a la lista (si la propiedad existe)

                // Añadir la lista de reproducción a la lista del cliente
                cliente.getListaReproduccion().add(nuevaLista);

                // Guardar la nueva lista en la base de datos
                controlpersis.createListaRep(nuevaLista);

                // Actualizar el cliente en la base de datos si es necesario
                controlpersis.editCliente(cliente);
            } else {
                System.out.println("Cliente no encontrado con el correo proporcionado.");
                // O lanzar una excepción personalizada si prefieres
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public void GuardarTemaFavorito(String nombreCliente, String recurso) {
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void GuardarAlbumFavorito(String correoCliente, String recurso) throws Exception {
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el álbum por nombre
            Album album = controlpersis.findAlbumByNombre(recurso);
            if (album == null) {
                throw new Exception("Álbum no encontrado con el nombre: " + recurso);
            }

            // Agregar el álbum al cliente
            cliente.getAlbums().add(album);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void GuardarListaFavorito(String correoCliente, String recurso) throws Exception{
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            
            ListaRep listarep= controlpersis.findListaRepByNombre(recurso);
            if (listarep == null) {
                throw new Exception("Lista de reproduccion no encontrada con el nombre: " + recurso);
            }
            
            
            // PUEDE AGREGAR LISTAS FAVORITAS SOLO SI SON SUYAS O SOLO SI SON PUBLICAS
          if (!cliente.getListaReproduccion().contains(listarep)) {  
             if (listarep.isPrivada() == true) {
                    throw new Exception("Lista de reproduccion inaccesible para el usuario" + correoCliente);
                }
          }

if (cliente.getListaRepFavoritos().contains(listarep)) {
    throw new Exception("La lista de reproducción ya está en los favoritos del cliente.");
}
            // Agregar el álbum al cliente
            cliente.getListaRepFavoritos().add(listarep);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw e;
        }
    }
    
    
    
    public List<Cliente> listaClientes(){
        return controlpersis.listaClientes();//retorno la lista de personas de la BD
    }
    public Cliente encontrarCliente(String mail){
        return controlpersis.encontrarCliente(mail);//la persis me manda el cliente encontrado
    }
    
    public List<Artista> listaArtistas(){
        return controlpersis.listaArtistas();//retorno la lista de personas de la BD
    }
    public Artista encontrarArtista(String mail){
        return controlpersis.encontrarArtista(mail);//la persis me manda el cliente encontrado
    }
}
