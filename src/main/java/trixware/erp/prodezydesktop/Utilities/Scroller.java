/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.Utilities;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;



/**
 *
 * @author Rajesh
 */
public class Scroller extends javax.swing.JPanel implements Runnable{

    
    JLabel label;
  String[] str = new String[]{ "Rajesh P sets new record of highest income", "Rajesh Pahadi awarded with most prestigious award in world", "Rolls Royce, Jagurar, Bugatti gifts their best models to Rajesh"};
  
    /**
     * Creates new form Scroller
     */
    public Scroller () {
        initComponents ();
        
  //      setBackground ( new Color ( 255 , 255 , 255 , 0 ) );
   //     jLabel1.setBackground ( new Color(255 , 255 , 255 , 0));
        
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void paintComponents ( Graphics g ) {
        super.paintComponents ( g ); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_RENDERING , RenderingHints.VALUE_RENDER_QUALITY );
        int w = getWidth ();
        int h = getHeight ();
        Color color1 = Color.GRAY;
        Color color2 = Color.BLACK;
        GradientPaint gp = new GradientPaint ( 0 , 0 , color1 , 0 , h , color2 );
        g2d.setPaint ( gp );
        g2d.fillRect ( 0 , 0 , w , h );
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

        setLayout(null);

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Rajesh P sets new record of highest income");
        add(jLabel1);
        jLabel1.setBounds(0, 0, 120, 16);
    }// </editor-fold>//GEN-END:initComponents


    public void run(){
    while(true){
//        char c = str.charAt(0);
//        String rest = str.substring(1);
//        str = rest + c;
//        jLabel1.setText(str);
//        try{
//            Thread.sleep(200);
//        }catch(InterruptedException e){}
    }  
    
    
    
  }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
