package Logica;

import Logica.Artista;
import Logica.Genero;
import Logica.Tema;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-07T17:12:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Album.class)
public class Album_ extends Favoritos_ {

    public static volatile SingularAttribute<Album, Artista> artista;
    public static volatile SingularAttribute<Album, Date> anioCreacion;
    public static volatile ListAttribute<Album, Tema> listaTemas;
    public static volatile SingularAttribute<Album, String> imagen;
    public static volatile ListAttribute<Album, Genero> listaGeneros;
    public static volatile SingularAttribute<Album, String> nombre;

}