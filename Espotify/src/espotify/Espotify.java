
package espotify;

import Datatypes.DTTema;
import GUI.Principal;
import Logica.Controlador;
import Logica.Fabrica;
import Logica.IControlador;

public class Espotify {

    public static void main(String[] args) throws Exception {
        
        //Fabrica fabrica = Fabrica.getInstance();
        //IControlador control = fabrica.getIControlador();
        Controlador control = new Controlador();
        Principal prin= new Principal();
        prin.setVisible(true);
        control.CrearListaRepSugerencia();
        //control.Buscador("cumbia", "fecha");
       // DTTema tema = new DTTema("Violeta","20 Grandes Éxitos","alcides@tuta.io");
      //  control.AgregaDescargaTema(tema);
       // control.AgregaReproduccionTema(tema);
        
       


        
    }
    
}
