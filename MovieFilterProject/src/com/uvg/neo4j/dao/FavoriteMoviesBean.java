/*
 * Maria Ines Vásquez Figueroa - 18250
 * Andrea Abril Palencia Gutierrez - 18198
 * Paula Camila Gonzalez Ortega - 18398
 * Estructura de Datos - Seccion 10 - Tercer Semestre
 * Util para tener las peliculas agrupadas y poder controlarlas
 */
package com.uvg.neo4j.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *administrar la lista de peliculas favoritas
 * @author maria.abril.camila
 */
public class FavoriteMoviesBean implements ApplicationContextAware{
    
    private String favoriteMoviesFile = null; //ruta del archivo de pelis fav
    private List<String> listado = null;//archivo en listado
    private Log log = LogFactory.getLog(FavoriteMoviesBean.class);
    
    /**
     *
     */
    public FavoriteMoviesBean(){
        this.listado = new ArrayList<String>();
    }
    
    //checks if archivo esxiste en ruta, si no lo crea
    private void checkFile() throws Exception{
        File f = new File(this.favoriteMoviesFile);
        if (!f.exists()){
            f.createNewFile();
        }
    }
    
    /**
     *metodo que se ejecuta al carga la aplicacion e inicializa el listado de pelis favoritos (one time)
     */
    public void init(){
        try{
            log.info("CARGANDO PELICULAS FAVORITAS");
            this.listado = getList();
            
        }catch(Exception ex){
            log.error("error", ex);
        }
    }
    
    /**
     *cerrar archiv cuando se cierra la aplicacion
     */
    public void close(){
        try{
            log.info("ACTUALIZANDO ARCHIVO DE PELICULAS");
            File f = new File(this.favoriteMoviesFile);
            FileWriter fw = new FileWriter(f);
            for (String movie : listado) {                
                fw.write(movie);
                fw.write("\n");
            }
            fw.close();
        }catch(Exception ex){
            log.error("error", ex);
        }
    }
    
    
    private List<String> getList() throws Exception{
        checkFile();
        FileReader fr = new FileReader(this.favoriteMoviesFile);
        BufferedReader bfr = new BufferedReader(fr);
        
        String line = null;
        List<String> l = new ArrayList<String>();
        while ((line = bfr.readLine()) != null){
            l.add(line.trim());
        }
        bfr.close();
        fr.close();
        
        return l;
    }
    
    /**
     *agregar pelicula al archivo
     * @param title el nombre de la pelicula
     * @throws Exception
     */
    public void addMovie(String title) throws Exception{
        this.listado.add(title);
    }
    
    /**
     *borrar pelicula de listado
     * @param title realiza la eliminacion de acuerdo al nombre
     */
    public void removeMovie(String title){
        this.listado.remove(title);
    }

    /**
     *
     * @param ac
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        
    }

    /**retorna direccion donda esta el archivo
     * @return the favoriteMoviesFile
     */
    public String getFavoriteMoviesFile() {
        return favoriteMoviesFile;
    }

    /**
     * set ruta del archivo
     * @param favoriteMoviesFile the favoriteMoviesFile to set
     */
    public void setFavoriteMoviesFile(String favoriteMoviesFile) {
        this.favoriteMoviesFile = favoriteMoviesFile;
    }

    /**
     * return lista de peliculas
     * @return the listado
     */
    public List<String> getListado() {
        return listado;
    }

    /**
     * @param listado the listado to set
     */
    public void setListado(List<String> listado) {
        this.listado = listado;
    }
    
}
