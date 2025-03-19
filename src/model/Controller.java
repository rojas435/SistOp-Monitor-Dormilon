package model;

public class Controller {
    private final Monitor monitor;
    private final int numEstudiantes;
    public Controller(int numEstudiantes, int numSillasCorredor) {
        this.numEstudiantes = numEstudiantes;
        this.monitor = new Monitor(numSillasCorredor);
    }

    public void iniciarSimulacion() {
        // Iniciar el hilo del monitor
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        // Crear e iniciar los hilos de los estudiantes
        for (int i = 1; i <= numEstudiantes; i++) {
            Estudiante estudiante = new Estudiante(i, monitor);
            Thread estudianteThread = new Thread(estudiante);
            estudianteThread.start();
        }
    }
}