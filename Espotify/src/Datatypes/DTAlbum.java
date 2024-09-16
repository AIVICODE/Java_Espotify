/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import Logica.Artista;
import Logica.Tema;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ivan
 */
public class DTAlbum {
    protected String nombre;
    protected Date anioCreacion;
    protected String imagen;
    protected List<String> listaGeneros;
    protected List<DTTema> listaTemas;
    protected DTArtista artista;
    
    public DTAlbum(String nombre, Date anioCreacion, String imagen, List<String> listaGeneros) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
    }
    
    public DTAlbum(String nombre, Date anioCreacion, String imagen, List<String> listaGeneros,List<DTTema> listaTemas,DTArtista artista) {
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

    public Date getAnioCreacion() {
        return anioCreacion;
    }

    public String getImagen() {
        return imagen;
    }

    public List<String> getListaGeneros() {
        return listaGeneros;
    }

    public List<DTTema> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<DTTema> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public DTArtista getArtista() {
        return artista;
    }

    public void setArtista(DTArtista artista) {
        this.artista = artista;
    }
  
}

