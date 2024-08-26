
package Logica;

import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.util.Date;


public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    //
    //
    
    // creamos cliente y recibe datos de GUI
    public void crearUsuario(DTUsuario user) throws Exception {
        
Usuario nuevoUsuario = new Cliente(
    user.getNickname(),
    user.getNombre(),
    user.getApellido(),
    user.getContrasenia(),
    user.getCorreo(),
    user.getFechaNac()
);        
        controlpersis.AddCliente((Cliente) nuevoUsuario);
    }
   
   
}
