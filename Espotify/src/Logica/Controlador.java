package Logica;

import Datatypes.DTAlbum;
import Datatypes.DTArtista;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Datatypes.DTSub;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Logica.Subscripcion.Estado;
import Persis.ControladoraPersistencia;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.tree.TreeModel;

public class Controlador implements IControlador {

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // month is 0-based in Calendar
        return calendar.getTime();
    }

    ControladoraPersistencia controlpersis = new ControladoraPersistencia();

    public void crearUsuario(DTUsuario user) throws Exception {
        try {

            if (controlpersis.findClienteByCorreo(user.getCorreo()) != null) {
                throw new Exception("Ya existe un cliente con el correo: " + user.getCorreo());
            }
            if (controlpersis.findArtistaByCorreo(user.getCorreo()) != null) {
                throw new Exception("Ya existe un artista con el correo: " + user.getCorreo());
            }

            if (controlpersis.findClienteByNickname(user.getNickname()) != null) {
                throw new Exception("Ya existe un cliente con el nickanme: " + user.getNickname());
            }

            if (controlpersis.findArtistaByNickname(user.getNickname()) != null) {
                throw new Exception("Ya existe un artista con el nickanme: " + user.getNickname());
            }
            
                            if(user.getCorreo().isEmpty() || user.getNickname().isEmpty() ){
                 throw new Exception("Correo o nickname vacios ");
                }
                
            if(!(user.getContrasenia().equals(user.getConfirmacion()))){
            throw new Exception("La contrasenia no coincide con su confirmacion");
            
            }
            
             if((user.getContrasenia().isEmpty())){
            throw new Exception("La contrasenia no puede estar vacia");
            
            }
                    

            if (user instanceof DTArtista) {
                Artista nuevoUsuario;
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
                Cliente nuevoUsuario;
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
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    public boolean verificarExistenciaArtista(String correo) throws Exception {

        return controlpersis.findArtista(correo);
    }

    public void CrearGenero(String nombre, String nombrePadre) throws Exception {
        // Buscar el género padre en la base de datos
        
        if(controlpersis.findGenerobynombre(nombre)!=null){
        throw new Exception("El género con nombre : " + nombre+" Ya existe");
        }
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

    public void CrearAlbum(String correoArtista, DTAlbum nuevoAlbum, List<DTTema> listaTemas) throws Exception {

        try {
            Artista artista = buscarArtistaPorCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo proporcionado.");
            }
            if (listaTemas == null || listaTemas.isEmpty()) {
                throw new Exception("No se han proporcionado temas para el álbum.");
            }

            boolean albumExiste = false;
            for (Album album : artista.getAlbumes()) {
                if (album.getNombre().equals(nuevoAlbum.getNombre())) {
                    albumExiste = true;
                    break;
                }
            }

            if (albumExiste) {
                throw new Exception("El nombre del álbum seleccionado ya existe para este artista");
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

            // Verificación de nombres de temas duplicados dentro del álbum
            Set<String> nombresTemasUnicos = new HashSet<>();

            // Ahora convertir y asociar los temas al álbum
            List<Tema> temas = listaTemas.stream().map(dtTema -> {
                if (!nombresTemasUnicos.add(dtTema.getNombre())) {
                    // Si el nombre ya existe, lanza una excepción
                    throw new RuntimeException("Insertaste temas  con nombre duplicados para este album.");
                }

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
            if (genero == null) {
                throw new Exception();
            }
            return genero;
        } catch (Exception e) {
            throw new Exception("No se encontró el género con nombre: " + nombreGenero, e);
        }
    }

    public void CrearListaRepGeneral(String nombreLista, String imagen,String nomGenero) {
// Encuentra al cliente por su correo
        try {
            // Crear una nueva instancia de ListaRep
            ListaRepGeneral nuevaLista = new ListaRepGeneral();
            nuevaLista.setNombre(nombreLista);  // Asigna el nombre de la lista
            nuevaLista.setImagen(imagen);  // Asigna la imagen a la lista (si la propiedad existe)
            nuevaLista.setGenero(buscarGeneroPorNombre(nomGenero));

            // Guardar la nueva lista en la base de datos
            controlpersis.createListaRep(nuevaLista);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void CrearListaRepParticular(String nombreLista, String correoCliente, String imagen, boolean privada) throws Exception {
        // Encuentra al cliente por su correo
        try {
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);

            if (cliente != null) {
                // Crear una nueva instancia de ListaRep
                ListaRepParticular nuevaLista = new ListaRepParticular();
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

    public void GuardarTemaFavorito(String correoCliente, String correoArtista, String nombreAlbum, String nombreTema) throws Exception {
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el artista por correo
            Artista artista = controlpersis.findArtistaByCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo: " + correoArtista);
            }

            // Buscar el álbum por nombre dentro del artista
            Album albumEncontrado = null;
            for (Album album : artista.getAlbumes()) {
                if (album.getNombre().equals(nombreAlbum)) {
                    albumEncontrado = album;
                    break;
                }
            }

            if (albumEncontrado == null) {
                throw new Exception("Álbum no encontrado con el nombre: " + nombreAlbum + " para el artista: " + correoArtista);
            }

            // Buscar el tema por nombre dentro del álbum
            Tema temaEncontrado = null;
            for (Tema tema : albumEncontrado.getListaTemas()) {
                if (tema.getNombre().equals(nombreTema)) {
                    temaEncontrado = tema;
                    break;
                }
            }

            if (temaEncontrado == null) {
                throw new Exception("Tema no encontrado con el nombre: " + nombreTema + " en el álbum: " + nombreAlbum);
            }

            if (cliente.getTemas().contains(temaEncontrado)) {
                throw new Exception("El tema ya está marcado como favorito.");
            }
            // Agregar el tema al cliente
            cliente.getTemas().add(temaEncontrado);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void GuardarAlbumFavorito(String correoCliente, String correoArtista, String nombreAlbum) throws Exception {
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el artista por correo
            Artista artista = controlpersis.findArtistaByCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo: " + correoArtista);
            }

            // Buscar el álbum por nombre dentro del artista
            Album albumEncontrado = null;
            for (Album album : artista.getAlbumes()) {
                if (album.getNombre().equals(nombreAlbum)) {
                    albumEncontrado = album;
                    break;
                }
            }

            if (cliente.getAlbums().contains(albumEncontrado)) {
                throw new Exception("El álbum ya está marcado como favorito.");
            }

            if (albumEncontrado == null) {
                throw new Exception("Álbum no encontrado con el nombre: " + nombreAlbum + " para el artista: " + correoArtista);
            }

            // Agregar el álbum al cliente
            cliente.getAlbums().add(albumEncontrado);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void GuardarListaFavorito(String correoCliente, String correo_Cliente_Con_Lista, String nombreLista) throws Exception {
        try {
            // Buscar el cliente que quiere guardar la lista como favorita
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el cliente que posee la lista
            Cliente clienteConLista = controlpersis.findClienteByCorreo(correo_Cliente_Con_Lista);
            if (clienteConLista == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correo_Cliente_Con_Lista);
            }

            // Buscar la lista por nombre dentro del cliente que posee la lista
            ListaRepParticular listaEncontrada = null;
            for (ListaRep lista : clienteConLista.getListaReproduccion()) {
                // Verificar si la lista es una instancia de ListaRepParticular
                if (lista instanceof ListaRepParticular) {
                    ListaRepParticular listaParticular = (ListaRepParticular) lista;

                    // Comparar el nombre de la lista
                    if (listaParticular.getNombre().equals(nombreLista)) {
                        listaEncontrada = listaParticular;
                        break;
                    }
                }
            }

            if (listaEncontrada == null) {
                throw new Exception("Lista no encontrada con el nombre: " + nombreLista + " para el cliente: " + correo_Cliente_Con_Lista);
            }
            if (listaEncontrada.isPrivada() == true) {

                throw new Exception("Intentas acceder  a una lista privada");
            }
            for (ListaRep l : cliente.getListaRepFavoritos()) {//agregado nuevo 21 del 9
                if (l.getNombre().equals(nombreLista)) {//si quiere agregar una lista que ya esta agregada en los favs del cliente
                    throw new Exception("La lista: '" + nombreLista + "' ya se encuentra en los favoritos del cliente: " + correoCliente);
                }
            }

            // Agregar la lista al cliente que la quiere guardar como favorita
            cliente.getListaRepFavoritos().add(listaEncontrada);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void GuardarLista_Por_Defecto_Favorito(String correoCliente, String nombreLista) throws Exception {
        try {
            // Buscar el cliente que quiere guardar la lista por defecto como favorita
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar la lista por defecto (ListaRepGeneral) por nombre directamente desde la base de datos
            ListaRepGeneral listaPorDefecto = controlpersis.findListaRep_Por_Defecto_ByNombre(nombreLista);
            if (listaPorDefecto == null) {
                throw new Exception("Lista por defecto no encontrada con el nombre: " + nombreLista);
            }

            // Verificar si la lista ya está marcada como favorita por el cliente
            for (ListaRep listaFavorita : cliente.getListaRepFavoritos()) {
                if (listaFavorita.getId().equals(listaPorDefecto.getId())) {
                    throw new Exception("La lista ya está marcada como favorita.");
                }
            }

            // Agregar la lista por defecto al cliente como favorita
            cliente.getListaRepFavoritos().add(listaPorDefecto);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void EliminarAlbumFavorito(String correoCliente, String correoArtista, String nombreAlbum) throws Exception {
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }
            if(correoArtista==null){
            throw new Exception("Debe seleccionar un artista");
            
            }
            // Buscar el artista por correo
            Artista artista = controlpersis.findArtistaByCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo: " + correoArtista);
            }

            // Buscar el álbum por nombre dentro del artista
            Album albumEncontrado = null;
            for (Album album : artista.getAlbumes()) {
                if (album.getNombre().equals(nombreAlbum)) {
                    albumEncontrado = album;
                    break;
                }
            }

            if (albumEncontrado == null) {
                throw new Exception("Álbum no encontrado con el nombre: " + nombreAlbum + " para el artista: " + correoArtista);
            }

            // Verificar si el álbum está en los favoritos del cliente
            if (!cliente.getAlbums().contains(albumEncontrado)) {
                throw new Exception("El álbum no está marcado como favorito.");
            }

            // Eliminar el álbum de los favoritos del cliente
            cliente.getAlbums().remove(albumEncontrado);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void EliminarTemaFavorito(String correoCliente, String correoArtista, String nombreAlbum, String nombreTema) throws Exception {
        try {
            // Buscar el cliente por correo
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el artista por correo
            Artista artista = controlpersis.findArtistaByCorreo(correoArtista);
            if (artista == null) {
                throw new Exception("Artista no encontrado con el correo: " + correoArtista);
            }

            // Buscar el álbum por nombre dentro del artista
            Album albumEncontrado = null;
            for (Album album : artista.getAlbumes()) {
                if (album.getNombre().equals(nombreAlbum)) {
                    albumEncontrado = album;
                    break;
                }
            }

            if (albumEncontrado == null) {
                throw new Exception("Álbum no encontrado con el nombre: " + nombreAlbum + " para el artista: " + correoArtista);
            }

            // Buscar el tema por nombre dentro del álbum
            Tema temaEncontrado = null;
            for (Tema tema : albumEncontrado.getListaTemas()) {
                if (tema.getNombre().equals(nombreTema)) {
                    temaEncontrado = tema;
                    break;
                }
            }

            if (temaEncontrado == null) {
                throw new Exception("Tema no encontrado con el nombre: " + nombreTema + " en el álbum: " + nombreAlbum);
            }

            // Verificar si el tema está en los favoritos del cliente
            if (!cliente.getTemas().contains(temaEncontrado)) {
                throw new Exception("El tema no está marcado como favorito.");
            }

            // Eliminar el tema de los favoritos del cliente
            cliente.getTemas().remove(temaEncontrado);

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void EliminarListaFavorito(String correoCliente, String correo_Cliente_Con_Lista, String nombreLista) throws Exception {
        try {
            // Buscar el cliente que tiene la lista marcada como favorita
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar el cliente que posee la lista original
            Cliente clienteConLista = controlpersis.findClienteByCorreo(correo_Cliente_Con_Lista);
            if (clienteConLista == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correo_Cliente_Con_Lista);
            }

            // Buscar la lista por nombre dentro del cliente que posee la lista
            ListaRepParticular listaEncontrada = null;
            for (ListaRep lista : clienteConLista.getListaReproduccion()) {
                if (lista instanceof ListaRepParticular) {
                    ListaRepParticular listaParticular = (ListaRepParticular) lista;
                    if (listaParticular.getNombre().equals(nombreLista)) {
                        listaEncontrada = listaParticular;
                        break;
                    }
                }
            }

            if (listaEncontrada == null) {
                throw new Exception("Lista no encontrada con el nombre: " + nombreLista + " para el cliente: " + correo_Cliente_Con_Lista);
            }

            // Crear una variable final para el nombre de la lista encontrada
            final String nombreListaEncontrada = listaEncontrada.getNombre();

            // Verificar si la lista está en los favoritos del cliente
            boolean esFavorita = cliente.getListaRepFavoritos().stream()
                    .anyMatch(listaFavorita -> listaFavorita.getNombre().equals(nombreListaEncontrada));

            if (!esFavorita) {
                throw new Exception("La lista no está marcada como favorita.");
            }

            // Eliminar la lista de los favoritos del cliente
            cliente.getListaRepFavoritos().removeIf(listaFavorita
                    -> listaFavorita.getNombre().equals(nombreLista)); // Compara por nombre

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public void EliminarLista_Por_Defecto_Favorito(String correoCliente, String nombreLista) throws Exception {
        try {
            // Buscar el cliente que quiere eliminar la lista por defecto de sus favoritos
            Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
            }

            // Buscar la lista por defecto (ListaRepGeneral) por nombre dentro de las listas del cliente
            ListaRepGeneral listaPorDefecto = null;
            for (ListaRep lista : cliente.getListaRepFavoritos()) {

                ListaRepGeneral listaGeneral = (ListaRepGeneral) lista;

                // Imprimir la lista que está siendo evaluada y el nombre que se busca

                if (listaGeneral.getNombre().equals(nombreLista)) {
                    listaPorDefecto = listaGeneral;
                    break;
                }

            }

            if (listaPorDefecto == null) {
                throw new Exception("Lista por defecto no encontrada con el nombre: " + nombreLista);
            }

            // Verificar si la lista por defecto está en los favoritos del cliente
            final String nombreListaEncontrada = listaPorDefecto.getNombre(); // Hacerla final para usar en la lambda
            boolean esFavorita = cliente.getListaRepFavoritos().stream()
                    .anyMatch(listaFavorita -> {
                        // Imprimir las listas favoritas que se comparan
                        return listaFavorita.getNombre().equals(nombreListaEncontrada);
                    });

            if (!esFavorita) {
                throw new Exception("La lista no está marcada como favorita.");
            }

            // Eliminar la lista por defecto de los favoritos del cliente comparando por nombre
            cliente.getListaRepFavoritos().removeIf(listaFavorita
                    -> listaFavorita.getNombre().equals(nombreListaEncontrada)); // Compara por nombre

            // Guardar los cambios en la base de datos
            controlpersis.editCliente(cliente);

        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }

    public List<String> MostrarNombreClientes() {
        List<Cliente> listaClientes = listaClientes(); // Supongamos que este método devuelve todos los clientes
        List<String> listaCorreos = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            listaCorreos.add(cliente.getMail()); // Añades el correo de cada cliente a la lista
        }

        return listaCorreos; // Devuelves la lista de correos
    }

    public List<Cliente> listaClientes() {
        return controlpersis.listaClientes();//retorno la lista de personas de la BD
    }

    public Cliente encontrarCliente(String mail) {
        return controlpersis.encontrarCliente(mail);//la persis me manda el cliente encontrado
    }

    public List<Artista> listaArtistas() {
        return controlpersis.listaArtistas();//retorno la lista de personas de la BD
    }

    public Artista encontrarArtista(String mail) {
        return controlpersis.encontrarArtista(mail);//la persis me manda el cliente encontrado
    }

    public void seguirUsuario(String correoSeguidor, String correoSeguido) throws Exception {
        Cliente seguidor = encontrarCliente(correoSeguidor);
        Cliente cSeguido = encontrarCliente(correoSeguido);
        Artista aSeguido = encontrarArtista(correoSeguido);
        if (seguidor != null) {
            if (correoSeguidor.equals(correoSeguido)) {
                throw new IllegalArgumentException("El cliente " + correoSeguidor + " no puede seguirse a si mismo ");
            }
            for (Cliente c : seguidor.getClientesSeguidos()) {//21/9 nuevo
                if (c.getMail().equals(correoSeguido)) {
                    throw new IllegalArgumentException("El cliente " + correoSeguidor + " ya sigue al cliente " + correoSeguido);
                }
            }
            for (Artista a : seguidor.getArtistasSeguidos()) {
                if (a.getMail().equals(correoSeguido)) {
                    throw new IllegalArgumentException("El cliente " + correoSeguidor + " ya sigue al artista " + correoSeguido);
                }
            }//////////////
            if (cSeguido != null) {
                seguidor.seguirCliente(cSeguido);
                controlpersis.editCliente(seguidor);
            } else if (aSeguido != null) {
                seguidor.seguirArtista(aSeguido);
                controlpersis.editCliente(seguidor);
            } else {
                throw new IllegalArgumentException("No se encontró Cliente o Artista con el correo: " + correoSeguido);
            }

        } else {
            throw new IllegalArgumentException("No se encontró el seguidor con el correo: " + correoSeguidor);
        }
    }

    public void dejarSeguirUsuario(String correoSeguidor, String correoSeguido) throws Exception {
        if (correoSeguido == null){//si se quiere dejar de seguir con la lista vacia
            throw new IllegalArgumentException("No se encontraron seguidos para el usuario " + correoSeguidor);
        }
        try {
            Cliente seguidor = encontrarCliente(correoSeguidor);
            Cliente cSeguido = encontrarCliente(correoSeguido);
            Artista aSeguido = encontrarArtista(correoSeguido);
            if (seguidor != null) {
                if (cSeguido != null) {
                    seguidor.dejarDeSeguirCliente(cSeguido);
                    controlpersis.editCliente(seguidor);
                } else if (aSeguido != null) {
                    seguidor.dejarDeSeguirArtista(aSeguido);
                    controlpersis.editCliente(seguidor);
                } else {
                    throw new IllegalArgumentException("No se encontró Cliente o Artista con el correo: " + correoSeguido);
                }
            } else {
                throw new IllegalArgumentException("No se encontró el seguidor con el correo: " + correoSeguidor);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error ");
        }
    }

    public Cliente encontrarClientePorNicknameTipoCli(String nickname) {
        try {
            return controlpersis.findClienteByNickname(nickname); // Llama al método del controlador de persistencia
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Artista encontrarArtistaPorNicknameTipoArt(String nickname) {
        try {
            return controlpersis.findArtistaByNickname(nickname);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void Cargar_Datos_Prueba() throws Exception {
        //modifico la tabla de artista
        controlpersis.modificarBiografiaArtista();
        
        Cargar_Perfiles();
        Cargar_Generos();
        Cargar_Albumes();
        Cargar_Seguidores();
        Cargar_Listas();
        CargarFavoritos();
        PruebaSubs();
    }

    private void Cargar_Perfiles() {
        try {
            // Artistas
            Artista artista1 = new Artista(
                    "vpeople", "Village", "People", "pass123",
                    createDate(1977, 1, 1), "vpeople@tuta.io",
                    "Village People es una innovadora formación musical de estilo disco de finales de los años 70. Fue famosa tanto por sus peculiares disfraces, como por sus canciones pegadizas, con letras sugerentes y llenas de dobles sentidos.",
                    "www.officialvillagepeople.com",
                    "bit.ly/vpeople"
            );
            Artista artista2 = new Artista(
                    "dmode", "Depeche", "Mode", "pass456",
                    createDate(1980, 6, 14), "dmode@tuta.io",
                    "",
                    "www.depechemode.com",
                    "bit.ly/depecheMode"
            );
            Artista artista3 = new Artista(
                    "clauper", "Cyndi", "Lauper", "pass789",
                    createDate(1953, 6, 22), "clauper@hotmail.com",
                    "Cynthia Ann Stephanie Lauper, conocida simplemente como Cyndi Lauper, es una cantautora, actriz y empresaria estadounidense. Después de participar en el grupo musical Blue Angel, en 1983 firmó con Portrait Records y lanzó su exitoso álbum debut *She's So Unusual* a finales de ese mismo año.",
                    "cyndilauper.com",
                    "bit.ly/cLauper"
            );
            Artista artista4 = new Artista(
                    "bruceTheBoss", "Bruce", "Springsteen", "pass101",
                    createDate(1949, 9, 23), "bruceTheBoss@gmail.com",
                    "",
                    "brucespringsteen.net",
                    "bit.ly/bruceTheBoss"
            );
            Artista artista5 = new Artista(
                    "tripleNelson", "La Triple", "Nelson", "pass202",
                    createDate(1998, 1, 1), "tripleNelson@tuta.io",
                    "La Triple Nelson es un grupo de rock uruguayo formado en enero de 1998.",
                    "", // No tiene página web
                    "bit.ly/tripleNelson"
            );
            Artista artista6 = new Artista(
                    "la_ley", "La", "Ley", "pass303",
                    createDate(1987, 2, 14), "la_ley@tuta.io",
                    "", "", // No hay biografía ni sitio web provisto
                    "bit.ly/laLey"
            );
            Artista artista7 = new Artista(
                    "tigerOfWales", "Tom", "Jones", "pass404",
                    createDate(1940, 6, 7), "tigerOfWales@tuta.io",
                    "Sir Thomas John, conocido por su nombre artístico de Tom Jones, es un cantante británico. Ha vendido más de 100 millones de discos en todo el mundo.",
                    "www.tomjones.com",
                    "bit.ly/tigerOfWales"
            );
            Artista artista8 = new Artista(
                    "chaiko", "Piotr", "Tchaikovsky", "pass505",
                    createDate(1840, 4, 25), "chaiko@tuta.io",
                    "Piotr Ilich Chaikovski fue un compositor ruso del período del Romanticismo.",
                    "", // No tiene página web
                    "" // No tiene imagen provista
            );
            Artista artista9 = new Artista(
                    "nicoleneu", "Nicole", "Neumann", "pass606",
                    createDate(1980, 10, 31), "nicoleneu@hotmail.com",
                    "", // No hay biografía provista
                    "", // No tiene página web
                    "bit.ly/nicoleneu"
            );
            Artista artista10 = new Artista(
                    "lospimpi", "Pimpinela", "", "pass707",
                    createDate(1981, 8, 13), "lospimpi@gmail.com",
                    "",
                    "www.pimpinela.net",
                    "bit.ly/losPimpinela"
            );
            Artista artista11 = new Artista(
                    "dyangounchained", "Dyango", "", "pass808",
                    createDate(1940, 3, 5), "dyangounchained@gmail.com",
                    "José Gómez Romero, conocido artísticamente como Dyango, es un cantante español de música romántica.",
                    "", // No tiene página web
                    "" // No tiene imagen provista
            );
            Artista artista12 = new Artista(
                    "alcides", "Alcides", "", "pass909",
                    createDate(1952, 7, 17), "alcides@tuta.io",
                    "Su carrera comienza en 1976 cuando forma la banda Los Playeros junto a su hermano.",
                    "", // No tiene página web
                    "" // No tiene imagen provista
            );

            // Clientes con imágenes
            Cliente cliente1 = new Cliente(
                    "cel_padrino", "Vito", "Corleone", "pass789",
                    "el_padrino@tuta.io", createDate(1972, 3, 8),
                    "bit.ly/vitoCorleone"
            );
            Cliente cliente2 = new Cliente(
                    "scarlettO", "Scarlett", "O’Hara", "pass101",
                    "scarlettO@tuta.io", createDate(1984, 11, 27),
                    "bit.ly/scarlettO"
            );
            Cliente cliente3 = new Cliente(
                    "ppArgento", "Pepe", "Argento", "pass202",
                    "ppArgento@hotmail.com", createDate(1955, 2, 14),
                    "bit.ly/ppArgento"
            );
            Cliente cliente4 = new Cliente(
                    "Heisenberg", "Walter", "White", "pass303",
                    "Heisenberg@tuta.io", createDate(1956, 3, 7),
                    "bit.ly/heisenbergWW"
            );
            Cliente cliente5 = new Cliente(
                    "benKenobi", "Obi-Wan", "Kenobi", "pass404",
                    "benKenobi@gmail.com", createDate(1914, 4, 2),
                    "bit.ly/benKenobi"
            );
            Cliente cliente6 = new Cliente(
                    "lachiqui", "Mirtha", "Legrand", "pass505",
                    "lachiqui@hotmail.com.ar", createDate(1927, 2, 23),
                    "bit.ly/laChiqui"
            );
            Cliente cliente7 = new Cliente(
                    "cbochinche", "Cacho", "Bochinche", "pass606",
                    "cbochinche@vera.com.uy", createDate(1937, 5, 8),
                    "bit.ly/cbochinche"
            );
            Cliente cliente8 = new Cliente(
                    "Eleven11", "Eleven", "", "pass707",
                    "Eleven11@gmail.com", createDate(1971, 12, 31),
                    "bit.ly/11Eleven11"
            );

            // Agregar Artistas
            controlpersis.AddArtista(artista1);
            controlpersis.AddArtista(artista2);
            controlpersis.AddArtista(artista3);
            controlpersis.AddArtista(artista4);
            controlpersis.AddArtista(artista5);
            controlpersis.AddArtista(artista6);
            controlpersis.AddArtista(artista7);
            controlpersis.AddArtista(artista8);
            controlpersis.AddArtista(artista9);
            controlpersis.AddArtista(artista10);
            controlpersis.AddArtista(artista11);
            controlpersis.AddArtista(artista12);

            // Agregar Clientes
            controlpersis.AddCliente(cliente1);
            controlpersis.AddCliente(cliente2);
            controlpersis.AddCliente(cliente3);
            controlpersis.AddCliente(cliente4);
            controlpersis.AddCliente(cliente5);
            controlpersis.AddCliente(cliente6);
            controlpersis.AddCliente(cliente7);
            controlpersis.AddCliente(cliente8);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Cargar_Generos() throws Exception {
        CrearGenero("Rock", "");
        CrearGenero("Rock Clásico", "Rock");
        CrearGenero("Rock Latino", "Rock");
        CrearGenero("Rock & Roll", "Rock");
        CrearGenero("Clásica", "");
        CrearGenero("Disco", "");
        CrearGenero("Pop", "");
        CrearGenero("Electropop", "Pop");
        CrearGenero("Dance-pop", "Pop");
        CrearGenero("Pop Clásico", "Pop");
        CrearGenero("Balada", "");
        CrearGenero("Cumbia", "");
    }

    private void Cargar_Albumes() throws Exception {

        Album1();
        Album2();
        Album3();
        Album4();
        Album5();
        Album6();
        Album7();
        Album8();
        Album9();
        Album10();
        Album11();
        Album12();
        Album13();
    }

    private void Album1() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Disco"));
                generos.add(buscarGeneroPorNombre("Dance-pop"));
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Live and Sleazy");
            nuevoAlbum.setImagen("Imagen");
            nuevoAlbum.setAnioCreacion(2003);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("vpeople@tuta.io"));
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el album");
            }
            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("YMCA", 4, 28, "bit.ly/SCvpymca"));
            temas.add(new Tema("Macho Man", 3, 28, "picosong.com/download/zf8T"));
            temas.add(new Tema("In the Navy", 3, 13, "bit.ly/SCvpinthenavy"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar datos de prueba: " + e.getMessage());
        }
    }

    private void Album2() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Electropop"));

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Violator");
            nuevoAlbum.setImagen("Imagen_Violator");
            nuevoAlbum.setAnioCreacion(1990);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("dmode@tuta.io"));
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el album");
            }
            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();

            temas.add(new Tema("Personal Jesus", 4, 56, "picosong.com/download/zfQ3"));
            temas.add(new Tema("Enjoy the Silence", 6, 12, "picosong.com/download/zfQX"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Violator': " + e.getMessage());
        }
    }

    private void Album3() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Dance-pop"));
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("She’s So Unusual");
            nuevoAlbum.setImagen("Imagen_She’s_So_Unusual");
            nuevoAlbum.setAnioCreacion(1983);  // Fecha ejemplo, ajusta si es necesario
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("clauper@hotmail.com"));
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el album");
            }
            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Girls Just Want To Have Fun", 3, 15, "bit.ly/shesunusual"));
            temas.add(new Tema("Time After Time", 5, 12, ""));  // No hay URL para este tema

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'She’s So Unusual': " + e.getMessage());
        }
    }

    private void Album4() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Rock Clásico"));
                generos.add(buscarGeneroPorNombre("Rock & Roll"));
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Born In The U.S.A.");
            nuevoAlbum.setImagen("Imagen_Born_In_The_USA");
            nuevoAlbum.setAnioCreacion(1984);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("bruceTheBoss@gmail.com"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Born In The U.S.A.", 4, 58, "picosong.com/download/zf86"));
            temas.add(new Tema("Glory Days", 5, 23, "bit.ly/SCbsglorydays"));
            temas.add(new Tema("Dancing In The Dark", 3, 58, "bit.ly/SCbsborninusa"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Born In The U.S.A.': " + e.getMessage());
        }
    }

    private void Album5() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Rock Clásico"));
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("It’s Not Unusual");
            nuevoAlbum.setImagen("Imagen_It’s_Not_Unusual");
            nuevoAlbum.setAnioCreacion(1965);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("tigerOfWales@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("It’s Not Unusual", 2, 0, "picosong.com/download/zfbS"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'It’s Not Unusual': " + e.getMessage());
        }
    }

    private void Album6() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Rock Latino"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Agua Y Sal");
            nuevoAlbum.setImagen("Imagen_Agua_Y_Sal");
            nuevoAlbum.setAnioCreacion(2012);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("tripleNelson@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Adagio De Mi País", 4, 50, "bit.ly/SCtnadagiopais"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Agua Y Sal': " + e.getMessage());
        }
    }

    private void Album7() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Rock Latino"));
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("MTV Unplugged");
            nuevoAlbum.setImagen("Imagen_MTV_Unplugged");
            nuevoAlbum.setAnioCreacion(2001);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("la_ley@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("El Duelo", 5, 23, "picosong.com/download/zfh6"));
            temas.add(new Tema("Mentira", 4, 48, "picosong.com/download/zfAe"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'MTV Unplugged': " + e.getMessage());
        }
    }

    private void Album8() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Clásica"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("El Lago De Los Cisnes");
            nuevoAlbum.setImagen("Imagen_El_Lago_De_Los_Cisnes");
            nuevoAlbum.setAnioCreacion(1875);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("chaiko@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Acto 2, Número 10, Escena (Moderato)", 2, 40, "bit.ly/SCptswanlake"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'El Lago De Los Cisnes': " + e.getMessage());
        }
    }

    private void Album9() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Clásica"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Concierto Para Piano No. 1");
            nuevoAlbum.setImagen("Imagen_Concierto_Para_Piano_No_1");
            nuevoAlbum.setAnioCreacion(1875);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("chaiko@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)", 21, 58, "bit.ly/SCptpiano"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Concierto Para Piano No. 1': " + e.getMessage());
        }
    }

    private void Album10() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Electropop"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Primer Amor");
            nuevoAlbum.setImagen("Imagen_Primer_Amor");
            nuevoAlbum.setAnioCreacion(1994);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("nicoleneu@hotmail.com"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("No Quiero Estudiar", 2, 12, "picosong.com/download/zfZN"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Primer Amor': " + e.getMessage());
        }
    }

    private void Album11() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
                generos.add(buscarGeneroPorNombre("Balada"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Hay Amores Que Matan");
            nuevoAlbum.setImagen("Imagen_Hay_Amores_Que_Matan");
            nuevoAlbum.setAnioCreacion(1993);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("lospimpi@gmail.com"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Por Ese Hombre", 4, 45, "picosong.com/download/zfa4"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Hay Amores Que Matan': " + e.getMessage());
        }
    }

    private void Album12() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Pop Clásico"));
                generos.add(buscarGeneroPorNombre("Balada"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("Un Loco Como Yo");
            nuevoAlbum.setImagen("Imagen_Un_Loco_Como_Yo");
            nuevoAlbum.setAnioCreacion(1993);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("dyangounchained@gmail.com"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Por Ese Hombre", 5, 13, "bit.ly/SCdyporesehombre"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum 'Un Loco Como Yo': " + e.getMessage());
        }
    }

    private void Album13() throws Exception {
        try {
            // Crear lista de géneros para el álbum
            List<Genero> generos = new ArrayList<>();
            try {
                generos.add(buscarGeneroPorNombre("Cumbia"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            // Crear el álbum
            Album nuevoAlbum = new Album();
            nuevoAlbum.setNombre("20 Grandes Éxitos");
            nuevoAlbum.setImagen("Imagen_20_Grandes_Exitos");
            nuevoAlbum.setAnioCreacion(1989);
            nuevoAlbum.setListaGeneros(generos);
            nuevoAlbum.setArtista(buscarArtistaPorCorreo("alcides@tuta.io"));

            // Verificar si el artista existe
            if (nuevoAlbum.getArtista() == null) {
                throw new Exception("El artista seleccionado no existe para el álbum");
            }

            // Persistir el álbum primero
            controlpersis.crearAlbum(nuevoAlbum);

            // Crear lista de temas para el álbum
            List<Tema> temas = new ArrayList<>();
            temas.add(new Tema("Violeta", 1, 56, "bit.ly/SCvioleta"));

            // Asociar los temas con el álbum
            for (Tema tema : temas) {
                tema.setAlbum(nuevoAlbum);  // Asocia cada tema con el álbum
            }
            nuevoAlbum.setListaTemas(temas);

            // Persistir los temas
            for (Tema tema : temas) {
                controlpersis.crearTema(tema);
            }

            // Actualizar el álbum con la lista de temas
            controlpersis.actualizarAlbum(nuevoAlbum);

        } catch (Exception e) {
            throw new Exception("Error al cargar el álbum '20 Grandes Éxitos': " + e.getMessage());
        }
    }

    public List<String> MostrarNombreArtistas() {
        List<Artista> listaArtista = listaArtistas(); // Supongamos que este método devuelve todos los clientes

        List<String> listaCorreos = new ArrayList<>();

        for (Artista artista : listaArtista) {
            listaCorreos.add(artista.getMail()); // Añades el correo de cada cliente a la lista
        }

        return listaCorreos; // Devuelves la lista de correos
    }

    private void Cargar_Seguidores() throws Exception {
        try {
            //cliente1 

            seguirUsuario("el_padrino@tuta.io", "vpeople@tuta.io");
            seguirUsuario("el_padrino@tuta.io", "dmode@tuta.io");
            seguirUsuario("el_padrino@tuta.io", "clauper@hotmail.com");
            seguirUsuario("el_padrino@tuta.io", "benKenobi@gmail.com");
            seguirUsuario("el_padrino@tuta.io", "lachiqui@hotmail.com.ar");
            seguirUsuario("el_padrino@tuta.io", "cbochinche@vera.com.uy");
            seguirUsuario("el_padrino@tuta.io", "Eleven11@gmail.com");

            //cliente 2
            seguirUsuario("scarlettO@tuta.io", "tigerOfWales@tuta.io");
            seguirUsuario("scarlettO@tuta.io", "dmode@tuta.io");
            seguirUsuario("scarlettO@tuta.io", "bruceTheBoss@gmail.com");
            seguirUsuario("scarlettO@tuta.io", "tripleNelson@tuta.io");
            seguirUsuario("scarlettO@tuta.io", "Heisenberg@tuta.io");
            seguirUsuario("scarlettO@tuta.io", "benKenobi@gmail.com");
            seguirUsuario("scarlettO@tuta.io", "lachiqui@hotmail.com.ar");

            //cliente 3
            seguirUsuario("ppArgento@hotmail.com", "dmode@tuta.io");
            seguirUsuario("ppArgento@hotmail.com", "bruceTheBoss@gmail.com");
            seguirUsuario("ppArgento@hotmail.com", "tripleNelson@tuta.io");
            seguirUsuario("ppArgento@hotmail.com", "benKenobi@gmail.com");
            seguirUsuario("ppArgento@hotmail.com", "lachiqui@hotmail.com.ar");
            seguirUsuario("ppArgento@hotmail.com", "cbochinche@vera.com.uy");
            seguirUsuario("ppArgento@hotmail.com", "Eleven11@gmail.com");

            //cliente 4
            seguirUsuario("Heisenberg@tuta.io", "dmode@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "bruceTheBoss@gmail.com");
            seguirUsuario("Heisenberg@tuta.io", "tigerOfWales@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "tripleNelson@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "lospimpi@gmail.com");
            seguirUsuario("Heisenberg@tuta.io", "dyangounchained@gmail.com");
            seguirUsuario("Heisenberg@tuta.io", "alcides@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "el_padrino@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "scarlettO@tuta.io");
            seguirUsuario("Heisenberg@tuta.io", "ppArgento@hotmail.com");
            seguirUsuario("Heisenberg@tuta.io", "benKenobi@gmail.com");
            seguirUsuario("Heisenberg@tuta.io", "lachiqui@hotmail.com.ar");
            seguirUsuario("Heisenberg@tuta.io", "Eleven11@gmail.com");

//       //cliente 5
            seguirUsuario("benKenobi@gmail.com", "dmode@tuta.io");
            seguirUsuario("benKenobi@gmail.com", "bruceTheBoss@gmail.com");
            seguirUsuario("benKenobi@gmail.com", "la_ley@tuta.io");
            seguirUsuario("benKenobi@gmail.com", "chaiko@tuta.io");
            seguirUsuario("benKenobi@gmail.com", "nicoleneu@hotmail.com");
            seguirUsuario("benKenobi@gmail.com", "lospimpi@gmail.com");
            seguirUsuario("benKenobi@gmail.com", "alcides@tuta.io");
            seguirUsuario("benKenobi@gmail.com", "el_padrino@tuta.io");
            seguirUsuario("benKenobi@gmail.com", "ppArgento@hotmail.com");
            seguirUsuario("benKenobi@gmail.com", "lachiqui@hotmail.com.ar");
            seguirUsuario("benKenobi@gmail.com", "cbochinche@vera.com.uy");
            seguirUsuario("benKenobi@gmail.com", "Eleven11@gmail.com");

            //cliente 6 
            seguirUsuario("lachiqui@hotmail.com.ar", "bruceTheBoss@gmail.com");
            seguirUsuario("lachiqui@hotmail.com.ar", "la_ley@tuta.io");
            seguirUsuario("lachiqui@hotmail.com.ar", "lospimpi@gmail.com");
            seguirUsuario("lachiqui@hotmail.com.ar", "alcides@tuta.io");
            seguirUsuario("lachiqui@hotmail.com.ar", "el_padrino@tuta.io");
            seguirUsuario("lachiqui@hotmail.com.ar", "scarlettO@tuta.io");
            seguirUsuario("lachiqui@hotmail.com.ar", "ppArgento@hotmail.com");
            // cliente 7 
            seguirUsuario("cbochinche@vera.com.uy", "lachiqui@hotmail.com.ar");
            seguirUsuario("cbochinche@vera.com.uy", "lospimpi@gmail.com");
            seguirUsuario("cbochinche@vera.com.uy", "dyangounchained@gmail.com");
            seguirUsuario("cbochinche@vera.com.uy", "alcides@tuta.io");
            seguirUsuario("cbochinche@vera.com.uy", "ppArgento@hotmail.com");

            // cliente 8 
            seguirUsuario("Eleven11@gmail.com", "la_ley@tuta.io");
            seguirUsuario("Eleven11@gmail.com", "el_padrino@tuta.io");
            seguirUsuario("Eleven11@gmail.com", "scarlettO@tuta.io");
            seguirUsuario("Eleven11@gmail.com", "ppArgento@hotmail.com");

        } catch (Exception e) {
            throw new Exception("Error al cargar seguidores: " + e.getMessage());
        }
    }

    private void ListaGeneral1() throws Exception {
//LISTA 1 GENERAL
        Artista artista1 = controlpersis.findArtistaByCorreo("vpeople@tuta.io");
        Artista artista2 = controlpersis.findArtistaByCorreo("clauper@hotmail.com");
        Artista artista3 = controlpersis.findArtistaByCorreo("bruceTheBoss@gmail.com");
        Artista artista4 = controlpersis.findArtistaByCorreo("tigerOfWales@tuta.io");

        // Buscar álbumes en los artistas
        Album album1 = artista1.buscarAlbumPorNombre("Live and Sleazy");
        Album album2 = artista2.buscarAlbumPorNombre("She’s So Unusual");
        Album album3 = artista3.buscarAlbumPorNombre("Born In The U.S.A.");
        Album album4 = artista4.buscarAlbumPorNombre("It’s Not Unusual");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema tema1 = album1.buscarTemaPorNombre("YMCA");
        Tema tema2 = album1.buscarTemaPorNombre("Macho Man");
        Tema tema3 = album1.buscarTemaPorNombre("In the Navy");

        Tema tema4 = album2.buscarTemaPorNombre("Girls Just Want To Have Fun");
        Tema tema5 = album2.buscarTemaPorNombre("Time After Time");

        Tema tema6 = album3.buscarTemaPorNombre("Born In The U.S.A.");
        Tema tema7 = album3.buscarTemaPorNombre("Glory Days");
        Tema tema8 = album3.buscarTemaPorNombre("Dancing In The Dark");

        Tema tema9 = album4.buscarTemaPorNombre("It’s Not Unusual");

        // Crear la lista de reproducción "Noche De La Nostalgia" y añadir los temas
        ListaRepGeneral listaRep = new ListaRepGeneral("Noche De La Nostalgia", "bit.ly/laNocheNostalgia", buscarGeneroPorNombre("Pop Clásico"));

        listaRep.getListaTemas().add(tema1);
        listaRep.getListaTemas().add(tema2);
        listaRep.getListaTemas().add(tema3);
        listaRep.getListaTemas().add(tema4);
        listaRep.getListaTemas().add(tema5);
        listaRep.getListaTemas().add(tema6);
        listaRep.getListaTemas().add(tema7);
        listaRep.getListaTemas().add(tema8);
        listaRep.getListaTemas().add(tema9);

        // Guardar la lista de reproducción en la base de datos
        controlpersis.createListaRep(listaRep);

    }

    public void ListaGeneral2() throws Exception {
        // Obtener artistas de la base de datos (suponiendo que tienes un método para esto)
        Artista artista1 = controlpersis.findArtistaByCorreo("tripleNelson@tuta.io");
        Artista artista2 = controlpersis.findArtistaByCorreo("la_ley@tuta.io");

        // Buscar álbumes en los artistas
        Album album1 = artista1.buscarAlbumPorNombre("Agua Y Sal");
        Album album2 = artista2.buscarAlbumPorNombre("MTV Unplugged");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema tema1 = album1.buscarTemaPorNombre("Adagio De Mi País");
        Tema tema2 = album2.buscarTemaPorNombre("El Duelo");
        Tema tema3 = album2.buscarTemaPorNombre("Mentira");

        // Crear la lista de reproducción "Rock En Español" y añadir los temas
        ListaRepGeneral listaRep = new ListaRepGeneral("Rock En Español", "bit.ly/rockEnEspañol", buscarGeneroPorNombre("Rock Latino"));

        // Inicializar la lista de temas si es necesario
        if (listaRep.getListaTemas() == null) {
            listaRep.setListaTemas(new ArrayList<>());
        }

        listaRep.getListaTemas().add(tema1);
        listaRep.getListaTemas().add(tema2);
        listaRep.getListaTemas().add(tema3);

        // Guardar la lista de reproducción en la base de datos
        controlpersis.createListaRep(listaRep);
    }

    public void ListaGeneral3() throws Exception {
        // Obtener el artista de la base de datos
        Artista artista = controlpersis.findArtistaByCorreo("chaiko@tuta.io");

        // Buscar álbumes en el artista
        Album album1 = artista.buscarAlbumPorNombre("El Lago De Los Cisnes");
        Album album2 = artista.buscarAlbumPorNombre("Concierto Para Piano No. 1");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema tema1 = album1.buscarTemaPorNombre("Acto 2, Número 10, Escena (Moderato)");
        Tema tema2 = album2.buscarTemaPorNombre("Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)");

        // Crear la lista de reproducción "Música Clásica" y añadir los temas
        ListaRepGeneral listaRep = new ListaRepGeneral("Música Clásica", "bit.ly/musicaCla", buscarGeneroPorNombre("Clásica"));

        // Inicializar la lista de temas si es necesario
        if (listaRep.getListaTemas() == null) {
            listaRep.setListaTemas(new ArrayList<>());
        }

        listaRep.getListaTemas().add(tema1);
        listaRep.getListaTemas().add(tema2);

        // Guardar la lista de reproducción en la base de datos
        controlpersis.createListaRep(listaRep);
    }

    private void Cargar_Listas() throws Exception {
        ListaGeneral1();
        ListaGeneral2();
        ListaGeneral3();

        ListaParticular1();
        ListaParticular2();
        ListaParticular3();
        ListaParticular4();
        ListaParticular5();
        ListaParticular6();

    }

    public void ListaParticular1() throws Exception {

        // Obtener los artistas de la base de datos
        Artista artistaTchaikovsky = controlpersis.findArtistaByCorreo("chaiko@tuta.io");
        Artista artistaDMV = controlpersis.findArtistaByCorreo("dmode@tuta.io");
        Cliente cliente = controlpersis.findClienteByCorreo("el_padrino@tuta.io");
        // Buscar los álbumes de ambos artistas
        Album albumTchaikovsky1 = artistaTchaikovsky.buscarAlbumPorNombre("El Lago De Los Cisnes");
        Album albumTchaikovsky2 = artistaTchaikovsky.buscarAlbumPorNombre("Concierto Para Piano No. 1");
        Album albumDMV = artistaDMV.buscarAlbumPorNombre("Violator");

        // Buscar los temas dentro de los álbumes correspondientes de Tchaikovsky
        Tema temaTchaikovsky1 = albumTchaikovsky1.buscarTemaPorNombre("Acto 2, Número 10, Escena (Moderato)");
        Tema temaTchaikovsky2 = albumTchaikovsky2.buscarTemaPorNombre("Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)");

        // Buscar los temas dentro del álbum "Violator" de Depeche Mode
        Tema temaDMV1 = albumDMV.buscarTemaPorNombre("Personal Jesus");

        // Crear o buscar la lista de reproducción particular "Para Cocinar" (WW - Walter White)
        ListaRepParticular listaRepParticular = new ListaRepParticular("Música Inspiradora", "bit.ly/musicInspi", false);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        // Agregar los temas de Tchaikovsky a la lista
        listaRepParticular.getListaTemas().add(temaTchaikovsky1);
        listaRepParticular.getListaTemas().add(temaTchaikovsky2);

        // Agregar los temas de Depeche Mode a la lista
        listaRepParticular.getListaTemas().add(temaDMV1);

        // Guardar la lista de reproducción en la base de datos
        //  controlpersis.createListaRep(listaRepParticular);
        cliente.getListaReproduccion().add(listaRepParticular);
        controlpersis.editCliente(cliente);
    }

    public void ListaParticular2() throws Exception {
        // Obtener el artista y el cliente de la base de datos
        Artista artistaClauper = controlpersis.findArtistaByCorreo("clauper@hotmail.com");
        Artista artistaChaiko = controlpersis.findArtistaByCorreo("chaiko@tuta.io");
        Cliente clienteScarlett = controlpersis.findClienteByCorreo("scarlettO@tuta.io");

        // Buscar los álbumes en los artistas
        Album albumClauper = artistaClauper.buscarAlbumPorNombre("She’s So Unusual");
        Album albumChaiko = artistaChaiko.buscarAlbumPorNombre("El Lago De Los Cisnes");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema tema1 = albumClauper.buscarTemaPorNombre("Girls Just Want To Have Fun");
        Tema tema2 = albumClauper.buscarTemaPorNombre("Time After Time");

        Tema tema3 = albumChaiko.buscarTemaPorNombre("Acto 2, Número 10, Escena (Moderato)");

        // Buscar el tema "It’s Not Unusual" en el álbum "It’s Not Unusual" del artista tigerOfWales@tuta.io
        Artista artistaTiger = controlpersis.findArtistaByCorreo("tigerOfWales@tuta.io");
        Album albumTiger = artistaTiger.buscarAlbumPorNombre("It’s Not Unusual");
        Tema tema4 = albumTiger.buscarTemaPorNombre("It’s Not Unusual");

        // Crear la lista de reproducción particular "De Todo Un Poco" y añadir los temas
        ListaRepParticular listaRepParticular = new ListaRepParticular("De Todo Un Poco", "bit.ly/deTodoUnPoco", false);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        listaRepParticular.getListaTemas().add(tema1);
        listaRepParticular.getListaTemas().add(tema2);
        listaRepParticular.getListaTemas().add(tema3);
        listaRepParticular.getListaTemas().add(tema4);

        // Guardar la lista de reproducción en la base de datos
        //controlpersis.createListaRep(listaRepParticular);
        // Asignar la lista de reproducción al cliente
        clienteScarlett.getListaReproduccion().add(listaRepParticular);
        controlpersis.editCliente(clienteScarlett);
    }

    public void ListaParticular3() throws Exception {
        // Obtener los artistas de la base de datos
        Artista artistaDM = controlpersis.findArtistaByCorreo("dmode@tuta.io");
        Artista artistaBruce = controlpersis.findArtistaByCorreo("bruceTheBoss@gmail.com");
        Cliente cliente = controlpersis.findClienteByCorreo("Heisenberg@tuta.io");

        // Buscar los álbumes de los artistas
        Album albumDM = artistaDM.buscarAlbumPorNombre("Violator");
        Album albumBruce = artistaBruce.buscarAlbumPorNombre("Born In The U.S.A.");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema temaDM1 = albumDM.buscarTemaPorNombre("Personal Jesus");
        Tema temaDM2 = albumDM.buscarTemaPorNombre("Enjoy The Silence");
        Tema temaBruce = albumBruce.buscarTemaPorNombre("Born In The U.S.A.");
        Tema temaBruce2 = albumBruce.buscarTemaPorNombre("Glory Days");
        // Crear la lista de reproducción particular "Para Cocinar"
        ListaRepParticular listaRepParticular = new ListaRepParticular("Para Cocinar", "bit.ly/ParaCocinar", true);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        // Agregar los temas a la lista
        listaRepParticular.getListaTemas().add(temaDM1);
        listaRepParticular.getListaTemas().add(temaDM2);
        listaRepParticular.getListaTemas().add(temaBruce);
        listaRepParticular.getListaTemas().add(temaBruce2);

        // Guardar la lista de reproducción en la base de datos
        //controlpersis.createListaRep(listaRepParticular);
        // Agregar la lista al cliente
        cliente.getListaReproduccion().add(listaRepParticular);
        controlpersis.editCliente(cliente);
    }

    public void ListaParticular4() throws Exception {
        // Obtener los artistas de la base de datos
        Artista artistaClauper = controlpersis.findArtistaByCorreo("clauper@hotmail.com");
        Artista artistaTchaikovsky = controlpersis.findArtistaByCorreo("chaiko@tuta.io");
        Artista artistaNicole = controlpersis.findArtistaByCorreo("nicoleneu@hotmail.com");
        Artista artistaDyang = controlpersis.findArtistaByCorreo("dyangounchained@gmail.com");
        Artista artistatiger = controlpersis.findArtistaByCorreo("tigerOfWales@tuta.io");

        // Buscar los álbumes de los artistas
        Album albumSheSoUnusual = artistaClauper.buscarAlbumPorNombre("She’s So Unusual");
        Album albumConciertoPiano = artistaTchaikovsky.buscarAlbumPorNombre("Concierto Para Piano No. 1");
        Album albumPrimerAmor = artistaNicole.buscarAlbumPorNombre("Primer Amor");
        Album albumUnLocoComoYo = artistaDyang.buscarAlbumPorNombre("Un Loco Como Yo");
        Album albumits = artistatiger.buscarAlbumPorNombre("It’s Not Unusual");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema temaGirlsJustWant = albumSheSoUnusual.buscarTemaPorNombre("Girls Just Want To Have Fun");
        Tema temaPrimerMovimiento = albumConciertoPiano.buscarTemaPorNombre("Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)");
        Tema temaNoQuieroEstudiar = albumPrimerAmor.buscarTemaPorNombre("No Quiero Estudiar");
        Tema temaPorEseHombre = albumUnLocoComoYo.buscarTemaPorNombre("Por Ese Hombre");

        // Crear la lista de reproducción particular "Para Las Chicas" y añadir los temas
        ListaRepParticular listaRepParticular = new ListaRepParticular("Para Las Chicas", "bit.ly/ParaLasChicas", false);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        // Agregar los temas a la lista
        listaRepParticular.getListaTemas().add(temaGirlsJustWant);
        listaRepParticular.getListaTemas().add(temaPrimerMovimiento);
        listaRepParticular.getListaTemas().add(temaNoQuieroEstudiar);
        listaRepParticular.getListaTemas().add(temaPorEseHombre);

        // Crear el cliente y asignar la lista (si no existe)
        Cliente cliente = controlpersis.findClienteByCorreo("lachiqui@hotmail.com.ar");
        if (cliente != null) {
            cliente.getListaReproduccion().add(listaRepParticular);
            controlpersis.editCliente(cliente);
        }

        // Guardar la lista de reproducción en la base de datos
        //controlpersis.createListaRep(listaRepParticular);
    }

    public void ListaParticular5() throws Exception {
        // Obtener los artistas de la base de datos
        Artista artistaVpeople = controlpersis.findArtistaByCorreo("vpeople@tuta.io");
        Artista artistaBruce = controlpersis.findArtistaByCorreo("bruceTheBoss@gmail.com");
        Artista artistaAlcides = controlpersis.findArtistaByCorreo("alcides@tuta.io");

        // Buscar los álbumes de los artistas
        Album albumLiveAndSleazy = artistaVpeople.buscarAlbumPorNombre("Live and Sleazy");
        Album albumBornInUSA = artistaBruce.buscarAlbumPorNombre("Born In The U.S.A.");
        Album album20GrandesExitos = artistaAlcides.buscarAlbumPorNombre("20 Grandes Éxitos");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema temaYMCA = albumLiveAndSleazy.buscarTemaPorNombre("YMCA");
        Tema temaMachoMan = albumLiveAndSleazy.buscarTemaPorNombre("Macho Man");
        Tema temaInTheNavy = albumLiveAndSleazy.buscarTemaPorNombre("In the Navy");
        Tema temaGloryDays = albumBornInUSA.buscarTemaPorNombre("Glory Days");
        Tema temaVioleta = album20GrandesExitos.buscarTemaPorNombre("Violeta");

        // Crear la lista de reproducción particular "Fiesteras" y añadir los temas
        ListaRepParticular listaRepParticular = new ListaRepParticular("Fiesteras", "bit.ly/fiestaFiesta ", false);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        // Agregar los temas a la lista
        listaRepParticular.getListaTemas().add(temaYMCA);
        listaRepParticular.getListaTemas().add(temaMachoMan);
        listaRepParticular.getListaTemas().add(temaInTheNavy);
        listaRepParticular.getListaTemas().add(temaGloryDays);
        listaRepParticular.getListaTemas().add(temaVioleta);

        // Crear el cliente y asignar la lista (si no existe)
        Cliente cliente = controlpersis.findClienteByCorreo("cbochinche@vera.com.uy");
        if (cliente != null) {
            cliente.getListaReproduccion().add(listaRepParticular);
            controlpersis.editCliente(cliente);
        }

        // Guardar la lista de reproducción en la base de datos
        //controlpersis.createListaRep(listaRepParticular);
    }

    public void ListaParticular6() throws Exception {
        // Obtener los artistas de la base de datos
        Artista artistaTripleNelson = controlpersis.findArtistaByCorreo("tripleNelson@tuta.io");
        Artista artistaChaiko = controlpersis.findArtistaByCorreo("chaiko@tuta.io");
        Artista artistaLospimpi = controlpersis.findArtistaByCorreo("lospimpi@gmail.com");

        // Buscar los álbumes de los artistas
        Album albumAguaYSala = artistaTripleNelson.buscarAlbumPorNombre("Agua Y Sal");
        Album albumConciertoPiano = artistaChaiko.buscarAlbumPorNombre("Concierto Para Piano No. 1");
        Album albumHayAmoresQueMatan = artistaLospimpi.buscarAlbumPorNombre("Hay Amores Que Matan");

        // Buscar los temas dentro de los álbumes correspondientes
        Tema temaAdagioDeMiPais = albumAguaYSala.buscarTemaPorNombre("Adagio De Mi País");
        Tema temaPrimerMovimiento = albumConciertoPiano.buscarTemaPorNombre("Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)");
        Tema temaPorEseHombre = albumHayAmoresQueMatan.buscarTemaPorNombre("Por Ese Hombre");

        // Crear la lista de reproducción particular "Mis Favoritas" y añadir los temas
        ListaRepParticular listaRepParticular = new ListaRepParticular("Mis Favoritas", "bit.ly/misFavoritas", true);

        // Inicializar la lista de temas si es necesario
        if (listaRepParticular.getListaTemas() == null) {
            listaRepParticular.setListaTemas(new ArrayList<>());
        }

        // Agregar los temas a la lista
        listaRepParticular.getListaTemas().add(temaAdagioDeMiPais);
        listaRepParticular.getListaTemas().add(temaPrimerMovimiento);
        listaRepParticular.getListaTemas().add(temaPorEseHombre);

        // Crear el cliente y asignar la lista (si no existe)
        Cliente cliente = controlpersis.findClienteByCorreo("cbochinche@vera.com.uy");
        if (cliente != null) {
            cliente.getListaReproduccion().add(listaRepParticular);
            controlpersis.editCliente(cliente);
        }

        // Guardar la lista de reproducción en la base de datos
        // controlpersis.createListaRep(listaRepParticular);
    }

    private void CargarFavoritos() throws Exception {
        //Cliente 1 

        try {
            GuardarTemaFavorito("el_padrino@tuta.io", "la_ley@tuta.io", "MTV Unplugged", "El Duelo");
            GuardarLista_Por_Defecto_Favorito("el_padrino@tuta.io", "Noche De La Nostalgia");
            GuardarLista_Por_Defecto_Favorito("el_padrino@tuta.io", "Música Clásica");
            GuardarAlbumFavorito("el_padrino@tuta.io", "dmode@tuta.io", "Violator");
            GuardarAlbumFavorito("el_padrino@tuta.io", "chaiko@tuta.io", "El Lago De Los Cisnes");
            GuardarAlbumFavorito("el_padrino@tuta.io", "chaiko@tuta.io", "Concierto Para Piano No. 1");

            //Cliente 2 
            GuardarLista_Por_Defecto_Favorito("scarlettO@tuta.io", "Música Clásica");

            //Cliente 3
            GuardarTemaFavorito("ppArgento@hotmail.com", "tripleNelson@tuta.io", "Agua Y Sal", "Adagio De Mi País");
            GuardarLista_Por_Defecto_Favorito("ppArgento@hotmail.com", "Noche De La Nostalgia");
            GuardarLista_Por_Defecto_Favorito("ppArgento@hotmail.com", "Rock En Español");

            //cliente 4
            GuardarListaFavorito("Heisenberg@tuta.io", "el_padrino@tuta.io", "Música Inspiradora");

            //cliente 5
            GuardarAlbumFavorito("benKenobi@gmail.com", "chaiko@tuta.io", "El Lago De Los Cisnes");
            GuardarAlbumFavorito("benKenobi@gmail.com", "chaiko@tuta.io", "Concierto Para Piano No. 1");

            //cliente 6  VACIO
            //Cliente 7
            GuardarTemaFavorito("cbochinche@vera.com.uy", "chaiko@tuta.io", "Concierto Para Piano No. 1", "Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito)");
            GuardarLista_Por_Defecto_Favorito("cbochinche@vera.com.uy", "Noche De La Nostalgia");
            GuardarLista_Por_Defecto_Favorito("cbochinche@vera.com.uy", "Rock En Español");
            GuardarAlbumFavorito("cbochinche@vera.com.uy", "lospimpi@gmail.com", "Hay Amores Que Matan");

            //cliente 8 
            GuardarTemaFavorito("Eleven11@gmail.com", "nicoleneu@hotmail.com", "Primer Amor", "No Quiero Estudiar");
        } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
    }
//nuevo PUBLICAR LISTA

    public DTCliente encontrarClientePorNickname(String nick) {
        List<Cliente> clientes = listaClientes();
        DTCliente encontrado = new DTCliente();
        for (Cliente c : clientes) {
            if (c.getNickname().equals(nick)) {
                encontrado = new DTCliente(c.getNickname(), c.getNombre(), c.getApellido(), c.getMail(), c.fechaNac, c.getContrasenia(), c.getImagen());
                encontrado.setListaReproduccion(c.getListaReproduccion());// esas son las particulares
            }
        }
        return encontrado;
    }

    public List<DTCliente> listaClientesDT() {
        List<DTCliente> listaClientesDT = new ArrayList();
        List<Cliente> clientes = controlpersis.listaClientes();
        for (Cliente c : clientes) {
            DTCliente pasarDT = new DTCliente(c.getNickname(), c.getNombre(), c.getApellido(), c.getMail(), c.fechaNac, c.getContrasenia(), c.getImagen());
            pasarDT.setListaReproduccion(c.getListaReproduccion());
            listaClientesDT.add(pasarDT);
        }
        return listaClientesDT;
    }

    public List<String> nombreDeListasPrivadasDeCliente(String mail) {//devuelve una lista con los nombres de las listas de rep privadas del cliente
        List<String> lisPrivadas = new ArrayList();
        Cliente client = controlpersis.encontrarCliente(mail);//busco el cliente
        List<ListaRep> listasdelcliente = client.getListaReproduccion();//guardo las listas
        for (ListaRep l : listasdelcliente) {//para cada lista en las del cliente
            if (l instanceof ListaRepParticular) {//si la lista del momento es una instancia de lista particular
                ListaRepParticular particular = (ListaRepParticular) l;//guardo esa lista en una particular
                if (particular.isPrivada() == true) {//si esa lista es privada
                    lisPrivadas.add(particular.getNombre());//la sumo a la lista a retornar
                }
            }
        }
        return lisPrivadas;
    }
    
    public List<String> nombreDeListasDeCliente(String mail) throws Exception{//devuelve una lista con los nombres de las listas de rep privadas del cliente
      try{
        List<String> listas= new ArrayList();
        Cliente client = controlpersis.findClienteByNickname(mail);//busco el cliente
        List<ListaRep> listasdelcliente = client.getListaReproduccion();//guardo las listas
        for (ListaRep l : listasdelcliente) {//para cada lista en las del cliente


                   listas.add(l.getNombre());//la sumo a la lista a retornar
                
            
        }
        return listas;
      }
        catch (Exception e) {
            throw new Exception("Error al obtener listas de cliente: " + e.getMessage());
        }
    }
    

    public void publicarListaPrivada(String nick, String nombreLista) throws Exception {
        List<Cliente> clientes = listaClientes();
        Cliente client = new Cliente();
        for (Cliente c : clientes) {//busco el cliente por nickname
            if (c.getNickname().equals(nick)) {
                client = c;
            }
        }
        //Busco la lista en sus listas
        List<ListaRep> listasdelcliente = client.getListaReproduccion();//guardo las listas
        for (ListaRep l : listasdelcliente) {
            if (l.getNombre().equals(nombreLista)) {//selecciono la lista a hacer publica
                ListaRepParticular particular = (ListaRepParticular) l;//la casteo para hacerla publica
                particular.setPrivada(false);//la hago publica
                controlpersis.editListaPrivada(particular);//le mando la lista para editarla en la bd
            }
        }
        if (nombreLista == null) {//21 del 9
            throw new Exception("El cliente no tiene listas a publicar");
        }
    }

    public List<String> ListaAlbumesParaArtista(String correoArtista) throws Exception {
        // Obtener el artista por correo
        Artista artista = buscarArtistaPorCorreo(correoArtista); // Debes implementar este método para buscar al artista

        // Si no se encuentra el artista, lanzar una excepción
        if (artista == null) {
            throw new Exception("Artista con correo " + correoArtista + " no encontrado");
        }

        // Obtener la lista de álbumes del artista
        List<Album> albumes = artista.getAlbumes();

        // Crear una lista para almacenar los nombres de los álbumes
        List<String> nombresAlbumes = new ArrayList<>();

        // Añadir los nombres de los álbumes a la lista
        for (Album album : albumes) {
            nombresAlbumes.add(album.getNombre());
        }

        return nombresAlbumes; // Devolver la lista de nombres de álbumes
    }

    public DTAlbum findAlbumPorArtistaYNombre(String correoArtista, String nombreAlbum) throws Exception {
        // Buscar al artista por correo
        Artista artista = controlpersis.findArtistaByCorreo(correoArtista);

        if (artista == null) {
            throw new Exception("Artista no encontrado con el correo proporcionado.");
        }

        // Buscar el álbum por nombre en la lista de álbumes del artista
        Album albumEncontrado = null;
        for (Album album : artista.getAlbumes()) {
            if (album.getNombre().equalsIgnoreCase(nombreAlbum)) {
                albumEncontrado = album;
                break;
            }
        }

        if (albumEncontrado == null) {
            throw new Exception("Álbum '" + nombreAlbum + "' no encontrado para el artista '" + artista.getNombre() + "'.");
        }

        // Crear la lista de géneros del álbum
        List<String> generosDT = new ArrayList<>();
        for (Genero auxG : albumEncontrado.getListaGeneros()) {
            generosDT.add(auxG.getNombre());
        }

        // Crear el objeto DTArtista
        DTArtista dtartista = new DTArtista(
                artista.getNickname(),
                artista.getNombre(),
                artista.getApellido(),
                artista.getContrasenia(),
                artista.getImagen(),
                artista.getFechaNac(),
                artista.getMail(),
                artista.getBiografia(),
                artista.getSitioWeb()
        );

        // Crear la lista de temas del álbum
        List<DTTema> dtTemas = new ArrayList<>();
        for (Tema auxT : albumEncontrado.getListaTemas()) {
            long duracionSegundos = auxT.getDuracionSegundos();
            int minutos = (int) (duracionSegundos / 60);
            int segundos = (int) (duracionSegundos % 60);

            DTTema dttema = new DTTema(auxT.getNombre(), minutos, segundos, auxT.getDireccion());
            dtTemas.add(dttema);
        }

        // Crear y retornar el DTAlbum
        return new DTAlbum(
                albumEncontrado.getNombre(),
                albumEncontrado.getAnioCreacion(),
                albumEncontrado.getImagen(),
                generosDT,
                dtTemas,
                dtartista
        );
    }

    public List<DTListaRep> obtenerDTListaPorGenero(String selectedGenero) {
        // Supongamos que tienes una lista de listas de reproducción por defecto cargadas en la lógica.
        List<ListaRepGeneral> listasPorGenero = controlpersis.buscarListasPorGenero(selectedGenero);

        // Crear una lista para los DTListaRep
        List<DTListaRep> dtListas = new ArrayList<>();

        // Itera sobre las listas para obtener el nombre de la lista
        for (ListaRepGeneral lista : listasPorGenero) {
            // Usa el constructor de DTListaRep para crear el objeto con solo el nombre de la lista
            DTListaRep dtListaRep = new DTListaRep(
                    lista.getNombre(), // nombreListaRep (nombre de la lista)
                    null, // nombreCliente (nulo porque es una lista por defecto)
                    selectedGenero, // género de la lista
                    null, // imagen (puedes ajustar esto si necesitas)
                    null // no se cargarán los temas
            );

            // Añade a la lista
            dtListas.add(dtListaRep);
        }

        return dtListas;
    }

    public DTListaRep obtenerDatosDeLista_Por_Defecto(String nombreSeleccionado) throws Exception {
        // Busca la lista de reproducción por nombre
        ListaRepGeneral listaPorDefecto = controlpersis.findListaRep_Por_Defecto_ByNombre(nombreSeleccionado);

        if (listaPorDefecto == null) {
            // Maneja el caso en que la lista no se encuentra
            throw new Exception("Lista no encontrada.");
        }

        // Obtén los temas de la lista
        List<DTTema> temas = listaPorDefecto.getListaTemas().stream()
                .map(tema -> {
                    // Convertir la duración de tema a minutos y segundos
                    long minutos = tema.getDuracion().toMinutes();
                    long segundos = tema.getDuracion().minusMinutes(minutos).getSeconds();

                    // Crear DTTema usando minutos y segundos separados
                    return new DTTema(tema.getNombre(), (int) minutos, (int) segundos, tema.getDireccion());
                })
                .collect(Collectors.toList());

        // Crea y retorna el DTO con la información de la lista
        return new DTListaRep(
                listaPorDefecto.getNombre(), // nombreListaRep
                null, // nombreCliente (quedará en null)
                listaPorDefecto.getGenero().getNombre(), // género (asumiendo que getNombre() devuelve el nombre del género)
                listaPorDefecto.getImagen(), // imagen
                temas // temas
        );
    }

    public List<DTListaRep> obtenerDTListaPorCliente(String correoCliente) {
        // Buscar al cliente por su correo
        Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);

        // Verificar si el cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con el correo: " + correoCliente);
        }

        // Obtener las listas de reproducción del cliente
        List<ListaRep> listasPorCliente = cliente.getListaReproduccion();

        // Crear una lista para los DTListaRep
        List<DTListaRep> dtListas = new ArrayList<>();

        // Iterar sobre las listas del cliente para obtener la información
        for (ListaRep lista : listasPorCliente) {
            // Crear un objeto DTListaRep con el nombre de la lista
            DTListaRep dtListaRep = new DTListaRep(
                    lista.getNombre(), // nombreListaRep (nombre de la lista)
                    null, // nombreCliente (correo del cliente)
                    null, // género de la lista, si existe
                    null, // imagen (si es necesario)
                    null // no se cargarán los temas en este paso
            );

            // Añadir la lista a la lista de DTListaRep
            dtListas.add(dtListaRep);
        }

        return dtListas;
    }

    public DTListaRep obtenerDatosDeLista_Por_Cliente(String correoCliente, String nombreLista) {
        // Buscar al cliente por su correo
        Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);

        // Verificar si el cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con el correo: " + correoCliente);
        }

        // Obtener la lista de reproducción específica por nombre
        ListaRep listaSeleccionada = cliente.getListaReproduccion().stream()
                .filter(lista -> lista.getNombre().equals(nombreLista))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lista de reproducción no encontrada con el nombre: " + nombreLista));

        // Obtener el género y la imagen de la lista
        String imagen = listaSeleccionada.getImagen();  // Asegúrate de que ListaRep tenga el método getImagen()

        // Obtener los temas de la lista
        List<DTTema> temas = listaSeleccionada.getListaTemas().stream()
                .map(tema -> new DTTema(
                tema.getNombre(),
                (int) tema.getDuracion().toMinutes(),
                (int) tema.getDuracion().toSeconds() % 60,
                tema.getDireccion()))
                .collect(Collectors.toList());

        // Crear y retornar el DTO con toda la información
        return new DTListaRep(
                listaSeleccionada.getNombre(), // nombreListaRep
                correoCliente, // nombreCliente
                null, // género (puedes ajustar esto si es necesario)
                imagen, // imagen
                temas // temas
        );
    }

    public List<String> nicknamesDeTodosLosArtistas() {
        List<String> nicknames = new ArrayList();
        for (Artista a : controlpersis.listaArtistas()) {//para cada artista en la bd
            nicknames.add(a.getNickname()); //agrego su nickname a la lista de nicks
        }
        return nicknames;
    }

    public DTArtista encontrarDTArtistaPorNickname(String nick) {
        for (Artista a : controlpersis.listaArtistas()) {//para cada artista en la bd
            if (a.getNickname().equals(nick)) {//si el nick es igual al a del momento
                DTArtista encontrado = new DTArtista(a.getNickname(), a.getNombre(), a.getApellido(), a.getContrasenia(), a.getImagen(), a.getFechaNac(), a.getMail(), a.getBiografia(), a.getSitioWeb());
                return encontrado;
            }
        }
        return null;
    }

    public List<String> nicksClientesSiguenArtista(String nickAr) {//lista de clientes q siguen un artista   
        List<String> clientesQueLoSiguen = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {//para cada cliente en bd
            for (Artista a : c.getArtistasSeguidos()) {//para cada artista que C sigue busco si tiene nickAr
                if (a.getNickname().equals(nickAr)) {//si el cliente sigue al artista
                    clientesQueLoSiguen.add(c.getNickname());//agrego a la lista el nick del cliente q lo sigue
                }
            }
        }
        return clientesQueLoSiguen;
    }

    public List<String> listaAlbumesArtistaNick(String nick) throws Exception {//devuelve string de albumes de artista encontrado por nick
        for (Artista a : controlpersis.listaArtistas()) {
            if (a.getNickname().equals(nick)) {
                List<String> albumes = ListaAlbumesParaArtista(a.getMail());
                return albumes;
            }
        }
        return null;
    }

    public List<String> nicksClientes() {
        List<String> nicknames = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {
            nicknames.add(c.getNickname());
        }
        return nicknames;
    }

    public List<String> clientesSeguidosDelCliente(String nick) {//nicknames de los clientes que sigue nick
        List<String> seguidos = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {
            if (c.getNickname().equals(nick)) {
                for (Cliente cs : c.getClientesSeguidos()) {
                    seguidos.add(cs.getNickname());//agrego a la lista los nicks de los clientes que sigue el cliente osea agrego sus seguidos
                }
            }
        }
        return seguidos;
    }

    public List<String> seguidoresDelCliente(String nick) {//nicknames de los que siguen al cliente
        List<String> seguidores = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {
            for (Cliente cs : c.getClientesSeguidos()) {
                if (cs.getNickname().equals(nick)) {
                    seguidores.add(c.getNickname());//agrego el seguidor del cliente a la lista
                }
            }
        }
        return seguidores;
    }

    public List<String> artistasSeguidosDelCliente(String nick) {
        List<String> seguidos = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {
            if (c.getNickname().equals(nick)) {
                for (Artista a : c.getArtistasSeguidos()) {
                    seguidos.add(a.getNickname());//agrego los nicks que sigue el cliente
                }
            }
        }
        return seguidos;
    }

    public List<String> nombresListaRepDeCliente(String nick) {
        List<String> listas = new ArrayList<>();

        // Buscar el cliente por su nickname
        Cliente cliente = controlpersis.listaClientes().stream()
                .filter(c -> c.getNickname().equals(nick)) // Si quieres evitar equals, usa otra forma de comparación.
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            listas.add("Cliente no encontrado con el nickname: " + nick);
            return listas;
        }

        // Verificar si el cliente tiene listas de reproducción
        if (cliente.getListaReproduccion().isEmpty()) {
            listas.add("El cliente no ha creado ninguna lista");
        } else {
            // Agregar el nombre de cada lista creada por el cliente
            for (ListaRep lista : cliente.getListaReproduccion()) {
                listas.add(lista.getNombre()); // Asegúrate de que getNombre no use equals en la lógica
            }
        }

        return listas;
    }

    public DefaultListModel favoritosDeCliente(String nick) {
        DefaultListModel favoritos = new DefaultListModel();

        try {
            // Buscar al cliente por su nickname
            Cliente c = controlpersis.findClienteByNickname(nick);

            if (c != null) {
                // Listas de reproducción favoritas
                if (!c.getListaRepFavoritos().isEmpty()) {
                    favoritos.addElement("  Listas:  ");

                    for (ListaRep l : c.getListaRepFavoritos()) {
                        favoritos.addElement(l.getNombre()); // Agregar el nombre de la lista
                    }
                }

                // Álbumes favoritos
                if (!c.getAlbums().isEmpty()) {
                    favoritos.addElement("  ");
                    favoritos.addElement("  Albumes:  ");

                    for (Album a : c.getAlbums()) {
                        favoritos.addElement(a.getNombre()); // Agregar el nombre del álbum
                    }
                }

                // Temas favoritos
                if (!c.getTemas().isEmpty()) {
                    favoritos.addElement("  ");
                    favoritos.addElement("  Temas:  ");

                    for (Tema t : c.getTemas()) {
                        favoritos.addElement(t.getNombre()); // Agregar el nombre del tema
                    }
                }

                // Si el cliente no tiene listas, álbumes ni temas favoritos
                if (c.getListaRepFavoritos().isEmpty() && c.getAlbums().isEmpty() && c.getTemas().isEmpty()) {
                    favoritos.removeAllElements();
                    favoritos.addElement("El cliente no tiene preferencias guardadas");
                }
            } else {
                // Si no se encontró el cliente
                favoritos.addElement("No se encontró al cliente con el nickname proporcionado.");
            }

        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            favoritos.addElement("Error al buscar el cliente.");
        }

        return favoritos;
    }

    public String encontrarNicknameCliente(String string) {
        return controlpersis.NicknameCliente(string);
    }

    public String encontrarNicknameArtista(String string) {
        return controlpersis.NicknameArtista(string);
    }

    public List<String> obtenerSeguidos(String correoSeguidor) {
        // Primero, obtener el nickname del cliente a partir del correo
        Cliente Seguidor = encontrarCliente(correoSeguidor);

        if (Seguidor.getNickname() == null) {
            return new ArrayList<>(); // O manejar el caso cuando no se encuentra el nickname
        }

        // Obtener los clientes que sigue el cliente con el nickname encontrado
        List<String> seguidosClientes = clientesSeguidosDelCliente(Seguidor.getNickname());//esto tiene nicks

        // Obtener los artistas que sigue el cliente con el nickname encontrado
        List<String> seguidosArtistas = artistasSeguidosDelCliente(Seguidor.getNickname());//esto tiene nicks

        // Combinar ambas listas en una sola
        List<String> seguidos = new ArrayList<>();
        seguidos.addAll(seguidosClientes);
        seguidos.addAll(seguidosArtistas);

        return seguidos;
    }

    public List<String> obtenerAlbumesFavoritosDeCliente(String correoCliente) throws Exception {
        Cliente cliente = controlpersis.findClienteByCorreo(correoCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado con el correo: " + correoCliente);
        }

        // Obtener los nombres de los álbumes favoritos del cliente
        List<String> nombresAlbumes = new ArrayList<>();
        for (Album album : cliente.getAlbums()) {
            nombresAlbumes.add(album.getNombre());
        }

        return nombresAlbumes;
    }

    public List<String> MostrarNombreArtistasbyAlbum(String nombreAlbum) throws Exception {
        // Buscar los álbumes con el nombre dado
        List<Album> albumes = controlpersis.findAlbumByNombre(nombreAlbum);

        // Lista para almacenar los nombres de los artistas
        List<String> nombreArtistas = new ArrayList<>();

        // Iterar sobre los álbumes y extraer los nombres de los artistas
        for (Album album : albumes) {
            Artista artista = album.getArtista();  // Obtener el artista del álbum
            if (artista != null) {
                nombreArtistas.add(artista.getNickname());  // Agregar el nombre del artista a la lista
            }
        }

        // Retornar la lista con los nombres de los artistas
        return nombreArtistas;
    }

    public List<String> temasDeAlbumDeArtista(String album, String artistaMail) {
        
        List<String> temas = new ArrayList();
        for (Artista a : controlpersis.listaArtistas()) {
            if (a.getMail().equals(artistaMail)) {
                for (Album al : a.getAlbumes()) {
                    if (al.getNombre().equals(album)) {
                        for (Tema t : al.getListaTemas()) {
                            temas.add(t.getNombre());//agrego el nombre del tema a lista de strings
                                                System.out.println(t.getNombre());

                        }
                    }
                }
            }
        }
        return temas;
    }

    public List<String> listasPublicasDeCliente(String correo) {
        List<String> lisPublicas = new ArrayList();
        for (Cliente c : controlpersis.listaClientes()) {
            if (c.getMail().equals(correo)) {
                for (ListaRep l : c.getListaReproduccion()) {
                    if (l instanceof ListaRepParticular) {
                        ListaRepParticular particular = (ListaRepParticular) l;
                        if (particular.isPrivada() == false) {
                            lisPublicas.add(particular.getNombre());
                        }
                    }
                }
            }
        }

        return lisPublicas;
    }

    public List<String> listaAlbumesArtistaMail(String correo) throws Exception {//devuelve string de albumes de artista encontrado por nick
        List<String> albumes = new ArrayList();
        for (Artista a : controlpersis.listaArtistas()) {
            if (a.getMail().equals(correo)) {
                albumes = ListaAlbumesParaArtista(a.getMail());
                return albumes;
            }
        }
        return albumes;
    }

    public List<String> listasDefecto() {
        List<String> listas = new ArrayList();
        for (ListaRep l : controlpersis.listas()) {
            if (l instanceof ListaRepGeneral) {
                ListaRepGeneral general = (ListaRepGeneral) l;
                listas.add(general.getNombre());
            }
        }
        return listas;
    }

    public List<Genero> listaGeneros() {
        return controlpersis.listaGeneros();//retorno la lista de personas de la BD
    }

    public List<String> MostrarNombreGeneros() {

        List<Genero> listaGenero = listaGeneros();
        List<String> nombreG = new ArrayList<>();
        for (Genero auxG : listaGenero) {
            nombreG.add(auxG.getNombre());
        }
        return nombreG;
    }

    public List<String> findstringAlbumPorGenero(String string) {

        //Le paso una lista de albumes que tengan al genero pasado por parametro, en su lista de generos
        List<Album> albumes = controlpersis.listaAlbumes(); //Obtengo todos los albumes
        //Quiero crear una lista de DTAlbumes
        List<String> nombreAlbumes = new ArrayList<>();
        //Recorrer la lista de objetos de albumes y averiguar si el genero pasado por parametro, pertenece a su lista de generos
        for (Album auxA : albumes) {
            //Si el genero por parametro, pertenece a la lista de generos del album (lo guardo en un datatype y lo meto a la lista de DT)
            for (Genero g : auxA.getListaGeneros()) {
                if (g.getNombre().equals(string)) {
                    nombreAlbumes.add(auxA.getNombre());

                }
            }
        }
        return nombreAlbumes;
    }
    
  public List<DTAlbum> findDTAlbum(String generopertenece) throws Exception {
    // Verificar si el parámetro de género es nulo o vacío
    if (generopertenece == null || generopertenece.trim().isEmpty()) {
        throw new IllegalArgumentException("El parámetro 'generopertenece' no puede ser nulo o vacío.");
    }

    // Obtener la lista de álbumes desde el controlador de persistencia
    List<Album> albumes = controlpersis.listaAlbumes(); 
    List<DTAlbum> nombreAlbumes = new ArrayList<>();
    
    // Verificar si la lista de álbumes está vacía
    if (albumes == null || albumes.isEmpty()) {
        System.out.println("No se encontraron álbumes en la base de datos.");
        return nombreAlbumes; // Retornar lista vacía
    }

    // Recorrer la lista de objetos de álbumes y averiguar si el género pasado por parámetro pertenece a su lista de géneros
    for (Album auxA : albumes) {
        // Verificar si el álbum es nulo
        if (auxA == null) {
            System.out.println("Álbum nulo encontrado en la lista.");
            continue; // Continuar con el siguiente álbum
        }

        // Si el género por parámetro pertenece a la lista de géneros del álbum
        for (Genero g : auxA.getListaGeneros()) {
            // Verificar si el género es nulo
            if (g == null) {
                System.out.println("Género nulo encontrado en el álbum: " + auxA.getNombre());
                continue; // Continuar con el siguiente género
            }

            if (g.getNombre().equals(generopertenece)) {
                // Crear el objeto DTAlbum y agregarlo a la lista
                DTAlbum dtAlbum = new DTAlbum(
                    auxA.getNombre(),
                    auxA.getAnioCreacion(),
                    auxA.getImagen(),
                    // Crear la lista de géneros para el DTAlbum
                    auxA.getListaGeneros().stream().map(Genero::getNombre).collect(Collectors.toList()),
                    // Crear la lista de temas del álbum
                    auxA.getListaTemas().stream().map(t -> new DTTema(t.getNombre(), (int) (t.getDuracionSegundos() / 60), (int) (t.getDuracionSegundos() % 60), t.getDireccion())).collect(Collectors.toList()),
                    // Obtener el artista asociado
                    new DTArtista(
                        auxA.getArtista().getNickname(),
                        auxA.getArtista().getNombre(),
                        auxA.getArtista().getApellido(),
                        auxA.getArtista().getContrasenia(),
                        auxA.getArtista().getImagen(),
                        auxA.getArtista().getFechaNac(),
                        auxA.getArtista().getMail(),
                        auxA.getArtista().getBiografia(),
                        auxA.getArtista().getSitioWeb()
                    )
                );
                nombreAlbumes.add(dtAlbum);
            }
        }
    }

    // Retornar la lista de DTAlbum
    return nombreAlbumes;
}


    

    private DTAlbum DTAlbum(String nombre, int anioCreacion, String imagen, List<String> generosDT, List<DTTema> dtTemas, DTArtista dtArtista) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DTAlbum findAlbumxNombreDT(String nombreAlbum,String correoArtista) throws Exception {

        // Buscar el artista por correo
    Artista art = controlpersis.encontrarArtista(correoArtista);
    if (art == null) {
        throw new Exception("Artista no encontrado con el correo: " + correoArtista);
    }
    
    // Buscar el álbum dentro de la lista de álbumes del artista
    Album albumEncontrado = null;
    for (Album album : art.getAlbumes()) {
        if (album.getNombre().equals(nombreAlbum)) {
            albumEncontrado = album;
            break;
        }
    }
 
    // Si no se encuentra el álbum, lanzamos una excepción
    if (albumEncontrado == null) {
        throw new Exception("Álbum no encontrado o no seleccionado, intente nuevamente ");
    }

    // Crear la lista de géneros del álbum
    List<String> generosDT = new ArrayList<>();
    for (Genero auxG : albumEncontrado.getListaGeneros()) {
        generosDT.add(auxG.getNombre());
    }

    // Crear el objeto DTArtista
    DTArtista dtartista = new DTArtista(
            art.getNickname(),
            art.getNombre(),
            art.getApellido(),
            art.getContrasenia(),
            art.getImagen(),
            art.getFechaNac(),
            art.getMail(),
            art.getBiografia(),
            art.getSitioWeb()
    );

    // Crear la lista de temas del álbum
    List<DTTema> dtTemas = new ArrayList<>();
    for (Tema auxT : albumEncontrado.getListaTemas()) {
        long duracionSegundos = auxT.getDuracionSegundos();
        int minutos = (int) (duracionSegundos / 60);
        int segundos = (int) (duracionSegundos % 60);

        DTTema dttema = new DTTema(auxT.getNombre(), minutos, segundos, auxT.getDireccion());
        dtTemas.add(dttema);
    }

    // Crear y retornar el DTAlbum
    return new DTAlbum(
            albumEncontrado.getNombre(),
            albumEncontrado.getAnioCreacion(),
            albumEncontrado.getImagen(),
            generosDT,
            dtTemas,
            dtartista
    );
    }

    public String ConvierteNick_A_Correo(String nickname) throws Exception {
        try {
            // Intentar encontrar un cliente con el nickname
            Cliente cliente = encontrarClientePorNicknameTipoCli(nickname);
            if (cliente != null) {
                return cliente.getMail(); // Si el cliente es encontrado, devolver su correo
            }

            // Si no se encuentra un cliente, intentar encontrar un artista con el nickname
            Artista artista = encontrarArtistaPorNicknameTipoArt(nickname);
            if (artista != null) {
                return artista.getMail(); // Si el artista es encontrado, devolver su correo
            }

            // Si no se encuentra ni cliente ni artista, lanzar una excepción
            throw new Exception("No se encontró ningún cliente o artista con el nickname: " + nickname);

        } catch (Exception ex) {
            throw new Exception("Error al convertir el nickname a correo: " + ex.getMessage());
        }
    }

    public boolean sePuedenCargarLosDatos(){
        //is empty retorna true si la lista esta vacia (se pueden cargar), si es false es porque hay artistas osea no se puede cargar
        return controlpersis.listaArtistas().isEmpty();
    }
    
    public List<String> Lista_Albumes(){
    List<String> listalbum = new ArrayList<>();
    List<Album> albumes = controlpersis.listaAlbumes();
    for (Album album : albumes){
        
        listalbum.add(album.getNombre());
        
    }
    return listalbum;
    }
    
    public List<String>FindListasRep_Duenios(String nombrelista) throws Exception{
            List<String> duenios = new ArrayList<>();
    
    try {
        // Obtenemos la lista de clientes desde el sistema
        List<Cliente> clientes = listaClientes(); // Método que devuelve la lista de clientes
        
        // Recorremos cada cliente
        for (Cliente cliente : clientes) {
            // Obtenemos las listas de reproducción del cliente
            List<ListaRep> listasRep = cliente.getListaReproduccion();
            
            // Recorremos las listas de reproducción del cliente
            for (ListaRep lista : listasRep) {
                // Si el nombre de la lista coincide con el nombre proporcionado
                if (lista.getNombre().equalsIgnoreCase(nombrelista)) {
                    // Añadimos el nombre del cliente a la lista de dueños
                    duenios.add(cliente.getNickname());
                }
            }
        }

        return duenios; // Devuelve la lista de nombres de clientes que tienen una lista con el nombre dado
    } catch (Exception e) {
        throw new Exception("Error buscando dueños de las listas de reproducción", e);
    }
    
    
    }
    public List<String> FindListas(){
    
    return controlpersis.NombreListasRepParticular();
    
    }
    
        public List<String> FindListasDefault(){
    
    return controlpersis.NombreListasRep_Defecto();
    
    }
        
        
        
        public List<String> ListaTemas_De_Lista(String lista, String NickCliente) {
    List<String> temas = new ArrayList<>(); // Inicializar la lista de temas
    try {
        // Buscar al cliente por su nickname
        Cliente cli = controlpersis.findClienteByNickname(NickCliente);
        
        // Verificar si el cliente existe
        if (cli == null) {
            throw new IllegalArgumentException("Cliente no encontrado con el nickname: " + NickCliente);
        }
        
        // Obtener las listas de reproducción del cliente
        List<ListaRep> listasDeCli = cli.getListaReproduccion();
        
        // Buscar la lista de reproducción por nombre
        ListaRep listaReproduccion = listasDeCli.stream()
                .filter(lr -> lr.getNombre().equals(lista))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lista de reproducción no encontrada con el nombre: " + lista));
        
        // Obtener los temas de la lista de reproducción
        List<Tema> temasDeLista = listaReproduccion.getListaTemas();
        
        // Verificar si hay temas en la lista
        if (temasDeLista.isEmpty()) {
            throw new IllegalArgumentException("No hay temas en la lista de reproducción.");
        }
        
        // Agregar los nombres de los temas a la lista de cadenas
        for (Tema tema : temasDeLista) {
            temas.add(tema.getNombre());
            
            
        }
        
    } catch (Exception ex) {
        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, "Error al obtener los temas de la lista", ex);
    }
    
    return temas; // Devolver la lista de temas
}
public List<String> ListaTemas_De_Lista_Def(String lista){
    List<String> temas= new ArrayList<>(); 
        try {
            ListaRep listarep = controlpersis.findListaRep_Por_Defecto_ByNombre(lista);
            
            for(Tema tema: listarep.getListaTemas()){
                temas.add(tema.getNombre());
            }
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
return temas;
}



public void AgregarTema_De_Album_A_Lista(String cliente, String lista_de_cliente, String album, String artista_de_album, String tema_selected) throws Exception{
    try {
        // Obtener cliente por nickname
        Cliente cli = controlpersis.findClienteByNickname(cliente);
        List<ListaRep> listascli = cli.getListaReproduccion();

        // Buscar la lista de reproducción específica del cliente
        ListaRep lista = null;
        for (ListaRep l : listascli) {
            if (l.getNombre().equals(lista_de_cliente)) {
                if (l instanceof ListaRepParticular) {
                    lista = l;
                    break;
                } else {
                    throw new Exception("La lista seleccionada no es una lista particular.");
                }
            }
        }

        if (lista == null) {
            throw new Exception("No se encontró la lista de reproducción particular del cliente: " + lista_de_cliente);
        }

        // Obtener artista por su nickname
        Artista art = controlpersis.findArtistaByNickname(artista_de_album);
        List<Album> albumesArtista = art.getAlbumes();

        // Buscar el álbum específico del artista
        Album albumEncontrado = null;
        for (Album alb : albumesArtista) {
            if (alb.getNombre().equals(album)) {
                albumEncontrado = alb;
                break;
            }
        }

        if (albumEncontrado == null) {
            throw new Exception("No se encontró el álbum: " + album);
        }

        // Buscar el tema en el álbum
        Tema temaSeleccionado = null;
        for (Tema tema : albumEncontrado.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en el álbum: " + album);
        }

        // Asignar el tema a la lista de reproducción particular del cliente
        ListaRepParticular listaParticular = (ListaRepParticular) lista;
        
        
        for (Tema t : listaParticular.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista_de_cliente);
            }
        }
        listaParticular.getListaTemas().add(temaSeleccionado);
        
        // Actualizar la lista en la base de datos
        controlpersis.editListaPrivada(listaParticular);

        System.out.println("El tema " + tema_selected + " fue agregado a la lista " + lista_de_cliente + " del cliente " + cliente);

    } catch (Exception ex) {
        throw new Exception("Error: "+ ex.getMessage(), ex);
    }
}

    public void AgregarTema_De_ListaPart_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String cliente_con_lista, String tema_selected) throws Exception {
    try {
        // Obtener cliente propietario de la lista destino
        Cliente cliDestino = controlpersis.findClienteByNickname(cliente);
        List<ListaRep> listascliDestino = cliDestino.getListaReproduccion();

        // Buscar la lista de reproducción destino
        ListaRep listaDestino = null;
        for (ListaRep l : listascliDestino) {
            if (l.getNombre().equals(lista_de_cliente)) {
                if (l instanceof ListaRepParticular) {
                    listaDestino = l;
                    break;
                } else {
                    throw new Exception("La lista seleccionada no es una lista particular.");
                }
            }
        }

        if (listaDestino == null) {
            throw new Exception("No se encontró la lista de reproducción particular destino del cliente: " + lista_de_cliente);
        }

        // Obtener cliente propietario de la lista donde está el tema
        Cliente cliOrigen = controlpersis.findClienteByNickname(cliente_con_lista);
        List<ListaRep> listascliOrigen = cliOrigen.getListaReproduccion();

        // Buscar la lista de reproducción de donde se obtendrá el tema
        ListaRep listaOrigen = null;
        for (ListaRep l : listascliOrigen) {
            if (l.getNombre().equals(lista_where_temais)) {
                if (l instanceof ListaRepParticular) {
                    listaOrigen = l;
                    break;
                } else {
                    throw new Exception("La lista origen seleccionada no es una lista particular.");
                }
            }
        }

        if (listaOrigen == null) {
            throw new Exception("No se encontró la lista de reproducción particular origen: " + lista_where_temais);
        }

        // Buscar el tema en la lista de origen
        Tema temaSeleccionado = null;
        for (Tema tema : listaOrigen.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en la lista: " + lista_where_temais);
        }

        // Comprobar si el tema ya está en la lista destino
        ListaRepParticular listaParticularDestino = (ListaRepParticular) listaDestino;
        for (Tema t : listaParticularDestino.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista_de_cliente);
            }
        }

        // Agregar el tema a la lista destino
        listaParticularDestino.getListaTemas().add(temaSeleccionado);

        // Actualizar la lista en la base de datos
        controlpersis.editListaPrivada(listaParticularDestino);

        System.out.println("El tema " + tema_selected + " fue agregado de la lista " + lista_where_temais + " a la lista " + lista_de_cliente + " del cliente " + cliente);

    } catch (Exception ex) {
        throw new Exception("Error: " + ex.getMessage(), ex);
    }
}

    public void AgregarTema_De_ListaDef_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String tema_selected) throws Exception {
    try {
        // Obtener el cliente propietario de la lista destino
        Cliente cliDestino = controlpersis.findClienteByNickname(cliente);
        List<ListaRep> listascliDestino = cliDestino.getListaReproduccion();

        // Buscar la lista de reproducción destino
        ListaRep listaDestino = null;
        for (ListaRep l : listascliDestino) {
            if (l.getNombre().equals(lista_de_cliente)) {
                if (l instanceof ListaRepParticular) {
                    listaDestino = l;
                    break;
                } else {
                    throw new Exception("La lista seleccionada no es una lista particular.");
                }
            }
        }

        if (listaDestino == null) {
            throw new Exception("No se encontró la lista de reproducción particular del cliente: " + lista_de_cliente);
        }

        // Obtener la lista de reproducción por defecto
        ListaRepGeneral listaGeneral = controlpersis.findListaRep_Por_Defecto_ByNombre(lista_where_temais);
        if (listaGeneral == null) {
            throw new Exception("No se encontró la lista de reproducción por defecto: " + lista_where_temais);
        }

        // Buscar el tema en la lista por defecto
        Tema temaSeleccionado = null;
        for (Tema tema : listaGeneral.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en la lista por defecto: " + lista_where_temais);
        }

        // Comprobar si el tema ya está en la lista destino
        ListaRepParticular listaParticularDestino = (ListaRepParticular) listaDestino;
        for (Tema t : listaParticularDestino.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista_de_cliente);
            }
        }

        // Agregar el tema a la lista destino
        listaParticularDestino.getListaTemas().add(temaSeleccionado);

        // Actualizar la lista en la base de datos
        controlpersis.editListaPrivada(listaParticularDestino);

        System.out.println("El tema " + tema_selected + " fue agregado de la lista por defecto " + lista_where_temais + " a la lista " + lista_de_cliente + " del cliente " + cliente);

    } catch (Exception ex) {
        throw new Exception("Error: " + ex.getMessage(), ex);
    }
}


