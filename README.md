# REST-MANICS
Proyecto basado en la búsqueda de mangas y comics dentro de un repositorio en la nube.

## Nombre del software
MANICS-REST-API V1.0.0

## Autores
+ Emmanuel Isai Chable Colli
+ Ricardo Nicolas Canul Ibarra
+ Iowa Olivera
## Titulo del documento 
“Documento de Arquitectura de Software”

# Introducción
## Propósito
Ante la problematica de que muchas personas no pueden conseguir sea un comic o manga de manera física por distintos motivos(poder adquisitivo, inexistencias de puntos de venta, pérdida de tiempo en la compra, etc)
esto provoca que simplemente no puedan leer alguna de las muchas historias que los comics/mangas nos pueden ofrecer. 

## Alcance 
Lo que se propone es que los usuarios desde una conexion a internet puedan acceder a un amplio catalogo de comics y mangas, y que sean capaces de porder localizar alguno de sus productos favoritos, mediante la busqueda de texto o imagen. 

## Documentos de referencia
+ Por el momento no se

# Arquitectura.
## Descripción de la arquitectura utilizada (Capas) (Describir responsabilidad de las capas)
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

## Diagrama de arquitectura con descripción (Arquitectura del proyecto completo)
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

## Diagrama de secuencia para los procesos más imporantes de la App (CRUD)
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

## Diagrama de la base de datos
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Descripción de las entidades
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Diagrama entidad relación
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

# Documentación de la API
## Documentación Individual de cada Endpoint por cada entidad
### Buscada de informacion
    https://manicsrestapi/search
### Descripción
Se encarga de realizar una busqueda mediante texto
### Campos requeridos
```JSON
{
    "response": 1232
}
```
### Validaciones
```JAVA
    @NotNull;
```
### Tipo de dato de cada campo
```JAVA
    int val;
```
### Respuesta (Response)
```JSON
{
    "response": 1232
}
```
### Ejemplos del Request
    https://manicsrestapi/search/mangas?q=Berserk
```JSON
{
    "status": "on-air"
}
```
# Criterios de calidad
Según el modelo de calidad ISO/IEC 25010 nos proponen una serie de atributos de calidad para el producto los cuales son los siguientes:
+ Adecuación funcional
+ Eficiencia de desempeño
+ Compatibilidad
+ Usabilidad
+ Fiabilidad
+ Seguridad
+ Mantenibilidad
+ Portabilidad

Debido a las necesidades del proyecto, el proyecto de centrara en cumplir con alguno de ellos:
1. La **seguridad** es muy importante para la gran mayoria de sistemas de hoy en dia por lo que es un punto que sin lugar a dudas se debe de tratar, para fines de este proyecto se podrá apreciar por algunas funcionalidades del sistema como por ejemplo:
    + "El servicio rest estará reestringido para dos tipos de usuarios:
        + Administrador
        + Usuario publico
    + "El administrador estará autorizado de capacidades que un usuario publico no tendrá acceso esto mediante una llave que se le otorgara al momento de registrarse y cumplir con los requisitos"
2. **Portabilidad** al tratarse de un servicio rest alojado en la nube, este podrá utilizarse en cualquier dispositivo que cuente con internet, y maneje estructuras basicas de protocolos http.
3. La **fiabilidad** siguiente el punto anterior el servicio rest siempre estará *disponible* para todo equipo o sistema que tenga conexion a internet.
Al tratarse de un servicio basado en el protocolo http, *el manejo de errores* estará visible para el usuario mediante los codigos correctos http para cada problema que se presente. Este servicio utilizará transacciones las cuales significan que ante un error del sistema este tendrá la *capacidad de recuperar* la informacion afectada durante dicho problema.
4. **Usabilidad** Este servicio estará planeado para realizar las diferentes funcionalidades de una manera sencilla,  esto debido a la utilización del formato de texto de intercambio de informacion más común dentro de la web los JSON.