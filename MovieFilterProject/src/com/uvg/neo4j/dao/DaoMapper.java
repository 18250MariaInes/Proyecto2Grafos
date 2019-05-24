/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
   
/**
 *
 * @author maria.abril.camila
 */
public class DaoMapper implements ApplicationContextAware{

    
    private ApplicationContext ctx = null;
    //mecanismo de acceso de todos los recursos de la aplicacion. vinculo entre beans y recursos
    private Log log = LogFactory.getLog(DaoMapper.class);
    //logs del proyecto
    private DataSource datasource = null;
    //application context, punto de entrada a la DB y crea conexiones a DB
    
    //

    /**
     *obtener application context de aplicacion
     * @param ac application context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ctx = ac;
    }
    
    //convierte un resulted a la instancia de un objeto. Crea objeto a partir de objeto
    private <T>T toInstance (Class<T> cls, ResultSet rs) throws Exception{
        
        //obtiene metadata de result set y obtener todas las columnad del query 
        ResultSetMetaData rsm = rs.getMetaData();
        int cols= rsm.getColumnCount();
        
        //referencia a constructor de los beans que deseamos instanciar 
        Constructor ctor= cls.getConstructor(new Class[]{});
        T obj = (T) ctor.newInstance(new Object[]{});
        
        //llena la tabla de la informacion obtenida del query. Va llenado el objeto
        for (int c=1; c<=cols; c++){
            String columnName = rsm.getColumnName(c);
            Object columnValue = rs.getObject(columnName);
            String fieldName = null;
            if (columnName.equals("id(n)")){
                fieldName = "id";
            }else{
                //retirar prefijos, si los tiene.
                fieldName = StringUtils.substringAfter(columnName, ".");
            }
            PropertyUtils.setProperty(obj, fieldName, columnValue != null ? columnValue.toString() : null);
            
            //field.set(obj, columnValue != null ? columnValue.toString() : null);
            
        }
        
        return obj;
    }
            

    /**
     *realiza la busqueda de informacion por medio de los queries invocados
     * 
     * @param <T> generico, que tipo desea que me devuelva
     * @param cls clase del tipo que desea buscar a instanciar
     * @param query busqueda a realizar
     * @param params de la busqyesa
     * @return la lista de datos 
     * @throws Exception
     */
    public <T>List<T> queryData(Class<T> cls, String query, String ... params) throws Exception{
        //jdbc
        Connection conn = null;//conexion del datasource
        PreparedStatement pstmt = null;//ejecutar sql con la base de datos
        ResultSet rs = null; //leer y devolver resultados (guarda todos los resultados)
        try{
            conn = datasource.getConnection();
            pstmt = conn.prepareStatement(query);//manda un query estructurado con sus place holders
            log.info("EJECUTANDO QUERY:"+ query);            
            
            if (params != null && params.length > 0){                
                int i = 1;
                for (String param : params) {
                    log.info("PARAMETRO (" + i + ") = " + param);
                    pstmt.setString(i, param);
                    i++;
                }
            }
            
            rs = pstmt.executeQuery();
            
            List<T> list = new ArrayList<T>();
            while (rs.next()){
                T obj = toInstance(cls, rs);
                list.add(obj);
            }
            
            return  list;
        }finally{
            //cerrando recursos
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (conn != null){
                conn.close();
            }
        }
    }
    
    //retorna solo el primer resultado de la busqueda

    /**
     *realizar busqueda puntual
     * @param <T> generico para poder realizar busqueda de cualquier tipo de objeto
     * @param cls clase del objeto que desea buscar
     * @param query instruccion de busquda
     * @param params de la busqueda
     * @return resultado de busquesa, objeto puntual
     * @throws Exception
     */
    public <T>T queryFirst(Class<T> cls, String query, String ... params) throws Exception{
        List<T> l = queryData(cls, query, params);
        if (l != null && l.size() > 0){
            return l.get(0);
        }
        return null;
    }
    
    

    /**
     *realizar insercion y eliminacion como vinculos
     * @param sql instruccion que se desea realizar, insert, delete y crear vinculos
     * @param params de la instruccion
     * @throws Exception
     */
    public void execute (String sql,  String ... params) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try{
            conn = datasource.getConnection();
           
            pstmt = conn.prepareStatement(sql);             
            log.info("EJECUTANDO SQL:" + sql);
            if (params != null && params.length > 0){
                int i = 1;
                for (String param : params) {
                    log.info("PARAMETRO (" + i + ") = " + param);
                    pstmt.setString(i, param);
                    i++;
                }
            }
            
            pstmt.executeUpdate();
          
        }finally{
            if (pstmt != null){
                pstmt.close();
            }
            if (conn != null){
                conn.close();
            }            
        }
    }
         
    /**
     *para realizar varias ejecuciones de instrucciones dml
     * @param sqls lista de instrucciones
     * @throws Exception
     */
    public void executeBatch (String[] sqls) throws Exception{
        Connection conn = null;
        Statement stmt = null;
        
        try{
            conn = datasource.getConnection();
            
            stmt = conn.createStatement();
            
            for (String sql : sqls) {
                stmt.addBatch(sql);
            }
            stmt.executeBatch();
        }finally{
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }            
        }
    }

    /**devuelve data spource
     * @return the datasource
     */
    public DataSource getDatasource() {
        return datasource;
    }

    /**settea data source
     * @param datasource the datasource to set
     */
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }
    
    
}
