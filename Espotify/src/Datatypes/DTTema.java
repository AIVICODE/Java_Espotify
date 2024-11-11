/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

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
public class DTTema extends DTContenido{
    @XmlElement
    private String nombre;
    @XmlElement
    private int minutos;
    @XmlElement
    private int segundos;
    @XmlElement
    private String directorio;
    @XmlElement
    private int orden;
    @XmlElement
    private String nombrealbum;
    @XmlElement
    private String nombreartista;
    @XmlElement
    private int anioCreacion;

    
    public DTTema() {
    }
    
    public DTTema(String nombre, int minutos, int segundos, String directorio) {
        this.nombre = nombre;
        this.minutos = minutos;
        this.segundos = segundos;
        this.directorio = directorio;
    }
    public DTTema(String nombre, int minutos, int segundos, String directorio,String nombrealbum, String nombreartista) {
        this.nombre = nombre;
        this.minutos = minutos;
        this.segundos = segundos;
        this.directorio = directorio;
        this.nombrealbum = nombrealbum;
        this.nombreartista = nombreartista;
    }
    
    public DTTema(String nombre, int minutos, int segundos, String directorio,String nombrealbum, String nombreartista,int anioCreacion) {
        this.nombre = nombre;
        this.minutos = minutos;
        this.segundos = segundos;
        this.directorio = directorio;
        this.nombrealbum = nombrealbum;
        this.nombreartista = nombreartista;
        this.anioCreacion = anioCreacion;
    }
    
    
    
     public DTTema(String nombre, int minutos, int segundos, String directorio,int orden) {
        this.nombre = nombre;
        this.minutos = minutos;
        this.segundos = segundos;
        this.directorio = directorio;
        this.orden = orden;
    }

       public DTTema(String nombre,String nombrealbum, String nombreartista) {
        this.nombre = nombre;
        this.nombrealbum = nombrealbum;
        this.nombreartista = nombreartista;
    }
     
    public String getNombre() {
        return nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public String getDirectorio() {
        return directorio;
    }

    
     public int getOrden() {
        return orden;
    }
     
    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public String getNombrealbum() {
        return nombrealbum;
    }

    public void setNombrealbum(String nombrealbum) {
        this.nombrealbum = nombrealbum;
    }

    public String getNombreartista() {
        return nombreartista;
    }

    public void setNombreartista(String nombreartista) {
        this.nombreartista = nombreartista;
    }

    
    
    
}
