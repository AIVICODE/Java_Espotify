/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import java.time.Year;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DTAlbum extends DTContenido{
    @XmlElement
    protected String nombre;
    @XmlElement
    protected int anioCreacion;
    @XmlElement
    protected String imagen;
    @XmlElement
    protected List<String> listaGeneros;
    @XmlElement
    protected List<DTTema> listaTemas;
    @XmlElement
    protected DTArtista artista;
    @XmlElement
    protected byte[] imagenBytes; 
    public DTAlbum() {
    }
    public DTAlbum(String nombre, int anioCreacion, String imagen, List<String> listaGeneros) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
    }
public DTAlbum(String nombre, int anioCreacion, List<String> listaGeneros, DTArtista artista) {
    this.nombre = nombre;
    this.anioCreacion = anioCreacion;
    this.listaGeneros = listaGeneros;
    this.artista = artista;
}

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }
    
    public DTAlbum(String nombre, int anioCreacion, String imagen, List<String> listaGeneros,List<DTTema> listaTemas,DTArtista artista) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
        this.listaTemas = listaTemas;
        this.artista = artista;
    }
     public DTAlbum(String nombre, int anioCreacion, String imagen, List<String> listaGeneros,List<DTTema> listaTemas,DTArtista artista,byte[] imagenBytes) {
        this.nombre = nombre;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
        this.listaGeneros = listaGeneros;
        this.listaTemas = listaTemas;
        this.artista = artista;
        this.imagenBytes = imagenBytes;
    }
    
    public DTAlbum (String nombre, List<DTTema> listaTemas, DTArtista artista){
        this.nombre = nombre;
        this.listaTemas = listaTemas;
        this.artista = artista;
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
