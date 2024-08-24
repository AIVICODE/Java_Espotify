package Logica;

public class Cliente extends Usuario {
    
    public Cliente() {}
    
    public Cliente(String nickname, String nombre, String apellido, String contrasenia, String mail) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.mail = mail;
    }

    // Setters
    @Override
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    @Override
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    @Override
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    @Override
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    @Override
    public void setMail(String mail){
        this.mail = mail;
    }
    
    // Getters
    @Override
    public String getNickname(){
        return nickname;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override
    public String getApellido(){
        return apellido;
    }

    @Override
    public String getContrasenia(){
        return contrasenia;
    }

    @Override
    public String getMail(){
        return mail;
    }
}
