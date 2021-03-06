# TOTECO API

_Se trata de una aplicaci贸n de gesti贸n de publicaciones de productos, estilo red social. La idea surge de poder crear una comunidad que publique fotos de desayunos incluyendo el precio de los productos y la puntuaci贸n._

## Modelo entidad relaci贸n 馃敍
![Screenshot](totecoApi.jpg)

## Explicaci贸n de clases 馃梻

_La API funciona de forma que puedes registrar usuarios, los cuales podr谩n crear las publicaciones.
Las publicaciones est谩n compuestas por una imagen, la fecha de creaci贸n (coge autom谩ticamente la del sistema), el precio total, la puntuaci贸n total, el id de usuario y el id del establecimiento.
A las publicaciones se le adhieren los productos. Cada producto tiene nombre, fecha de creaci贸n, precio, puntuaci贸n, el id del tipo, y, en el caso de que est茅 incluida en un men煤, contiene tambi茅n el id del menu. Si los productos pertenecen a una oferta o men煤 desayuno el precio de la publicaci贸n depender谩 煤nicamente del precio del men煤, mientras que sino el precio de la publicaci贸n es la suma del precio de los productos. En caso de la punctuaci贸n de la publicaci贸n s铆 que se obtiene de la suma de las puntuaciones tanto de men煤 como de productos. Para facilitar la inserci贸n de productos se crea la tabla de tipos, donde se guarda el nombre del producto y el tipo (ejemplo: Tostada(nombre) de tomate(tipo)). Por 煤ltimo, est谩 la tabla de establecimientos. En esta se guardan los datos del establecimiento (nombre, localizaci贸n, si est谩 abierto o no) y la puntuaci贸n del mismo que se calcula sumando las puntuaciones de las publicaciones de ese establecimiento y haciendo una media._

_Cada clase tiene una serie de filtros que se pueden aplicar. Todos ellos est谩n recogidos en la colecci贸n de Postman incluida en el repositorio._

## Comenzando 馃殌

_Estas instrucciones te permitir谩n obtener una copia del proyecto en funcionamiento en tu m谩quina local para prop贸sitos de desarrollo y pruebas._


### Pre-requisitos 馃搵

_Cosas que necesitas para instalar el software y como instalarlas_

```
- Tener instalado java en el ordenador
- Programa que permita hacer despliegue Maven
- Programa que tenga instalados los plugins de Springboot y Lombok
- Programa de gesti贸n de bases de datos mySQL
```

### Instalaci贸n 馃敡

_Una serie de ejemplos paso a paso que te dice lo que debes ejecutar para tener un entorno de desarrollo ejecutandose_

```
- Clonar el este repositorio y situarse en la rama FINAL
- Importar el archivo SQL al interfaz de mySQL
- Importar la colecci贸n de Postman a Postman
```

## Despliegue 馃摝

_Pasos a seguir:_
* Inicia la base de datos
* Ejecuta spring boot
* Prueba las consultas de la colecci贸n de Postman

## Construido con 馃洜锔?

* [Visual Studio Code](https://www.jetbrains.com/es-es/idea/) - Framework web usado
* [Maven](https://maven.apache.org/) - Gestor de dependencias
* [Spring Boot](https://spring.io/projects/spring-boot) - Fremework de creaci贸n de APIs
* [XAMPP](https://www.apachefriends.org/es/index.html) - Gestor de bases de datos
* [Postman](https://www.postman.com) - Plataforma de creaci贸n y uso de APIs

## Autores 鉁掞笍

* **Irene Cunto** - *Trabajo Principal y documentaci贸n* - [cunba](https://github.com/cunba)

Tambi茅n puedes mirar la lista de todos los [contribuyentes](https://github.com/cunba/toastsapi) que han participado en este proyecto.



---
鈱笍 con 鉂わ笍 por [cunba](https://github.com/cunba) 馃槉