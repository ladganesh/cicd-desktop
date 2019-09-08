/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.quotation;

import trixware.erp.prodezydesktop.Model.StaticValues;
import static trixware.erp.prodezydesktop.examples.HomeScreen.home;
import static trixware.erp.prodezydesktop.examples.HomeScreen.scroll;
import static trixware.erp.prodezydesktop.examples.HomeScreen.width;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.json.JSONObject;
import trixware.erp.prodezydesktop.web_services.WebAPITester;

/**
 *
 * @author Rajesh
 */
public class FeasibilityCheckForm extends javax.swing.JPanel {

    JPanel  abc = null ;
    ArrayList<String[]> conditions = new ArrayList<String[]>() ;
    ArrayList<FeasibilityPanel> feasbilityInputList = new ArrayList<FeasibilityPanel> () ;
    String selectionSeq = "";
    String remark, decisionStr = "Rejected";
    boolean decision = false ;
    List<String> list;
    
    /**
     * Creates new form FeasibilityCheckForm
     */
    public FeasibilityCheckForm () {
        initComponents ();
        
        abc = new JPanel() ;
        abc.setBounds( 0,0,1120, 500  ) ;
        abc.setLayout(null) ;
        abc.setBackground( Color.WHITE );
        
        abc.setLayout ( new BoxLayout ( abc , BoxLayout.Y_AXIS ) );
        
        jScrollPane2 = new JScrollPane( abc, 
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED   ) ;
        
        jScrollPane2.setBounds(  30, 30, 650, 450   ) ;
        add(jScrollPane2) ;
        
        String[] conditions = new String[] { "Customer Drawing / Details Obtained ? " ,
        "Customer Sample Obtained ? " ,
        "Customer Quality Plan Obtained ? " ,
        "Can product to be manufactured to Tollerances specified on Drg ? " ,
        "Is statistical process controll required on product ? " ,
        "Is control plan approval required ? " ,
        "Sample aproval is required before production ? " ,
        "Any special packaging / dispatch instruction given ? " ,
        "If yes does it reflect on Drg or Instruction given ? " ,
        "Is machine capacity required adequate ? " ,
        "Any new insoection equippment needed ? " ,
        "Any additional inspection to be carried out ? " ,
        "Any representative sample is needed for inspection ? " ,
        "Any statutory or regulatory requirements to be fullfilled with respect to product ? " ,
        "Has customer informed about Product annual volume ? " ,
        "Is any FTG planning required ? ( Fictures, Tools, Gauges ) " 
        } ;
        
        for ( int i = 0 ; i < conditions.length ; i ++ ) {

            FeasibilityPanel p1 = new FeasibilityPanel( i+1, conditions[i] , false  ) ;
            p1.setBounds (  0 , 0 , 650, 60  ) ;
            abc.add(p1);
            feasbilityInputList.add( p1 );
        }
      
    }
    public void setList(List list)
    {
        this.list=list;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 550));
        setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        add(jScrollPane2);
        jScrollPane2.setBounds(740, 35, 350, 110);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rejected", "Accepted" }));
        add(jComboBox1);
        jComboBox1.setBounds(740, 200, 350, 30);

        jButton1.setText("Save & Next");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(980, 250, 110, 23);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel1.setText("Feasibility Checklist");
        add(jLabel1);
        jLabel1.setBounds(10, 0, 200, 30);

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel2.setText("Reamrks");
        add(jLabel2);
        jLabel2.setBounds(740, 0, 200, 30);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel3.setText("Enquiry Status");
        add(jLabel3);
        jLabel3.setBounds(740, 170, 200, 30);

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("*");
        add(jLabel28);
        jLabel28.setBounds(720, 10, 20, 20);

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("*");
        add(jLabel29);
        jLabel29.setBounds(730, 180, 10, 20);

        jButton2.setText("Back");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(840, 250, 120, 23);

        jButton3.setText("Home");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(730, 250, 100, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //  Capture data from fields
        // 1. List of all condition selected as true / checked
        selectionSeq="";
        for ( int i = 0 ; i < feasbilityInputList.size () ; i ++ ) {
            FeasibilityPanel p1 = feasbilityInputList.get ( i ) ;
            if(  p1.isSelected ()  ){
                if(selectionSeq.equals(""))
                {
                    selectionSeq=p1.getConditionInt ()+"";
                }else{
                    selectionSeq = selectionSeq +"-" +p1.getConditionInt ();
                }
            }
        }
        
        // 2. Remarks added by the user
        remark = jTextArea1.getText().trim() ;
        
        // 3. decision on the enquiry
        decisionStr = jComboBox1.getSelectedItem().toString() ;
        if( ! decisionStr.equals ( "Deferred" ) && ! decisionStr.equals ( "Rejected" ))
        {
            decision = true ;
        }
            
        
        if( selectionSeq.equals ( "")  &&  remark.equals ( "")  ||  decision==false ){
            JOptionPane.showMessageDialog(  null, "Please select necessary conditions, fill in remark and select a status as \"Accepted\" to proceed" );            
        }else{
        // #1 Entry in feasibility report of that enquiry against latest qotation added
        try {
            String enqDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String feasiStatus="";
            String itemDesc=list.get(0);
            String drwNo=list.get(1);
            String revNo=list.get(2);
            String revDate=list.get(3);
            String partType=list.get(4);
            String quantity=list.get(5);
            String handelBy=list.get(6);
            String customerId=list.get(7);
            String assemblyNo=list.get(9);
     //       String enqLatestVer=list.get(10);
            String enqNo=list.get(11);
            String refEmpId=list.get(12);

            int index=jComboBox1.getSelectedIndex();
            if(index==0)
            {
                feasiStatus="N";
            }else if(index==1)
            {
                feasiStatus="Y";
            }            

            String addEmpAPICall = "";
            String enquiryId="";
            addEmpAPICall = "enquiry_add?enq_no=" + URLEncoder.encode(enqNo,"UTF-8")+ "&enq_date=" + URLEncoder.encode(enqDate,"UTF-8") + "&enq_latest_version=" + revNo + "&customer=" + customerId + "&feasiblity_points=" + URLEncoder.encode(selectionSeq, "UTF-8") + "&feasibility_remark=" + jTextArea1.getText() + "&feasibility_status=" + feasiStatus + "&latest_rev_date=" + URLEncoder.encode(revDate,"UTF-8")+"&ref_emp_id="+refEmpId;
            String result2 = WebAPITester.prepareWebCall(addEmpAPICall, StaticValues.getHeader(), "");
            
            if (!result2.contains("not found")) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject jObject = new JSONObject(result2);
                Iterator<?> keys = jObject.keys();

                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Object value = jObject.get(key);
                    map.put(key, value);
                }

                JSONObject st = (JSONObject) map.get("data");
                enquiryId = st.get("insert_id").toString();
            }
            addEmpAPICall="assembly_add?assly_no="+URLEncoder.encode(assemblyNo,"UTF-8")+"&ref_enq_id="+enquiryId+"&assembly_desc="+URLEncoder.encode(itemDesc,"UTF-8")+"&quantity="+quantity+"&drwg_no="+URLEncoder.encode(drwNo,"UTF-8")+"&drwg_rev_no="+revNo+"&drwg_rev_date="+URLEncoder.encode(revDate,"UTF-8")+"&type=" + URLEncoder.encode(partType,"UTF-8");
            String result = WebAPITester.prepareWebCall(addEmpAPICall, StaticValues.getHeader(), "");
            System.err.println(result);
            String ass_id="";
            if (!result.contains("not found")) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject jObject = new JSONObject(result);
                Iterator<?> keys = jObject.keys();

                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Object value = jObject.get(key);
                    map.put(key, value);
                }

                JSONObject st = (JSONObject) map.get("data");
                ass_id = st.get("insert_id").toString();
            }

                String refData[]={ass_id,enquiryId,itemDesc,quantity,drwNo,revNo,revDate,partType};
        // TODO add your handling code here:
                CreateQuotationForm process = new CreateQuotationForm();
                process.setList(refData);
                scroll = new JScrollPane ( process ,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

                int leftMargin = ( int ) ( width - process.getPreferredSize ().width ) / 2;
                process.setBounds ( leftMargin , 5 , process.getPreferredSize ().width , 580 );
                scroll.setBounds ( leftMargin - 15 , 5 , width - leftMargin , 580 );

                scroll.setBackground ( Color.WHITE );
                scroll.setBorder ( BorderFactory.createEmptyBorder () );

                home.jPanel1.removeAll ();
                home.jPanel1.setLayout ( null );
                home.jPanel1.add ( scroll , BorderLayout.CENTER );
                home.jLabel6.setText ( "  BOM of Assembly / Component" );

                home.revalidate ();
                home.repaint ();
        } catch (UnsupportedEncodingException ex) {
            System.err.println();
        }
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
               CreateEnquiryForm process = new CreateEnquiryForm(Integer.parseInt(list.get(2)),false);
               process.setList(list);
                scroll = new JScrollPane ( process ,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

                int leftMargin = ( int ) ( width - process.getPreferredSize ().width ) / 2;
                process.setBounds ( leftMargin , 5 , process.getPreferredSize ().width , 580 );
                scroll.setBounds ( leftMargin - 15 , 5 , width - leftMargin , 580 );

                scroll.setBackground ( Color.WHITE );
                scroll.setBorder ( BorderFactory.createEmptyBorder () );

                home.jPanel1.removeAll ();
                home.jPanel1.setLayout ( null );
                home.jPanel1.add ( scroll , BorderLayout.CENTER );
                home.jLabel6.setText ( "  Create Enquiry" );

                home.revalidate ();
                home.repaint ();        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EnquiriesForm process = new EnquiriesForm ( );
            //  RMEfficienctReport process = new RMEfficienctReport ( "Raw Material Efficiency" );
            scroll = new JScrollPane ( process ,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

            //process.setBounds ( ( int ) ( width - process.getPreferredSize ().width ) / 2 , 5 , process.getPreferredSize ().width , process.getPreferredSize ().height );
            //scroll.setBounds ( 5 , 5 , width-25 , 605 );
            int leftMargin = ( int ) ( width - process.getPreferredSize ().width ) / 2;
            process.setBounds ( leftMargin , 5 , process.getPreferredSize ().width , 580 );
            scroll.setBounds ( leftMargin - 15 , 5 , width - leftMargin , 580 );

            scroll.setBackground ( Color.WHITE );
            scroll.setBorder ( BorderFactory.createEmptyBorder () );

            home.jPanel1.removeAll ();
            home.jPanel1.setLayout ( null );
            //home.jPanel1.add ( customer );
            home.jPanel1.add ( scroll , BorderLayout.CENTER );
            //home.jPanel1.add ( bp1 );
            home.jLabel6.setText ( "  Enquiries" );

            home.revalidate ();
            home.repaint ();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}