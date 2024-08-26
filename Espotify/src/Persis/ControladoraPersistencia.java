
package Persis;

import Logica.Artista;
import Logica.Cliente;

   


public class ControladoraPersistencia {
    ClienteJpaController clijpa=new ClienteJpaController();
    ArtistaJpaController artjpa=new ArtistaJpaController();
    public void AddCliente(Cliente cli) throws Exception {
       clijpa.create(cli);
    }
    
    public void AddArtista(Artista art) throws Exception{
    artjpa.create(art);
    }
}
