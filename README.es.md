# 🎬 Crime Scene — Movie Management with Java & MySQL

> Console application built in pure Java (vanilla) to manage a movie catalog connected to a MySQL database. Implements a full MVC architecture with JDBC data access.

---

## 📋 Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Project Architecture](#project-architecture)
    - [Data Flow](#data-flow)
- [Prerequisites](#prerequisites)
- [Database Configuration](#database-configuration)
    - [1. Create the database and table](#1-create-the-database-and-table)
    - [2. Connection credentials](#2-connection-credentials)
- [Installation and Execution](#installation-and-execution)
    - [Option 1 — Command line with Maven](#option-1--command-line-with-maven)
    - [Option 2 — IntelliJ IDEA](#option-2--intellij-idea)
    - [Option 3 — Build an executable JAR](#option-3--build-an-executable-jar)
- [Application Usage](#application-usage)
    - [List all movies](#list-all-movies)
    - [Create a movie (via MovieView)](#create-a-movie-via-movieview)
- [Class Structure](#class-structure)
    - [Movie.java — Model](#moviejava--model)
    - [DBManager.java — Configuration](#dbmanagerjava--configuration)
    - [MovieRepositoryImp.java — Repository](#movierepositoryimpjava--repository)
    - [MovieController.java — Controller](#moviecontrollerjava--controller)
    - [MovieView.java — View](#movieviewjava--view)
- [Development Notes](#development-notes)
- [License](#license)

---

## Description

**Crime Scene** is a command-line application developed in **Java 25 without frameworks**, designed to manage a movie catalog stored in a MySQL database.

This project serves as a practical exercise in:

- Relational database access with JDBC
- MVC architecture in pure Java
- CRUD operations handling

### Main Features

- List all movies stored in the database
- Create new movies interactively from the console
- Manage detailed information: title, year, director, actors, FilmAffinity score, description, image URL, and price

---

## 🛠️ technologies

| Technology | Version | Purpose in the Project |
|---|---|---|
| Java (JDK) | 25 | Main programming language |
| Maven | 3.x | Dependency management and build automation |
| JDBC | — | Relational database connectivity |
| MySQL Connector/J | 8.3.0 | JDBC driver for MySQL |
| MySQL | 8.x | Database Management System |
| IntelliJ IDEA / Eclipse | — | Development Environment (IDE) |

---

## Project Architecture

The project follows the **MVC pattern (Model – View – Controller)** with an additional repository layer for data access:

```
VideoclubFinal/
│
├── pom.xml
│
└── src/main/java/org/example/
    │
    ├── Main.java
    │
    ├── config/
    │   └── DBManager.java
    │
    ├── model/
    │   └── Movie.java
    │
    ├── repository/
    │   └── MovieRepositoryImp.java
    │
    ├── controller/
    │   └── MovieController.java
    │
    └── view/
        └── MovieView.java
```

### Data Flow

```
User (Console)
      ↓
  MovieView          ← Reads user input via Scanner
      ↓
  MovieController    ← Validates and delegates operations
      ↓
  MovieRepositoryImp ← Executes SQL queries via JDBC
      ↓
  DBManager          ← Opens/closes MySQL connection
      ↓
  MySQL Database (escena_del_crimen)
```

---

## Prerequisites

Make sure the following software is installed:

- **Java 25 (JDK)** or higher → https://adoptium.net/
- **Maven 3.6+** → https://maven.apache.org/download.cgi
- **MySQL 8.x** running locally → https://dev.mysql.com/downloads/
- **IntelliJ IDEA** or any Maven-compatible IDE (optional but recommended)

Check installed versions:

```bash
java -version
mvn -version
mysql --version
```

---

## Database Configuration

### 1. Create the database and table

Run the following SQL script in your MySQL instance:

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

> ⚠️ **Important:** The column name is `filmAffinittyScore` (double **t**), exactly as defined in the code. Keep it unchanged to avoid SQL errors.

---

### 2. Connection credentials

Database connection is configured in `DBManager.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/escena_del_crimen";
```

If your MySQL setup differs, edit the values in:

```
src/main/java/org/example/config/DBManager.java
```

---

## Installation and Execution

### Option 1 — Command line with Maven

```bash
cd escena_del_crimen_java_vanilla-main
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

---

### Option 2 — IntelliJ IDEA

1. Open IntelliJ IDEA → **File > Open** → select the project folder
2. IntelliJ will detect `pom.xml` and import dependencies
3. Open `Main.java` → click ▶ **Run**

---

### Option 3 — Build an executable JAR

```bash
mvn clean package
java -jar target/EscenaDelCrimen-1.0-SNAPSHOT.jar
```

---

## Application Usage

### List all movies

At startup, `getAllMovies()` is executed to fetch all records from the `films` table and display them in the console:

```
Movie{title='The Lord of the Rings', year=2001, director='Peter Jackson', actors=[Elijah Wood, Ian McKellen, ...], filmAffinityScore=8.8, filmDescription='...', imgUrl='https://...', price=9.99}
```

---

### Create a movie (via MovieView)

`MovieView` allows interactive movie creation via console prompts:

```
Enter movie title:
> The Lord of the Rings: The Fellowship of the Ring

Enter movie genre:
> Fantasy / Adventure

Enter release year:
> 2001

Enter movie synopsis:
> A hobbit begins a journey to destroy the One Ring...

Enter an actor/actress name:
> Elijah Wood

Are there more actors? s->yes / other key->no
> s

Enter FilmAffinity score (#.##):
> 8.8

Enter image URL:
> https://example.com/image.jpg

Enter movie director:
> Peter Jackson

Enter movie price:
> 9.99
```

---

## Class Structure

### `Movie.java` — Model

Represents a movie entity:

| Field | Type | Description |
|---|---|---|
| `id` | `int` | Unique identifier (auto-generated) |
| `title` | `String` | Movie title |
| `year` | `int` | Release year |
| `director` | `String` | Director |
| `actors` | `String[]` | Actors array |
| `filmAffinityScore` | `double` | FilmAffinity rating |
| `filmDescription` | `String` | Synopsis |
| `imgUrl` | `String` | Cover image URL |
| `price` | `double` | Rental or purchase price |

---

### `DBManager.java` — Configuration

Handles JDBC connection using a simplified Singleton pattern:

- `initConnection()` → Opens and returns connection
- `closeConnection()` → Closes active connection

---

### `MovieRepositoryImp.java` — Repository

Implements CRUD operations using SQL and `Statement`:

- `createMovie(Movie movie)` → `INSERT INTO films ...`
- `getAllMovies()` → `SELECT * FROM films`

---

### `MovieController.java` — Controller

Acts as intermediary between View and Repository:

- `createMovieController(Movie movie)` → Delegates creation
- `addMovie(Movie movie)` → Checks duplicates before insert

---

### `MovieView.java` — View

Handles console UI using `Scanner` to collect input and create `Movie` objects.

---

## Development Notes

- Uses **JDBC with `Statement`** (no `PreparedStatement`), making it vulnerable to SQL injection. Production environments should use `PreparedStatement`.
- Actors are stored as a **comma-separated string** and parsed using `split(",\\s*")`.
- Database connections are opened and closed per repository operation. For better performance, a **connection pool** (e.g., HikariCP) is recommended.

---

## License

This is an academic project released under the **CC BY** license.