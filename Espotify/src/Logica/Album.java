package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Album extends Favoritos {

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date anioCreacion;
    private String imagen;

    @ManyToMany
    @JoinTable(
        name = "album_genero",
        joinColumns = @JoinColumn(name = "album_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> listaGeneros;

    @OneToMany(mappedBy = "album")
    private List<Tema> listaTemas;

    @ManyToOne
    @JoinColumn(name = "artista_mail") // Nombre de la columna en la tabla album
    private Artista artista;

    public Album() {
    }

    public Album(String nombre, Date anioCreacion, String imagen, List<Genero> listaGeneros, List<Tema> listaTemas, Artista artista) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
        this.listaTemas = listaTemas;
        this.artista = artista;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(Date anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public List<Tema> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Tema> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
