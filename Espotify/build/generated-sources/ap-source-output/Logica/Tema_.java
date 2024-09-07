package Logica;

import Logica.Album;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-07T17:12:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tema.class)
public class Tema_ extends Favoritos_ {

    public static volatile SingularAttribute<Tema, Long> duracionSegundos;
    public static volatile SingularAttribute<Tema, Album> album;
    public static volatile SingularAttribute<Tema, String> direccion;
    public static volatile SingularAttribute<Tema, Integer> orden;
    public static volatile SingularAttribute<Tema, String> nombre;

}