/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.screens;

import com.uvg.neo4j.beans.Actor;
import com.uvg.neo4j.beans.Movie;
import com.uvg.neo4j.dao.ActorDao;
import com.uvg.neo4j.dao.DaoMapper;
import com.uvg.neo4j.dao.MovieDao;
import com.uvg.neo4j.models.SearchPanel;
import com.uvg.neo4j.models.TableListBeanModel;
import com.uvg.neo4j.utils.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author mariaines.camila.abril
 */
public class MoviePanel extends javax.swing.JPanel {

    
        private ApplicationContext ctx = null;
    private JDialog parent = null;
    private Actor actorSeleccionado = null;
    private String generoSeleccionado = "Todos";
    private Log log = LogFactory.getLog(MoviePanel.class);
    private List<Movie> listadoPelis = null;
    private List<Actor> listadoActoresXPeli = null;
    private Movie peliculaSeleccionada = null;
    private DaoMapper daoMapper = null;
    private ActorDao daoActor = null;
    private MovieDao daoPeli = null;
    //Buscar peliculas de un genero
    private String queryMoviePerGenre= "MATCH (n:Movie) WHERE n.genre = ? RETURN id(n), n.genre, n.released, n.tagline, n.title ORDER BY n.title";
    //--donde name es el genero de interes
    //--retorna todas las peliculas de un genero

    //Buscar películas de un actor específico
    private String queryMoviePerActor = "MATCH (d:Person {name:?})-[:ACTED_IN]->(n:Movie) RETURN id(n), n.genre, n.released, n.tagline, n.title ORDER BY n.title";
    //--donde name es el nombre del actor de interes
    //--retorna todas las peliculas donde sale el actor de interes

    //Buscar peliculas de un actor y genero específico
    private String queryMoviePerGenreActor = "MATCH (d:Person {name:?})-[:ACTED_IN]->(n:Movie {genre:?}) RETURN id(n), n.genre, n.released, n.tagline, n.title ORDER BY n.title";
    //--donde name es el nombe de un actor especifico y genero es un genero especifico
    //--retorna todas las peliculas donde aparece un actor deseado de un genero deseado
    
    private String actorPerMovie = "MATCH (d:Movie {title:?})<-[:ACTED_IN]-(n:Person) RETURN n.name, id(n), n.born";
    
    /**
     * Creates new form MoviePanel
     * @param ctx
     */
    public MoviePanel( ApplicationContext ctx) {
        initComponents();
        this.ctx = ctx;
        this.tblPeliculas.setRowSelectionAllowed(true);
        this.tblPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        daoMapper = this.ctx.getBean("DaoMapper", DaoMapper.class);            
        daoActor =  this.ctx.getBean("ActorDao", ActorDao.class);            
        daoPeli = this.ctx.getBean("MovieDao", MovieDao.class);
    }
    
