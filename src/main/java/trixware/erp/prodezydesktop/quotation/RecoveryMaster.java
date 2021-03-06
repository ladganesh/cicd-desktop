/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.quotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import trixware.erp.prodezydesktop.Model.StaticValues;
import trixware.erp.prodezydesktop.web_services.WebAPITester;

/**
 *
 * @author Akshay
 */
public class RecoveryMaster extends javax.swing.JPanel {

    /**
     * Creates new form RecoveryMaster
     */
    Vector<Vector<Object>> data = null;
    Vector<String> columnNames = null;
    Vector<String> columnNames2 = null;   
    ArrayList<RejectionDR> rejModel=null;

    public RecoveryMaster() {
        initComponents();
        loadTable();
    }

    public void loadTable()
    {
        rejModel=new ArrayList<RejectionDR>();
        data =new Vector<Vector<Object>>();
        columnNames=new Vector<String>();
        columnNames2=new Vector<String>();
        
        columnNames.add("quan_from");
        columnNames2.add("From");
        columnNames.add("quan_to");
        columnNames2.add("To");
        columnNames.add("recovery");
        columnNames2.add("Recovery %");

        String addEmpAPICall="recoverys";
        String result2 = WebAPITester.prepareWebCall ( addEmpAPICall, StaticValues.getHeader ()   , "");
        System.err.println(result2);
        if( !  result2.contains(  "not found"   )  ){
            if ( result2!= null ) 
            {
                String recovId="";
                String from="";
                String to="";
                String recoveryPer="";

                HashMap<String, Object> map=new HashMap<String, Object>();
                JSONObject jObject = new JSONObject( result2 );
                Iterator<?> keys =jObject.keys();
                
                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    Object value = jObject.get ( key ) ; 
                    map.put(key, value);
                }
    
                JSONObject st =  (JSONObject) map.get ( "data" );
                JSONArray records = st.getJSONArray ( "records");

                JSONObject enq = null;
                
                for( int i=0; i<records.length (); i++ )
                {
                    Vector<Object> vector=new Vector<Object>();
                    enq = records.getJSONObject ( i);
                    recovId=enq.get("rej_recov_id").toString();
                    from=enq.get("quan_from").toString();
                    to=enq.get ( "quan_to" ).toString();
                    recoveryPer=enq.get ( "recovery" ).toString();
                    
                    vector.add(from);
                    vector.add(to);
                    vector.add(recoveryPer);  

                    data.add(vector);
                    rejModel.add(new RejectionDR(Integer.parseInt(recovId),Integer.parseInt(from),Integer.parseInt(to),Double.parseDouble(recoveryPer)));

                }
                }                
                jTable1.setModel ( new DefaultTableModel ( data , columnNames2 ) );
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jLabel1.setText("<html>Rejection<br>Recovery %</html>");
        add(jLabel1);
        jLabel1.setBounds(600, 50, 80, 30);

        jLabel2.setText("<html>Quantity<br>From</html>");
        add(jLabel2);
        jLabel2.setBounds(100, 50, 80, 40);

        jLabel3.setText("<html>Quantity<br>To</html>");
        add(jLabel3);
        jLabel3.setBounds(350, 50, 80, 30);
        add(jTextField1);
        jTextField1.setBounds(700, 50, 90, 30);
        add(jTextField2);
        jTextField2.setBounds(190, 50, 90, 30);
        add(jTextField3);
        jTextField3.setBounds(450, 50, 100, 30);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(300, 103, 90, 30);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(70, 140, 730, 220);

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(420, 103, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String from=jTextField2.getText();
        String to=jTextField3.getText();
        String recovery=jTextField1.getText();

        int fromInt=Integer.parseInt(jTextField2.getText());
        int toInt=Integer.parseInt(jTextField3.getText());

        boolean chk=true;
        boolean chk1=true;
        for(RejectionDR rej:rejModel)
        {
//            if (lowerBound <= value && value <= upperBound)
            if(rej.getFrom()<=fromInt && fromInt<=rej.getTo())
            {
                chk=false;
            }else{
                chk=true;
            }
            if(chk)
            {
                if(rej.getFrom()<=toInt && toInt<=rej.getTo())
                {
                    chk=false;
                }else{
                    chk=true;
                }
            }

            if(fromInt<=rej.getFrom() && rej.getFrom()<=toInt)
            {
                chk1=false;
            }else{
                chk1=true;
            }
            if (fromInt <= rej.getTo() && rej.getTo() <= toInt) {
                chk1 = false;
            } else {
                chk1 = true;
            }    
        }

        if(chk && chk1)
        {
            if(fromInt<toInt)
            {
                String addEmpAPICall="recoverys_add?quan_from="+from+"&quan_to="+to+"&recovery="+recovery;
                String result2 = WebAPITester.prepareWebCall ( addEmpAPICall, StaticValues.getHeader ()   , "");
                JOptionPane.showMessageDialog(null, "" + result2);

            }else{
                JOptionPane.showMessageDialog(null, "" + "Please enter correct quantity range!");
            }            
        }else{
            JOptionPane.showMessageDialog(null, "" + "Quantity Range Already exists!");
        }
        loadTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index=jTable1.getSelectedRow();     

        jTextField1.setText(rejModel.get(index).getRecoveryPercent()+"");
        jTextField2.setText(rejModel.get(index).getFrom()+"");
        jTextField3.setText(rejModel.get(index).getTo()+"");

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = jTable1.getSelectedRow();
        int id = rejModel.get(index).getId();
        String from = jTextField2.getText();
        String to = jTextField3.getText();
        String recovery = jTextField1.getText();

        int fromInt = Integer.parseInt(jTextField2.getText());
        int toInt = Integer.parseInt(jTextField3.getText());

        boolean chk = true;
        boolean chk1 = true;
        for (RejectionDR rej : rejModel) {
//            if (lowerBound <= value && value <= upperBound)
            if (id != rej.getId()) {
                if (rej.getFrom() <= fromInt && fromInt <= rej.getTo()) {
                    chk = false;
                } else {
                    chk = true;
                }
                if (chk) {
                    if (rej.getFrom() <= toInt && toInt <= rej.getTo()) {
                        chk = false;
                    } else {
                        chk = true;
                    }
                }

                if (fromInt <= rej.getFrom() && rej.getFrom() <= toInt) {
                    chk1 = false;
                } else {
                    chk1 = true;
                }
                if (fromInt <= rej.getTo() && rej.getTo() <= toInt) {
                    chk1 = false;
                } else {
                    chk1 = true;
                }
            }
        }

        if(chk && chk1)
        {
            if(fromInt<toInt)
            {
                String addEmpAPICall="recoverys_update?rej_recov_id="+id+"&quan_from="+from+"&quan_to="+to+"&recovery="+recovery;
                String result2 = WebAPITester.prepareWebCall ( addEmpAPICall, StaticValues.getHeader ()   , "");
                JOptionPane.showMessageDialog(null, "" + result2);
            }else{
                JOptionPane.showMessageDialog(null, "" + "Please enter correct quantity range!");
            }            
        }else{
            JOptionPane.showMessageDialog(null, "" + "Quantity Range Already exists!");
        }
        loadTable();        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
