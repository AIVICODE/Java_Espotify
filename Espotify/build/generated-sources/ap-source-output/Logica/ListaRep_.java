package Logica;

import Logica.Tema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T13:41:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ListaRep.class)
public abstract class ListaRep_ extends Favoritos_ {

    public static volatile ListAttribute<ListaRep, Tema> listaTemas;
    public static volatile SingularAttribute<ListaRep, String> imagen;
    public static volatile SingularAttribute<ListaRep, Long> id;
    public static volatile SingularAttribute<ListaRep, String> nombre;

}