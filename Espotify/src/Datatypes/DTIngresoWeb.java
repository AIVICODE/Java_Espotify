/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DTIngresoWeb {
    @XmlElement
    String ip;
    @XmlElement
    String url;
    @XmlElement
    String browser;
    @XmlElement
    String So;
    @XmlElement
    Date fecha;

    public DTIngresoWeb() {}

    public DTIngresoWeb(String ip, String url, String browser, String So,Date fecha) {
        this.ip = ip;
        this.url = url;
        this.browser = browser;
        this.So = So;
        this.fecha=fecha;
    }

    public String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }

    public String getBrowser() {
        return browser;
    }

    public String getSo() {
        return So;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
    
}
