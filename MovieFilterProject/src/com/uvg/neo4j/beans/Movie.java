/*
 * Maria Ines Vásquez Figueroa - 18250
 * Andrea Abril Palencia Gutierrez - 18198
 * Paula Camila Gonzalez Ortega - 18398
 * Estructura de Datos - Seccion 10 - Tercer Semestre
 * Getters y Setters para las peliculas
 */
package com.uvg.neo4j.beans;

/**
 *
 * @author mariaines.camila.abril
 */
public class Movie {
    private String id = null;
    private String genre = null;
    private String released = null;
    private String title = null;
    private String tagline = null;

    @Override
    public String toString() {
        return id + "-" + title;
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
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the released
     */
    public String getReleased() {
        return released;
    }

    /**
     * @param released the released to set
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * @param tagline the tagline to set
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    
    
}
