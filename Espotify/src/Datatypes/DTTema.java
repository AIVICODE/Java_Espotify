/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

/**
 *
 * @author ivan
 */
public class DTTema extends DTContenido{
    private String nombre;
    private int minutos;
    private int segundos;
    private String directorio;
    private int orden;
    private String nombrealbum;
    private String nombreartista;
    private int anioCreacion;

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

    
    
    
}
