
package Logica;

import Datatypes.DTArtista;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
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
        //controlpersis.AddArtista((Artista) nuevoUsuario);
    } else if (user instanceof DTUsuario){
        DTUsuario cliente = (DTUsuario) user;
        
        nuevoUsuario = new Cliente(
            cliente.getNickname(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getContrasenia(),
            cliente.getCorreo(),
            cliente.getFechaNac()
        );
         //controlpersis.AddCliente((Cliente) nuevoUsuario);
    }else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }
    controlpersis.addUsuario(nuevoUsuario);

    // Llamada al método adecuado según el tipo de usuario

    }
    
    public boolean verificarExistenciaArtista(String correo) throws Exception {

    return false; //controlpersis.findArtista(correo);
}
   
}
