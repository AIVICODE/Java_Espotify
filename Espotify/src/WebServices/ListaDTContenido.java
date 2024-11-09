package WebServices;

import Datatypes.DTContenido;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTContenido {
    private List<DTContenido> lista;
    
    @XmlElement
    public List<DTContenido> getLista(){
        return lista;
    }
    
    public void setLista(List<DTContenido> lista){
        this.lista = lista;
    }
}