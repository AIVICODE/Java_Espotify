
package Logica;

import Datatypes.DTArtista;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.util.Date;


public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    //
    //
    
    // creamos cliente y recibe datos de GUI
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

    // Llamada al método adecuado según el tipo de usuario

    }
   
   
}
