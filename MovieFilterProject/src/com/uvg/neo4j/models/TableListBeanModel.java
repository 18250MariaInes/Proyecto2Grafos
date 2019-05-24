/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *llenar las tablas (tablemodel interfaz de swing) en base a los objetos que mandemos
 * @author mariaies.camila.abril
 * @param <T>
 */
public class  TableListBeanModel<T> implements TableModel{
    
    private List<String> columns = null; //encabezados de columnas
    private List<T> listObject = null; //listado de objetos a mostrar
    
    /**
     *
     * @param listObject
     * @param cls
     */
    public TableListBeanModel(List<T> listObject, Class<T> cls){
        this.listObject = listObject;
        Field[] fields= cls.getDeclaredFields();
        this.columns = new ArrayList<String>();
        for (Field field : fields) {
            this.columns.add(field.getName());
        }
    }

    @Override
    public int getRowCount() {
        return this.listObject.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.size();
    }

    @Override
    public String getColumnName(int i) {
        return this.columns.get(i);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        T obj= this.listObject.get(i);
        String colName = this.columns.get(i1);
        try{
            return PropertyUtils.getSimpleProperty(obj, colName);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param rowid
     * @return
     */
    public T getObjectAt(int rowid){
        return this.listObject.get(rowid);
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        
    }
    
}