// Método para agregar un tema de un álbum a una lista por defecto
public void AgregarTema_De_Album_A_ListaDef(String lista, String album, String artista_de_album, String tema_selected) throws Exception {
    try {
        // Obtener la lista por defecto
        ListaRepGeneral listaDestino = controlpersis.findListaRep_Por_Defecto_ByNombre(lista);
        if (listaDestino == null) {
            throw new Exception("No se encontró la lista de reproducción por defecto: " + lista);
        }

        // Obtener artista por su nickname
        Artista art = controlpersis.findArtistaByNickname(artista_de_album);
        List<Album> albumesArtista = art.getAlbumes();

        // Buscar el álbum específico del artista
        Album albumEncontrado = null;
        for (Album alb : albumesArtista) {
            if (alb.getNombre().equals(album)) {
                albumEncontrado = alb;
                break;
            }
        }

        if (albumEncontrado == null) {
            throw new Exception("No se encontró el álbum: " + album);
        }

        // Buscar el tema en el álbum
        Tema temaSeleccionado = null;
        for (Tema tema : albumEncontrado.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en el álbum: " + album);
        }

        // Comprobar si el tema ya está en la lista destino
        for (Tema t : listaDestino.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista);
            }
        }

        // Agregar el tema a la lista por defecto
        listaDestino.getListaTemas().add(temaSeleccionado);

        // Actualizar la lista por defecto en la base de datos
        controlpersis.editListaPorDefecto(listaDestino);

        System.out.println("El tema " + tema_selected + " fue agregado del álbum " + album + " a la lista por defecto " + lista);

    } catch (Exception ex) {
        throw new Exception("Error: " + ex.getMessage(), ex);
    }
}

