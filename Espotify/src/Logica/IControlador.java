
package Logica;

import Datatypes.DTAlbum;
import Datatypes.DTArtista;
import Datatypes.DTCliente;
import Datatypes.DTListaRep;
import Datatypes.DTSub;
import Datatypes.DTTema;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.tree.TreeModel;


//me dan error los private porq los usa solo el controlador VER SI QUEDA TODO ACTUALIZADO CON EL CONTROLER IGUAL 
public interface IControlador {
    //public abstract 
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();////ESTO NO SE SI VA AHI
    //private abstract Date createDate(int year, int month, int day);
    public abstract void crearUsuario(DTUsuario user) throws Exception;
    public abstract boolean verificarExistenciaArtista(String correo) throws Exception;
    public abstract void CrearGenero(String nombre, String nombrePadre) throws Exception;
    public abstract TreeModel buildGeneroTree();
    public abstract void CrearAlbum(String correoArtista, DTAlbum nuevoAlbum, List<DTTema> listaTemas) throws Exception;
    //private abstract Artista buscarArtistaPorCorreo(String correo) throws Exception;
    //@SuppressWarnings("empty-statement")
    //private abstract Genero buscarGeneroPorNombre(String nombreGenero) throws Exception;
    public abstract void CrearListaRepGeneral(String nombreLista, String imagen,String nomGenero);
    public abstract void CrearListaRepParticular(String nombreLista, String correoCliente, String imagen, boolean privada) throws Exception;
    public abstract void GuardarTemaFavorito(String correoCliente, String correoArtista, String nombreAlbum, String nombreTema) throws Exception;
    public abstract void GuardarAlbumFavorito(String correoCliente, String correoArtista, String nombreAlbum) throws Exception;
    public abstract void GuardarListaFavorito(String correoCliente, String correo_Cliente_Con_Lista, String nombreLista) throws Exception;
    public abstract void GuardarLista_Por_Defecto_Favorito(String correoCliente, String nombreLista) throws Exception;
    public abstract void EliminarAlbumFavorito(String correoCliente, String correoArtista, String nombreAlbum) throws Exception;
    public abstract void EliminarTemaFavorito(String correoCliente, String correoArtista, String nombreAlbum, String nombreTema) throws Exception;
    public abstract void EliminarListaFavorito(String correoCliente, String correo_Cliente_Con_Lista, String nombreLista) throws Exception;
    public abstract void EliminarLista_Por_Defecto_Favorito(String correoCliente, String nombreLista) throws Exception;
    public abstract List<String> MostrarNombreClientes();
    public abstract List<Cliente> listaClientes();
    public abstract Cliente encontrarCliente(String mail);
   public abstract List<Artista> listaArtistas();
    public abstract Artista encontrarArtista(String mail);
    public abstract void seguirUsuario(String correoSeguidor, String correoSeguido) throws Exception;
    public abstract void dejarSeguirUsuario(String correoSeguidor, String correoSeguido) throws Exception;
    public abstract void Cargar_Datos_Prueba() throws Exception;
    public abstract  String encontrarNicknameArtista(String string) ;
    public abstract String encontrarNicknameCliente(String string) ;
    public abstract Cliente encontrarClientePorNicknameTipoCli(String nickname);
    public abstract Artista encontrarArtistaPorNicknameTipoArt(String nickname);
    public abstract List<String> obtenerAlbumesFavoritosDeCliente(String correoCliente) throws Exception ;
    public abstract DTAlbum findAlbumxNombreDT(String nombreAlbum,String correoArtista) throws Exception;
    public abstract List<String> findstringAlbumPorGenero(String string) ;
    public abstract List<String> MostrarNombreGeneros();
    public abstract String ConvierteNick_A_Correo(String nickname) throws Exception ;
     public abstract List<String> nombreDeListasDeCliente(String mail)throws Exception ;
     public abstract  List<String> Lista_Albumes() ;
     public List<String>FindListasRep_Duenios(String nombrelista) throws Exception;
     public abstract List<DTAlbum> findDTAlbum(String generopertenece) throws Exception;
    /*private abstract void Cargar_Perfiles();
    private abstract void Cargar_Generos() throws Exception;
    private abstract void Cargar_Albumes() throws Exception;
    private abstract void Album1() throws Exception;
    private abstract void Album2() throws Exception;
    private abstract void Album3() throws Exception;
    private abstract void Album4() throws Exception;
    private abstract void Album5() throws Exception;
    private abstract void Album6() throws Exception;
    private abstract void Album7() throws Exception;
    private abstract void Album8() throws Exception;
    private abstract void Album9() throws Exception;
    private abstract void Album10() throws Exception;
    private abstract void Album11() throws Exception;
    private abstract void Album12() throws Exception;
    private abstract void Album13() throws Exception;
    */
    public abstract List<String> MostrarNombreArtistas();
    
