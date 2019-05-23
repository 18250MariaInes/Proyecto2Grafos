/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.test;

import com.uvg.neo4j.beans.Actor;
import com.uvg.neo4j.beans.Movie;
import com.uvg.neo4j.dao.DaoMapper;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cesar
 */
public class TestQueryClass {
    
    public static void main(String[] args){
        try{
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");     
            
            DaoMapper daoMapper= ctx.getBean("DaoMapper", DaoMapper.class);
            
            /*List<Actor> actores = daoMapper.queryData(Actor.class, "MATCH (n:Person) RETURN id(n), n.born, n.name");
            
            for (Actor actor : actores) {
                System.out.println(actor.toString());
            }*/
            
            //List<Movie> pelis = daoMapper.queryData(Movie.class, "MATCH (n:Movie) RETURN id(n), n.genre, n.released, n.tagline, n.title");
            List<Movie> pelis = daoMapper.queryData(Movie.class, "MATCH (d:Person {name:?})-[:ACTED_IN]->(n:Movie) RETURN id(n), n.genre, n.released, n.tagline, n.title", "Gal Gadot");
            
            for (Movie peli : pelis) {
                System.out.println(peli.toString());
            }
            
            System.exit(0);
        }catch(Exception ex){
            System.out.println("error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