// Método para agregar un tema de una lista particular a una lista por defecto
public void AgregarTema_De_ListaPart_A_ListaDef(String lista, String lista_where_temais, String cliente_con_lista, String tema_selected) throws Exception {
    try {
        // Obtener la lista por defecto
        ListaRepGeneral listaDestino = controlpersis.findListaRep_Por_Defecto_ByNombre(lista);
        if (listaDestino == null) {
            throw new Exception("No se encontró la lista de reproducción por defecto: " + lista);
        }

        // Obtener el cliente propietario de la lista particular
        Cliente cliente = controlpersis.findClienteByNickname(cliente_con_lista);
        ListaRepParticular listaParticular = null;
        for (ListaRep l : cliente.getListaReproduccion()) {
            if (l instanceof ListaRepParticular && l.getNombre().equals(lista_where_temais)) {
                listaParticular = (ListaRepParticular) l;
                break;
            }
        }

        if (listaParticular == null) {
            throw new Exception("No se encontró la lista particular: " + lista_where_temais);
        }

        // Buscar el tema en la lista particular
        Tema temaSeleccionado = null;
        for (Tema tema : listaParticular.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en la lista particular: " + lista_where_temais);
        }

        // Comprobar si el tema ya está en la lista destino
        for (Tema t : listaDestino.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista);
            }
        }

        // Agregar el tema a la lista por defecto
        listaDestino.getListaTemas().add(temaSeleccionado);

        // Actualizar la lista por defecto en la base de datos
        controlpersis.editListaPorDefecto(listaDestino);

        System.out.println("El tema " + tema_selected + " fue agregado de la lista particular " + lista_where_temais + " a la lista por defecto " + lista);

    } catch (Exception ex) {
        throw new Exception("Error: " + ex.getMessage(), ex);
    }
}

