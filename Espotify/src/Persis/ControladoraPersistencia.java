package Persis;

import Logica.Album;
import Logica.Artista;
import Logica.Cliente;
import Logica.Favoritos;
import Logica.Genero;
import Logica.ListaRep;
import Logica.ListaRepGeneral;
import Logica.ListaRepParticular;
import Logica.RegistroIngresoWeb;
import Logica.Subscripcion;
import Logica.Tema;
import Persis.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ControladoraPersistencia {
    ClienteJpaController clijpa = new ClienteJpaController();
    ArtistaJpaController artjpa = new ArtistaJpaController();
    GeneroJpaController genjpa = new GeneroJpaController();
    AlbumJpaController albjpa = new AlbumJpaController();
    TemaJpaController temajpa = new TemaJpaController();
    ListaRepJpaController listjpa = new ListaRepJpaController();
    FavoritosJpaController favjpa = new  FavoritosJpaController();
    SubscripcionJpaController subjpa = new SubscripcionJpaController();
    RegistroIngresoWebJpaController regjpa=new RegistroIngresoWebJpaController();
    
    public void AddCliente(Cliente cli) throws Exception {
        clijpa.create(cli);
    }
    
    public void AddArtista(Artista art) throws Exception {
        artjpa.create(art);
    }
    
    public void AddGenero(Genero gen) throws Exception {
        try{
        genjpa.create(gen);
        }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("Ha ocurrido un error al intentar crear el Genero: " + gen.getNombre(), e);
    }
    }
    
    public Genero findGenerobynombre(String nombre)  {
        
        return genjpa.findGenero(nombre);
    }
    
    public boolean findArtista(String correo) throws Exception {
        return artjpa.findArtista(correo) != null; // Si encuentra al artista, devuelve true, de lo contrario, false
    }
    
    // Método para construir el modelo de árbol (JTree)
   public DefaultTreeModel buildGeneroTree() {
    // Crear un nodo raíz
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Géneros");

    // Obtener todos los géneros desde la base de datos
    List<Genero> generos = genjpa.findGeneroEntities();

    // Crear un mapa para organizar los géneros
    Map<String, DefaultMutableTreeNode> nodes = new HashMap<>();

    // Primero, crear todos los nodos y añadirlos al mapa
    for (Genero genero : generos) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(genero.getNombre());
        nodes.put(genero.getNombre(), node);
    }

    // Ahora, construir las relaciones padre-hijo
    for (Genero genero : generos) {
        DefaultMutableTreeNode node = nodes.get(genero.getNombre());

        if (genero.getPadre() != null) {
            DefaultMutableTreeNode parentNode = nodes.get(genero.getPadre().getNombre());

            if (parentNode != null) {
                // Verificar si el nodo a agregar no es un ancestro del nodo actual
                if (!isAncestor(parentNode, node)) {
                    parentNode.add(node);
                } else {
                    System.out.println("No se puede agregar " + genero.getNombre() + " como hijo de " + genero.getPadre().getNombre() + " porque es un ancestro.");
                }
            } else {
                System.out.println("Padre no encontrado para: " + genero.getNombre());
            }
        } else {
            // Si no tiene padre, agregar el nodo al nodo raíz
            root.add(node);
        }
    }

    return new DefaultTreeModel(root);
}

// Método para verificar si un nodo es ancestro de otro
private boolean isAncestor(DefaultMutableTreeNode ancestor, DefaultMutableTreeNode node) {
    DefaultMutableTreeNode current = node;
    while (current != null) {
        if (current.equals(ancestor)) {
            return true;
        }
        current = (DefaultMutableTreeNode) current.getParent();
    }
    return false;
}

