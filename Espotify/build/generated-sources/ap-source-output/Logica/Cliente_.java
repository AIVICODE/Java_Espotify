package Logica;

import Logica.Album;
import Logica.ListaRep;
import Logica.Tema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-06T17:01:37", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, Album> albums;
    public static volatile ListAttribute<Cliente, ListaRep> listaReproduccion;
    public static volatile ListAttribute<Cliente, Tema> temas;
    public static volatile ListAttribute<Cliente, ListaRep> listaRepFavoritos;

}