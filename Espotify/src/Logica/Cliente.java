package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mail")
public class Cliente extends Usuario implements Serializable {
    


    @ManyToMany
    @JoinTable(
        name = "cliente_artista",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistasSeguidos;
    @ManyToMany
    @JoinTable(
        name = "cliente_cliente",
        joinColumns = @JoinColumn(name = "seguidor_id"),
        inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    private List<Cliente> clientesSeguidos;

   @OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "cliente_id")
private List<Subscripcion> subs;

    public List<Subscripcion> getSubs() {
        return subs;
    }

    public void setSubs(List<Subscripcion> subs) {
        this.subs = subs;
    }
    
    public List<Artista> getArtistasSeguidos() {
        return artistasSeguidos;
    }

    
    
    public void setArtistasSeguidos(List<Artista> artistasSeguidos) {
        this.artistasSeguidos = artistasSeguidos;
    }

    public List<Cliente> getClientesSeguidos() {
        return clientesSeguidos;
    }

    public void setClientesSeguidos(List<Cliente> clientesSeguidos) {
        this.clientesSeguidos = clientesSeguidos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail, Date fechaNac) {
        super(nickname, nombre, apellido, contrasenia, mail, "", fechaNac); // Llamar al constructor de la clase base
        this.clientesSeguidos = new ArrayList<>();
        this.artistasSeguidos = new ArrayList<>();
    }

    
    
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
    @JoinTable(name = "CLIENTE_LISTA_FAVORITOS",
               joinColumns = @JoinColumn(name = "cliente_mail"),
               inverseJoinColumns = @JoinColumn(name = "lista_reproduccion_id"))
    private List<ListaRep> listaRepFavoritos;

        public Cliente() {}
          
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
        return this.listaRepFavoritos;
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

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return Objects.equals(mail, cliente.mail);  // Compara por correo
    }
    @Override
    public int hashCode() {
        return Objects.hash(mail);  // Utiliza el correo como base para el hash
    }
    public void seguirCliente(Cliente cliente) {
        this.clientesSeguidos.add(cliente);
    }
    public void dejarDeSeguirCliente(Cliente cliente) {
        
        this.clientesSeguidos.remove(cliente);
    }
      public void seguirArtista(Artista artista) {
        this.artistasSeguidos.add(artista);
    }
    public void dejarDeSeguirArtista(Artista artista) {
        this.artistasSeguidos.remove(artista);
    }
    
    
}
