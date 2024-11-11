package Datatypes;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DTUsuario {
    @XmlElement
    protected String nickname;
    @XmlElement
    protected String nombre, apellido, contrasenia;
    @XmlElement
    protected String correo, imagen;
    @XmlElement
    protected Date fechaNac;
    @XmlElement
    protected String confirmacion;
    @XmlElement
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
    
    public String getImagen(){
        return imagen;
    }

    public List<String> getListaUsuariosFavoritos() {
        return listaUsuariosFavoritos;
    }

    public void setListaUsuariosFavoritos(List<String> listaUsuariosFavoritos) {
        this.listaUsuariosFavoritos = listaUsuariosFavoritos;
    }
}