// Método para agregar un tema de una lista por defecto a otra lista por defecto
public void AgregarTema_De_ListaDef_A_ListaDef(String lista, String lista_where_temais, String tema_selected) throws Exception {
    try {
        // Obtener la lista por defecto de destino
        ListaRepGeneral listaDestino = controlpersis.findListaRep_Por_Defecto_ByNombre(lista);
        if (listaDestino == null) {
            throw new Exception("No se encontró la lista de reproducción por defecto: " + lista);
        }

        // Obtener la lista por defecto origen
        ListaRepGeneral listaOrigen = controlpersis.findListaRep_Por_Defecto_ByNombre(lista_where_temais);
        if (listaOrigen == null) {
            throw new Exception("No se encontró la lista de reproducción por defecto origen: " + lista_where_temais);
        }

        // Buscar el tema en la lista por defecto origen
        Tema temaSeleccionado = null;
        for (Tema tema : listaOrigen.getListaTemas()) {
            if (tema.getNombre().equals(tema_selected)) {
                temaSeleccionado = tema;
                break;
            }
        }

        if (temaSeleccionado == null) {
            throw new Exception("No se encontró el tema: " + tema_selected + " en la lista por defecto: " + lista_where_temais);
        }

        // Comprobar si el tema ya está en la lista destino
        for (Tema t : listaDestino.getListaTemas()) {
            if (t.getNombre().equals(tema_selected)) {
                throw new Exception("El tema " + tema_selected + " ya está en la lista " + lista);
            }
        }

        // Agregar el tema a la lista por defecto destino
        listaDestino.getListaTemas().add(temaSeleccionado);

        // Actualizar la lista por defecto en la base de datos
        controlpersis.editListaPorDefecto(listaDestino);

        System.out.println("El tema " + tema_selected + " fue agregado de la lista por defecto " + lista_where_temais + " a la lista por defecto " + lista);

    } catch (Exception ex) {
        throw new Exception("Error: " + ex.getMessage(), ex);
    }
}

