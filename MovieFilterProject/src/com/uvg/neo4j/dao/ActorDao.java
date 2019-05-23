/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.dao;

import com.uvg.neo4j.beans.Actor;
import com.uvg.neo4j.beans.Movie;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author cesar.asada
 */
public class ActorDao implements ApplicationContextAware{

    private DaoMapper daoMapper = null;
    private Log log = LogFactory.getLog(ActorDao.class);
    
    public void agregar(Actor parametro) throws Exception{
        daoMapper.execute("CREATE (d:Person {name:?, born:?})",  parametro.getName(), parametro.getBorn());       
    }
    
    public void asociarAPeli(Actor parametro, Movie pelicula) throws Exception{
        //validar si ya existe la asociacion
        List<Actor> vinculo= daoMapper.queryData(Actor.class, "MATCH (d:Person {name:?})-[:ACTED_IN]->(x:Movie {title:?}) RETURN d.name ORDER BY d.name"
                , parametro.getName(), pelicula.getTitle());
        if (vinculo != null && vinculo.size() > 0){
           throw new Exception("ACTOR:" + parametro.toString() + " ya ingresado en la pelicula " + pelicula.toString()) ;
        }else{
           //daoMapper.queryData(cls, query, params)
           //daoMapper.execute("MERGE (d:Person {name:?})-[:ACTED_IN]->(x:Movie {title:?})", false, parametro.getName(), pelicula.getTitle());
           daoMapper.execute("MATCH (a:Person) WHERE a.name=? MATCH (b:Movie) WHERE b.title=? MERGE (a)-[:ACTED_IN]->(b)"
                , parametro.getName(), pelicula.getTitle());
           
        }
    }
    
    public void eliminarDePeli(Actor parametro, Movie pelicula) throws Exception{
        daoMapper.execute("MATCH (d:Person {name:?})-[r:ACTED_IN]->(x:Movie {title:?}) DELETE r"
                , parametro.getName(), pelicula.getTitle());
    }
    
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        
    }

    /**
     * @return the daoMapper
     */
    public DaoMapper getDaoMapper() {
        return daoMapper;
    }

    /**
     * @param daoMapper the daoMapper to set
     */
    public void setDaoMapper(DaoMapper daoMapper) {
        this.daoMapper = daoMapper;
    }
    
}