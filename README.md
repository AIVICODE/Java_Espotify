# Proyecto en Java para la Materia Programación de Aplicaciones

## Primera Parte

### Aprender las Construcciones Básicas del Lenguaje Java

Este proyecto cubre los conceptos fundamentales del lenguaje Java, incluyendo variables, tipos de datos, estructuras de control y métodos.

### Realizar una Interfaz Gráfica (GUI) en Swing

Se desarrolla una interfaz gráfica utilizando Swing, una biblioteca de Java para crear aplicaciones de escritorio con interfaces gráficas de usuario.

### Aplicar Técnicas Básicas de Verificación de Aplicaciones

Se implementan técnicas de verificación para asegurar la funcionalidad y calidad del software, incluyendo pruebas y validaciones básicas.

### Utilizar Herramientas Avanzadas para el Desarrollo de Software

El proyecto utiliza herramientas avanzadas como entornos de desarrollo integrados (IDEs) y sistemas de gestión de configuración (SCM) para facilitar el desarrollo y el control de versiones.

### Aplicar Prácticas Usuales en el Desarrollo de Software

Se fomenta el trabajo en grupo, la planificación de actividades y el registro de horas para una gestión efectiva del proyecto.

## Tecnologías Utilizadas

- **Lenguaje**: Java
- **Bibliotecas**: Swing (para la GUI)
- **Entorno de Desarrollo**: NetBeans
- **Sistema de Gestión de Configuración**: Git

## Descripción del Proyecto

**Espotify** es una plataforma de música que conecta a dos tipos de usuarios: clientes y artistas, ofreciendo un espacio para explorar y compartir música.

### Usuarios

#### Clientes

- **Registro**: Ingresan sus datos personales para crear una cuenta.
- **Funcionalidades**:
  - Buscar y reproducir música.
  - Seguir la actividad de otros usuarios.
  - Guardar elementos favoritos como temas, álbumes y listas de reproducción.
- **Listas de Reproducción**: 
  - Crear listas de reproducción particulares que pueden ser privadas (no accesibles por otros clientes) y asociadas a sus gustos musicales.

#### Artistas

- **Registro**: Similar al cliente, con datos personales, además de biografía y dirección de su sitio web de promoción.
- **Funcionalidades**: 
  - Publicar y gestionar su discografía, incluyendo álbumes y temas.

### Música y Organización

- **Géneros**: La música está organizada en géneros jerárquicos, donde cada género puede contener subgéneros.

- **Álbumes**:
  - **Detalles**: 
    - Cada álbum tiene un nombre único dentro de un artista y un año de creación.
    - Puede estar clasificado en múltiples géneros y puede tener una imagen asociada.
  - **Temas**: 
    - Cada álbum contiene uno o más temas.
    - Cada tema tiene un nombre, duración y orden en el álbum.
    - Puede ser accedido mediante un archivo de música o una dirección web.
    - Un tema pertenece a un solo álbum.

- **Listas de Reproducción**:
  - **Tipos**: 
    - **Por Defecto**: Creadas por el administrador de la plataforma y asociadas a un género específico.
    - **Particulares**: Creadas por los clientes, pueden ser privadas o públicas.
  - **Detalles**: 
    - Cada lista tiene un nombre único dentro de su categoría.
    - Imagen opcional.
    - Puede contener temas de diferentes artistas y géneros.

### Características Sociales

- **Seguimiento**: Los clientes pueden seguir la actividad de otros usuarios, ya sean clientes o artistas.
- **Recomendaciones**: Basadas en las preferencias de los clientes y los usuarios a los que siguen.