public void EliminarTemaDeLista_Part(String NomLista, String nickCli, Long idTema) throws Exception {
    try {
        // Obtener el cliente propietario de la lista
        Cliente cli = controlpersis.findClienteByNickname(nickCli);
        List<ListaRep> listasCli = cli.getListaReproduccion();

        // Buscar la lista de reproducción particular donde eliminar el tema
        ListaRep listaParticular = null;
        for (ListaRep l : listasCli) {
            if (l.getNombre().equals(NomLista)) {
                if (l instanceof ListaRepParticular) {
                    listaParticular = l;
                    break;
                } else {
                    throw new Exception("La lista seleccionada no es una lista particular.");
                }
            }
        }

        if (listaParticular == null) {
            throw new Exception("No se encontró la lista de reproducción particular: " + NomLista);
        }

        // Buscar el tema en la lista de reproducción por su ID
        ListaRepParticular listaParticularDestino = (ListaRepParticular) listaParticular;

        // Agregar más detalles de depuración para asegurarnos de que estamos iterando correctamente
        System.out.println("Lista de temas en la lista seleccionada:");
        for (Tema tema : listaParticularDestino.getListaTemas()) {
            System.out.println("- Tema: " + tema.getNombre() + ", ID: " + tema.getId());  // Mostrar todos los temas en la lista con su ID
        }

        // Eliminar el tema usando removeIf, que elimina si el ID coincide
        boolean eliminado = listaParticularDestino.getListaTemas().removeIf(tema -> tema.getId() == idTema);

        if (!eliminado) {
            throw new Exception("No se encontró el tema con ID: " + idTema + " en la lista " + NomLista);
        }

        // Actualizar la lista en la base de datos
        controlpersis.editListaPrivada(listaParticularDestino);

        System.out.println("El tema con ID " + idTema + " fue eliminado de la lista " + NomLista + " del cliente " + nickCli);

    } catch (Exception ex) {
        throw new Exception("Error al eliminar el tema: " + ex.getMessage(), ex);
    }
}


