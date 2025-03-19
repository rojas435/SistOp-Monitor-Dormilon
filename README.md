# Estudiantes
- Felipe Rojas Prado - A00393918
- Juan Felipe Jojoa Crespo - A00382042

# Monitor Dormilón

## Descripción del Proyecto

Este proyecto es una simulación del problema del "Monitor Dormilón", un problema clásico de concurrencia que modela la interacción entre estudiantes y un monitor que los ayuda en una sesión de tutoría. La implementación utiliza Java con mecanismos de sincronización como semáforos para coordinar la concurrencia.

## Estructura del Proyecto

El proyecto está compuesto por las siguientes clases:

### 1. **Estudiante.java**

Esta clase representa a un estudiante que llega a la tutoría. Los estudiantes intentan ocupar una de las sillas del pasillo para esperar su turno. Si todas las sillas están ocupadas, el estudiante se retira y regresa después de un tiempo aleatorio.

- Si encuentra una silla disponible, se sienta y espera ser atendido por el monitor.
- Si el monitor está dormido, el estudiante lo despierta.
- Luego de ser atendido, el estudiante libera la silla y termina su ejecución.

### 2. **Monitor.java**

El monitor es el encargado de atender a los estudiantes:

- Si no hay estudiantes esperando, el monitor se duerme.
- Cuando un estudiante llega y lo despierta, el monitor comienza a atenderlos en orden.
- El monitor atiende a los estudiantes en orden de llegada y cada atención toma un tiempo determinado.
- Se utilizan semáforos para manejar la disponibilidad de sillas y la sincronización del monitor con los estudiantes.

### 3. **Controller.java**

Esta clase administra la simulación:

- Inicializa el monitor y los estudiantes.
- Crea hilos para cada estudiante y el monitor.
- Inicia la ejecución de los hilos para simular la llegada de estudiantes y la atención del monitor.

### 4. **Main.java**

El punto de entrada del programa. En esta clase:

- Se define el número de estudiantes y el número de sillas disponibles en el pasillo.
- Se crea una instancia de `Controller` para iniciar la simulación.

## Ejecución del Proyecto

### Requisitos Previos

Para ejecutar este proyecto, necesitas:
- Tener instalado Java 8 o superior.
- Un entorno de desarrollo como IntelliJ IDEA, Eclipse o VS Code con soporte para Java.

### Pasos para Compilar y Ejecutar

1. Clona o descarga el proyecto en tu equipo.
2. Asegúrate de que los archivos `.java` estén dentro de un directorio de paquete adecuado.
3. Compila los archivos con el siguiente comando en la terminal:
   ```sh
   javac -d . model/*.java ui/*.java
   ```
4. Ejecuta la aplicación con:
   ```sh
   java ui.Main
   ```

## Funcionamiento del Programa

1. Se inicia la simulación con `Main.java`.
2. Se crean los hilos de los estudiantes y del monitor.
3. Los estudiantes llegan a la tutoría e intentan sentarse.
4. El monitor atiende a los estudiantes en orden.
5. Si no hay estudiantes esperando, el monitor se duerme.
6. La simulación finaliza cuando todos los estudiantes han sido atendidos.

## Conclusión

Este programa proporciona una implementación básica pero efectiva del problema del "Monitor Dormilón" utilizando sincronización con semáforos en Java. Es un excelente ejemplo de cómo manejar concurrencia en sistemas multihilo.


