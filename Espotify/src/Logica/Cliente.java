package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mail")
public class Cliente extends Usuario implements Serializable {
    
    public Cliente() {}
    
    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail, Date fechaNac) {
        super(nickname, nombre, apellido, contrasenia, mail, "", fechaNac); // Llamar al constructor de la clase base
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
