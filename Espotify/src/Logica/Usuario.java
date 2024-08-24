package Logica;

public abstract class Usuario {
    protected String nickname;
    protected String nombre, apellido, contrasenia;
    protected String mail, imagen;
    
    // Constructor vacío
    public Usuario() {}
    
    // Constructor con parámetros
    public Usuario(String nickname, String nombre, String apellido, String contrasenia, String mail) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.mail = mail;
    }

    // Setters
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    // Getters
    public String getNickname (){
        return nickname;
    }
    
    public String getNombre (){
        return nombre;
    }
    
    public String getApellido (){
        return apellido;
    }
    
    public String getContrasenia (){
        return contrasenia;
    }
    
    public String getMail (){
        return mail;
    }
    
    
   // public abstract void metodoAbstracto1();
   // public abstract void metodoAbstracto2();
}
