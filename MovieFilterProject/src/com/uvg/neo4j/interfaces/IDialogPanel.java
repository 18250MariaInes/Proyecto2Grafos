/*
 * Maria Ines Vásquez Figueroa - 18250
 * Andrea Abril Palencia Gutierrez - 18198
 * Paula Camila Gonzalez Ortega - 18398
 * Estructura de Datos - Seccion 10 - Tercer Semestre
 */
package com.uvg.neo4j.interfaces;

import javax.swing.JDialog;

/**
 *interfaz que deben implementar todos los paneles
 * @author mariaines.camila.abril
 */
public interface IDialogPanel {
 
    public void setParent(JDialog dialog);
    public JDialog getParent();
}
