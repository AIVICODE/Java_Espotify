package WebServices;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Base64BinaryArray {
    private List<byte[]> items;

    public Base64BinaryArray() {
        items = new ArrayList<>();
    }

    @XmlElement
    public List<byte[]> getItems() {
        return items;
    }

    public void setItems(List<byte[]> items) {
        this.items = items;
    }
}
