/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.components;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Rajesh
 */
public class ButtonForm extends javax.swing.JPanel {

    boolean mandatory = false; 
    boolean showError = false ;
    String labelValue, textValue, errorValue ;
    boolean selectedOrNot = false ;
    
    /**
     * Creates new form TextInputForm
     */
    public ButtonForm () {
        initComponents ();
        //jCheckBox1.addFocusListener ( valueListener );
        jLabel3.setVisible ( false );
        jButton1.setText("Default Action");
    }

    public ButtonForm (String labelVal,   String errorString) {
        initComponents ();
       
        errorValue = errorString ;
        jLabel3.setToolTipText ( errorValue );
        jLabel3.setVisible ( false );
        jButton1.setText(labelVal);
    }
    
    
    public void AddActionListener( ActionListener abc ){
        
        jButton1.addActionListener ( abc );
        jButton1.revalidate ();
        jButton1.repaint ();
    }
    
    public void setEnabled(boolean value)
    {
        jButton1.setEnabled(value);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(260, 50));
        setLayout(null);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel3);
        jLabel3.setBounds(0, 35, 260, 15);

        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(250, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(250, 30));
        jButton1.setOpaque(false);
        add(jButton1);
        jButton1.setBounds(10, 7, 250, 30);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}