public List<Long> GetIdTemas(String nomTema) throws Exception {
    List<Tema> temas = controlpersis.findTemaEntities();
    List<Long> ids = new ArrayList<>(); // Inicializa la lista aquí

    for (Tema tema : temas) {
        // Compara el nombre del tema
        if (tema.getNombre() == null ? nomTema == null : tema.getNombre().equals(nomTema)) {
            ids.add(tema.getId()); // Agrega el ID a la lista
        }
    }

    return ids; // Retorna la lista de IDs
}


public void EliminarTemaDeLista_Def(String nombreLista, String nombreTema) throws Exception {
    // Buscar la lista de reproducción por defecto por su nombre
    ListaRepGeneral lista = controlpersis.findListaRep_Por_Defecto_ByNombre(nombreLista);
    
    if (lista == null) {
        throw new Exception("No se encontró la lista de reproducción por defecto: " + nombreLista);
    }
    
    // Obtener los temas de la lista
    List<Tema> temas = lista.getListaTemas();
    
    // Comprobar si el tema existe y eliminarlo
    boolean eliminado = temas.removeIf(tema -> tema.getNombre().equals(nombreTema));
    
    if (!eliminado) {
        throw new Exception("No se encontró el tema: " + nombreTema + " en la lista " + nombreLista);
    }

    // Actualizar la lista en la base de datos
    controlpersis.editListaPorDefecto(lista); // Asumiendo que este método se encarga de la persistencia

    System.out.println("El tema " + nombreTema + " fue eliminado de la lista " + nombreLista);
}

