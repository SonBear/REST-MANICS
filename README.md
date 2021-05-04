# REST-MANICS
Proyecto de basado en la búsqueda de mangas y comics dentro de un repositorio en la nube.

## Nombre del software
MANICS-REST-API
## Autores
Emmanuel Isai Chable Colli
Ricardo Nicolas Canul Ibarra
Iowa Olivera
## Titulo del documento 
“Documento de Arquitectura de Software”

# Introducción
## Propósito
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Alcance 
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 
## Documentos de referencia
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 

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
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown 