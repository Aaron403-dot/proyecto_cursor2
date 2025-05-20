package com.miapp.controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.miapp.model.Tarea;
import com.miapp.service.JsonService;

/**
 * Panel principal que contiene la interfaz de usuario de la lista TO-DO
 */
public class TodoPanel extends JPanel {
    private final JTextField nuevaTareaField;
    private final JList<Tarea> tareasList;
    private final DefaultListModel<Tarea> listModel;
    private final JsonService jsonService;
    private List<Tarea> tareas;

    public TodoPanel() {
        // Configuración inicial del panel
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Inicializar componentes
        jsonService = new JsonService();
        tareas = new ArrayList<>();
        listModel = new DefaultListModel<>();
        nuevaTareaField = new JTextField();
        tareasList = new JList<>(listModel);
        
        // Configurar la lista
        tareasList.setCellRenderer(new TareaListCellRenderer());
        tareasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Crear panel superior
        JPanel topPanel = new JPanel(new BorderLayout(5, 0));
        topPanel.add(new JLabel("Nueva tarea:"), BorderLayout.WEST);
        topPanel.add(nuevaTareaField, BorderLayout.CENTER);
        
        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(e -> agregarTarea());
        topPanel.add(agregarButton, BorderLayout.EAST);
        
        // Crear panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton eliminarButton = new JButton("Eliminar Seleccionada");
        eliminarButton.addActionListener(e -> eliminarTareaSeleccionada());
        buttonPanel.add(eliminarButton);
        
        // Agregar componentes al panel principal
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tareasList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Configurar evento de doble clic
        tareasList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = tareasList.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        Tarea tarea = listModel.getElementAt(index);
                        tarea.setCompletada(!tarea.isCompletada());
                        guardarTareas();
                        tareasList.repaint();
                    }
                }
            }
        });
        
        // Cargar tareas existentes
        cargarTareas();
    }
    
    private void agregarTarea() {
        String descripcion = nuevaTareaField.getText().trim();
        if (!descripcion.isEmpty()) {
            Tarea tarea = new Tarea(descripcion);
            tareas.add(tarea);
            listModel.addElement(tarea);
            nuevaTareaField.setText("");
            guardarTareas();
        }
    }
    
    private void eliminarTareaSeleccionada() {
        int selectedIndex = tareasList.getSelectedIndex();
        if (selectedIndex != -1) {
            tareas.remove(selectedIndex);
            listModel.remove(selectedIndex);
            guardarTareas();
        }
    }
    
    private void cargarTareas() {
        try {
            tareas = jsonService.cargarTareas();
            listModel.clear();
            for (Tarea tarea : tareas) {
                listModel.addElement(tarea);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar tareas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void guardarTareas() {
        try {
            jsonService.guardarTareas(tareas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar tareas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Renderizador personalizado para las tareas en la lista
     */
    private static class TareaListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Tarea) {
                Tarea tarea = (Tarea) value;
                setText((tarea.isCompletada() ? "✓ " : "□ ") + tarea.getDescripcion());
                if (tarea.isCompletada()) {
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
            }
            return this;
        }
    }
} 