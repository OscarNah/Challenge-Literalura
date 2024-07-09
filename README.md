# Literalura

## Descripción
Literalura es una aplicación basada en Java que se conecta a la API de Gutendex para buscar libros por título, listar libros y autores guardados, y realizar varias operaciones relacionadas con libros y autores. La aplicación proporciona una interfaz de consola para la interacción del usuario.

## Funcionalidades
- Buscar libros por título utilizando la API de Gutendex.
- Listar libros guardados.
- Listar autores guardados.
- Listar autores vivos en un año especificado.
- Listar libros por idioma.
- Listar libros por autor.

## Requisitos Previos
- Java 17 o superior
- Maven 3.6.3 o superior
- PostgreSQL 14 o superior

## Configuración del Proyecto
1. Clonar el repositorio:
git clone https://github.com/OscarNah/Challenge-Literalura

2. Configurar la base de datos:
- Crear una base de datos en PostgreSQL.
- Actualizar las propiedades de conexión a la base de datos en el archivo `application.properties`:
  ```
spring.datasource.url=jdbc:postgresql://${DB_HOST}/{DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
  ```

## Uso
- Una vez iniciada la aplicación, se mostrará un menú en la consola con diversas opciones de interacción:
Elige una opcion:

1.- Buscar libros en GUTENDEX.
2.- Buscar libro por título en la DB.
3.- Listar los autores registrados en la DB.
4.- Listar los libros registrados en la DB.
5.- Listar los libros por idioma en GUTENDEX
6.- Listar los autores vivos en un determinado año registrados en la DB.                
0.- Salir de la aplicación
- Selecciona una opción e ingresa la información solicitada para interactuar con la aplicación.

## El proyecto fue trabajado por capaz por lo que es mas sencillo de entender ya que esta formado por las siguientes capas:
-**1. Model**
-**2. Principal**
-**3. Repository**
-**4. Service**

## API Utilizada
Se utilizó la API de Gutendex para obtener la información de los libros. [Gutendex API](https://gutendex.com)
