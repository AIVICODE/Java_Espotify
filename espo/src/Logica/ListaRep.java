package Logica;

import Logica.Favoritos;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ListaRep extends Favoritos {

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

    public ListaRep() {}

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
