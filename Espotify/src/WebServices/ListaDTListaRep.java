package WebServices;

import Datatypes.DTListaRep;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTListaRep {
    private List<DTListaRep> lista;
    
    @XmlElement
    public List<DTListaRep> getLista(){
        return lista;
    }
    
    public void setLista(List<DTListaRep> lista){
        this.lista = lista;
    }
}