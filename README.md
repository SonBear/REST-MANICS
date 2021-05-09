# REST-MANICS
Proyecto basado en la búsqueda de mangas y comics dentro de un repositorio en la nube.

## Nombre del software
MANICS-REST-API V1.0.0

## Autores
+ Emmanuel Isai Chable Colli
+ Ricardo Nicolas Canul Ibarra
+ Iowa Alejandro Olivera Pérez
## Titulo del documento 
“Documento de Arquitectura de Software”

# Introducción
## Propósito
Ante la problematica de que muchas personas no pueden conseguir sea un comic o manga de manera física por distintos motivos(poder adquisitivo, inexistencias de puntos de venta, pérdida de tiempo en la compra, etc)
esto provoca que simplemente no puedan leer alguna de las muchas historias que los comics/mangas nos pueden ofrecer. 

## Alcance 
Lo que se propone es que los usuarios desde una conexion a internet puedan acceder a un amplio catalogo de comics y mangas, y que sean capaces de porder localizar alguno de sus productos favoritos, mediante la busqueda de texto o imagen. 

## Documentos de referencia
1. http://www.idglat.com/afiliacion/whitepapers/453167_API%20Strategy%20and%20Architecture%20A%20Coordinated%20Approach-LAS.pdf?tk=/:
2. https://juanda.gitbooks.io/webapps/content/api/arquitectura-api-rest.html

# Arquitectura.
## Descripción de la arquitectura utilizada (Capas) (Describir responsabilidad de las capas)
Para realizar este proyecto se decidió utilizar una arquitectura por capas de tal forma que sea posible configurar cada capa de manera independiente sin que el funcionamiento de una tenga consecuencias en otra facilitando así la conexión entre ellas. Las capas en la que se dividirá el proyecto son las siguientes: 

1. Capa de seguridad: Debido a que nuestra API contará con información sensible de los usuarios registrados será necesarió contar con una capa de seguridad sólida en la cual se detendrán las posibles peticiones malintencionadas o sin autorización. 
2. Capa de almacenamiento en caché: Con el fin de mantener una sensación de fluidez en nuestra plataforma será necesario contar con una capa de almacenamiento en caché que sea capaz de almacenar y devolver los resultados de algunas de las peticiones más comunes con el fin de reducir la carga a la base de datos y obtener mejores tiempos de respuesta. 
3. Capa de representación: La capa de representación será la encargada de manejar las peticiones entrantes de tal forma que sean reconocibles por la api. En esta capa se manejará la sintaxis y la semantica. 
4. Capa de negocio: Esta capa será la encargada de procesar todas las peticiones entrantes y es la única que se podrá comunicar con la capa de datos de tal forma que sea capaz de devolver a los clientes las respuestas correspondientes a sus solicitudes. 
6. Capa de datos: Esta capa será la encargada de obtener y almacenar datos en nuestra base de datos de acuerdo a las solicitudes que le lleguen desde la capa de negocio. 

## Diagrama de arquitectura con descripción (Arquitectura del proyecto completo)
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

## Diagrama de secuencia para los procesos más imporantes de la App (CRUD)
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

## Diagrama de la base de datos
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Descripción de las entidades
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Diagrama entidad relación
![image](https://user-images.githubusercontent.com/48814909/117584706-11049380-b0d4-11eb-828c-7d22a7327350.png)


# Documentación de la API
## Documentación Individual de cada Endpoint por cada entidad
### Buscada de informacion
    https://manicsrestapi/v1/search
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
    https://manicsrestapi/v1/search/mangas?q=Berserk
```JSON
{
    "status": "on-air"
}
```
### Usuarios
#### GET Obtener usuarios
    https://manicsrestapi/v1/usuarios
##### Descripción
Devuelve todos los usuarios registrados. 
##### Respuesta
```JSON
{
    "response": [
        {
            id: int,
            username: string,
            email: string
        },
                {
            id: int,
            username: string,
            email: string
        }...
    ]
}
```
#### GET Obtener usuario
    https://manicsrestapi/v1/usuarios/{id}

##### Descripción
Devuelve el usuario con el id ingresado. 
##### Respuesta
```JSON
{
    "response": {
        "id": int,
        "username": string,
        "email": string
    }
}
```
#### POST Crear usuario
    https://manicsrestapi/v1/usuarios
##### Descripción
Crea un nuevo usuario
#### Campos requeridos
```JSON
{
    "username": string,
    "password": string,
    "email": string
}
```
##### Respuesta
```JSON
{
    "response": {
        "id": int,
        "username": string,
        "email": string
    }
}
```
#### PUT Editar usuario
    https://manicsrestapi/v1/usuarios/{id}
##### Descripción
Actualiza la información de un usuario existente
#### Campos requeridos
```JSON
{
    "id": int,
    "username": string,
    "password": string,
    "email": string
}
```
##### Respuesta
```JSON
{
    "response": {
        "id": int,
        "username": string,
        "email": string
    }
}
```
#### DELETE Eliminar usuario
    https://manicsrestapi/v1/usuarios/{id}
##### Descripción
Elimina el usuario del id dado. 
##### Respuesta
Esta petición no devuelve una respuesta. 


### Comics
#### GET Obtener comics
    https://manicsrestapi/v1/comics
##### Descripción
Devuelve todos los comics registrados. 
##### Respuesta
```JSON
{
    "response": [
        {
            "id": Integer,
            "nombre": String,
            "autor": String,
            "fecha_publicacion": String,
            "paginas": String
            
        },
        {
            "id": Integer,
            "nombre": String,
            "autor": String,
            "fecha_publicacion": String,
            "paginas": String
        }...
    ]
}
```
#### GET Obtener comic
    https://manicsrestapi/v1/comics/{id}

##### Descripción
Devuelve el comic con el id ingresado. 
##### Respuesta
```JSON
{
    "response": {
        "id": Integer,
        "nombre": String,
        "autor": String,
        "fecha_publicacion": String,
        "paginas": String
    }
}
```
#### POST Crear comic
    https://manicsrestapi/v1/comics
##### Descripción
Crea un nuevo comic
#### Campos requeridos
```JSON
{
    "nombre": String,
    "autor": String,
    "fecha_publicacion": String,
    "paginas": String
}
```
##### Respuesta
```JSON
{
    "response": {
        "id": Integer,
        "nombre": String,
        "autor": String,
        "fecha_publicacion": String,
        "paginas": String
    }
}
```
#### PUT Editar comic
    https://manicsrestapi/v1/comics/{id}
##### Descripción
Actualiza la información de un comic existente
#### Campos requeridos
```JSON
{
    "id": Integer,
    "nombre": String,
    "autor": String,
    "fecha_publicacion": String,
    "paginas": String
}
```
##### Respuesta
```JSON
{
    "response": {
        "id": Integer,
        "nombre": String,
        "autor": String,
        "fecha_publicacion": String,
        "paginas": String
    }
}
```
#### DELETE Eliminar comic
    https://manicsrestapi/v1/comics/{id}
##### Descripción
Elimina el comic del id dado. 
##### Respuesta
Esta petición no devuelve una respuesta. 


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
