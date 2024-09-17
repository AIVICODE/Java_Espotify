
package espotify;

import GUI.Principal;
import Logica.Controlador;

public class Espotify {

    public static void main(String[] args) throws Exception {
        
        Controlador control= new Controlador();
        
        Principal prin= new Principal();
        prin.setVisible(true);
        
      control.Cargar_Datos_Prueba();


        
    }
    
}
