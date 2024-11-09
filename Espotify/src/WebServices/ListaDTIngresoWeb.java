package WebServices;

import Datatypes.DTIngresoWeb;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTIngresoWeb {
    private List<DTIngresoWeb> lista;
    
    @XmlElement
    public List<DTIngresoWeb> getLista(){
        return lista;
    }
    
    public void setLista(List<DTIngresoWeb> lista){
        this.lista = lista;
    }
}