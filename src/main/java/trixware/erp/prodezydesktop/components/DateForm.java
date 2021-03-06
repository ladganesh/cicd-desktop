/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Rajesh
 */
public class DateForm extends javax.swing.JPanel {

    boolean mandatory = false; 
    boolean showError = false ;
    String labelValue, textValue, errorValue ;
    JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
    
    /**
     * Creates new form TextInputForm
     */
    public DateForm () {
        initComponents ();
      //  jTextField1.addFocusListener ( valueListener );
        jLabel3.setVisible ( false );
        
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        timeSpinner.setBounds ( 10,17, 100, 30   );
        timeSpinner.setOpaque ( false );
        add (  timeSpinner );
    }

    public DateForm (String labelVal,  boolean isMandatory, String errorString) {
        initComponents ();
        mandatory = isMandatory ;
        jLabel2.setVisible ( mandatory );
        errorValue = errorString ;
        jLabel3.setToolTipText ( errorValue );
        jLabel1.setText( labelVal  ) ;
      //  jTextField1.addFocusListener ( valueListener );
        jLabel3.setVisible ( false );
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        timeSpinner.setBounds ( 10,17, 100, 30   );
        add (  timeSpinner );
    }
    
    public String getText(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        try {
            Date dt = (Date)sdf.parse( timeSpinner.getValue ().toString () ) ;
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
            return tf.format ( dt );
        } catch ( ParseException ex ) {
            return null ;
        }
        
                
        
    }
   

    FocusListener valueListener = new FocusListener () {
        @Override
        public void focusGained ( FocusEvent e ) {
            JTextField jcb = ( JTextField ) e.getSource ();
            if( ! jcb.getText ().equals ( "")){
                jcb.selectAll ();
            }
            showError  = false ;
                    jLabel3.setText ("" );
                    jLabel3.setVisible ( false );
        }

        @Override
        public void focusLost ( FocusEvent e ) {
            JTextField jcb = ( JTextField ) e.getSource ();
            if( mandatory ){
                if(  jcb.getText ().equals ( "" )){
                    showError  = true ;
                    jLabel3.setText ( errorValue );
                    jLabel3.setVisible ( true );
                }else{
                    showError  = false ;
                    jLabel3.setText ( "" );
                    jLabel3.setVisible ( false );
                }
            }
        }
    };
    
    
    //public String getText(){
    //    return jTextField1.getText ().trim () ;
    //}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jSpinner1 = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(260, 64));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel1.setText("Label Name");
        add(jLabel1);
        jLabel1.setBounds(14, 0, 230, 16);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("*");
        add(jLabel2);
        jLabel2.setBounds(0, 0, 10, 19);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel3);
        jLabel3.setBounds(0, 50, 260, 15);

        dateChooserCombo1.setCalendarBackground(new java.awt.Color(255, 255, 255));
        dateChooserCombo1.setFormat(2);
        dateChooserCombo1.setOpaque (false);
        add(dateChooserCombo1);
        dateChooserCombo1.setBounds(120, 17, 140, 30);

        jSpinner1.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        jSpinner1.setEditor(new javax.swing.JSpinner.DateEditor(jSpinner1, "HH:mm:s"));
        add(jSpinner1);
        jSpinner1.setBounds(60, 0, 90, 26);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
