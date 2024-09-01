package Logica;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Artista extends Usuario{
    protected String biografia, sitioWeb;
    
    public Artista() {}

    public Artista(String nickname, String nombre, String apellido, String contrasenia,Date FechaNac, String mail, String biografia, String sitioWeb) {
        
        super();
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
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
}
