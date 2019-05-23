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
 *
 * @author cesar
 */
public class Utilities {
    
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
    
    public static void showErrorMessage(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Error del sistema", JOptionPane.ERROR_MESSAGE);
    }
    
    public static <T>TableListBeanModel<T>  fillTable (Class<T> cls, List<T> listObject, JTable table){
        TableListBeanModel<T> model = new TableListBeanModel<T>(listObject, cls);
        table.setModel(model);
        
        return model;
    }
}
