/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author ivan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTInfoTema {
    int cantidadDescargas;
    int cantidadReproducciones;
    int cantidadfavoritos;
    int cantidadlistas;

    public DTInfoTema(int cantidadDescargas, int cantidadReproducciones, int cantidadfavoritos, int cantidadlistas) {
        this.cantidadDescargas = cantidadDescargas;
        this.cantidadReproducciones = cantidadReproducciones;
        this.cantidadfavoritos = cantidadfavoritos;
        this.cantidadlistas = cantidadlistas;
    }

    public DTInfoTema() {
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public int getCantidadReproducciones() {
        return cantidadReproducciones;
    }

    public int getCantidadfavoritos() {
        return cantidadfavoritos;
    }

    public int getCantidadlistas() {
        return cantidadlistas;
    }
    
}
