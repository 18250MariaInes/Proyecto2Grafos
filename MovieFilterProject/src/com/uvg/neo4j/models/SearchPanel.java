/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.models;

import com.uvg.neo4j.dao.DaoMapper;
import com.uvg.neo4j.interfaces.IDialogPanel;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 *pantalla para realizar busqueda de actores
 * @author mariaines.abril.camila
 * @param <T>
 */
public class SearchPanel<T> extends javax.swing.JPanel implements IDialogPanel {

    private Log log =LogFactory.getLog(SearchPanel.class);
    private ApplicationContext ctx = null;
    private T seleccionado = null; //actor seleccionado
    private TableListBeanModel<T> tableModel = null;
    private JDialog parent = null; //contenedor del panel, jdialog
    
            
    /**
     * Creates new form SearchPanel
     * @param ctx aplication context y acceso de applicaccion
     * @param cls clase que va recibir la busqeda, en este caso el actor
     * @param query query que llena la tabla de actores
     */
    public SearchPanel(ApplicationContext ctx, String query, Class<T> cls) {
        initComponents();
        try{
            this.ctx = ctx;
            this.parent = parent;
            DaoMapper daoMapper= this.ctx.getBean("DaoMapper", DaoMapper.class);
            List<T> listadoObjetos= daoMapper.queryData(cls, query);
            if (listadoObjetos != null && listadoObjetos.size() > 0){
                tableModel = new TableListBeanModel<T>(listadoObjetos, cls);
                this.jTable1.setModel(tableModel);
            }
            this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
        }catch(Exception ex){
            this.setVisible(false);
            log.error("ERROR", ex);
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmdSeleccionar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 540, 340));

        cmdSeleccionar.setText("seleccionar");
        cmdSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSeleccionarMouseClicked(evt);
            }
        });
        cmdSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSeleccionarActionPerformed(evt);
            }
        });
        add(cmdSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSeleccionarActionPerformed

    }//GEN-LAST:event_cmdSeleccionarActionPerformed

    private void cmdSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSeleccionarMouseClicked
        // TODO add your handling code here:
        int rowID = this.jTable1.getSelectedRow();
        log.info("fila seleccionada:" + rowID);
        this.seleccionado = this.tableModel.getObjectAt(rowID);
        this.parent.setVisible(false);
    }//GEN-LAST:event_cmdSeleccionarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     * @return the seleccionado actor seleccionado
     */
    public T getSeleccionado() {
        return seleccionado;
    }

    /**
     *
     * @param frame
     */
    @Override
    public void setParent(JDialog frame) {
        this.parent = frame;
    }

    @Override
    public JDialog getParent() {
        return parent;
    }
}
