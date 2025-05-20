# Lista de Tareas en Java

Aplicación de lista de tareas (TO-DO) desarrollada en Java utilizando Swing y serialización de objetos.

## Características

- Interfaz gráfica con Swing
- Persistencia de datos mediante serialización Java
- Agregar nuevas tareas
- Marcar tareas como completadas (doble clic)
- Eliminar tareas seleccionadas
- Guardado automático de tareas
- Carga automática al iniciar

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── miapp/
│               ├── Main.java
│               ├── model/
│               │   └── Tarea.java
│               ├── service/
│               │   └── JsonService.java
│               └── controllers/
│                   └── TodoPanel.java
```

## Cómo Ejecutar

1. Clona este repositorio
2. Navega al directorio del proyecto
3. Compila el proyecto:
   ```bash
   mvn clean compile
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn exec:java -Dexec.mainClass="com.miapp.Main"
   ```

## Uso de la Aplicación

1. **Agregar Tarea**:
   - Escribe la descripción en el campo de texto
   - Presiona el botón "Agregar" o Enter

2. **Completar Tarea**:
   - Haz doble clic en la tarea para marcarla como completada
   - Las tareas completadas se muestran en gris con un ✓

3. **Eliminar Tarea**:
   - Selecciona la tarea
   - Presiona el botón "Eliminar Seleccionada"

## Almacenamiento de Datos

Las tareas se guardan automáticamente en un archivo `tareas.json` en el directorio raíz del proyecto. Los datos se cargan automáticamente al iniciar la aplicación.

## Tecnologías Utilizadas

- Java Swing para la interfaz gráfica
- Jackson para la serialización JSON
- Maven para la gestión de dependencias
