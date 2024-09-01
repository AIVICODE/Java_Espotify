package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "nickname"))
public abstract class Usuario implements Serializable{
    @Id
    @Column(name = "mail", nullable = false, unique = true)
    protected String mail;
    @Column(name = "nickname", nullable = false, unique = true)
    protected String nickname;
    protected String nombre, apellido, contrasenia;
    protected String imagen;
    @Temporal(TemporalType.DATE)
    protected Date fechaNac;
    
    // Constructor vacío
    public Usuario() {}
    
    // Constructor con parámetros
    public Usuario(String nickname, String nombre, String apellido, String contrasenia, String mail, String imagen, Date fechaNac) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.imagen = imagen;
        this.fechaNac = fechaNac;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // Setters
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    // Getters
    public String getNickname(){
        return nickname;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getContrasenia(){
        return contrasenia;
    }
    
    public String getMail(){
        return mail;
    }
    
    public Date getFechaNac() {
        return fechaNac;
    }
}
