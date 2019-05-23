/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvg.neo4j.interfaces;

import javax.swing.JDialog;

/**
 *
 * @author cesar
 */
public interface IDialogPanel {
 
    public void setParent(JDialog dialog);
    public JDialog getParent();
}
