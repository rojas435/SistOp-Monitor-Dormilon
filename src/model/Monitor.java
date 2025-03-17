package model;

import java.util.concurrent.Semaphore;

public class Monitor implements Runnable {
    Semaphore availableChairs;
    Semaphore sleepyMonitor;   // Controla si el monitor está dormido o despierto
    Semaphore monitorAttention;

    public Monitor(int sillasPasillo) {
        this.availableChairs = new Semaphore(sillasPasillo);
        this.sleepyMonitor = new Semaphore(0); 
        this.monitorAttention = new Semaphore(1);
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Si no hay estudiantes esperando, el monitor se va a dormir
                if (availableChairs.availablePermits() == 3) { 
                    System.out.println("El monitor no tiene a quien atender, se va a mimir...");
                    sleepyMonitor.acquire(); // El monitor se va a mimir
                    System.out.println("El monitor ha sido despertado y está listo para atender.");
                }

                if (availableChairs.availablePermits() < 3) { // Si hay estudiantes esperando
                    System.out.println("El monitor está atendiendo a un estudiante...");
                }

                // Atender al siguiente estudiante
                monitorAttention.acquire(); // Bloquea el semáforo de atencion para que no meta a otro
                long endTime = System.currentTimeMillis() + 3000; // Simula el tiempo
                while (System.currentTimeMillis() < endTime) {
                    // Busy-wait loop to simulate delay
                }
                monitorAttention.release(); // Libera el semáforo de atencion

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo del monitor fue interrumpido.");
            }
        }
    }

    public void wakeUpMonitor() {
        if (sleepyMonitor.availablePermits() == 0) {
            sleepyMonitor.release(); 
        }
    }

    public Semaphore getAvailableChairs() {
        return availableChairs;
    }

    public Semaphore getSleepyMonitor() {
        return sleepyMonitor;
    }

    public Semaphore getMonitorAttention() {
        return monitorAttention;
    }
}