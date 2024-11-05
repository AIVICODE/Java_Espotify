package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registro_ingreso_web")
public class RegistroIngresoWeb implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private String url;
    private String browser;
    
    private String so; 
    private Date fecha;
    public RegistroIngresoWeb() {
        // Constructor sin parámetros requerido por JPA.
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public RegistroIngresoWeb(String ip, String url, String browser, String so) {
        this.ip = ip;
        this.url = url;
        this.browser = browser;
        this.so = so;
            this.fecha=new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }
}
