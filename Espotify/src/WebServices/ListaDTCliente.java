package WebServices;

import Datatypes.DTCliente;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTCliente {
    private List<DTCliente> lista;
    
    @XmlElement
    public List<DTCliente> getLista(){
        return lista;
    }
    
    public void setLista(List<DTCliente> lista){
        this.lista = lista;
    }
}