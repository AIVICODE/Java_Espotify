
package espotify;

import GUI.Principal;
//import Logica.Controlador;
import Logica.Fabrica;
import Logica.IControlador;

public class Espotify {

    public static void main(String[] args) throws Exception {
        
        Fabrica fabrica = Fabrica.getInstance();
        IControlador control = fabrica.getIControlador();
        //Controlador control= new Controlador();
        
        Principal prin= new Principal();
        prin.setVisible(true);
        
       //control.Cargar_Datos_Prueba();


        
    }
    
}