public double calculaMontoSubscripcion(String tipo) throws Exception {
    switch (tipo.toLowerCase()) { // Convertir a minúsculas para evitar problemas con mayúsculas/minúsculas
        case "semanal":
            return 5.0; // Monto para suscripción semanal
        case "mensual":
            return 19.0; // Monto para suscripción mensual
        case "anual":
            return 120.0; // Monto para suscripción anual
        default:
            throw new IllegalArgumentException("Tipo de suscripción no válido: " + tipo);
    }
}

public void crearSubscripcion(String nicknameCliente, String tipoSub) throws Exception {
    try {
        // Validar el tipo de suscripción
        if (tipoSub == null || tipoSub.isEmpty()) {
            throw new Exception("Tipo de suscripción no proporcionado.");
        }

        double monto;
        switch (tipoSub.toLowerCase()) {
            case "semanal":
                monto = 5.0;
                break;
            case "mensual":
                monto = 19.0;
                break;
            case "anual":
                monto = 120.0;
                break;
            default:
                throw new Exception("Tipo de suscripción no válido.");
        }

        // Buscar el cliente por su nickname
        Cliente cliente = controlpersis.findClienteByNickname(nicknameCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado con el nickname proporcionado.");
        }

        // Crear la nueva suscripción con el cliente
   
        Subscripcion nuevaSubscripcion = new Subscripcion(cliente.getNickname(), tipoSub, new Date(), Subscripcion.Estado.PENDIENTE, monto);

        // Aquí puedes agregar la lógica para guardar la subscripción en la base de datos
        controlpersis.crearSubscripcion(nuevaSubscripcion); // Ejemplo de método para persistir la suscripción
cliente.getSubs().add(nuevaSubscripcion);
controlpersis.editCliente(cliente);

    } catch (Exception e) {
        throw new Exception("Error al crear la suscripción: " + e.getMessage());
    }
}

