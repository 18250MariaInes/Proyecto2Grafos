/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.dao;

import com.uvg.neo4j.beans.Movie;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *actualizacion y mantenimiento de peliculas
 * @author mariaines.camila.abril
 */
public class MovieDao implements ApplicationContextAware{
    
    private DaoMapper daoMapper = null;
    
    /**
     *agregar pelicula a la DB
     * @param parametro el objeto pelicula
     * @throws Exception
     */
    public void agregar(Movie parametro) throws Exception{
        daoMapper.execute("CREATE (d:Movie {title:? , tagline:?, released:?, genre:?})",  parametro.getTitle(), parametro.getTagline(), parametro.getReleased(), parametro.getGenre());
        Movie s = busquedaPuntual(parametro.getTitle());
        parametro.setId(s.getId());
    }
    
    /**
     *
     * @param parametro
     * @throws Exception
     */
    public void actualizar(Movie parametro) throws Exception{
        daoMapper.execute("",  parametro.getId() ,parametro.getTitle(), parametro.getTagline(), parametro.getGenre(), parametro.getReleased());
    }
    
    /**
     *metodo para eliminar pelicula de la DB
     * @param parametro objeto pelicula
     * @throws Exception
     */
    public void eliminar(Movie parametro) throws Exception{
        daoMapper.execute("MATCH (d:Movie {title:?})DETACH DELETE d", parametro.getTitle());
    }
    
    /**
     *busqueda puntual de una pelicula
     * @param titulo de la pelicula
     * @return objeto tipo pelicula
     * @throws Exception
     */
    public Movie busquedaPuntual(String titulo)throws Exception{
         return daoMapper.queryFirst(Movie.class, "MATCH (n:Movie) WHERE n.title = ? RETURN id(n), n.title, n.tagline, n.released, n.genre", titulo);
    }
    
    /**
     *query que realizar la recomendacion en base a actores y genero
     * @param pelicula pelicula seleccionada para realizar recomendacion
     * @return listado de peliculas recomendadas
     * @throws Exception
     */
    public List<Movie> recomendaciones (String pelicula) throws Exception{
        List<Movie> l= daoMapper.queryData(Movie.class , "MATCH (n:Movie {title:?}) RETURN n.genre", pelicula);
        String genero = l.get(0).getGenre();
        List<Movie> r= daoMapper.queryData(Movie.class
                , "MATCH (a:Movie {title:?})<-[:ACTED_IN]-(d:Person)-[:ACTED_IN]->(n:Movie {genre:?}) RETURN id(n), n.title, n.genre, n.released ORDER BY n.title"
                ,  pelicula, genero);
        return r;
    }

    /**
     *
     * @param ac
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        
    }

    /**
     * settea dao mapper
     * @return the daoMapper
     */
    public DaoMapper getDaoMapper() {
        return daoMapper;
    }

    /**
     * get dao mapper
     * @param daoMapper the daoMapper to set
     */
    public void setDaoMapper(DaoMapper daoMapper) {
        this.daoMapper = daoMapper;
    }
}
