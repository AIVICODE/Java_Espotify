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
public class DTInfoTema {
    @XmlElement
    int cantidadDescargas;
    @XmlElement
    int cantidadReproducciones;
    @XmlElement
    int cantidadfavoritos;
    @XmlElement
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
