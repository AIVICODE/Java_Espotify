
package Logica;

import Persis.ControladoraPersistencia;
import java.util.Date;


public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    //
    
    public void AddCliente(Cliente cli) throws Exception{
            //controlpersis.AddCliente(cli);
    }
    // creamos cliente y recibe datos de GUI
    public void Guardar(String NombreCli) throws Exception{
        Cliente cli= new Cliente();
        cli.setNombre(NombreCli); // para cambiar
        cli.setApellido(NombreCli);
        cli.setNickname(NombreCli);
        /*cli.setFechnac(new Date());
        cli.setImagen(NombreCli);
        cli.setCorreoelectronico(NombreCli);
        */
        //controlpersis.guardar(cli);
    }
    
}
