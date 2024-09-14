
package Datatypes;

import Logica.ListaRep;
import java.util.Date;
import java.util.List;


public class DTCliente extends DTUsuario{
    
    public DTCliente(){}
    public DTCliente(String nickname, String nombre, String apellido, String correo, Date fechaNac, String contrasenia, String imagen) {
        super(nickname, nombre, apellido, correo, fechaNac, contrasenia, imagen);
    }
    private List<ListaRep> listaReproduccion;
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    public List<ListaRep> getListaReproduccion() {
        return listaReproduccion;
    }
    public void setListaReproduccion(List<ListaRep> listaReproduccion) {
        this.listaReproduccion = listaReproduccion;
    }
}