    //private abstract void Cargar_Seguidores() throws Exception;
    //private abstract void ListaGeneral1() throws Exception;
    public abstract void ListaGeneral2() throws Exception;
    public abstract void ListaGeneral3() throws Exception;
    //private abstract void Cargar_Listas() throws Exception;
    public abstract void ListaParticular1() throws Exception;
    public abstract void ListaParticular2() throws Exception;
    public abstract void ListaParticular3() throws Exception;
    public abstract void ListaParticular4() throws Exception;
    public abstract void ListaParticular5() throws Exception;
    public abstract void ListaParticular6() throws Exception;
    //private abstract void CargarFavoritos() throws Exception;
    public abstract DTCliente encontrarClientePorNickname(String nick);
    public abstract List<DTCliente> listaClientesDT();
    public abstract List<String> nombreDeListasPrivadasDeCliente(String mail);
    public abstract void publicarListaPrivada(String nick, String nombreLista) throws Exception;
    public abstract List<String> ListaAlbumesParaArtista(String correoArtista) throws Exception;
    public abstract DTAlbum findAlbumPorArtistaYNombre(String correoArtista, String nombreAlbum) throws Exception;
    public abstract List<DTListaRep> obtenerDTListaPorGenero(String selectedGenero);
    public abstract DTListaRep obtenerDatosDeLista_Por_Defecto(String nombreSeleccionado) throws Exception;
    public abstract List<DTListaRep> obtenerDTListaPorCliente(String correoCliente);
    public abstract DTListaRep obtenerDatosDeLista_Por_Cliente(String correoCliente, String nombreLista);
    public abstract List<String> nicknamesDeTodosLosArtistas();
    public abstract DTArtista encontrarDTArtistaPorNickname(String nick);
    public abstract List<String> nicksClientesSiguenArtista(String nickAr);
    public abstract List<String> listaAlbumesArtistaNick(String nick) throws Exception;
    public abstract List<String> nicksClientes();
    public abstract List<String> clientesSeguidosDelCliente(String nick);
    public abstract List<String> seguidoresDelCliente(String nick);
    public abstract List<String> artistasSeguidosDelCliente(String nick);
    public abstract List<String> nombresListaRepDeCliente(String nick);
    public abstract DefaultListModel favoritosDeCliente (String nick);
    public abstract List<String> obtenerSeguidos(String correoSeguidor);
    public abstract List<String> MostrarNombreArtistasbyAlbum(String nombreAlbum) throws Exception;
    public abstract List<String> temasDeAlbumDeArtista(String album, String artistaMail);
    public abstract List<String> listasPublicasDeCliente (String correo);
    public abstract List<String> listaAlbumesArtistaMail(String correo) throws Exception;
    public abstract List<String> listasDefecto();
    public abstract boolean sePuedenCargarLosDatos();

    public abstract List<String> FindListas();
    public abstract List<String> FindListasDefault();

    public abstract List<String>ListaTemas_De_Lista(String lista, String NickArtist);

    public abstract List<String> ListaTemas_De_Lista_Def(String lista);

    public abstract void AgregarTema_De_Album_A_Lista(String cliente, String lista_de_cliente, String album, String artista_de_album, String tema_selected) throws Exception;

    public abstract void AgregarTema_De_ListaPart_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String cliente_con_lista, String tema_selected)throws Exception;

    public abstract void AgregarTema_De_ListaDef_A_Lista(String cliente, String lista_de_cliente, String lista_where_temais, String tema_selected) throws Exception;

    public abstract void AgregarTema_De_Album_A_ListaDef(String lista, String album,String artista_de_album, String tema_selected)throws Exception;

    public abstract void AgregarTema_De_ListaPart_A_ListaDef(String lista, String lista_where_temais, String cliente_con_lista, String tema_selected)throws Exception;

    public abstract void AgregarTema_De_ListaDef_A_ListaDef(String lista, String lista_where_temais, String tema_selected)throws Exception;

    public abstract void EliminarTemaDeLista_Part(String NomLista, String nickCli, Long idTema) throws Exception;

    public abstract List<Long> GetIdTemas(String nomTema) throws Exception;

    public abstract void EliminarTemaDeLista_Def(String nombreLista, String nombreTema)throws Exception;
    
    public abstract double calculaMontoSubscripcion(String tipo) throws Exception;
    
    public abstract void crearSubscripcion(String nicknameCliente, String tipoSub) throws Exception;
    
    public abstract void PruebaSubs() throws Exception;

    
    public abstract List<DTSub> listarSubscripciones() throws Exception;

    public abstract void modificarEstadoSuscripcion(Long id, String nuevoEstado)throws Exception;
    
    public abstract List<DTAlbum> findAlbumesPorArtista(String nickArtista) throws Exception;
    
    public abstract List<DTListaRep> obtenerDTListaPorClientepublica(String correoCliente) ;
}
