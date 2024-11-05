/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author ivan
 */
@Entity
public class ListaRepGeneral extends ListaRep {
    @OneToOne
    @JoinColumn(name = "genero_id")
    Genero genero;
    public ListaRepGeneral(String nombre, String imagen) {
        super(nombre, imagen);
    }
    
public ListaRepGeneral(String nombre) {
    super(nombre, null); 

}

    public ListaRepGeneral( String nombre, String imagen,Genero genero) {
        super(nombre, imagen);
        this.genero = genero;
    }

    public ListaRepGeneral() {}

    public ListaRepGeneral(List<Tema> listaTemas, String nombre, String imagen) {
        super(listaTemas, nombre, imagen);
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
}
