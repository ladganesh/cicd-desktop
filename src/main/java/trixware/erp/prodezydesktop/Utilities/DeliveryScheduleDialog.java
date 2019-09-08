/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.Utilities;

/**
 *
 * @author Rajesh
 */
import trixware.erp.prodezydesktop.components.DeliverySchDetailPanel;
import static trixware.erp.prodezydesktop.examples.HomeScreen.height;
import static trixware.erp.prodezydesktop.examples.HomeScreen.width;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
 
public class DeliveryScheduleDialog extends JDialog {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
    DeliveryScheduleForm2 confirm ;
    
    
    
    public DeliveryScheduleDialog(Frame parent) {
        super(parent, "Delivery Schedule", true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
                GraphicsDevice[] gs = ge.getScreenDevices ();
                for ( int i = 0 ; i < gs.length ; i ++ ) {
                    DisplayMode dm = gs[ i ].getDisplayMode ();
                    if ( width > dm.getWidth () || width == 0 ) {
                        width = dm.getWidth ();
                        height = dm.getHeight ();
                    }
                }
       
        confirm = new DeliveryScheduleForm2(  );
        confirm.setBounds(  0    , 0, 432,  385);
                
        getContentPane().add( confirm  , BorderLayout.CENTER);
        
        pack();
        setResizable(true);
        setBounds(  0    , 0,  480,420 );
        setLocationRelativeTo(parent);
        
    }
    
    public ArrayList< DeliverySchDetailPanel  > getSchedule(){
        
        return confirm.getSchedule() ;
    }
    
    public void closeBOMWindow(){
         dispose();
    }
    
    class DeliveryScheduleForm2 extends javax.swing.JPanel {

    JPanel listPanel = null ;
    JScrollPane jScrollPane1 = null ;
    
    ArrayList< DeliverySchDetailPanel  >  shcdPanelList = new ArrayList< DeliverySchDetailPanel  > () ;
    public ArrayList< DeliverySchDetailPanel  >  filledShcdPanelList = new ArrayList< DeliverySchDetailPanel  > () ;
     
    /**
     * Creates new form DeliveryScheduleForm
     */
    public DeliveryScheduleForm2 () {
        initComponents ();
        
        listPanel = new JPanel() ;
        listPanel.setBackground( Color.white  );
        listPanel.setBounds( 0,0, 250, 5 ) ;
        
        listPanel.setLayout ( new BoxLayout ( listPanel , BoxLayout.Y_AXIS ) );
        //listPanel.setLayout (null );
        
        jScrollPane1 = new JScrollPane( listPanel, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED   ) ;
        
        jScrollPane1.setBounds(  20, 50, 400, 280  ) ;
        
        add(jScrollPane1);
        
       dp = new DeliverySchDetailPanel ();
       dp.setBounds ( 0, 0, 250, 50);
       listPanel.add(dp);
        
        revalidate ();
        repaint ();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setLayout(null);

        jButton1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jButton1.setText("Submit Schedule");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(290, 340, 130, 32);

        jButton3.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jButton3.setText("Add New Row");
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(307, 10, 110, 32);
    }// </editor-fold>                        

    DeliverySchDetailPanel dp = null ;
    int totalPanels = 1 ;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        dp = new DeliverySchDetailPanel ();
        dp.setBounds ( 0, (totalPanels)*40, 250, 50);
        listPanel.add(dp);
     
        shcdPanelList.add(   dp   ) ;
        
        totalPanels++ ;
        revalidate ();
        repaint ();
    
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        for (   int i=0; i<shcdPanelList.size () ; i++   )  {
            if(  shcdPanelList.get ( i ).getQty ()  > 0.0   ){
                filledShcdPanelList.add(  shcdPanelList.get ( i ) ) ;
            }
        }      
        
        closeBOMWindow ();
    }                                        

    
    public ArrayList< DeliverySchDetailPanel  > getSchedule(){
        return filledShcdPanelList ;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration                   
    }
}