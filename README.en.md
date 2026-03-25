# 🎬 Crime Scene — Movie Management with Java & MySQL

> A console application written in pure (vanilla) Java that allows you to manage a movie catalog connected to a MySQL database. It implements the full MVC pattern with data access via JDBC.

---

## 📋 Table de Contents

- [Descriptión](#descripción)
- [Tecnologies](#tecnologías)
- [Proyect arquitecture](#arquitectura-del-proyecto)
- [Prerequisites](#requisitos-previos)
- [Database configuration](#configuración-de-la-base-de-datos)
- [Install and execution](#instalación-y-ejecución)
- [Aplication use](#uso-de-la-aplicación)
- [Clases structure](#estructura-de-clases)
- [development notes](#notas-de-desarrollo)

---

## Description
Crime Scene is a command-line Java 21 application without frameworks, designed to manage a movie catalog stored in MySQL. Implements CRUD operations, MVC pattern, and JDBC handling.

## Technologies
- Java 21
- Maven 3.x
- MySQL 8.x + Connector/J 8.3.0
- IntelliJ IDEA / Eclipse

## Architecture
MVC with a repository layer for data access:
If your MySQL configuration is different, edit these values in `src/main/java/org/example/config/DBManager.java` before running the application.

---

## Installation and Execution

### Option 1 — From the command line using Maven

```bash
# 1. Clone or unzip the project
cd crime_scene_java_vanilla-main

# 2. Compile and download dependencies
mvn clean compile

# 3. Run the application
mvn exec:java -Dexec.mainClass=“org.example.Main”
```

### Option 2 — From IntelliJ IDEA

1. Open IntelliJ IDEA → **File > Open** → select the project folder.
2. IntelliJ will automatically detect the `pom.xml` and import the dependencies.
3. Open `Main.java` and click the ▶ **Run** button.

### Option 3 — Generate an executable JAR

```bash
mvn clean package
java -jar target/CrimeScene-1.0-SNAPSHOT.jar
```

---

## Using the Application

Upon startup, the application automatically performs the following actions:

### List all movies

In `Main.java`, `getAllMovies()` is executed at startup, which queries the `films` table and displays each movie in the console in the following format:

```
Movie{title=‘The Lord of the Rings’, year=2001, director=‘Peter Jackson’, actors=[Elijah Wood, Ian McKellen, ...], filmAffinityScore=8.8, filmDescription=‘...’, imgUrl=‘https://...’, price=9.99}
```

### Create a movie (via `MovieView`)

The `MovieView` class allows you to create a movie interactively by requesting the following data via the console:

```
Enter the movie title: