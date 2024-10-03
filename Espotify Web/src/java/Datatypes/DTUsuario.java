package Datatypes;

import java.util.Date;
import java.util.List;

public class DTUsuario {

    protected String nickname;
    protected String nombre, apellido, contrasenia;
    protected String correo, imagen;
    protected Date fechaNac;
    protected String confirmacion;
    private List<String> listaUsuariosFavoritos;
    
    public DTUsuario() {}
    public DTUsuario(String nickname, String nombre, String apellido, String correo, Date fechaNac, String contrasenia,String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.contrasenia = contrasenia;
        this.imagen= imagen;
    }
    
     public DTUsuario(String nickname, String nombre, String apellido, String correo, Date fechaNac, String contrasenia,String confirmacion,String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.contrasenia = contrasenia;
        this.confirmacion = confirmacion;
        this.imagen= imagen;
    }

    public DTUsuario(String nickname, String nombre, String apellido, String correo, Date fechaNac, String contrasenia, List<String> listaUsuariosFavoritos) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.contrasenia = contrasenia;
        this.listaUsuariosFavoritos = listaUsuariosFavoritos;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public List<String> getListaUsuariosFavoritos() {
        return listaUsuariosFavoritos;
    }

    public void setListaUsuariosFavoritos(List<String> listaUsuariosFavoritos) {
        this.listaUsuariosFavoritos = listaUsuariosFavoritos;
    }
}
