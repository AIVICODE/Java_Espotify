/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

/**
 *
 * @author ivan
 */
public class DTTema {
    private String nombre;
    private int minutos;
    private int segundos;
    private String directorio;

    public DTTema(String nombre, int minutos, int segundos, String directorio) {
        this.nombre = nombre;
        this.minutos = minutos;
        this.segundos = segundos;
        this.directorio = directorio;
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

    
    
    
    
    
}
