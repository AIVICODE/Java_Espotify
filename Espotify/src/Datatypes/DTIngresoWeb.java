/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datatypes;

import java.util.Date;

/**
 *
 * @author ivan
 */
public class DTIngresoWeb {
      String ip;
    String url;
    String browser;
    String So;
    Date fecha;

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
