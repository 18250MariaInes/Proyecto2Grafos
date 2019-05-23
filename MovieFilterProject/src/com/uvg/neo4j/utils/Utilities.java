/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.utils;

import com.uvg.neo4j.interfaces.IDialogPanel;
import com.uvg.neo4j.models.TableListBeanModel;
import java.awt.Component;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *sirve para cargar pantallas 
 * @author mariaines.camila.abril
 */
public class Utilities {
    
    /**
     *cargar una pantalla modal desde un frame  
     * @param parent frame que la que invoca
     * @param title titulo de la pantalla
     * @param panel contenido de pantalla
     */
    public static void loadDialog(JFrame parent, String title, JPanel panel){
        JDialog dialog = new JDialog(parent, title, true);
        dialog.getContentPane().add(panel);
        if (panel instanceof IDialogPanel){
            ((IDialogPanel)panel).setParent(dialog);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);
        dialog.pack();
        dialog.setVisible(true);
        
    }
    
    /**
     *cargar una pantalla modal desde otro dialog
     * @param parent dialog que lo invoca
     * @param title titulo de la pantalla
     * @param panel contenido de pantalla
     */
    public static void loadDialog(JDialog parent, String title, JPanel panel){
        JDialog dialog = new JDialog(parent, title, true);
        dialog.getContentPane().add(panel);
        if (panel instanceof IDialogPanel){
            ((IDialogPanel)panel).setParent(dialog);
        }        
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);
        dialog.pack();
        dialog.setVisible(true);
        
    }
    
    /**
     *
     * @param parent
     * @param message
     */
    public static void showErrorMessage(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Error del sistema", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     *llenar tabla
     * @param <T> generico
     * @param cls clase de la que desea llenar
     * @param listObject listado de objetos
     * @param table tabla que desea llenar
     * @return modelo de la tabla
     */
    public static <T>TableListBeanModel<T>  fillTable (Class<T> cls, List<T> listObject, JTable table){
        TableListBeanModel<T> model = new TableListBeanModel<T>(listObject, cls);
        table.setModel(model);
        
        return model;
    }
}
