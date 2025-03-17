package ui;

import model.Controller;

public class Main {
    public static void main(String[] args) {
        int numEstudiantes = 20; // Número de estudiantes
        int numSillasCorredor = 5; // Número de sillas en el corredor

        Controller controller = new Controller(numEstudiantes, numSillasCorredor);
        controller.iniciarSimulacion();
    }
}