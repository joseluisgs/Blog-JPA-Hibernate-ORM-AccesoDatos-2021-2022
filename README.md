# Blog-Hibernate-AccesoDatos-2021-2022
Ejemplo de desarrollo de un blog (backend básico) para Acceso a Datos, usando una base de datos realacional realizando el mapeo 
ObjetoRelacional (ORM) usando Hibernate y JPA e implementando distintas técnicas y patrones de Acceso a Datos vistos en clase.

[![Kotlin](https://img.shields.io/badge/Code-Java-blue)](https://www.java.com/es/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Blog-Hibernate-AccesoDatos-2021-2022)

- [Blog-Hibernate-AccesoDatos-2021-2022](#blog-hibernate-accesodatos-2021-2022)
  - [Descripción](#descripción)
  - [Tecnologías](#tecnologías)
  - [Enunciado](#enunciado)
    - [Ejemplo de diagrama](#ejemplo-de-diagrama)
  - [Desarrollo](#desarrollo)
    - [GitFlow](#gitflow)
    - [Arquitectura](#arquitectura)
  - [Hibernate](#hibernate)
  - [JPA: Java Persistence API](#jpa-java-persistence-api)
  - [Ejecución](#ejecución)
    - [Docker](#docker)
    - [Adminer o cliente de Bases de Datos](#adminer-o-cliente-de-bases-de-datos)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Descripción
Se ha implementado el desarrollo del un blog a nivel de backend para el acceso a los datos que se necesiten con fines didácticos para el módulo de Acceso a Datos de 2DAM.
Debes entender que es un ejemplo didáctico para clase, por lo que parte de la solución simplemente es para mostrar distintas técnicas y patrones y por lo tanto 
puede que no sea la más óptima o adecuada a niveles de producción o empresarial. Tenlo en cuenta.

Este ejemplo, su arquitectura y parte de su solución proviene del anterior ejemplo visto en clase y que puedes encontrar [aquí](https://github.com/joseluisgs/Blog-Relacional-AccesoDatos-2021-2022).

A lo largo de este desarrollo actualizarmos el ejemplo anterior para trabajar con tecnología orientada a objetos y con ella usar Hibernate y JPA para
realizar el Mapeo Objeto-Relacional con nuestra base de datos

## Tecnologías
Se han usado las siguientes tecnologías:
- Java 11, como lenguaje de programación.
- MariaDB como motor de base de datos relacional.
- Docker para lanzar la base de datos, así como otras utilidades para manejarla.
- Hibernate como ORM
- JPA: Java Persistence API

## Enunciado
Se desea implementar la base de un blog teniendo en cuenta que: 
- Un usuario una vez registrado mediante email y password puede hacer login y logout en el sistema.
- El usuario puede escribir varios posts los cuales pertenecen solo a una categoría existente, como general, dudas o evaluación. Se pueden crear nuevas categorías.
- Los usuarios pueden hacer distintos comentarios sobre posts existentes.

### Ejemplo de diagrama
![diagrama](./diagrams/Diagrams.png)

## Desarrollo
### GitFlow
Se ha usado GitFlow como modelo de flujo de desarrollo y trabajo con el repositorio.

### Arquitectura
Puedes leer sobre ella [aquí](https://github.com/joseluisgs/Blog-Relacional-AccesoDatos-2021-2022#arquitectura). 

## Hibernate
Hibernate es una herramienta de mapeo objeto-relacional (ORM). Bibernate busca solucionar el problema de la diferencia 
entre los dos modelos de datos coexistentes en una aplicación: el usado en la memoria de la computadora (orientación a objetos) 
y el usado en las bases de datos (modelo relacional). 

Para lograr esto permite detallar cómo es su modelo de datos, qué relaciones existen y qué forma tienen. 
Con esta información Hibernate nos facilita poder manipular los datos en la base de datos operando sobre objetos, 
convirtiendo dichas acciones en sentencias SQL y liberándonos de realizar las conversiones necesarias como resultado de haber realizado dichas acciones. 

## JPA: Java Persistence API
Java Persistence API, más conocida por sus siglas JPA, es la API de persistencia desarrollada para la plataforma Java EE.
JPA es una especificación y no un Framework como tal, por lo tanto necesita de alguien que lo implemente, por ejemplo Hibernate.

## Ejecución
### Docker
Entrar en el directorio docker y ejecutar
```sh
$ docker-compose up -d
```
Para iniciar la BD con algunos datos modifica el fichero [docker/mariadb/sql/init.sql](docker/mariadb/sql/init-db.sql)


### Adminer o cliente de Bases de Datos
Debes conectarte a http://localhost:8080/
- server: mariadb
- user: blog
- password: blog1234 
- base de datos blog

## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://cdn.iconscout.com/icon/free/png-256/github-153-675523.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>


## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su uso docente y educativo.
