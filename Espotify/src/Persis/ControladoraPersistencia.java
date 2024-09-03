
package Persis;

import Logica.Artista;
import Logica.Cliente;
import Logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

   


public class ControladoraPersistencia {
    ClienteJpaController clijpa=new ClienteJpaController();
    ArtistaJpaController artjpa=new ArtistaJpaController();
    public void AddCliente(Cliente cli) throws Exception {
       clijpa.create(cli);
    }
    
    public void AddArtista(Artista art) throws Exception{
    artjpa.create(art);
    }
    
    public boolean findArtista(String correo) throws Exception{
    
        return artjpa.findArtista(correo)!=null;// Si encuentra al artista, devuelve true, de lo contrario, false
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
    
}
