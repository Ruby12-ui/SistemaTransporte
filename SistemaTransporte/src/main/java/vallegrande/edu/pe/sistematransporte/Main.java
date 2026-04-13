package vallegrande.edu.pe.sistematransporte;

import vallegrande.edu.pe.sistematransporte.view.Vista;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Ejecutar la interfaz correctamente en Swing
        SwingUtilities.invokeLater(() -> {
            new Vista();
        });

    }
}