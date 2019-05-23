/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author cesar
 */
public class FavoriteMoviesBean implements ApplicationContextAware{
    
    private String favoriteMoviesFile = null;
    
    private void checkFile() throws Exception{
        File f = new File(this.favoriteMoviesFile);
        if (!f.exists()){
            f.createNewFile();
        }
    }
    
    public List<String> getList() throws Exception{
        checkFile();
        FileReader fr = new FileReader(this.favoriteMoviesFile);
        BufferedReader bfr = new BufferedReader(fr);
        
        String line = null;
        List<String> l = new ArrayList<String>();
        while ((line = bfr.readLine()) != null){
            l.add(line.trim());
        }
        
        return l;
    }
    
    public void addMovie(String title) throws Exception{
        checkFile();
        FileWriter fw = new FileWriter(this.favoriteMoviesFile);        
        fw.write(title);        
        fw.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        
    }

    /**
     * @return the favoriteMoviesFile
     */
    public String getFavoriteMoviesFile() {
        return favoriteMoviesFile;
    }

    /**
     * @param favoriteMoviesFile the favoriteMoviesFile to set
     */
    public void setFavoriteMoviesFile(String favoriteMoviesFile) {
        this.favoriteMoviesFile = favoriteMoviesFile;
    }
    
}
