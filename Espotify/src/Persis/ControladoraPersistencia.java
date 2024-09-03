
package Persis;

import Logica.Artista;
import Logica.Cliente;
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
}
