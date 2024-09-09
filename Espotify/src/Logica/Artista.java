package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "mail")
public class Artista extends Usuario implements Serializable {
    private String biografia;
    private String sitioWeb;

    @OneToMany(mappedBy = "artista")
    private List<Album> albumes;
    
    public Artista() {}

    public Artista(String nickname, String nombre, String apellido, String contrasenia, Date fechaNac, String mail, String biografia, String sitioWeb) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.fechaNac = fechaNac;
        this.mail = mail;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
    }
     public Artista(String nickname, String nombre, String apellido, String contrasenia, Date fechaNac, String mail, String biografia, String sitioWeb,String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.fechaNac = fechaNac;
        this.mail = mail;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
        this.imagen = imagen;
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
    
    public void setBiografia(String biografia){
        this.biografia = biografia;
    }
    
    public void setSitioWeb(String sitioWeb){
        this.sitioWeb = sitioWeb;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    // Getters
    @Override
    public String getNickname(){
        return nickname;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override
    public String getApellido(){
        return apellido;
    }

    @Override
    public String getContrasenia(){
        return contrasenia;
    }

    @Override
    public String getMail(){
        return mail;
    }
    
    public String getBiografia(){
        return biografia;
    }
    
    public String getSitioWeb(){
        return sitioWeb;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }
    
     @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Artista artista = (Artista) o;
    return Objects.equals(mail, artista.mail);  // Compara por correo
    }
    @Override
    public int hashCode() {
        return Objects.hash(mail);  // Utiliza el correo como base para el hash
    }
    
}
