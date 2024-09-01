Hice este cambio por la siguiente situacion: 
  Para poder usar nickname para poder buscar artistas y clientes en lugar de mail habia que etiquetarlo como Unique.
  Ahi me di cuenta de que se no se podian repetir entre artistas o clientes, pero si podian haber clientes y artistas con mismo nickname.. lo que no deberia pasar,
  ademas de que tambien se podia usar un mismo mail para registrar un cliente y un artista.

Lo que hice se lñlama herencia SINGLE_TABLE en JPA, todas las subclases de Usuario (Cliente y Artista) compartiran una unica tabla en la base de datos.
Esto significa que los datos de Cliente y Artista estan en la misma tabla, con una columna extra que indica el tipo de la subclase (dtype, por defecto).

En la clase base Usuario, use la anotacion @Inheritance(strategy = InheritanceType.SINGLE_TABLE) para definir la estrategia de herencia.
Tambie defini la restriccn de unicidad en el atributo nickname usando @Table y @UniqueConstraint.
Las subclases Cliente y Artista se extienden de Usuario. Asi q no es necesario hacer nada especial en las subclases para mapearlas con SINGLE_TABLE, se maneja automáticamente por JPA.
El valor de dtype es el nombre de la clase, pero se puede cambiar usando @DiscriminatorValue por lo q lei pero por ahora mas vale dejarlo asi.

Cuando accedes a la tabla usuario, JPA va a manejar automaticamente el casting a la subclase adecuada dependiendo del valor de dtype.

Era necesario agregar usuario en el persistence xml porque ahora usuario aunq es abstracta figura como entidad a raiz de ese cambio.

En la controladoraPersistencia esta el metodo para addUsuario todo bonito, todo chiquito.
