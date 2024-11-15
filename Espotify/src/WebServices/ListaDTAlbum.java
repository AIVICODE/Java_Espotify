package WebServices;

import Datatypes.DTAlbum;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaDTAlbum {
    private List<DTAlbum> lista;
    
    @XmlElement
    public List<DTAlbum> getLista(){
        return lista;
    }
    
    public void setLista(List<DTAlbum> lista){
        this.lista = lista;
    }
}
