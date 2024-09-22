package Datatypes;

import java.util.Date;
import java.util.List;

public class DTArtista extends DTUsuario {
    private String biografia;
    private String sitioWeb;

    public DTArtista(){ }
    public DTArtista(String nickname, String nombre, String apellido, String contrasenia, String imagen, Date fechaNac, String correo, String biografia, String sitioWeb) {
        super(nickname, nombre, apellido, correo, fechaNac, contrasenia,imagen);
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
    }

    public DTArtista(String nickname, String nombre, String apellido, String contrasenia, Date fechaNac, String correo, String biografia, String sitioWeb, List<String> listaUsuariosFavoritos) {
        super(nickname, nombre, apellido, correo, fechaNac, contrasenia, listaUsuariosFavoritos);
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
    }

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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
}
