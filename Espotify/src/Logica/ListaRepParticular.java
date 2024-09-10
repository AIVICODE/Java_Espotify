
package Logica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ListaRepParticular extends ListaRep implements Serializable {

    @Column
    private boolean privada;

    // Constructor, getters, and setters
    public ListaRepParticular(String nombre, String imagen, boolean privada) {
        super(nombre, imagen);
        this.privada = privada;
    }

    public ListaRepParticular() {
        super(); // Asegúrate de llamar al constructor de la clase base
    }

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
}

