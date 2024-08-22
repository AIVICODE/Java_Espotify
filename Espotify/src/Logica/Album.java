
package Logica;

import java.util.List;


public class Album {
    protected String nombre;
    protected int anioCreacion;
    protected List<Genero> listaGeneros;
    //temas

    //Setters 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }
    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }
    
    //Getters
    public String getNombre() {
        return nombre;
    }    
    public int getAnioCreacion() {
        return anioCreacion;
    }
    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }
    
    
}
