package WebServices;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaString{
    private List<String> lista;
    @XmlElement
    public List<String> getLista(){
        return lista;
    }
    public void setLista(List<String> lista){
        this.lista = lista;
    }
}
