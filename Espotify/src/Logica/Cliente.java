package Logica;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario{
    
    public Cliente() {}
    
    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail, Date fechaNac) {
        super();
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
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
}
