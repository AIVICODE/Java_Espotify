package Datatypes;

import java.util.Date;
import java.util.List;

public class DTArtista extends DTUsuario {
    private String biografia;
    private String sitioWeb;

    public DTArtista(String nickname, String nombre, String apellido, String contrasenia, String imagen, Date fechaNac, String correo, String biografia, String sitioWeb) {
        super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
    }

    public DTArtista(String nickname, String nombre, String apellido, String contrasenia, Date fechaNac, String correo, String biografia, String sitioWeb, List<String> listaUsuariosFavoritos) {
        super(nickname, nombre, apellido, correo, fechaNac, contrasenia, listaUsuariosFavoritos);
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
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
