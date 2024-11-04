
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Genero implements Serializable {
   @Id
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Genero padre;

    
    public Genero(String nombre, Genero padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    public Genero() {
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getPadre() {
        return padre;
    }

    public void setPadre(Genero padre) {
        this.padre = padre;
    }
}
