/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Rajesh
 */
public class TextFeildinGrid extends javax.swing.JPanel {

    boolean mandatory = false; 
    boolean showError = false ;
    String labelValue, textValue, errorValue ;
    
    
    /**
     * Creates new form TextInputForm
     */
    public TextFeildinGrid () {
        initComponents ();
        jTextField1.addFocusListener ( valueListener );
        jLabel3.setVisible ( false );
    }

    public TextFeildinGrid (String labelVal,  boolean isMandatory, String errorString) {
        initComponents ();
        mandatory = isMandatory ;
        //jLabel2.setVisible ( mandatory );
        errorValue = errorString ;
        jLabel3.setToolTipText ( errorValue );
        //jLabel1.setText( labelVal  ) ;
        jTextField1.addFocusListener ( valueListener );
        jLabel3.setVisible ( false );
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
    
    
    public String getText(){
        return jTextField1.getText ().trim () ;
    }
    
    public void setText(String value){
        jTextField1.setText( value );
        jTextField1.revalidate ();
        jTextField1.repaint ();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(260, 64));
        setLayout(null);
        add(jTextField1);
        jTextField1.setBounds(10, 17, 250, 30);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel3);
        jLabel3.setBounds(0, 50, 260, 15);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
