package com.miapp;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.miapp.controllers.TodoPanel;

/**
 * Clase principal que inicia la aplicación de lista TO-DO
 */
public class Main {
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lista de Tareas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setLocationRelativeTo(null);
            
            // Crear y agregar el panel principal
            TodoPanel todoPanel = new TodoPanel();
            frame.add(todoPanel);
            
            frame.setVisible(true);
        });
    }
} 