    /**
     *
     */
    public void queryMovies() {
        try{        
            
            if (actorSeleccionado != null){
                if (generoSeleccionado != null && !generoSeleccionado.equals("Todos")){
                    this.listadoPelis = daoMapper.queryData(Movie.class
                            , queryMoviePerGenreActor, this.actorSeleccionado.getName(), this.generoSeleccionado);
                }else{
                    this.listadoPelis = daoMapper.queryData(Movie.class
                            , this.queryMoviePerActor, this.actorSeleccionado.getName());
                }
            }else{
                if (generoSeleccionado != null && !generoSeleccionado.equals("Todos")){
                    this.listadoPelis = daoMapper.queryData(Movie.class, this.queryMoviePerGenre, this.generoSeleccionado);
                }
            }
            if (this.listadoPelis == null){
                this.listadoPelis = new ArrayList<Movie>();
            }
            TableListBeanModel<Movie> tmodel = new TableListBeanModel<Movie>(listadoPelis, Movie.class);
            this.tblPeliculas.setModel(tmodel);
            
        }catch(Exception ex){
            log.error("ERROR", ex);
            Utilities.showErrorMessage(this, ex.getMessage());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblActorSeleccionado = new javax.swing.JLabel();
        cmdBuscarActor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstGeneros = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        cmdBuscarPelis = new javax.swing.JButton();
        cmdLimpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        cmdCargarFavoritos = new javax.swing.JButton();
        cmdVerFavoritos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JTextField();
        cmdPeliculaNueva = new javax.swing.JButton();
        txtTagline = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        lblPeliculaId = new javax.swing.JLabel();
        cmdAceptar = new javax.swing.JButton();
        cmdEliminarPelicula = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblActoresPelicula = new javax.swing.JTable();
        cmdSeleccionarActor = new javax.swing.JButton();
        txtActorNombre = new javax.swing.JTextField();
        cmdAsociarActorNuevo = new javax.swing.JButton();
        txtAnioNacimiento = new javax.swing.JTextField();
        lblActorSeleccionadoEdicion = new javax.swing.JLabel();
        cmdEliminarDePeli = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("filtro"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblActorSeleccionado.setBorder(javax.swing.BorderFactory.createTitledBorder("Actor seleccionado"));
        jPanel3.add(lblActorSeleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 50));

        cmdBuscarActor.setText("buscar");
        cmdBuscarActor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdBuscarActorMouseClicked(evt);
            }
        });
        cmdBuscarActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarActorActionPerformed(evt);
            }
        });
        jPanel3.add(cmdBuscarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        lstGeneros.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Todos", "Accion", "Aventura", "Biografico", "Ciencia ficcion", "Cine negro", "Comedia", "Crimen", "Drama", "Familiar", "Guerra", "Historico", "Intriga", "Melodrama", "Musical", "Romance", "Serie", "Terror", "Tragicomedia", "Western" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstGeneros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstGenerosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstGeneros);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 120, -1));

        jLabel4.setText("Genero");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        cmdBuscarPelis.setText("buscar");
        cmdBuscarPelis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdBuscarPelisMouseClicked(evt);
            }
        });
        cmdBuscarPelis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarPelisActionPerformed(evt);
            }
        });
        jPanel3.add(cmdBuscarPelis, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, -1, -1));

        cmdLimpiar.setText("limpiar");
        cmdLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdLimpiarMouseClicked(evt);
            }
        });
        cmdLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(cmdLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 70, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 840, 170));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Peliculas encontradas"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 820, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Peliculas encontradas"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel1.setText("Peliculas encontradas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeliculasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeliculas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 830, 230));

        cmdCargarFavoritos.setText("Cargar favoritos");
        cmdCargarFavoritos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdCargarFavoritosMouseClicked(evt);
            }
        });
        cmdCargarFavoritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCargarFavoritosActionPerformed(evt);
            }
        });
        jPanel1.add(cmdCargarFavoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        cmdVerFavoritos.setText("ver favoritos");
        cmdVerFavoritos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdVerFavoritosMouseClicked(evt);
            }
        });
        jPanel1.add(cmdVerFavoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jTabbedPane1.addTab("busqueda", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTitulo.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulo"));
        jPanel2.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 150, -1));

        cmdPeliculaNueva.setText("Nueva");
        cmdPeliculaNueva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdPeliculaNuevaMouseClicked(evt);
            }
        });
        cmdPeliculaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPeliculaNuevaActionPerformed(evt);
            }
        });
        jPanel2.add(cmdPeliculaNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtTagline.setBorder(javax.swing.BorderFactory.createTitledBorder("Tagline"));
        jPanel2.add(txtTagline, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 350, -1));

        txtAnio.setBorder(javax.swing.BorderFactory.createTitledBorder("Anio"));
        jPanel2.add(txtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 90, -1));

        lblPeliculaId.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));
        jPanel2.add(lblPeliculaId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 40));

        cmdAceptar.setText("Aceptar");
        cmdAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdAceptarMouseClicked(evt);
            }
        });
        cmdAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAceptarActionPerformed(evt);
            }
        });
        jPanel2.add(cmdAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        cmdEliminarPelicula.setText("Eliminar");
        cmdEliminarPelicula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdEliminarPeliculaMouseClicked(evt);
            }
        });
        jPanel2.add(cmdEliminarPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        tblActoresPelicula.setModel(new javax.swing.table.DefaultTableModel(
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
        tblActoresPelicula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActoresPeliculaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblActoresPelicula);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 280));

        cmdSeleccionarActor.setText("Seleccionar");
        cmdSeleccionarActor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSeleccionarActorMouseClicked(evt);
            }
        });
        cmdSeleccionarActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSeleccionarActorActionPerformed(evt);
            }
        });
        jPanel2.add(cmdSeleccionarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        txtActorNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre"));
        txtActorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActorNombreActionPerformed(evt);
            }
        });
        jPanel2.add(txtActorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 140, -1));

        cmdAsociarActorNuevo.setText("Agregar nuevo");
        cmdAsociarActorNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdAsociarActorNuevoMouseClicked(evt);
            }
        });
        jPanel2.add(cmdAsociarActorNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        txtAnioNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder("anio nacimiento"));
        jPanel2.add(txtAnioNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 110, -1));

        lblActorSeleccionadoEdicion.setBorder(javax.swing.BorderFactory.createTitledBorder("Actor seleccionado"));
        jPanel2.add(lblActorSeleccionadoEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 260, 40));

        cmdEliminarDePeli.setText("Eliminar");
        cmdEliminarDePeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdEliminarDePeliMouseClicked(evt);
            }
        });
        jPanel2.add(cmdEliminarDePeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, -1, -1));

        jLabel2.setText("Actores");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jTabbedPane1.addTab("mantenimiento", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 870, 510));
    }// </editor-fold>//GEN-END:initComponents

    private void txtActorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActorNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActorNombreActionPerformed

    private void cmdBuscarActorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdBuscarActorMouseClicked
        SearchPanel<Actor> searchActor = new SearchPanel<Actor>(ctx
                ,"MATCH (n:Person) RETURN id(n), n.born, n.name ORDER BY n.name", Actor.class);
        Utilities.loadDialog(parent, "seleccion de actores", searchActor );
        this.actorSeleccionado = searchActor.getSeleccionado();
        if (this.actorSeleccionado != null){
           this.lblActorSeleccionado.setText(this.actorSeleccionado.toString());
        }
    }//GEN-LAST:event_cmdBuscarActorMouseClicked

    private void lstGenerosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstGenerosMouseClicked
        // TODO add your handling code here:
        this.generoSeleccionado = (String) this.lstGeneros.getSelectedValue();
    }//GEN-LAST:event_lstGenerosMouseClicked

    private void cmdLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdLimpiarActionPerformed

    private void cmdLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdLimpiarMouseClicked
        // TODO add your handling code here:
        this.lblActorSeleccionado.setText(null);
        this.actorSeleccionado = null;
    }//GEN-LAST:event_cmdLimpiarMouseClicked

    private void cmdCargarFavoritosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCargarFavoritosMouseClicked
        int row = this.tblPeliculas.getSelectedRow();
        Movie peli= this.listadoPelis.get(row);        
        try{
            Utilities.loadDialog(parent, "Pelis favoritas", new MyMovieListPanel(ctx, peli.getTitle()));        
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }//GEN-LAST:event_cmdCargarFavoritosMouseClicked

    private void cmdBuscarActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdBuscarActorActionPerformed

    private void cmdBuscarPelisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdBuscarPelisMouseClicked
        // TODO add your handling code here:
        this.cmdBuscarPelis.setEnabled(false);
        queryMovies();
        this.cmdBuscarPelis.setEnabled(true);
    }//GEN-LAST:event_cmdBuscarPelisMouseClicked

    private void setearPeliSeleccionada(){
        int fila = this.tblPeliculas.getSelectedRow();
        this.peliculaSeleccionada = this.listadoPelis.get(fila);
        this.lblPeliculaId.setText(this.peliculaSeleccionada.getId());
        this.txtTitulo.setText(this.peliculaSeleccionada.getTitle());
        this.txtTagline.setText(this.peliculaSeleccionada.getTagline());
        this.txtAnio.setText(this.peliculaSeleccionada.getReleased());
        try{
            //obtener todos los actores relacionados.
            this.listadoActoresXPeli = this.daoMapper.queryData(Actor.class, this.actorPerMovie, this.peliculaSeleccionada.getTitle());
            Utilities.fillTable(Actor.class, listadoActoresXPeli, tblActoresPelicula);
        }catch(Exception ex){
            log.error("ERROR", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }
    
    private void tblPeliculasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeliculasMouseClicked
        // TODO add your handling code here:
        setearPeliSeleccionada();
    }//GEN-LAST:event_tblPeliculasMouseClicked

    private void cmdBuscarPelisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarPelisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdBuscarPelisActionPerformed

    private void cmdPeliculaNuevaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPeliculaNuevaMouseClicked
        // TODO add your handling code here:
        this.peliculaSeleccionada = null;
        this.lblPeliculaId.setText(null);
        this.txtTitulo.setText(null);
        this.txtTagline.setText(null);
        this.txtAnio.setText(null);
        
        this.listadoActoresXPeli = new ArrayList<Actor>();
        Utilities.fillTable(Actor.class, this.listadoActoresXPeli, tblActoresPelicula);
    }//GEN-LAST:event_cmdPeliculaNuevaMouseClicked

    private void cmdAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAceptarMouseClicked
        // TODO add your handling code here:        
        try {
            if (this.peliculaSeleccionada == null) {   //ingresar
                this.peliculaSeleccionada = new Movie();
                this.peliculaSeleccionada.setGenre(this.generoSeleccionado);
                this.peliculaSeleccionada.setTitle(this.txtTitulo.getText());
                this.peliculaSeleccionada.setTagline(this.txtTagline.getText());
                this.peliculaSeleccionada.setReleased(this.txtAnio.getText());
                
                daoPeli.agregar(peliculaSeleccionada);                
                this.lblPeliculaId.setText(peliculaSeleccionada.getId());
            } else {   //actualizar
                this.peliculaSeleccionada.setGenre(this.generoSeleccionado);
                this.peliculaSeleccionada.setTitle(this.txtTitulo.getText());
                this.peliculaSeleccionada.setTagline(this.txtTagline.getText());                
                daoPeli.actualizar(peliculaSeleccionada);
            }
            
        } catch (Exception ex) {
            log.error("ERROR", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }//GEN-LAST:event_cmdAceptarMouseClicked

    private void cmdSeleccionarActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSeleccionarActorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSeleccionarActorActionPerformed

    
    
    private void cmdSeleccionarActorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSeleccionarActorMouseClicked
        // TODO add your handling code here:
        SearchPanel<Actor> searchActor = new SearchPanel<Actor>(ctx
                ,"MATCH (n:Person) RETURN id(n), n.born, n.name ORDER BY n.name", Actor.class);
        Utilities.loadDialog(parent, "seleccion de actores", searchActor );
        this.actorSeleccionado = searchActor.getSeleccionado();
        try {
            if (this.actorSeleccionado != null) {
                this.lblActorSeleccionadoEdicion.setText(this.actorSeleccionado.toString());
                //aplicar vinculo.
                daoActor.asociarAPeli(this.actorSeleccionado, this.peliculaSeleccionada);
                setearPeliSeleccionada();
            }
        } catch (Exception ex) {
            log.error("error", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }//GEN-LAST:event_cmdSeleccionarActorMouseClicked

    private void cmdAsociarActorNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAsociarActorNuevoMouseClicked
        // TODO add your handling code here:
        //agregar actor.
        try{
            this.actorSeleccionado = new Actor();
            this.actorSeleccionado.setName(this.txtActorNombre.getText());
            this.actorSeleccionado.setBorn(this.txtAnioNacimiento.getText());
            this.daoActor.agregar(actorSeleccionado);
            this.lblActorSeleccionado.setText(this.actorSeleccionado.toString());
            daoActor.asociarAPeli(this.actorSeleccionado, this.peliculaSeleccionada);
            
            this.listadoActoresXPeli = this.daoMapper.queryData(Actor.class, this.actorPerMovie, this.peliculaSeleccionada.getTitle());
            Utilities.fillTable(Actor.class, listadoActoresXPeli, tblActoresPelicula);
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }//GEN-LAST:event_cmdAsociarActorNuevoMouseClicked

    private void cmdEliminarPeliculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEliminarPeliculaMouseClicked
        // TODO add your handling code here:
        //eliminar peli.
        try{
            daoPeli.eliminar(peliculaSeleccionada);
            this.peliculaSeleccionada = null;
            this.txtAnio.setText(null);
            this.txtTagline.setText(null);
            this.txtTitulo.setText(null);
            this.listadoActoresXPeli = new ArrayList<Actor>();
            Utilities.fillTable(Actor.class, this.listadoActoresXPeli, tblActoresPelicula);
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());
        }
    }//GEN-LAST:event_cmdEliminarPeliculaMouseClicked

    private void tblActoresPeliculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActoresPeliculaMouseClicked
        // TODO add your handling code here:
        int row= this.tblActoresPelicula.getSelectedRow();
        this.actorSeleccionado = this.listadoActoresXPeli.get(row);
        this.lblActorSeleccionadoEdicion.setText(this.actorSeleccionado.toString());
    }//GEN-LAST:event_tblActoresPeliculaMouseClicked

    private void cmdEliminarDePeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEliminarDePeliMouseClicked
        // TODO add your handling code here:
        try{
            daoActor.eliminarDePeli(actorSeleccionado, peliculaSeleccionada);
            
            this.listadoActoresXPeli = this.daoMapper.queryData(Actor.class, this.actorPerMovie, this.peliculaSeleccionada.getTitle());
            Utilities.fillTable(Actor.class, listadoActoresXPeli, tblActoresPelicula);            
        } catch (Exception ex) {
            Utilities.showErrorMessage(parent, ex.getMessage());
        }        
    }//GEN-LAST:event_cmdEliminarDePeliMouseClicked

    private void cmdPeliculaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPeliculaNuevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdPeliculaNuevaActionPerformed

    private void cmdAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdAceptarActionPerformed

    private void cmdCargarFavoritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCargarFavoritosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdCargarFavoritosActionPerformed

    private void cmdVerFavoritosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdVerFavoritosMouseClicked
        // TODO add your handling code here:
        try{
            Utilities.loadDialog(parent, "Pelis favoritas", new MyMovieListPanel(ctx));                
        }catch(Exception ex){
            log.error("error", ex);
            Utilities.showErrorMessage(parent, ex.getMessage());            
        }
    }//GEN-LAST:event_cmdVerFavoritosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAceptar;
    private javax.swing.JButton cmdAsociarActorNuevo;
    private javax.swing.JButton cmdBuscarActor;
    private javax.swing.JButton cmdBuscarPelis;
    private javax.swing.JButton cmdCargarFavoritos;
    private javax.swing.JButton cmdEliminarDePeli;
    private javax.swing.JButton cmdEliminarPelicula;
    private javax.swing.JButton cmdLimpiar;
    private javax.swing.JButton cmdPeliculaNueva;
    private javax.swing.JButton cmdSeleccionarActor;
    private javax.swing.JButton cmdVerFavoritos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblActorSeleccionado;
    private javax.swing.JLabel lblActorSeleccionadoEdicion;
    private javax.swing.JLabel lblPeliculaId;
    private javax.swing.JList<String> lstGeneros;
    private javax.swing.JTable tblActoresPelicula;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTextField txtActorNombre;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnioNacimiento;
    private javax.swing.JTextField txtTagline;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
