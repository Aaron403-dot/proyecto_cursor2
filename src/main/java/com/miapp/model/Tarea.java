package com.miapp.model;

import java.io.Serializable;

/**
 * Clase que representa una tarea en la lista TO-DO.
 * Cada tarea tiene una descripción y un estado de completado.
 */
public class Tarea implements Serializable{
    /** Descripción de la tarea */
    private String descripcion;
    
    /** Estado de la tarea (completada o no) */
    private boolean completada;

    /**
     * Constructor vacío necesario para la serialización
     */
    public Tarea() {}

    /**
     * Constructor con descripción inicial.
     * La tarea se crea como no completada por defecto.
     * @param descripcion Texto descriptivo de la tarea
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    /**
     * @return La descripción de la tarea
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion Nueva descripción para la tarea
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return true si la tarea está completada, false en caso contrario
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * @param completada Nuevo estado de la tarea
     */
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
} 