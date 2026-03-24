# 🎬 Escena del Crimen — Gestión de Películas con Java & MySQL

> Aplicación de consola en Java puro (vanilla) que permite gestionar un catálogo de películas conectado a una base de datos MySQL. Implementa el patrón MVC completo con acceso a datos mediante JDBC.

---

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Uso de la Aplicación](#uso-de-la-aplicación)
- [Estructura de Clases](#estructura-de-clases)
- [Notas de Desarrollo](#notas-de-desarrollo)

---

## Descripción

**Escena del Crimen** es una aplicación de línea de comandos desarrollada en **Java 21 sin frameworks**, cuyo objetivo es gestionar un catálogo de películas almacenado en una base de datos MySQL. El proyecto sirve como ejercicio práctico de acceso a bases de datos relacionales con JDBC, arquitectura MVC en Java puro y manejo de operaciones CRUD.

Las funcionalidades principales incluyen:

- Listar todas las películas almacenadas en la base de datos.
- Crear nuevas películas de forma interactiva desde la consola.
- Gestionar información detallada: título, año, director, actores, puntuación FilmAffinity, descripción, URL de imagen y precio.

---

## Tecnologías

| Tecnología | Versión | Rol |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Maven | 3.x | Gestor de dependencias y build |
| MySQL Connector/J | 8.3.0 | Driver JDBC para MySQL |
| MySQL | 8.x | Base de datos relacional |
| IntelliJ IDEA / Eclipse | — | IDE recomendado |

---

## Arquitectura del Proyecto

El proyecto sigue el patrón **MVC (Modelo - Vista - Controlador)** con una capa adicional de repositorio para el acceso a datos:

```
escena_del_crimen_java_vanilla-main/
│
├── pom.xml                          # Configuración Maven y dependencias
│
└── src/main/java/org/example/
    │
    ├── Main.java                    # Punto de entrada de la aplicación
    │
    ├── config/
    │   └── DBManager.java           # Gestión de conexión JDBC a MySQL
    │
    ├── model/
    │   └── Movie.java               # Entidad/POJO que representa una película
    │
    ├── repository/
    │   └── MovieRepositoryImp.java  # Acceso a datos: SQL con JDBC (CRUD)
    │
    ├── controller/
    │   └── MovieController.java     # Lógica de negocio e intermediario MVC
    │
    └── view/
        └── MovieView.java           # Interacción con el usuario por consola
```

### Flujo de datos

```
Usuario (Consola)
      ↓
  MovieView          ← Lee datos del usuario con Scanner
      ↓
  MovieController    ← Valida y delega operaciones
      ↓
  MovieRepositoryImp ← Ejecuta queries SQL con JDBC
      ↓
  DBManager          ← Abre/cierra conexión con MySQL
      ↓
  Base de datos MySQL (escena_del_crimen)
```

---

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 21** o superior → [Descargar JDK](https://adoptium.net/)
- **Maven 3.6+** → [Descargar Maven](https://maven.apache.org/download.cgi)
- **MySQL 8.x** en ejecución local → [Descargar MySQL](https://dev.mysql.com/downloads/)
- **IntelliJ IDEA** o cualquier IDE compatible con Maven (opcional pero recomendado)

Comprueba las versiones instaladas:

```bash
java -version
mvn -version
mysql --version
```

---

## Configuración de la Base de Datos

### 1. Crear la base de datos y la tabla

Ejecuta el siguiente script SQL en tu instancia de MySQL:

```sql
CREATE DATABASE IF NOT EXISTS escena_del_crimen;

USE escena_del_crimen;

CREATE TABLE IF NOT EXISTS films (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    title             VARCHAR(255)   NOT NULL,
    year              INT            NOT NULL,
    director          VARCHAR(255),
    actors            TEXT,
    filmAffinittyScore DOUBLE,
    imgUrl            VARCHAR(500),
    url               VARCHAR(500),
    ranking           INT
);
```

> ⚠️ **Atención:** el nombre de la columna en la base de datos es `filmAffinittyScore` (con doble `t`), tal como está definido en el código. Respeta este nombre para evitar errores en las queries.

### 2. Credenciales de conexión

La conexión está configurada en `DBManager.java` con los siguientes valores por defecto:

```java
private static final String URL      = "jdbc:mysql://localhost:3306/escena_del_crimen";
private static final String USER     = "root";
private static final String PASSWORD = "root";
```

Si tu configuración de MySQL es diferente, edita estos valores en `src/main/java/org/example/config/DBManager.java` antes de ejecutar.

---

## Instalación y Ejecución

### Opción 1 — Desde la línea de comandos con Maven

```bash
# 1. Clona o descomprime el proyecto
cd escena_del_crimen_java_vanilla-main

# 2. Compila y descarga dependencias
mvn clean compile

# 3. Ejecuta la aplicación
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Opción 2 — Desde IntelliJ IDEA

1. Abre IntelliJ IDEA → **File > Open** → selecciona la carpeta del proyecto.
2. IntelliJ detectará el `pom.xml` automáticamente e importará las dependencias.
3. Abre `Main.java` y pulsa el botón ▶ **Run**.

### Opción 3 — Generar un JAR ejecutable

```bash
mvn clean package
java -jar target/EscenaDelCrimen-1.0-SNAPSHOT.jar
```

---

## Uso de la Aplicación

Al arrancar, la aplicación realiza las siguientes acciones automáticamente:

### Listar todas las películas

En `Main.java`, al iniciar se ejecuta `getAllMovies()` que consulta la tabla `films` y muestra por consola cada película en el siguiente formato:

```
Movie{title='El Señor de los Anillos', year=2001, director='Peter Jackson', actors=[Elijah Wood, Ian McKellen, ...], filmAffinityScore=8.8, filmDescription='...', imgUrl='https://...', price=9.99}
```

### Crear una película (vía `MovieView`)

La clase `MovieView` permite crear una película de forma interactiva solicitando los siguientes datos por consola:

```
Escriba el título de la película:
> El Señor de los Anillos: La Comunidad del Anillo

Escriba el género de la película:
> Fantasía / Aventura

Escriba el año de la película:
> 2001

Escriba la sinopsis de la película:
> Un hobbit emprende un viaje para destruir el Anillo Único...

escribame el nombre de uno de sus actores/actrices.
> Elijah Wood

¿Hay más actores?  s->si / otra cosa-> no.
> s

Escriba su nota en FilmAffinity(#.##)
> 8.8

Escriba la URL de la imagen:
> https://ejemplo.com/imagen.jpg

Escriba el director de la película:
> Peter Jackson

Escriba la valoración de la película:
> 9.99
```

---

## Estructura de Clases

### `Movie.java` — Modelo

Representa una película con los siguientes atributos:

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `int` | Identificador único (auto-generado por la BD) |
| `title` | `String` | Título de la película |
| `year` | `int` | Año de estreno |
| `director` | `String` | Director/a |
| `actors` | `String[]` | Array de actores/actrices |
| `filmAffinityScore` | `double` | Puntuación en FilmAffinity |
| `filmDescription` | `String` | Sinopsis o descripción |
| `imgUrl` | `String` | URL de la imagen/carátula |
| `price` | `double` | Precio de alquiler o compra |

### `DBManager.java` — Configuración

Gestiona la conexión JDBC de forma estática mediante el patrón Singleton simplificado:

- `innitConnection()` → abre y retorna la conexión.
- `closeConnection()` → cierra la conexión activa.

### `MovieRepositoryImp.java` — Repositorio

Implementa las operaciones CRUD directamente con `Statement` y SQL plano:

- `createMovie(Movie movie)` → `INSERT INTO films ...`
- `getAllMovies()` → `SELECT * FROM films`

### `MovieController.java` — Controlador

Actúa como intermediario entre la vista y el repositorio:

- `createMovieController(Movie movie)` → delega la creación.
- `addMovie(Movie movie)` → comprueba duplicados antes de insertar.

### `MovieView.java` — Vista

Maneja la interfaz de usuario en consola usando `Scanner` para recoger los datos y crear objetos `Movie`.

---

## Notas de Desarrollo

- El proyecto usa **JDBC con `Statement`** directamente (sin PreparedStatement), lo que lo hace susceptible a SQL injection. Para un entorno de producción se recomienda usar `PreparedStatement`.
- Los actores se almacenan en la base de datos como una **cadena de texto separada por comas** y se parsean con `split(",\\s*")` al recuperarlos.
- La conexión a la BD se abre y se cierra en cada operación del repositorio. Para mejorar el rendimiento en producción convendría implementar un **connection pool** (por ejemplo con HikariCP).
- El campo `filmAffinityScore` tiene una errata en la base de datos (`filmAffinittyScore` con doble `t`) que debe mantenerse consistente entre el código Java y el esquema SQL.

---

## Licencia

Este proyecto es de carácter académico y no incluye licencia de distribución. Uso libre para fines educativos.
