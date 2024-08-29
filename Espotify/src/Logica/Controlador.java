
package Logica;

import Datatypes.DTArtista;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.util.Date;
import java.util.List;
import javax.swing.tree.TreeModel;


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
    }
    
    public boolean verificarExistenciaArtista(String correo) throws Exception {

    return controlpersis.findArtista(correo);
}
    
    public void CrearGenero(String nombre,String nombrePadre) throws Exception{
        // Buscar el género padre en la base de datos
    Genero padre = null;
    if (nombrePadre != null && !nombrePadre.isEmpty()) {
        padre = controlpersis.findGenerobynombre(nombrePadre);
        if (padre == null) {
            throw new Exception("El género padre no existe: " + nombrePadre);
        }
    }

    // Crear el nuevo género
    Genero nuevoGenero = new Genero(nombre, padre);

    // Añadir el nuevo género a la base de datos
    controlpersis.AddGenero(nuevoGenero);
    }

    public TreeModel buildGeneroTree() {
        return controlpersis.buildGeneroTree();
    }
    
    
    
   
}
