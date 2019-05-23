/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.screens;

import com.uvg.neo4j.beans.Movie;
import com.uvg.neo4j.dao.DaoMapper;
import com.uvg.neo4j.dao.FavoriteMoviesBean;
import com.uvg.neo4j.dao.MovieDao;
import com.uvg.neo4j.utils.Utilities;
import java.util.List;
import javax.swing.DefaultListModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author mariaines.camila.abril
 */
public class MyMovieListPanel extends javax.swing.JPanel {

    private Log log = LogFactory.getLog(MyMovieListPanel.class);
    private ApplicationContext ctx = null;
    private FavoriteMoviesBean bean = null;
    private String selectedMovie = null;
    private List<String> listado = null;
    private String recomendationsQuery = "MATCH (a:Movie {title:?})<-[:ACTED_IN]-(d:Person)-[:ACTED_IN]->(n:Movie {genre:?}) RETURN id(n), n.title, n.genre, n.released ORDER BY n.title";
    private MovieDao movieDao = null;
    private List<Movie> listadoRecomendaciones = null;
            
    /**
     * Creates new form MyMovieListPanel
     */
    public MyMovieListPanel(ApplicationContext ctx) throws Exception{
        initComponents();
        this.ctx = ctx;
        bean =this.ctx.getBean("FavoriteMoviesBean", FavoriteMoviesBean.class);
        movieDao = this.ctx.getBean("MovieDao", MovieDao.class);
        loadList();
    }
    
    public MyMovieListPanel(ApplicationContext ctx, String selectedMovie) throws Exception{
        initComponents();
        this.ctx = ctx;
        this.selectedMovie = selectedMovie;                
        bean =this.ctx.getBean("FavoriteMoviesBean", FavoriteMoviesBean.class);
        movieDao = this.ctx.getBean("MovieDao", MovieDao.class);
        loadList();
        //agregar pelicula nueva.
        if (!this.listado.contains(selectedMovie)){
            bean.addMovie(selectedMovie);
            loadList();
        }
        
    }
    
    private void loadList() throws Exception{        
        listado = bean.getListado();
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (String peli : listado) {
            model.addElement(peli);
        }
        this.lstPelisFavoritas.setModel(model);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmdEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPelisFavoritas = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecomendaciones = new javax.swing.JTable();
        cmdRecomendaciones = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Lista de peliculas escogidas");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cmdEliminar.setText("eliminar");
        cmdEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdEliminarMouseClicked(evt);
            }
        });
        add(cmdEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        lstPelisFavoritas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstPelisFavoritas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPelisFavoritasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstPelisFavoritas);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 260, -1));

        tblRecomendaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblRecomendaciones);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 230));

        cmdRecomendaciones.setText("ver recomendaciones");
        cmdRecomendaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdRecomendacionesMouseClicked(evt);
            }
        });
        cmdRecomendaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRecomendacionesActionPerformed(evt);
            }
        });
        add(cmdRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void lstPelisFavoritasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPelisFavoritasMouseClicked
        // TODO add your handling code here:
        this.selectedMovie = (String) this.lstPelisFavoritas.getSelectedValue();
    }//GEN-LAST:event_lstPelisFavoritasMouseClicked

    private void cmdRecomendacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRecomendacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdRecomendacionesActionPerformed

    private void cmdRecomendacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdRecomendacionesMouseClicked
        // TODO add your handling code here:
        try{
            this.listadoRecomendaciones = movieDao.recomendaciones(selectedMovie);
            Utilities.fillTable(Movie.class, this.listadoRecomendaciones, tblRecomendaciones);
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(jLabel1, ex.getMessage());
        }
    }//GEN-LAST:event_cmdRecomendacionesMouseClicked

    private void cmdEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEliminarMouseClicked
        // TODO add your handling code here:
        try{
            bean.removeMovie(selectedMovie);
            loadList();
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(jLabel1, ex.getMessage());            
        }
    }//GEN-LAST:event_cmdEliminarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdRecomendaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstPelisFavoritas;
    private javax.swing.JTable tblRecomendaciones;
    // End of variables declaration//GEN-END:variables
}
