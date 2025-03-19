package model;

import java.util.concurrent.ThreadLocalRandom;

public class Estudiante implements Runnable {
    
    // Constantes de la clase
    private static final int TIEMPO_ATENCION_ESTUDIANTE = 3000; // Tiempo que tarda el monitor en ayudar

    // Atributos de la clase
    private final int idEstudiante;
    private final Monitor monitor;
    private boolean atendido;

    // Constructor de la clase
    public Estudiante(int idEstudiante, Monitor monitor) {
        this.idEstudiante = idEstudiante;
        this.monitor = monitor;
        this.atendido = false;
    }

    // Método que simula la llegada del estudiante a la monitoría
    @Override
    public void run() {
        do {
            try {
                System.out.println(String.format("Estudiante %d: Llega a la monitoría.", idEstudiante));

                if (monitor.getAvailableChairs().tryAcquire()) { // Intenta ocupar una silla
                    try {
                        System.out.println(String.format("Estudiante %d: Se sienta a esperar.", idEstudiante));
                        monitor.getMonitorAttention().acquire(); // Espera su turno para ser atendido por el monitor
                        atencionEstudiante();
                        atendido = true;
                    } finally {
                        monitor.getAvailableChairs().release(); // Libera la silla después de ser atendido
                        monitor.getMonitorAttention().release(); 
                    }
                } else {
                    // Si no hay sillas disponibles, se va a parchar y regresa despuej
                    int espera = ThreadLocalRandom.current().nextInt(2, 7); // Tiempo aleatorio entre 2 y 6 segundos
                    System.out.println(String.format("Estudiante %d: No encontró lugar. Regresará en %d segundos.", idEstudiante, espera));
                    Thread.sleep(espera * 1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(String.format("El hilo del estudiante %d fue interrumpido.", idEstudiante));
            }
        } while (!atendido);
    }

    // Método que simula la atención del monitor al estudiante
    private void atencionEstudiante() throws InterruptedException {
        if (monitor.getSleepyMonitor().availablePermits() == 0) { // Si el monitor esta mimido
            System.out.println(String.format("Estudiante %d: Despertando al monitor...", idEstudiante));
            monitor.wakeUpMonitor(); // Despierta al monitor
        }
        System.out.println(String.format("Estudiante %d: Está siendo atendido.", idEstudiante));
        Thread.sleep(TIEMPO_ATENCION_ESTUDIANTE);
        System.out.println(String.format("Estudiante %d: Ha terminado.", idEstudiante));
    }
}