/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ivan
 */
@Entity
public class ListaRep extends Favoritos  {
    

    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String imagen;
    private boolean privada;

    public ListaRep(String nombre, String imagen, boolean privada) {
        
        this.nombre = nombre;
        this.imagen = imagen;
        this.privada = privada;
    }

    public ListaRep() {
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

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
    


    
}
