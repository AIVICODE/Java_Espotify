package Logica;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Cliente extends Usuario{
    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_seguidores",
        joinColumns = @JoinColumn(name = "cliente_seguidor_nickname"),
        inverseJoinColumns = @JoinColumn(name = "usuario_seguido_nickname")
    )
    private Set<Usuario> usuariosSeguidos = new HashSet<>();
    public Cliente() {}
    
    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail, Date fechaNac) {
        super();
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }
    //getters
    public Set<Usuario> getUsuariosSeguidos() {
        return usuariosSeguidos;
    }
    // Setters
    @Override
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    @Override
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    @Override
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    @Override
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    @Override
    public void setMail(String mail){
        this.mail = mail;
    }
    
    @Override
    public Date getFechaNac() {
        return fechaNac;
    }
     public void setUsuariosSeguidos(Set<Usuario> usuariosSeguidos) {
        this.usuariosSeguidos = usuariosSeguidos;
    }
     public void seguirUsuario(Usuario usuario) {
        this.usuariosSeguidos.add(usuario);
    }
     public void dejarDeSeguirUsuario(Usuario usuario) {
        this.usuariosSeguidos.remove(usuario);
    }
 
}
