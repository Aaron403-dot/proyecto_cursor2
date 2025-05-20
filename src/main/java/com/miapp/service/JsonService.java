package com.miapp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miapp.model.Tarea;

/**
 * Servicio para manejar la persistencia de tareas en formato JSON.
 * Permite guardar y cargar la lista de tareas desde un archivo.
 */
public class JsonService {
    /** Nombre del archivo donde se guardarán las tareas */
    private static final String JSON_FILE = "tareas.json";
    
    /** Mapper de Jackson para la serialización/deserialización JSON */
    private final ObjectMapper objectMapper;

    /**
     * Constructor que inicializa el ObjectMapper de Jackson
     */
    public JsonService() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Guarda la lista de tareas en el archivo JSON
     * @param tareas Lista de tareas a guardar
     * @throws IOException Si hay un error al escribir el archivo
     */
    public void guardarTareas(List<Tarea> tareas) throws IOException {
        objectMapper.writeValue(new File(JSON_FILE), tareas);
    }

    /**
     * Carga la lista de tareas desde el archivo JSON
     * @return Lista de tareas cargada
     * @throws IOException Si hay un error al leer el archivo
     */
    public List<Tarea> cargarTareas() throws IOException {
        File file = new File(JSON_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, 
            objectMapper.getTypeFactory().constructCollectionType(List.class, Tarea.class));
    }
} 