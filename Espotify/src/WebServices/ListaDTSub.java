package WebServices;

import Datatypes.DTSub;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTSub {
    private List<DTSub> lista;
    
    @XmlElement
    public List<DTSub> getLista(){
        return lista;
    }
    
    public void setLista(List<DTSub> lista){
        this.lista = lista;
    }
}