public void PruebaSubs() throws Exception{
crearSubscripcion("cel_padrino", "mensual");
crearSubscripcion("cel_padrino", "anual");
crearSubscripcion("lachiqui", "semanal");



}

public List<DTSub> listarSubscripciones() throws Exception {
    // Obtener todas las suscripciones desde la base de datos
    List<DTSub> listsubs = new ArrayList<>();
    List<Subscripcion> subs = controlpersis.getAllSubscripciones(); // Implementa este método en tu capa de persistencia
    
    for (Subscripcion sub : subs) {
        // Crear un objeto DTSub a partir de cada suscripción
        DTSub dtSub = new DTSub(
                sub.getId(), // Suponiendo que la suscripción tiene un método getId()
                sub.getCliente(), // Suponiendo que la suscripción tiene un método getCliente() que devuelve el cliente
                sub.getTipo(), // Suponiendo que la suscripción tiene un método getTipo()
                sub.getEstado().name(), // Suponiendo que la suscripción tiene un método getEstado()
                sub.getFechaInicio()// Suponiendo que la suscripción tiene un método getFechaActivacion()
        );
        listsubs.add(dtSub);
    }
    
    return listsubs; 
}

public void modificarEstadoSuscripcion(Long id, String nuevoEstado)throws Exception{
    Subscripcion sub = controlpersis.findSubscripcion(id);
    
    if (sub != null) {
        
        if (sub.getEstado() != Estado.PENDIENTE) {
            throw new Exception("Solo se pueden modificar las suscripciones en estado PENDIENTE.");
        }

        try {
            Estado estado = Estado.valueOf(nuevoEstado.toUpperCase()); // Convierte a mayúsculas para la comparación
            sub.setEstado(estado); // Asigna el nuevo estado
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no válido: " + nuevoEstado);
        }
        sub.setFechaInicio(new Date());
        // Persistir la modificación en la base de datos
        controlpersis.updateSubscripcion(sub); // Asegúrate de tener este método en tu capa de persistencia
    } else {
        throw new Exception("Suscripción no encontrada con ID: " + id);
    }
}

}

