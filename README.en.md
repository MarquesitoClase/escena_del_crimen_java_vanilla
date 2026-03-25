# Crime Scene — Movie Management with Java & MySQL

> Aplicación de consola en Java puro (vanilla) para gestionar un catálogo de películas conectado a una base de datos MySQL. Implementa el patrón MVC completo con acceso mediante JDBC.

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
## 📝 description

**Escena del Crimen** is an app development with java **Java 21 without frameworks**, whaty it's objective is make one CRUD of a DDBB with mySQL.

El project primary works are:

- List all films saved into the database.
- Allow the user to create new films into the console
- Check detailed information: title, year, director, actors, FilmAffinity score, descriptión, URL of image and price.
---

### Main functionalities

- List all the films on the DDBB
- Create new films on the console
- Check films information

---

## 🛠️ Tecnologías

| Tecnología              | Versión | Use into the proyect                 |
|-------------------------|--------|--------------------------------------|
| Java                    | 21     | Main development language            |
| Maven                   | 3.x    | Dependency and build management      |
| JDBC                    | —      | Conection and use of the database    |
| MySQL Connector/J       | 8.3.0  | Driver JDBC for MySQL                |
| MySQL                   | 8.x    | System of management of the database |
| IntelliJ IDEA / Eclipse | —      | development enviroment (IDE)         |

---

## 🏗️ project-architecture

This proyect use **MVC (Model - View - Controller)** with a repository for the use of data:

VideoclubFinal/
│
├── pom.xml
│
└── src/main/java/org/example/
│
├── Main.java
│
├── config/
│ └── DBManager.java
│
├── model/
│ └── Movie.java
│
├── repository/
│ └── MovieRepositoryImp.java
│
├── controller/
│ └── MovieController.java
│
└── view/
└── MovieView.java

### 🔄 Flujo de Datos
Usuario (Consola)
↓
MovieView
↓
MovieController
↓
MovieRepositoryImp
↓
DBManager
↓
Base de datos MySQL (escena_del_crimen)

