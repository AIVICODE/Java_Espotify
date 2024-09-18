
package Logica;


public class Fabrica {
    public static Fabrica instancia;
    
    Fabrica(){}
    
    public static Fabrica getInstance(){
        if(instancia == null){
            instancia = new Fabrica();
        }
        return instancia;
    }
    public IControlador getIControlador(){
        return new Controlador();
    }
    
}
