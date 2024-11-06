package Logica;

import Logica.Favoritos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ListaRep extends Favoritos {

    @OneToMany
    @OrderColumn(name = "orden")
    private List<Tema> listaTemas = new ArrayList<>();

    public ListaRep(List<Tema> listaTemas, String nombre, String imagen) {
        this.listaTemas = listaTemas;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public List<Tema> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Tema> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String imagen;

    // Constructor, getters, and setters
    public ListaRep(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public ListaRep() {
    this.listaTemas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
