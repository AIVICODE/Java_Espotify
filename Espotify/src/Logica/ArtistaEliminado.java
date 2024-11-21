
package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity


public class ArtistaEliminado implements Serializable{
  
    private String biografia;
    private String sitioWeb;
    private String albumes;
    private String temas;
    private String fechaEliminado;
    private String nickname;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Date fechaNac;
    private String mail;
    private String imagen;
    
    @Id
    private String id;
    
    public ArtistaEliminado() {}

    
    

    public String getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFechaEliminado() {
        return fechaEliminado;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    public void setId(String id) {
        this.id = id;
    }
    

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
    
    public void setBiografia(String biografia){
        this.biografia = biografia;
    }
    
    public void setSitioWeb(String sitioWeb){
        this.sitioWeb = sitioWeb;
    }

    public void setAlbumes(String albumes) {
        this.albumes = albumes;
    }
    public void setTemas(String temas) {
        this.temas = temas;
    }
    
    public void setFechaEliminado(String fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }
    
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
    
    public String getBiografia(){
        return biografia;
    }
    
    public String getSitioWeb(){
        return sitioWeb;
    }

    public String getAlbumes() {
        return albumes;
    }
    public String getTemas() {
        return temas;
    }
    
}
