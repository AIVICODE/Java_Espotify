/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template  encontrarClientePorNickname 
 */
package WebServices;
import Datatypes.DTUsuario;
import Datatypes.DTAlbum;
import Datatypes.DTArtista;
import Datatypes.DTCliente;
import Datatypes.DTContenido;
import Datatypes.DTListaRep;
import Datatypes.DTSub;
import Datatypes.DTTema;
import Datatypes.DTInfoTema;
import Datatypes.DTIngresoWeb;
import Logica.Subscripcion.Estado;

import Logica.Fabrica;
import Logica.IControlador;
import Logica.Tema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.Endpoint;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorSoap {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    //private Endpoint endpoint = null;

    public ControladorSoap() {
    }

    @WebMethod
    public DTUsuario login(@WebParam(name = "usuario") String usuario,@WebParam(name = "password") String pass ) throws Exception {
        // Llama al método correspondiente del controlador
        
        return control.login(usuario, pass);
    }
    
    @WebMethod
    public ListaDTSub listarSubdeCliente(String nickname) throws Exception {
        ListaDTSub result = new ListaDTSub();
        result.setLista(control.listarSubdeCliente(nickname));
        return result;
 
    }
    
    @WebMethod
    public void ClienteModificaEstadoSuscripcion(Long id, String nuevoEstado) throws Exception {
        // Llama al método correspondiente del controlador
        try{
            control.ClienteModificaEstadoSuscripcion(id, nuevoEstado);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    @WebMethod
    public void GuardarAlbumFavorito(String correoCliente, String correoArtista, String nombreAlbum) throws Exception {
        try{
            control.GuardarAlbumFavorito(correoCliente, correoArtista, nombreAlbum);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
     @WebMethod
     public String ConvierteNick_A_Correo(String nickname) throws Exception {
         try{
            return control.ConvierteNick_A_Correo(nickname);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
     }
     
     @WebMethod
     public void GuardarListaFavorito(String correoCliente, String correo_Cliente_Con_Lista, String nombreLista) throws Exception {
         try{
             control.GuardarListaFavorito(correoCliente, correo_Cliente_Con_Lista, nombreLista);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }
     
     @WebMethod
     public void GuardarLista_Por_Defecto_Favorito(String correoCliente, String nombreLista) throws Exception {
        try{
             control.GuardarLista_Por_Defecto_Favorito(correoCliente, nombreLista);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }
     
     @WebMethod
     public String guardarImagenesLista(byte[] archivoImagen, String nombreLista) throws IOException {
         try{
             return control.guardarImagenesLista(archivoImagen, nombreLista);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
     }
     
     @WebMethod
     public void CrearListaRepParticular(String nombreLista, String correoCliente, String imagen, boolean privada) throws Exception {
         try{
             control.CrearListaRepParticular(nombreLista, correoCliente, imagen, privada);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }
     
     @WebMethod
     public void GuardarTemaFavorito(String correoCliente, String correoArtista, String nombreAlbum, String nombreTema) throws Exception {
         try{
             control.GuardarTemaFavorito(correoCliente, correoArtista, nombreAlbum, nombreTema);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }
     
     
     @WebMethod
     public String guardarImagenesAlbum(byte[] archivoImagen, String nombreAlbum, String nombreArtista) throws IOException {
          try{
             return control.guardarImagenesAlbum(archivoImagen, nombreAlbum, nombreArtista);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
     }
     
     @WebMethod
     public String guardarTemaEnCarpeta(byte[] archivoTema, String nombreTema) throws IOException {
         try{
             return control.guardarTemaEnCarpeta(archivoTema, nombreTema);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
     }
     
     @WebMethod
     public void CrearAlbum(String correoArtista, DTAlbum nuevoAlbum, ArrayList<DTTema> listaTemas) throws Exception {
         try{
             control.CrearAlbum(correoArtista, nuevoAlbum, listaTemas);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }
     
     @WebMethod
     public ListaString nicknamesDeTodosLosArtistas() {
         ListaString result = new ListaString();
         result.setLista(control.nicknamesDeTodosLosArtistas());
         return result;
     }
     @WebMethod
     public ListaString nicknamesDeTodosLosClientes() {
         List<DTCliente> dtClientes = control.listaClientesDT();
         List<String> nicknames = new ArrayList<>();
         for (DTCliente cliente : dtClientes) {
              System.out.println(cliente.getNickname());
            nicknames.add(cliente.getNickname());
        }
         ListaString result = new ListaString();
         result.setLista(nicknames);
         return result;
     }

     
    @WebMethod
    public ListaDTCliente listaClientesDT() {
        ListaDTCliente result = new ListaDTCliente();
        result.setLista(control.listaClientesDT());
        
        return result;
     }
    
    @WebMethod
    public void crearSubscripcion(String nicknameCliente, String tipoSub) throws Exception {
         try{
             control.crearSubscripcion(nicknameCliente, tipoSub);
         } catch (Exception e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new Exception(e.getMessage());
        }
     }

    @WebMethod
    public boolean existeMail(String mail){
        return control.existeMail(mail);
    }
    
    @WebMethod
    public boolean existeNickname(String nickname){
        return control.existeNickname(nickname);
    }
    
    @WebMethod
    public byte[] obtenerImagenComoBytes(String rutaImagen) throws IOException {
        try{
             return control.obtenerImagenComoBytes(rutaImagen);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
    }
    
    @WebMethod
    public byte[] obtenerTemaComoBytes(String rutaTema) throws IOException {
        try{
             return control.obtenerTemaComoBytes(rutaTema);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTAlbum findDTAlbumTodos() throws Exception {
        try{
            ListaDTAlbum result = new ListaDTAlbum();
            result.setLista(control.findDTAlbumTodos());
        
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTAlbum findAlbumesPorArtista(String nickArtista) throws Exception {
        try{
            ListaDTAlbum result = new ListaDTAlbum();
            result.setLista(control.findAlbumesPorArtista(nickArtista));
        
            return result;

        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTAlbum findDTAlbum(String generopertenece) throws Exception {
        try{
            ListaDTAlbum result = new ListaDTAlbum();
            result.setLista(control.findDTAlbum(generopertenece));
        
            return result;
           
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTContenido Buscador(String filtro, String sortBy) throws Exception {
        try{
            ListaDTContenido result = new ListaDTContenido();
            result.setLista(control.Buscador(filtro, sortBy));
        
            return result;
            
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaString nicksClientes() {
        ListaString result = new ListaString();
        result.setLista(control.nicksClientes());
        
        return result;
    }
    
    @WebMethod
    public ListaDTListaRep obtenerDTListaPorClientepublica(String correoCliente) throws Exception{
        try{
            ListaDTListaRep result = new ListaDTListaRep();
            result.setLista(control.obtenerDTListaPorClientepublica(correoCliente));
        
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public DTCliente encontrarClientePorNickname(String nick) {
        try {
            // Crear una instancia de DTCliente y asignar valores
            DTCliente cliente = new DTCliente();
            DTCliente cli = control.encontrarClientePorNickname(nick);
            cliente.setNickname(cli.getNickname());
            cliente.setNombre(cli.getNombre());
            cliente.setApellido(cli.getApellido());
            cliente.setCorreo(cli.getCorreo());
            cliente.setFechaNac(cli.getFechaNac());
            cliente.setContrasenia(cli.getContrasenia());
            cliente.setImagen(cli.getImagen());  
            cliente.setListaUsuariosFavoritos(cli.getListaUsuariosFavoritos());

            // Configurar JAXB para serializar DTCliente
            JAXBContext context = JAXBContext.newInstance(DTCliente.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar a XML y mostrar en consola
            marshaller.marshal(cliente, System.out);
            return cliente;
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    @WebMethod
    public DTArtista encontrarDTArtistaPorNickname(String nick) throws Exception {
        //return control.encontrarDTArtistaPorNickname(nick);
        
        try {
            // Crear una instancia de DTCliente y asignar valores
            DTArtista artista = new DTArtista();
            DTArtista art = control.encontrarDTArtistaPorNickname(nick);
            artista.setNickname(art.getNickname());
            artista.setNombre(art.getNombre());
            artista.setApellido(art.getApellido());
            artista.setCorreo(art.getCorreo());
            artista.setFechaNac(art.getFechaNac());
            artista.setContrasenia(art.getContrasenia());
            artista.setImagen(art.getImagen());  
            artista.setBiografia(art.getBiografia());
            artista.setSitioWeb(art.getSitioWeb());
            //artista.setListaUsuariosFavoritos(art.getListaUsuariosFavoritos());

            // Configurar JAXB para serializar DTCliente
            JAXBContext context = JAXBContext.newInstance(DTArtista.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar a XML y mostrar en consola
            marshaller.marshal(artista, System.out);
            return artista;
            
        } catch (IllegalArgumentException  e) {
            //e.printStackTrace();
            throw new Exception(e.getMessage());
        }
   
    }
    
    @WebMethod
    public ListaString nicksClientesSiguenArtista(String nickAr) {
        ListaString result = new ListaString();
        result.setLista(control.nicksClientesSiguenArtista(nickAr));
        
        return result;

    }
    
    @WebMethod
    public ListaString listaAlbumesArtistaNick(String nick) throws Exception {
        try{
            ListaString result = new ListaString();
            result.setLista(control.listaAlbumesArtistaNick(nick));
        
            return result;
       
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaString seguidoresDelCliente(String nick) {
        ListaString result = new ListaString();
        result.setLista(control.seguidoresDelCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString clientesSeguidosDelCliente(String nick) {
        ListaString result = new ListaString();
        result.setLista(control.clientesSeguidosDelCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString artistasSeguidosDelCliente(String nick) {
        ListaString result = new ListaString();
        result.setLista(control.artistasSeguidosDelCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString nombresListaRepDeCliente(String nick) {
        ListaString result = new ListaString();
        result.setLista(control.nombresListaRepDeCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString listaFavoritosDeCliente(String nick) {
        ListaString result = new ListaString();
        result.setLista(control.listaFavoritosDeCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString listasPublicasDeCliente(String correo) {
        ListaString result = new ListaString();
        result.setLista(control.listasPublicasDeCliente(correo));
        
        return result;
    }
    
    @WebMethod
    public ListaString nombreAlbumyNombreArtistaFavoritosCliente(String nick){
        ListaString result = new ListaString();
        result.setLista(control.nombreAlbumyNombreArtistaFavoritosCliente(nick));
        
        return result;
    }
    
    @WebMethod
    public ListaString MostrarNombreGeneros() {
        ListaString result = new ListaString();
        result.setLista(control.MostrarNombreGeneros());
        
        return result;
        
    }
    
    @WebMethod
    public ListaDTListaRep ListasParticulares() throws Exception {
        try{
            ListaDTListaRep result = new ListaDTListaRep();
            result.setLista(control.ListasParticulares());
        
            return result;
            
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTListaRep ListasPorDefecto() throws Exception {
        try{
            ListaDTListaRep result = new ListaDTListaRep();
            result.setLista(control.ListasPorDefecto());
        
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public ListaDTListaRep obtenerDTListaPorCliente(String correoCliente) {
        ListaDTListaRep result = new ListaDTListaRep();
        result.setLista(control.obtenerDTListaPorCliente(correoCliente));
        
        return result;
        
    }
    
    @WebMethod
    public ListaDTListaRep obtenerDTListaPorGenero(String selectedGenero) {
        ListaDTListaRep result = new ListaDTListaRep();
        result.setLista(control.obtenerDTListaPorGenero(selectedGenero));
        
        return result;
    }
    
    @WebMethod
    public ListaString nombreDeListasPrivadasDeCliente(String mail) {
        ListaString result = new ListaString();
        result.setLista(control.nombreDeListasPrivadasDeCliente(mail));
        
        return result;
    }
    
    @WebMethod
    public DTAlbum findAlbumxNombreDT(String nombreAlbum,String correoArtista) throws Exception {
        try{
            return control.findAlbumxNombreDT(nombreAlbum, correoArtista);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public DTListaRep obtenerDatosDeLista_Por_Defecto(String nombreSeleccionado) throws Exception {
        try{
            return control.obtenerDatosDeLista_Por_Defecto(nombreSeleccionado);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public void AgregarTema_De_ListaDef_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String tema_selected) throws Exception {
        try{
            control.AgregarTema_De_ListaDef_A_Lista(cliente, lista_de_cliente, lista_where_temais, tema_selected);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public DTListaRep obtenerDatosDeLista_Por_Cliente(String correoCliente, String nombreLista) throws Exception{
        try{
            return control.obtenerDatosDeLista_Por_Cliente(correoCliente, nombreLista);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @WebMethod
    public byte[][] obtenerTemasComoBytes(/*String[]*/ListaString rutasTemas) throws IOException {
        try{
            List<String> rutas =rutasTemas.getLista();
            return control.obtenerTemasComoBytes(rutas.toArray(new String[0]));
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
    }
    
    
    @WebMethod
    public void publicarListaPrivada(String nick, String nombreLista) throws Exception {
        try{
            control.publicarListaPrivada(nick, nombreLista);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public String guardarImagenesEnCarpeta(byte[] archivoImagen, String nickname) throws IOException {
        try{
             return control.guardarImagenesEnCarpeta(archivoImagen, nickname);
         } catch (IOException e) {
            // Lanza la excepción para que sea gestionada en un nivel superior
            throw new IOException(e.getMessage());
        }
    }
    
    
    @WebMethod
    public void crearUsuario(DTUsuario user) throws Exception {
        try{
            
            control.crearUsuario(user);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public boolean estaSiguiendoUsuario(String nicknameSeguidor, String nicknameSeguido) {
        return control.estaSiguiendoUsuario(nicknameSeguidor, nicknameSeguido);
    }
    
    
    @WebMethod
    public void dejarSeguirUsuario(String correoSeguidor, String correoSeguido) throws Exception {
        try{
            control.dejarSeguirUsuario(correoSeguidor, correoSeguido);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @WebMethod
    public void seguirUsuario(String correoSeguidor, String correoSeguido) throws Exception {
        try{
            control.seguirUsuario(correoSeguidor, correoSeguido);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public boolean verificarSubscripcion(String cliente) {
        return control.verificarSubscripcion(cliente);
    }
    
    @WebMethod
    public void GeneraRegistro(String ip,String url,String browser,String so) {
        control.GeneraRegistro(ip, url, browser, so);
    }

    @WebMethod
    public ListaDTIngresoWeb convertToDTIngresoWebList() {
        ListaDTIngresoWeb result = new ListaDTIngresoWeb();
        result.setLista(control.convertToDTIngresoWebList());
        
        return result;
    }
    
    @WebMethod
    public void AgregaDescargaTema(DTTema tema){
        control.AgregaDescargaTema(tema);
    }
    
    @WebMethod
    public void AgregaReproduccionTema(DTTema tema){
        control.AgregaReproduccionTema(tema);
    }
    
    @WebMethod
    public void CrearListaRepSugerencia() throws Exception {
        try{
            control.CrearListaRepSugerencia();
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public DTListaRep obtenerListaSugerida() throws Exception {
         try{
            return control.obtenerListaSugerida();
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public DTInfoTema ObtenerInfoTema(String nombreTema,String nombreArtista,String nombreAlbum) throws Exception{
        try{
            return control.ObtenerInfoTema(nombreTema, nombreArtista, nombreAlbum);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void AgregarTema_De_Album_A_Lista(String cliente, String lista_de_cliente, String album, String artista_de_album, String tema_selected) throws Exception{
        try{
            control.AgregarTema_De_Album_A_Lista(cliente,lista_de_cliente, album, artista_de_album, tema_selected);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void AgregarTema_De_ListaPart_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String cliente_con_lista, String tema_selected) throws Exception {
        try{
            control.AgregarTema_De_ListaPart_A_Lista(cliente,lista_de_cliente, lista_where_temais, cliente_con_lista, tema_selected);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @WebMethod
    public Base64BinaryArray obtTemasComoBytes(ListaString rutasTemas) throws IOException {
        try {
            List<String> rutas =rutasTemas.getLista();
            byte[][] temasBytes = control.obtenerTemasComoBytes(rutas.toArray(new String[0]));

            // Convierte el byte[][] a Base64BinaryArray
            Base64BinaryArray array = new Base64BinaryArray();
            for (byte[] tema : temasBytes) {
                array.getItems().add(tema);
            }
            return array;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


    public void eliminarArt(String nickname) {
        // Llama al controlador para eliminar el artista
        control.eliminarArtista(nickname);
    }

}
