
package Logica;

import Persis.ControladoraPersistencia;
import java.util.Date;


public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    //
    //
    public void AddCliente(Cliente cli) throws Exception{
            //controlpersis.AddCliente(cli);
    }
    // creamos cliente y recibe datos de GUI
    public void Guardar(String Nickname, String Nombre, String Apellido, String Correo, Date FechaNac, String Imagen, boolean esCli, String Biografia, String SitioWeb) throws Exception {

    if (esCli) {
        Cliente cli = new Cliente();
        cli.setNombre(Nombre);
        cli.setApellido(Apellido);
        cli.setNickname(Nickname);
        cli.setMail(Correo);
        cli.setFechaNac(FechaNac);
         if (Imagen != null && !Imagen.isEmpty()) {
        cli.setImagen(Imagen);
    }
    } else {
        Artista artis= new Artista(Nickname,Nombre,Apellido,Correo,FechaNac,Imagen,Biografia,SitioWeb);
        
        if (Imagen != null && !Imagen.isEmpty()) {
        artis.setImagen(Imagen);
    }
    }
    

    // controlpersis.guardar(user);
}
}
