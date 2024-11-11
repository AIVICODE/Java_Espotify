/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

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
public class DTListaRep extends DTContenido{
    @XmlElement
    protected String nombreListaRep;
    @XmlElement
    protected String nombreCliente;
    @XmlElement
    protected String Genero;
    @XmlElement
    protected String imagen;
    @XmlElement
    protected List<DTTema> temas;
    @XmlElement
    protected byte[] imagenBytes; 
    public DTListaRep() {}

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

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }
     
         public DTListaRep(String nombreListaRep, String nombreCliente, String Genero, String imagen, List<DTTema> temas,byte[] imagenBytes) {
        this.nombreListaRep = nombreListaRep;
        this.nombreCliente = nombreCliente;
        this.Genero = Genero;
        this.imagen = imagen;
        this.temas = temas;
        this.imagenBytes = imagenBytes;
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
    
    public DTListaRep(String nombreListaRep, String nombreCliente, String Genero, List<DTTema> temas){
        this.nombreListaRep = nombreListaRep;
        this.nombreCliente = nombreCliente;
        this.Genero = Genero;
        this.temas = temas;
    }
    
}
