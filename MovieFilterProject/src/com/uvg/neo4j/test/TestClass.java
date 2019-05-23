/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;



/**
 *
 * @author cesar.asada
 */
public class TestClass {
    
    
    public static void main(String[] args){
        Connection con = null;
        try{
            Class.forName("org.neo4j.jdbc.bolt.BoltDriver");
             con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7687", "neo4j", "netsa");
             
             PreparedStatement pstmt = con.prepareStatement("MATCH (n:Person) RETURN id(n), n.born, n.name");
             
             ResultSet rs = pstmt.executeQuery();
             
             while(rs.next()){
                 //System.out.println("id:" + rs.getString("id"));
                 ResultSetMetaData rsm= rs.getMetaData();
                 int cols= rsm.getColumnCount();
                 System.out.println("cols=" + cols);
                 for(int c=1; c<=cols;c++){
                     System.out.println("columna=" + rsm.getColumnName(c));
                 }                 
                 System.out.println("data:" + rs.getObject("id(n)"));
                 System.out.println("data:" + rs.getObject("n.born"));
                 System.out.println("data:" + rs.getObject("n.name"));
                 
             }
                 
             
             pstmt.close();
             
             con.close();
             System.out.println("fin");
             
        }catch(Exception ex){
            System.out.println("error:" + ex.getMessage());
            ex.printStackTrace();
        }
        System.exit(0);
    }
} 