public void crearAlbum(Album album) throws Exception {
    try {
        albjpa.create(album);
    }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("Ha ocurrido un error al intentar crear el álbum: " + album.getNombre(), e);
    }
        
}

    public void crearTema(Tema tema) throws Exception,PreexistingEntityException {
        temajpa.create(tema);
    }

    public void actualizarAlbum(Album album) throws Exception {
        albjpa.edit(album);
    }

    public Artista findArtistaByCorreo(String correo) throws Exception {
        try{
        return artjpa.findArtista(correo);
        }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("No se encuentra el Artista: " + correo, e);
    }
    }

    public Cliente findClienteByCorreo(String correoCliente) {
        return clijpa.findCliente(correoCliente);
    }

    public void createListaRep(ListaRep nuevaLista) {
        listjpa.create(nuevaLista);
    }

    public void editCliente(Cliente cliente) throws Exception {
        clijpa.edit(cliente);
    }

    public List<Album> findAlbumByNombre(String recurso) throws Exception{
        
           try {
        return albjpa.findAlbumByName(recurso);
    }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("No se encuentra el nombre del album: " +recurso, e);
    }
        
    }
    
    

    public List<ListaRep> findListaRepByNombre(String recurso) throws Exception{
        try {
        return listjpa.findListaRepByNombre(recurso);
    }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("No se encuentra el nombre de la lista de reproduccion: " +recurso, e);
    }
    }
    
    public List<String> NombreListasRepParticular(){
        return listjpa.NombreDeListasParticulares();
}
    
    
        public Album findOneAlbumByNombre(String recurso) throws Exception{
        
           try {
        return albjpa.findOneAlbumByName(recurso);
    }catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("No se encuentra el nombre del album: " +recurso, e);
    }
        
    }
    
    public List<Cliente> listaClientes (){
        return clijpa.findClienteEntities(); //me devuelve una lista con todos los clientes de la BD para mostrarlos de ahi
    }
    
    public Cliente encontrarCliente(String mail){
        return clijpa.findCliente(mail);//devuelvo el cliente
    }
    public List<Artista> listaArtistas (){
        return artjpa.findArtistaEntities(); //me devuelve una lista con todos los artistas de la BD para mostrarlos de ahi
    }
    
    public Artista encontrarArtista(String mail){
        return artjpa.findArtista(mail);//devuelvo el artista
    }

    public ListaRepGeneral findListaRep_Por_Defecto_ByNombre(String nombreLista) throws Exception {
        return listjpa.findListaRep_Por_Defecto_ByNombre(nombreLista);
    }

public void editListaPrivada(ListaRepParticular lista) throws Exception{//puede ser que la tenga q cambiar a particular
        listjpa.edit(lista);//le ando la lista hecha publica
    } 
public void editListaPorDefecto(ListaRepGeneral lista) throws Exception{
    listjpa.edit(lista);
}
     public Album findAlbumId(Long id){
        
        return albjpa.findAlbum(id);
    }

   public List<ListaRepGeneral> buscarListasPorGenero(String selectedGenero) {
    // Obtener el género desde la base de datos
    Genero genero = genjpa.findGenero(selectedGenero);
    if (genero == null) {
        throw new IllegalArgumentException("El género con nombre '" + selectedGenero + "' no existe.");
    }

    // Buscar listas de reproducción generales asociadas al género
    return listjpa.findListasRepGeneralByGenero(genero);
}

    public String NicknameCliente(String string) {
       Cliente cli = clijpa.findCliente(string);//devuelvo el artista
        return cli.getNickname();
    }

    public String NicknameArtista(String string) {
       Artista art = artjpa.findArtista(string);//devuelvo el artista
        return art.getNickname();
    }

    public Cliente findClienteByNickname(String nickname) throws Exception {
        try{
       return clijpa.findClienteByNickname(nickname);
        }
       catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("Ocurrio un error con el nickname: "+nickname);
    }
        
    }

    public Artista findArtistaByNickname(String nickname) throws Exception {
        try{
    return artjpa.findArtistaByNickname(nickname); // Llama al método del controlador de persistencia
    }
       catch (Exception e) {
        // Captura cualquier otra excepción y lanza una nueva excepción general
        throw new Exception("Ocurrio un error con el nickname: "+nickname);
    }
    }

public Artista encontrarArtistaPorNickname(String nickname) {
    return artjpa.findArtistaByNickname(nickname); // Llama al método del controlador de persistencia
}
     
    public List<ListaRep> listas(){
        return listjpa.findListaRepEntities();
    }
    
        public List<Genero> listaGeneros (){
        return genjpa.findGeneroEntities(); //me devuelve una lista con todos los artistas de la BD para mostrarlos de ahi
    }
    
    public List<Album> listaAlbumes(){
        return albjpa.findAlbumEntities(); //me devuelve una lista con todos los albumes de la BD para mostrarlos de ahi
    }
    
    public void modificarBiografiaArtista(){
        artjpa.modificarBiografiaArtista();
    }

    public List<String> NombreListasRep_Defecto() {
       return listjpa.NombreDeListasDefault();
    }

    public List<Tema> findTemaEntities() {
        
        return temajpa.findTemaEntities();
    }

    public void crearSubscripcion(Subscripcion nuevaSubscripcion) {
       subjpa.create(nuevaSubscripcion);
    }

    public List<Subscripcion> getAllSubscripciones() {
       return subjpa.findSubscripcionEntities();

    }

    public Subscripcion findSubscripcion(Long id) {
       return subjpa.findSubscripcion(id);
    }

    public void updateSubscripcion(Subscripcion sub) throws Exception {
      subjpa.edit(sub);
    }

    public List<Favoritos> findFavoritosByFiltroCriteria(String filtro, String sortBy) {
       return favjpa.findFavoritosByFiltroCriteria(filtro, sortBy);
    }
    
  public List<Album> findDTAlbumTodos(){
      return albjpa.findAlbumEntities();

}
  
  public List<ListaRep> findListasRep(){
       
       return listjpa.findListaRepEntities();
  
   }
  
  public void GeneraRegistro(RegistroIngresoWeb regweb){
      regjpa.create(regweb);
  
  }
}
