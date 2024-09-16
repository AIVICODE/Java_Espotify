/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import java.time.Year;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ivan
 */
public class DTAlbum {
    protected String nombre;
    protected int anioCreacion;
    protected String imagen;
    protected List<String> listaGeneros;
    protected List<DTTema> listaTemas;
    protected DTArtista artista;

    public DTAlbum(String nombre, int anioCreacion, String imagen, List<String> listaGeneros) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
    }

    
    public DTAlbum(String nombre, int anioCreacion, String imagen, List<String> listaGeneros,List<DTTema> listaTemas,DTArtista artista) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
        this.listaTemas = listaTemas;
        this.artista = artista;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getImagen() {
        return imagen;
    }

    public List<String> getListaGeneros() {
        return listaGeneros;
    }

   

    
    
    
    
}
