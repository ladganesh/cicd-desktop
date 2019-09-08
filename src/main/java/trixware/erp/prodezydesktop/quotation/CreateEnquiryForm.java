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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.json.JSONArray;
import org.json.JSONObject;
import trixware.erp.prodezydesktop.web_services.WebAPITester;

/**
 *
 * @author Rajesh
 */
public class CreateEnquiryForm extends javax.swing.JPanel {

    ArrayList<String[]> employees = new ArrayList<String[]>() ;
    ArrayList<CustomerDR> customer=new ArrayList<CustomerDR>();
    /**
     * Creates new form EnquiriesForm
     */
    int enqRevNo;
    int assId;
    boolean newEnqForm;
    String enqID;
    String[] refID;
    
    public CreateEnquiryForm()
    {
        initComponents ();
        newEnqForm=true;
        dateChooserCombo2.addFocusListener ( date1Focus );
        loadEmpCombo();
        loadComboBox(null);        
        
        jComboBox3.addActionListener(actionListner);
        jTextField4.setText(1+"");
        jTextField4.setEnabled(false);
        enquiryType();
    }
    public CreateEnquiryForm (int enqNo,boolean chk) {
        
        this.enqRevNo=enqNo;
        initComponents ();
        dateChooserCombo2.addFocusListener ( date1Focus );
        loadEmpCombo();
        loadComboBox(null);
        if(chk)
        {
            newEnqForm=false;
            int enqNo1=enqNo+1;
            jTextField4.setText(enqNo1+"");
            jTextField4.setEnabled(false);
        }else{
            newEnqForm=true;
            jTextField4.setText(enqNo+"");
            jTextField4.setEnabled(false);
        }
        jComboBox3.addActionListener(actionListner);

    }
    ActionListener actionListner=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            enquiryType();
        }
    };

    public void enquiryType()
    {
        String type=jComboBox3.getSelectedItem().toString();
        
        if(type.equals("Assembly"))
        {
            jLabel17.setText("Assembly No");
        }else if(type.equals("SubAssembly"))
        {
            jLabel17.setText("<html>Sub Assembly<br>No</html>");

        }else if(type.equals("Part/Component"))
        {
            jLabel17.setText("<html>Part/<br>Component No</html>");
        }
    }
    public void setArray(List<String> list)
    {
        int enqrevRevID=enqRevNo+1;
        
        String[] ref={assId+"",enqID+"",list.get(0),list.get(5),jTextField5.getText(),enqrevRevID+"",list.get(3),list.get(4),enqRevNo+""};
        refID=ref;
    }
    public void loadEmpCombo()
    {
        jComboBox4.setVisible(false);
        String addEmpAPICall = "employeeedit?EmployeePK="+EnquiriesForm.empId;
        String result2 =  WebAPITester.prepareWebCall ( addEmpAPICall, StaticValues.getHeader ()   , "");
        System.err.println(result2);
        if( !  result2.contains(  "not found"   )  ){
            if ( result2!= null ) 
            {
                JSONArray jARR = null;
                JSONObject jOB = null;

                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject jObject = new JSONObject( result2 );
                Iterator<?> keys = jObject.keys();

                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    Object value = jObject.get ( key ) ; 
                    map.put(key, value);
                }

//                JSONObject st = (JSONObject) map.get ( "data" );
                JSONArray records = jObject.getJSONArray ( "data");

                JSONObject emp = null;

                for( int i=0; i<records.length (); i++ )
                {
                        emp = records.getJSONObject ( i);
//                        jComboBox4.addItem( emp.get ( "EMP_NAME" ).toString()  );
                    jLabel18.setText(emp.get ( "EMP_NAME" ).toString());
                }
            }
        }
    }
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
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 550));
        setLayout(null);

        jLabel1.setText("Select Customer");
        add(jLabel1);
        jLabel1.setBounds(530, 60, 100, 30);

        jLabel2.setText("OR");
        add(jLabel2);
        jLabel2.setBounds(560, 90, 100, 20);
        add(jTextField2);
        jTextField2.setBounds(650, 110, 340, 30);

        jButton1.setText("Save & Next");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(870, 470, 120, 23);

        jLabel4.setText("<html>Part / Assembly <br>Description</html>");
        add(jLabel4);
        jLabel4.setBounds(40, 200, 100, 30);

        jLabel6.setText("<html>Revision Date</html>");
        add(jLabel6);
        jLabel6.setBounds(40, 320, 100, 30);

        jLabel8.setText("Part Type");
        add(jLabel8);
        jLabel8.setBounds(40, 110, 90, 30);

        jLabel9.setText("<html>Enquiry<br>Handled By</html>");
        add(jLabel9);
        jLabel9.setBounds(40, 400, 110, 30);

        jTextField3.setNextFocusableComponent(jTextField5);
        add(jTextField3);
        jTextField3.setBounds(160, 200, 330, 30);

        dateChooserCombo2.setFormat(2);
        dateChooserCombo2.setCurrentNavigateIndex(0);
        add(dateChooserCombo2);
        dateChooserCombo2.setBounds(160, 320, 155, 30);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Part/Component", "SubAssembly", "Assembly" }));
        jComboBox3.setNextFocusableComponent(jTextField1);
        add(jComboBox3);
        jComboBox3.setBounds(160, 110, 330, 35);

        add(jComboBox4);
        jComboBox4.setBounds(140, 460, 330, 35);

        jLabel10.setText("<html>Revision No</html>");
        add(jLabel10);
        jLabel10.setBounds(40, 280, 100, 30);

        jTextField4.setNextFocusableComponent(dateChooserCombo2);
        add(jTextField4);
        jTextField4.setBounds(160, 280, 330, 30);

        jLabel11.setText("<html>Drawing No</html>");
        add(jLabel11);
        jLabel11.setBounds(40, 240, 100, 30);

        jTextField5.setNextFocusableComponent(jTextField4);
        add(jTextField5);
        jTextField5.setBounds(160, 240, 330, 30);

        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        add(jComboBox5);
        jComboBox5.setBounds(650, 60, 340, 35);

        jLabel12.setText("New Customer");
        add(jLabel12);
        jLabel12.setBounds(530, 110, 100, 30);

        jLabel13.setText("Address");
        add(jLabel13);
        jLabel13.setBounds(530, 160, 110, 30);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(0);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(650, 160, 340, 96);

        jLabel5.setText("City");
        add(jLabel5);
        jLabel5.setBounds(530, 260, 80, 30);

        jLabel14.setText("Contact Person");
        add(jLabel14);
        jLabel14.setBounds(530, 310, 100, 30);
        add(jTextField7);
        jTextField7.setBounds(650, 310, 340, 30);

        jLabel15.setText("Email");
        add(jLabel15);
        jLabel15.setBounds(530, 350, 100, 30);
        add(jTextField8);
        jTextField8.setBounds(650, 350, 340, 30);

        jLabel16.setText("Phone");
        add(jLabel16);
        jLabel16.setBounds(530, 390, 100, 30);

        jTextField9.setNextFocusableComponent(jButton1);
        add(jTextField9);
        jTextField9.setBounds(650, 390, 340, 30);

        jLabel3.setText("Quantity");
        add(jLabel3);
        jLabel3.setBounds(40, 360, 70, 30);

        jTextField1.setNextFocusableComponent(jComboBox4);
        add(jTextField1);
        jTextField1.setBounds(160, 360, 130, 30);

        jTextField6.setText("jTextField6");
        add(jTextField6);
        jTextField6.setBounds(650, 260, 340, 30);

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 51));
        jLabel28.setText("*");
        add(jLabel28);
        jLabel28.setBounds(30, 200, 30, 20);

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("*");
        add(jLabel29);
        jLabel29.setBounds(30, 240, 30, 30);

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 51));
        jLabel30.setText("*");
        add(jLabel30);
        jLabel30.setBounds(30, 280, 30, 30);

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 51));
        jLabel31.setText("*");
        add(jLabel31);
        jLabel31.setBounds(30, 320, 30, 30);

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 51));
        jLabel32.setText("*");
        add(jLabel32);
        jLabel32.setBounds(30, 110, 30, 30);

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 51));
        jLabel33.setText("*");
        add(jLabel33);
        jLabel33.setBounds(30, 360, 20, 20);

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 51));
        jLabel34.setText("*");
        add(jLabel34);
        jLabel34.setBounds(30, 400, 30, 20);

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 0, 51));
        jLabel35.setText("*");
        add(jLabel35);
        jLabel35.setBounds(520, 70, 30, 10);

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 0, 51));
        jLabel36.setText("*");
        add(jLabel36);
        jLabel36.setBounds(520, 390, 30, 30);

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 51));
        jLabel37.setText("*");
        add(jLabel37);
        jLabel37.setBounds(520, 350, 30, 30);

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 0, 51));
        jLabel38.setText("*");
        add(jLabel38);
        jLabel38.setBounds(520, 310, 30, 30);

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 0, 51));
        jLabel39.setText("*");
        add(jLabel39);
        jLabel39.setBounds(520, 260, 30, 30);

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 0, 51));
        jLabel40.setText("*");
        add(jLabel40);
        jLabel40.setBounds(520, 160, 30, 30);

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 0, 51));
        jLabel41.setText("*");
        add(jLabel41);
        jLabel41.setBounds(520, 110, 30, 30);

        jLabel7.setText("Enquiry No");
        add(jLabel7);
        jLabel7.setBounds(40, 70, 100, 30);

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 0, 51));
        jLabel42.setText("*");
        add(jLabel42);
        jLabel42.setBounds(30, 70, 30, 20);

        jTextField10.setNextFocusableComponent(jTextField5);
        add(jTextField10);
        jTextField10.setBounds(160, 70, 140, 30);

        jTextField11.setNextFocusableComponent(jTextField5);
        add(jTextField11);
        jTextField11.setBounds(160, 160, 140, 30);

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 0, 51));
        jLabel43.setText("*");
        add(jLabel43);
        jLabel43.setBounds(30, 160, 30, 20);
        add(jLabel17);
        jLabel17.setBounds(40, 160, 100, 30);

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(760, 470, 100, 23);
        add(jLabel18);
        jLabel18.setBounds(160, 400, 330, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Capture data from fields
        // 1. item description
        String itemDesc = jTextField3.getText().trim() ;
        String drwgNo = jTextField5.getText().trim() ;
        String revNo = jTextField4.getText().trim() ;
        String revDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( dateChooserCombo2.getSelectedDate ().getTime ());
        String partType = jComboBox3.getSelectedItem().toString() ;
        String quantity = jTextField1.getText().trim();
        String handledBy = jLabel18.getText();//jComboBox4.getSelectedItem().toString() ;
        String assemblyNo=jTextField11.getText().trim();
        String customerName="", customerAddress="", custCity="", custCP="", custEmail="", custPhone="" ;
        String custId="";

        List<String> list=new ArrayList<String>();
        
        customerName = jTextField2.getText().trim();
        customerAddress = jTextArea1.getText().trim() ;
        custCity = jTextField6.getText().trim() ;
        custCP = jTextField7.getText().trim() ;
        custEmail = jTextField8.getText().trim() ;
        custPhone = jTextField9.getText().trim();
        
        if( jComboBox5.getSelectedItem().toString().equals( "Create New Customer" ) ){
        
            
            if(!customerName.equals("")&&!customerAddress.equals("")&&!custCity.equals("")&&!custCP.equals("")&&!custEmail.equals("")&&!custPhone.equals(""))
            {
                if(!itemDesc.equals("")&&!handledBy.equals("")&&!quantity.equals("")&&!partType.equals("")&&!revDate.equals("")&&!revNo.equals("")&&!drwgNo.equals("")&&!assemblyNo.equals(""))
                {
 
                    String addEmpAPICall = "";

                    try {
                        addEmpAPICall = "customeradd?CUSTOMER_NAME=" + URLEncoder.encode(customerName, "UTF-8") + "&CUST_ADDRESS=" + URLEncoder.encode(customerAddress, "UTF-8") + "&CITY=" + URLEncoder.encode(custCity, "UTF-8");
                        String result2 = WebAPITester.prepareWebCall(addEmpAPICall, StaticValues.getHeader(), "");
                        if( !  result2.contains(  "not found"   )  ){
                            HashMap<String, Object> map = new HashMap<String, Object>();
                            JSONObject jObject = new JSONObject( result2 );
                            Iterator<?> keys = jObject.keys();

                            while( keys.hasNext() ){
                                String key = (String)keys.next();
                                Object value = jObject.get ( key ) ; 
                                map.put(key, value);
                            }
                            
                            JSONObject st = (JSONObject) map.get ( "data" );                          
                            custId=st.get("insert_id").toString();                           
                        }
                        String addContactPersonCall = "contactadd?CONT_EMAIL="+URLEncoder.encode(custEmail, "UTF-8")+"&CONT_NO="+URLEncoder.encode(custPhone, "UTF-8")+"&CUST_ID="+URLEncoder.encode(custId+"", "UTF-8")+"&CONT_NAME="+URLEncoder.encode(customerName,"UTF-8");
                        String result =  WebAPITester.prepareWebCall ( addContactPersonCall, StaticValues.getHeader ()   , "");
                    } catch (UnsupportedEncodingException ex) {
                        System.err.println(ex);
                    }  
                    list.add(itemDesc);
                    list.add(drwgNo);
                    list.add(revNo);
                    list.add(revDate);
                    list.add(partType);
                    list.add(quantity);
                    list.add(handledBy);
                    list.add(custId);
                    list.add(customerName);
                    list.add(assemblyNo);
                    list.add(enqRevNo+"");
                    list.add(jTextField10.getText());
                    list.add(EnquiriesForm.empId+"");

                    loadFesibilityScreen(list);
                }else{
                    JOptionPane.showMessageDialog ( null , "Please fill all mandetory fields!");
                }
            }else{
                JOptionPane.showMessageDialog ( null , "Please fill all mandetory fields!");
            }            
        }else{
            if(!itemDesc.equals("")&&!handledBy.equals("")&&!quantity.equals("")&&!partType.equals("")&&!revDate.equals("")&&!revNo.equals("")&&!drwgNo.equals("")&&!assemblyNo.equals(""))
            {
                list.add(itemDesc);
                list.add(drwgNo);
                list.add(revNo);
                list.add(revDate);
                list.add(partType);
                list.add(quantity);
                list.add(handledBy);
                list.add(customer.get(jComboBox5.getSelectedIndex()-1).CUS_ID+"");
                list.add(customer.get(jComboBox5.getSelectedIndex()-1).CUS_NAME);
                list.add(assemblyNo);
                list.add(enqRevNo+"");
                list.add(jTextField10.getText());//enq id
                list.add(EnquiriesForm.empId+"");
                loadFesibilityScreen(list);
            }else{
                JOptionPane.showMessageDialog ( null , "Please fill all mandetory fields!");
            }
        }
        //StringBuilder sb = new StringBuilder().append ( itemDesc );        sb.append ( "\n").append ( drwgNo ).append ( "\n");        sb.append ( revNo ).append ( "\n");        sb.append ( revDate ).append ( "\n");        sb.append ( partType ).append ( "\n");        sb.append ( quantity ).append ( "\n");        sb.append ( handledBy ).append ( "\n");        sb.append ( customerName ).append ( "\n");        sb.append ( customerAddress ).append ( "\n");        sb.append ( custCity ).append ( "\n");        sb.append ( custCP ).append ( "\n");        sb.append ( custEmail ).append ( "\n");        sb.append ( custPhone ).append ( "\n");
        //JOptionPane.showMessageDialog ( null, sb.toString ()  );

        // #3  ENTRY IN CUSTOMER MASTER    :   IF CUSTOMER IS NOT SELECTED FROM COMBO BOX
        // #1  ENTRY IN QUOTATION MASTER  :    CUSTOMER_ID ( NEW CREATED OR SELECTED IN COMBO BOX ) , QUOTATION_NO, CREATION-DATE, TOTAL_REVISIONS, FIRST_HANDLED_BY
        // #2  ENTRY IN ASSEMBLY MASTER     :   ASSEMBLY_DESC, QTY, DRWG_NO, DRWG_REV_NO, REV_DATE                                   
    }//GEN-LAST:event_jButton1ActionPerformed

    public void loadFesibilityScreen(List list)
    {                
        // Done with capturing data and Navigate to Feasbility check screen - 
            if(newEnqForm){
                FeasibilityCheckForm process = new FeasibilityCheckForm();
                process.setList(list);
                scroll = new JScrollPane ( process ,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            
                int leftMargin = ( int ) ( width - process.getPreferredSize ().width ) / 2;
                process.setBounds ( leftMargin , 5 , process.getPreferredSize ().width , 580 );
            
                scroll.setBounds ( leftMargin - 15 , 5 , width - leftMargin , 580 );
            }else{
                setArray(list);
                CreateQuotationForm  process = new CreateQuotationForm (refID[1],refID[0],refID[8]);
                process.setList(refID);
                process.setList(list);
                scroll = new JScrollPane ( process ,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            
                int leftMargin = ( int ) ( width - process.getPreferredSize ().width ) / 2;
                process.setBounds ( leftMargin , 5 , process.getPreferredSize ().width , 580 );
            
                scroll.setBounds ( leftMargin - 15 , 5 , width - leftMargin , 580 );

            }
            
            scroll.setBackground ( Color.WHITE );
            scroll.setBorder ( BorderFactory.createEmptyBorder () );

            home.jPanel1.removeAll ();
            home.jPanel1.setLayout ( null );
            home.jPanel1.add ( scroll , BorderLayout.CENTER );
            jLabel6.setText ( "  BOM of Assembly / Component" );

            home.revalidate ();
            home.repaint ();
    }
    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
        OnActionjConboBox5();
    }//GEN-LAST:event_jComboBox5ActionPerformed
    public void OnActionjConboBox5(){
        try{
            if( jComboBox5.getSelectedItem().toString().equals( "Create New Customer" ) ){
                jTextField2.setText("");
                jTextField2.setEnabled(true) ;
                jTextField6.setText("");
                jTextField6.setEnabled(true) ;
                jTextField7.setText("");
                jTextField7.setEnabled(true) ;
                jTextField8.setText("");               
                jTextField8.setEnabled(true) ;
                jTextField9.setText("");
                jTextField9.setEnabled(true) ;
                jTextArea1.setText("");
                jTextArea1.setEnabled(true);                
                jScrollPane1.setEnabled( true ) ;
                jLabel36.setVisible ( true );
                jLabel37.setVisible ( true );
                jLabel38.setVisible ( true );
                jLabel39.setVisible ( true );
                jLabel40.setVisible ( true );
               jLabel41.setVisible ( true );
            }else{
                int index=jComboBox5.getSelectedIndex()-1;
                int custId=customer.get(index).CUS_ID;
                jTextArea1.setText(customer.get(index).CUS_ADDRESS);
                jTextField6.setText(customer.get(index).CUS_CITY);
            
                String addEmpAPICall = "contacts?customer_id=" + custId;
                String result2 = WebAPITester.prepareWebCall ( addEmpAPICall , StaticValues.getHeader () , "" );

                if ( result2 != null &&  ! result2.contains ( "not found" ) ) {
                    
                   HashMap<String , Object> map = new HashMap<String , Object> ();
                    JSONObject jObject = new JSONObject ( result2 );
                    Iterator<?> keys = jObject.keys ();

                    while ( keys.hasNext () ) {
                        String key = ( String ) keys.next ();
                        Object value = jObject.get ( key );
                       map.put ( key , value );
                    }

                    JSONObject st = ( JSONObject ) map.get ( "data" );
                    JSONArray records = st.getJSONArray ( "records" );

                    JSONObject contact = null;

                    // data of the table
          
                    contact = records.getJSONObject ( 0 );
                    jTextField7.setText( contact.get("CONT_NAME" ).toString());
                    jTextField9.setText(contact.get( "CONT_NO" ).toString());
                    jTextField8.setText(contact.get( "CONT_EMAIL" ).toString());
                }else{
                    jTextField7.setText( "");
                    jTextField9.setText("");
                    jTextField8.setText("");
                }
        
                jTextField2.setEnabled(false);
                jTextField6.setEnabled(false);
                jTextField7.setEnabled(false);
                jTextField8.setEnabled(false);
                jTextField9.setEnabled(false);
                jTextArea1.setEnabled(false);
                jScrollPane1.setEnabled( false);            
                jLabel36.setVisible ( false);
                jLabel37.setVisible ( false);
                jLabel38.setVisible ( false);
                jLabel39.setVisible ( false);
                jLabel40.setVisible ( false);
                jLabel41.setVisible ( false);
            }
        }catch(Exception e){}
    }
    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
            jLabel6.setText ( "  Enquiries" );

            home.revalidate ();
            home.repaint ();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    FocusListener date1Focus = new FocusListener () {
        @Override
        public void focusGained ( FocusEvent e ) {        
        }
        @Override
        public void focusLost ( FocusEvent e ) {
            jComboBox3.requestFocus ( true ) ;
        }
    };

    public void setList(List<String> list) {
 
        jTextField3.setText(list.get(0));
        jTextField5.setText(list.get(1));
     //   jTextField4.setText(list.get(2));
//        String revDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( dateChooserCombo2.getSelectedDate ().getTime ());
        jComboBox3.setSelectedItem(list.get(4));
        jTextField1.setText(list.get(5));
        jLabel18.setText(list.get(6));
//        jComboBox4.setSelectedItem(list.get(6));
//        dateChooserCombo2.setSelectedDate(aDate);
        jComboBox5.setSelectedItem(list.get(8));
        jTextField11.setText(list.get(9));
        jTextField10.setText(list.get(11));
        
        loadComboBox(list.get(8));        
        OnActionjConboBox5();
        enquiryType();
    }
    public void setLabels(List<String> list)
    {
        String enqNo=list.get(0);
        String assNo=list.get(1);
        String partDesc=list.get(2);
        String drwgNo=list.get(3);
        String quantity=list.get(4);
        String customerName=list.get(5);

        this.enqID=list.get(6);
        this.assId=Integer.parseInt(list.get(10));
        jTextField10.setText(enqNo);
        jTextField11.setText(assNo);
        jTextField3.setText(partDesc);
        jTextField5.setText(drwgNo);
        jTextField1.setText(quantity);

        loadComboBox(customerName);        
        OnActionjConboBox5();
        
    }
    public void loadRev()
    {
        
    }
    private void loadComboBox(String choice)
    {
        String addEmpAPICall = "customers";
        String result2 =  WebAPITester.prepareWebCall ( addEmpAPICall, StaticValues.getHeader ()   , "");
     
        if( !  result2.contains(  "not found"   )  ){
            if ( result2!= null ) 
            {
                JSONArray jARR = null;
                JSONObject jOB = null;

                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject jObject = new JSONObject( result2 );
                Iterator<?> keys = jObject.keys();

                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    Object value = jObject.get ( key ) ; 
                    map.put(key, value);
                }

                JSONObject st = (JSONObject) map.get ( "data" );
                JSONArray records = st.getJSONArray ( "records");

                JSONObject cust = null;
                jComboBox5.removeAllItems();
                jComboBox5.addItem("Create New Customer");
                for( int i=0; i<records.length (); i++ )
                {
                    cust = records.getJSONObject ( i);
                    jComboBox5.addItem( cust.get ( "CUSTOMER_NAME" ).toString()  );
                    customer.add(new CustomerDR(Integer.parseInt(cust.get ( "CUSTOMER_ID" ).toString()),cust.get ( "CUSTOMER_NAME" ).toString(),cust.get ( "CITY" ).toString() ,cust.get ( "CUST_ADDRESS" ).toString()));
                }
                
                if(choice!=null)
                {
                    jComboBox5.setSelectedItem(choice);
                }
            }
        }
    }
}