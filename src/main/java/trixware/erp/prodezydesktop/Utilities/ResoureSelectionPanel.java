/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.Utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Rajesh
 */
public class ResoureSelectionPanel extends javax.swing.JPanel {

int resourceid;
    String resourcename ;
    public boolean isSelected ;
    
    /**
     * Creates new form RMQtys
     */
    public ResoureSelectionPanel ( ) {
        initComponents (); 
        jLabel2.addMouseListener ( click );
        jCheckBox1.addMouseListener ( click1 );
    }
    
   
   

    public int getDeptId () {             
        return this.resourceid ;
    }

    public void setDeptId ( int _deptid ) {    
        this.resourceid = _deptid ;      
         jLabel1.setText( resourceid+"");
    }

    
    
    public boolean getSelection () {
        return isSelected ;
    }

    public void setSelection (  boolean _selection) {       
       this.isSelected = _selection ;
       jCheckBox1.setSelected ( isSelected );
    }
    
    
    public void setDepartmentName(  String _dname  ){
        resourcename = _dname ;
        jLabel2.setText( resourcename);
    }
    
    public String getDepartmentName(  ){
        return resourcename ;
    }
    
    MouseListener click = new  MouseListener(){
    @Override
    public void mouseClicked ( MouseEvent e ) {
        if( jCheckBox1.isSelected () ){
                jCheckBox1.setSelected ( false );
                isSelected = false ;
            }else{
                jCheckBox1.setSelected ( true );
                isSelected = true ;
            }
    }

    @Override
    public void mousePressed ( MouseEvent e ) {
    }

    @Override
    public void mouseReleased ( MouseEvent e ) {
    }

    @Override
    public void mouseEntered ( MouseEvent e ) {
    }

    @Override
    public void mouseExited ( MouseEvent e ) {
    }
    
    };
    
    MouseListener click1 = new  MouseListener(){
    @Override
    public void mouseClicked ( MouseEvent e ) {
        if( jCheckBox1.isSelected () ){
                isSelected = true ;
            }else{
                isSelected = false ;
            }
    }

    @Override
    public void mousePressed ( MouseEvent e ) {
    }

    @Override
    public void mouseReleased ( MouseEvent e ) {
    }

    @Override
    public void mouseEntered ( MouseEvent e ) {
    }

    @Override
    public void mouseExited ( MouseEvent e ) {
    }
    
    };
    
    
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
        jCheckBox1 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(220, 20));
        setLayout(null);
        add(jLabel1);
        jLabel1.setBounds(0, 0, 0, 0);
        add(jLabel2);
        jLabel2.setBounds(0, 0, 180, 20);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        add(jCheckBox1);
        jCheckBox1.setBounds(181, 2, 20, 15);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
