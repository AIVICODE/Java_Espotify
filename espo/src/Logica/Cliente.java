package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mail")
public class Cliente extends Usuario implements Serializable {
    


    @OneToMany
    private List<Tema> temas;

    @OneToMany
    private List<Album> albums;
    
    
     @OneToMany
    @JoinTable(name = "CLIENTE_LISTAS_REPRODUCCION",
               joinColumns = @JoinColumn(name = "cliente_mail"),
               inverseJoinColumns = @JoinColumn(name = "lista_reproduccion_id"))
    private List<ListaRep> listaReproduccion;

    @OneToMany
    @JoinTable(name = "CLIENTE_FAVORITOS",
               joinColumns = @JoinColumn(name = "cliente_mail"),
               inverseJoinColumns = @JoinColumn(name = "lista_reproduccion_id"))
    private List<ListaRep> listaRepFavoritos;

    public Cliente(List<Tema> temas, List<Album> albums) {
        this.temas = temas;
        this.albums = albums;
    }

    public Cliente(List<Tema> temas, List<Album> albums, String nickname, String nombre, String apellido, String contrasenia, String mail, String imagen, Date fechaNac) {
        super(nickname, nombre, apellido, contrasenia, mail, imagen, fechaNac);
        this.temas = temas;
        this.albums = albums;
    }

    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail,  Date fechaNac,String imagen) {
        super(nickname, nombre, apellido, contrasenia, mail, imagen, fechaNac);
    }

    

   
    public List<ListaRep> getListaRepFavoritos() {
        return listaRepFavoritos;
    }

    public void setListaRepFavoritos(List<ListaRep> listaRepFavoritos) {
        this.listaRepFavoritos = listaRepFavoritos;
    }
    
    
    
    public List<ListaRep> getListaReproduccion() {
        return listaReproduccion;
    }

    public void setListaReproduccion(List<ListaRep> listaReproduccion) {
        this.listaReproduccion = listaReproduccion;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    
    
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
