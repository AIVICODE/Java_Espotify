/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author ivan
 */
@Entity
public class ListaRepGeneral extends ListaRep {

    public ListaRepGeneral(String nombre, String imagen) {
        super(nombre, imagen);
    }

    public ListaRepGeneral() {}

    public ListaRepGeneral(List<Tema> listaTemas, String nombre, String imagen) {
        super(listaTemas, nombre, imagen);
    }
    
    
}
