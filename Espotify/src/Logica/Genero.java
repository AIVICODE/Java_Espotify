
package Logica;

import java.util.ArrayList;
import java.util.List;


public class Genero {
    protected String nombre;
    protected List<Genero> listaGeneros;
    //
    public Genero(){}
    
    public Genero(String nombre){//no le puse la lista aca porq puede estar vacia si es el genero mas grande
        this.nombre = nombre;
        this.listaGeneros = new ArrayList<>();
    }
    
    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setListaGeneros(List<Genero> listaGeneros){
        this.listaGeneros = listaGeneros;
    }
    //Getters
    public String getNombre(){
        return nombre;
    }
    public List<Genero> getListaGeneros (){
        return this.listaGeneros;        
    }
}
