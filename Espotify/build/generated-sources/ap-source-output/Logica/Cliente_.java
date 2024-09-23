package Logica;

import Logica.Album;
import Logica.Artista;
import Logica.Cliente;
import Logica.ListaRep;
import Logica.Tema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T23:35:02", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, Album> albums;
    public static volatile ListAttribute<Cliente, Cliente> clientesSeguidos;
    public static volatile ListAttribute<Cliente, ListaRep> listaReproduccion;
    public static volatile ListAttribute<Cliente, Artista> artistasSeguidos;
    public static volatile ListAttribute<Cliente, Tema> temas;
    public static volatile ListAttribute<Cliente, ListaRep> listaRepFavoritos;

}