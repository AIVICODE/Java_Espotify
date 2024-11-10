
package espotify;

import Datatypes.DTInfoTema;
import Datatypes.DTTema;
import GUI.Principal;
import Logica.Controlador;
import javax.xml.ws.Endpoint;
import WebServices.ControladorSoap;
import Logica.Fabrica;
import Logica.IControlador;

public class Espotify {

    public static void main(String[] args) throws Exception {
        
        //Fabrica fabrica = Fabrica.getInstance();
        //IControlador control = fabrica.getIControlador();
        Endpoint.publish("http://localhost:8567/ControladorSoap", new ControladorSoap());
        System.out.println("Servicio SOAP iniciado en http://localhost:8567/ControladorSoap");

    
        Controlador control = new Controlador();
        Principal prin= new Principal();
        prin.setVisible(true);
        control.CrearListaRepSugerencia();
        //control.Buscador("cumbia", "fecha");
       // DTTema tema = new DTTema("Violeta","20 Grandes Éxitos","alcides@tuta.io");
      //  control.AgregaDescargaTema(tema);
       // control.AgregaReproduccionTema(tema);
        
//       DTInfoTema info = control.ObtenerInfoTema("Violeta","alcides","20 Grandes Éxitos");
//
//System.out.println(info.getCantidadDescargas());
//System.out.println(info.getCantidadReproducciones());
        
    }
    
}
