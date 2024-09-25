package Logica;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@Table(name = "tema")
public class Tema extends Favoritos {

    public long getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(long duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    private String nombre;

    @Column(name = "duracion_segundos")
    private long duracionSegundos; // Almacenar la duración en milisegundos

    @Transient
    private Duration duracion; // Campo no persistente para usar como Duration

    private String direccion;
    
    private int orden;
    
    @JoinColumn(name = "album_id") // Nombre de la columna en la tabla tema
    private Album album;

    public Tema() {
    }

    public Tema(String nombre, int minutos, int segundos, String direccion) {
        this.nombre = nombre;
        this.duracion = Duration.ofMinutes(minutos).plusSeconds(segundos);
        this.direccion = direccion;
                
        // Establecer el orden basado en la cantidad de temas existentes
        if (album != null) {
            List<Tema> temas = album.getListaTemas(); // Supongamos que hay un método en Album que devuelve la lista de temas
            this.orden = temas.size() + 1; // El nuevo tema tendrá un orden uno mayor que el número de temas existentes
        } else {
            this.orden = 1; // Si no hay álbum, establecer el orden en 1
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @PostLoad
private void postLoad() {
    if (duracionSegundos > 0) {
        this.duracion = Duration.ofSeconds(duracionSegundos);
    } else {
        this.duracion = Duration.ZERO; // O cualquier valor por defecto que consideres
    }
}


    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (duracion != null) {
            this.duracionSegundos = duracion.getSeconds();
        }
    }


    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

   
}
