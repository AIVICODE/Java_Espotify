/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import java.util.List;

/**
 *
 * @author ivan
 */
public class DTListaRep extends DTContenido{
    protected String nombreListaRep;
    protected String nombreCliente;
    protected String Genero;
    protected String imagen;
    protected List<DTTema> temas;

    public DTListaRep(String nombreListaRep, String nombreCliente, String Genero, String imagen, List<DTTema> temas) {
        this.nombreListaRep = nombreListaRep;
        this.nombreCliente = nombreCliente;
        this.Genero = Genero;
        this.imagen = imagen;
        this.temas = temas;
    }
     public DTListaRep(String nombreListaRep, String nombreCliente, String Genero) {
        this.nombreListaRep = nombreListaRep;
        this.nombreCliente = nombreCliente;
        this.Genero = Genero;
    }

    public String getNombreListaRep() {
        return nombreListaRep;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getGenero() {
        return Genero;
    }

    public String getImagen() {
        return imagen;
    }

    public List<DTTema> getTemas() {
        return temas;
    }
    
}
