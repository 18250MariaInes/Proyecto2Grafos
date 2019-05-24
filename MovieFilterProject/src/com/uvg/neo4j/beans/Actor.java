/*
 * Maria Ines Vásquez Figueroa - 18250
 * Andrea Abril Palencia Gutierrez - 18198
 * Paula Camila Gonzalez Ortega - 18398
 * Estructura de Datos - Seccion 10 - Tercer Semestre
 * Getters y Setters para los actores de las peliculas
 */
package com.uvg.neo4j.beans;

/**
 *
 * @author mariaines.camila.abril
 */
public class Actor {
    
    private String id = null;
    private String name = null;
    private String born = null;

    @Override
    public String toString() {
        return id + "-" + name;
    }

    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the born
     */
    public String getBorn() {
        return born;
    }

    /**
     * @param born the born to set
     */
    public void setBorn(String born) {
        this.born = born;
    }
    
    
}
