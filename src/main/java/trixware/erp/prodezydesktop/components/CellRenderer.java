/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rajesh
 */
public class CellRenderer{
        
        public  DefaultTableCellRenderer getCellRenderer() {
      
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            //table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            
            return centerRenderer ;
        }
    }