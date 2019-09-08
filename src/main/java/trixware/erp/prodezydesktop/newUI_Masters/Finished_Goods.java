/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.newUI_Masters;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import trixware.erp.prodezydesktop.components.SpinnerForm;
import trixware.erp.prodezydesktop.components.TextInputForm;

/**
 *
 * @author Rajesh
 */
public class Finished_Goods extends javax.swing.JPanel {
    
        TextInputForm field1, field2, field3, field4, field5, field6; 
    
    /**
     * Creates new form Finished_Goods
     */
    public Finished_Goods () {
        initComponents ();
        
         field1 = new TextInputForm ( "FG name" , true , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field1 );

         field2 = new TextInputForm ( "FG Unique Code" , true , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field2 );

         field3 = new TextInputForm ( "FG Code" , false , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field3 );

         field4 = new TextInputForm ( "FG Sales Rate" , true , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field4 );

         field5 = new TextInputForm ( "FG Measure" , false , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field5 );

         field6 = new TextInputForm ( "FG BOM" , true , "Finished Goods Name is Mandatory Field. Please Enter an Appropriate Value" );
        add ( field6 );
        
        ArrayList<String> DBTablesDesc = new ArrayList<String>(  Arrays.asList(  "BATCH_DETAILS",  "BD_ID"      , "BM_ID"   , "RM_ID"   , "RM_QTY"   , "RM_UOM" )   );	            

        SpinnerForm spinner1 = new SpinnerForm( "Machine", true,  "Machine selection is required. Please select a machine",  DBTablesDesc);
        add( spinner1 );

        ArrayList<String> DBTablesDesc1 = new ArrayList<String>(  Arrays.asList(  "sdfsdfsdf",  "BDsdfsdfsdf_ID"      , "sdfsdfsdf"   , "sdfsdfsdf"   , "RM_QTY"   , "RM_UOM" )   );	            

        SpinnerForm spinner2 = new SpinnerForm( "Machine", true,  "Machine selection is required. Please select a machine",  DBTablesDesc1);
        add( spinner2 );
        
        JButton submit = new JButton();
        add(submit);
        
        setLayout ( new GridLayout (5, 2));
        
        submit.addActionListener ( submitAction );
    }

    ActionListener submitAction = new ActionListener () {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                showDetails ();
            }
        };
    
    public void showDetails(){
        
        String one, two, three, four , five, six ;
        one = field1.getText ();
        two = field2.getText ();
        three = field3.getText ();
        four = field4.getText ();
        five = field5.getText ();
        six = field6.getText ();
        
        JOptionPane.showMessageDialog ( null, one+" "+two+" " +three+" "+four+" "+five+" "+six);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(530, 350));
        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
