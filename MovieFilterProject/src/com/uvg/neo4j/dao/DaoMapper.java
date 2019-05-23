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
 * @author cesar.asada
 */
public class DaoMapper implements ApplicationContextAware{

    private ApplicationContext ctx = null;
    private Log log = LogFactory.getLog(DaoMapper.class);
    private DataSource datasource = null;
    
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ctx = ac;
    }
    
    private <T>T toInstance (Class<T> cls, ResultSet rs) throws Exception{
        ResultSetMetaData rsm = rs.getMetaData();
        int cols= rsm.getColumnCount();
        Constructor ctor= cls.getConstructor(new Class[]{});
        T obj = (T) ctor.newInstance(new Object[]{});
        
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
            
    
    public <T>List<T> queryData(Class<T> cls, String query, String ... params) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = datasource.getConnection();
            pstmt = conn.prepareStatement(query);
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
    
    public <T>T queryFirst(Class<T> cls, String query, String ... params) throws Exception{
        List<T> l = queryData(cls, query, params);
        if (l != null && l.size() > 0){
            return l.get(0);
        }
        return null;
    }
    
    
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

    /**
     * @return the datasource
     */
    public DataSource getDatasource() {
        return datasource;
    }

    /**
     * @param datasource the datasource to set
     */
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }
    
    
}
