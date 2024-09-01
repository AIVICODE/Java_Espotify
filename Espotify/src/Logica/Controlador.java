
package Logica;

import Datatypes.DTArtista;
import Datatypes.DTUsuario;
import Persis.ControladoraPersistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
public class Controlador {
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
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
        } else if (user instanceof DTUsuario) {
            DTUsuario cliente = (DTUsuario) user;

            nuevoUsuario = new Cliente(
                    cliente.getNickname(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getContrasenia(),
                    cliente.getCorreo(),
                    cliente.getFechaNac()
            );
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }
        controlpersis.addUsuario(nuevoUsuario);
    }
    
    public void seguirUsuario (String seguidor, String seguido) throws Exception{
        Usuario s1 = controlpersis.obtenerUsuarioPorNickname(seguidor);
        Usuario s2 = controlpersis.obtenerUsuarioPorNickname(seguido);
        if (s1 != null && s2 != null) {
            if (s1 instanceof Cliente) {
                Cliente cliente = (Cliente) s1;
                //LOGICA QUE AGREGA USUARIO A SET
                cliente.seguirUsuario(s2);
                controlpersis.updateUsuario(cliente);
            } else if (s1 instanceof Artista) {
                //TE MANDA A ERROR PORQUE (CREO QUE..) LOS ARTISTAS NBO PUEDEN SEGUIR GENTE
                throw new IllegalArgumentException("Los artistas no siguen clienters");
            }
        } else {
            throw new IllegalArgumentException("No existe un usuario cliente/artista con ese nickname: "+ seguidor);
        }
    }
    public void dejarDeSeguirUsuario (String seguidor, String seguido) throws Exception{
        Usuario s1 = controlpersis.obtenerUsuarioPorNickname(seguidor);
        Usuario s2 = controlpersis.obtenerUsuarioPorNickname(seguido);
        if (s1 != null && s2 != null) {
            if (s1 instanceof Cliente) {
                Cliente cliente = (Cliente) s1;
                Usuario usr = (Usuario) s2;
                //LOGICA QUE borra USUARIO de SET
                cliente.dejarDeSeguirUsuario(usr);
                controlpersis.updateUsuario(cliente);
            } else if (s1 instanceof Artista) {
                //TE MANDA A ERROR PORQUE (CREO QUE..) LOS ARTISTAS NBO PUEDEN SEGUIR GENTE
                throw new IllegalArgumentException("Los artistas no siguen clienters");
            }
        } else {
            throw new IllegalArgumentException("No existe un usuario cliente/artista con ese nickname: "+ seguidor);
        }
    }
    
    public Usuario obtenerUsuarioPorNickname(String nickname) {
        Usuario u = controlpersis.obtenerUsuarioPorNickname(nickname); 
        return u;
    }

    public boolean verificarExistenciaArtista(String correo) {
        Usuario u = controlpersis.obtenerUsuarioPorCorreo(correo);
        if(u instanceof Artista){
            return true;
        }else{
            return false;
        }
    }
   
}
