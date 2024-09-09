package Logica;

import Logica.Album;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-08T22:19:34", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Artista.class)
public class Artista_ extends Usuario_ {

    public static volatile SingularAttribute<Artista, String> sitioWeb;
    public static volatile SingularAttribute<Artista, String> biografia;
    public static volatile ListAttribute<Artista, Album> albumes;

}