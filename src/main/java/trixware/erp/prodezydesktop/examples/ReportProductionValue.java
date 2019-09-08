/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.examples;

import trixware.erp.prodezydesktop.Model.CustomerDR;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JCheckBox;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import trixware.erp.prodezydesktop.Model.ProductDR;
import trixware.erp.prodezydesktop.Utilities.DB_Operations;
/**
 *
 * @author Rajesh
 */
public class ReportProductionValue extends javax.swing.JPanel {

    static ArrayList<ProductDR> products = null;
    ArrayList<CustomerDR> customer = null;
    

    String selectAll = "-- select --";

    ProductDR prdr = null;
    CustomerDR custdr = null;
    
    DecimalFormat df = new DecimalFormat( "#.##" );

//SimpleDateFormat sdf = null;
    static String fromDate, toDate;

    String reportLabel1 = "Process Wise / ProductWise Report for ";
    String reportLabel2 = "Date Wise report for all processes for product";
    String reportLabel3 = "Date wIse combined report for selected process";
    String reportLabel4 = "Combined report between";

    public static int resIndex = 0;

    static Vector<Vector<Object>> data = null;
    static Vector<String> columnNames = null;
    static DefaultCategoryDataset dataSet = null;
    static ArrayList<String[]> abc = null;
    static ChartPanel cp = null;

    Calendar c1 = Calendar.getInstance ();
    Calendar c2 = Calendar.getInstance ();

    int columnCount;

    /**
     * Creates new form DailyReportForm
     */
    public ReportProductionValue () {
        initComponents ();

        loadComboBoxes ();

        Dimension d = Toolkit.getDefaultToolkit ().getScreenSize ();
        int screenW = ( int ) d.getWidth ();
        int screenH = ( int ) d.getHeight ();

        dateChooserCombo4.setText ( dateChooserCombo3.getText () );

    //    jCheckBox1.setVisible ( false);
    }

    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent ( g );
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

    // Report no 1 for 'Daily' filter
    // select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  rdate as 'Start Date', qtyout as 'Output Qty', (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid=4 order by rdate
    // Report no 2 for 'Weekly' filter
    //select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',   SUM(qtyout) as 'Output Qty', SUM((qtyout/(stoptime-starttime))) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '03-06-2018'  AND rdate <= '08-06-2018' AND fgid=4 AND processid = 1 
    //select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',   SUM(qtyout) as 'Output Qty', SUM((qtyout/(stoptime-starttime))) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '03-06-2018'  AND rdate <= '08-06-2018' AND fgid=4 AND processid = 2 
    //select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',   SUM(qtyout) as 'Output Qty', SUM((qtyout/(stoptime-starttime))) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '03-06-2018'  AND rdate <= '08-06-2018' AND fgid=4 AND processid = 3
    public JFreeChart generateBarChart ( String reportTitle , String xAxis , String yAxis ) {

        dataSet = new DefaultCategoryDataset ();

        for ( int i = 0 ; i < abc.size () ; i ++ ) {
            //try{
            dataSet.setValue ( Float.parseFloat(  df.format(  Float.parseFloat( abc.get ( i )[ 1 ] ))) , "Production Value" , abc.get ( i )[ 0 ] );
            // }catch(NumberFormatException e){
            //     System.out.println("errorrrr") ;
            // }                                                                     
        }

        JFreeChart chart = ChartFactory.createBarChart3D (
                reportTitle , xAxis , yAxis ,
                dataSet , PlotOrientation.VERTICAL , true , true , false );

        ChartPanel cp = new ChartPanel ( chart );
        cp.setPreferredSize ( new java.awt.Dimension ( 900 , 330 ) );
        cp.setBounds ( 330 , 320 , 950 , 330 );

        CategoryPlot plot = ( CategoryPlot ) chart.getPlot ();
        plot.setBackgroundPaint ( Color.lightGray );
        plot.setDomainGridlinePaint ( Color.white );
        plot.setDomainGridlinesVisible ( false );
        plot.setRangeGridlinePaint ( Color.white );

        CategoryAxis domainAxis = plot.getDomainAxis ();
        domainAxis.setCategoryLabelPositions ( CategoryLabelPositions.UP_45 );

        BarRenderer renderer = ( BarRenderer ) plot.getRenderer ();
        renderer.setBaseItemLabelsVisible ( true );
        renderer.setBaseItemLabelGenerator ( new StandardCategoryItemLabelGenerator () );
        renderer.setBarPainter ( new StandardBarPainter () );
        renderer.setSeriesPaint ( 0 , Color.blue );
        renderer.setSeriesItemLabelFont ( 1 , new Font ( "Times New Roman" , Font.BOLD , 10 ) );
        renderer.setSeriesItemLabelPaint ( 0 , Color.white );

//                                for(int x = 0;x<5;x++){
//                                    renderer.setSeriesItemLabelFont(x, new Font("Times New Roman",Font.PLAIN,10));
//                                    renderer.setSeriesPositiveItemLabelPosition(x,   
//                                            new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, 0.0));
//                                }
        try {
            remove ( 21 );
        } catch ( Exception e ) {
        }

        add ( cp );

        HomeScreen.home.pack ();
        HomeScreen.home.revalidate ();
        HomeScreen.home.repaint ();

        return chart;
    }

    public static JFreeChart generatePieChart () {
        DefaultPieDataset dataSet = new DefaultPieDataset ();
        dataSet.setValue ( "China" , 19.64 );
        dataSet.setValue ( "India" , 17.3 );
        dataSet.setValue ( "United States" , 4.54 );
        dataSet.setValue ( "Indonesia" , 3.4 );
        dataSet.setValue ( "Brazil" , 2.83 );
        dataSet.setValue ( "Pakistan" , 2.48 );
        dataSet.setValue ( "Bangladesh" , 2.38 );

        JFreeChart chart = ChartFactory.createPieChart (
                "Production Value" , dataSet , true , true , false );

        return chart;
    }

    public void loadComboBoxes () {

        String queryOne = "SELECT FG_ID, PART_NAME FROM finished_goods";
        String queryTwo = "SELECT Customer_ID, CUSTOMER_NAME FROM customer";
        

        ResultSet rs = null;

        try {
            rs = DB_Operations.executeSingle ( queryOne );
            //  products = new ArrayList<HashMap<String, String>> ();
            products = new ArrayList<ProductDR> ();
            while ( rs.next () ) {
                //   entity =  new  HashMap<String, String>();
                prdr = new ProductDR ();
                //    entity.put ( rs.getString(1) /*  String.valueOf( rs.getInt(1) ) */, rs.getString(2)) ;
                prdr.FG_ID = Integer.parseInt ( rs.getString ( 1 ) );
                prdr.FG_PART_NO = rs.getString ( 2 );
                products.add ( prdr );
                jComboBox1.addItem ( rs.getString ( 2 ) );
            }
        } catch ( Exception e ) {
            System.out.println ( " Error 1 " + e.getMessage () );
        }
        
        try {
            rs = DB_Operations.executeSingle ( queryTwo );
            //  products = new ArrayList<HashMap<String, String>> ();
            customer = new ArrayList<CustomerDR> ();
            while ( rs.next () ) {
                //   entity =  new  HashMap<String, String>();
                custdr = new CustomerDR ();
                //    entity.put ( rs.getString(1) /*  String.valueOf( rs.getInt(1) ) */, rs.getString(2)) ;
                custdr.CUS_ID = Integer.parseInt ( rs.getString ( 1 ) );
                custdr.CUS_NAME = rs.getString ( 2 );
                customer.add ( custdr );
                jComboBox6.addItem ( rs.getString ( 2 ) );
            }
        } catch ( Exception e ) {
            System.out.println ( " Error 1 " + e.getMessage () );
        }

       
    }

    
    public int getLastProcess( int productId){
        
        ResultSet queryLastProcess = DB_Operations.executeSingle (  "SELECT REF_PROCESS_ID, max(FPM_ID) FROM FG_PROCESS_MACH_MAP WHERE REF_FG_ID = "+productId ); 
       
        try{
            if( queryLastProcess!=null  && queryLastProcess.isBeforeFirst () ){
                return queryLastProcess.getInt ( 1 ) ;
            }
        }catch(SQLException  e){
            
        }
                
        return 0 ;
    }
    
    public float getRateforProductAndCustomer( int productId , int customerId ){
    
        ResultSet queryLastProcess = DB_Operations.executeSingle (  "SELECT SALES_RATE FROM FG_CUSTOMER_MAP WHERE M_FG_ID = "+productId+" AND  M_CUST_ID = "+customerId ); 
        
        try{
            if( queryLastProcess!=null  && queryLastProcess.isBeforeFirst () ){
                return queryLastProcess.getInt ( 1 ) ;
            }
        }catch(SQLException  e){
            
        }
        
        return 0 ;
    }
        
    
     public void loadEntriesSelectedProduct ( String reportType , int product ) {

        ResultSet result;

        try {

            String xAxisLabel;

           

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product  );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j ) + "' AND fgid=" + product );
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) + "' AND fgid=" + product );
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j ) + "' AND fgid=" + product );
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }
    
     public void loadEntries( String reportType, int productId, int customerId ) {

        ResultSet result;
        
        int lastProcess = getLastProcess ( productId ) ;
        float rate = getRateforProductAndCustomer ( productId , customerId ) ;

        try {

            String xAxisLabel;

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                // "select ( select PART_NAME from finished_goods where FG_ID in ( fgid )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in (customer_id) ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id)) as 'Sales Rate',  SUM(qtyout) as 'Produced Qty' , SUM(qtyout*(select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id))) as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = (select MAX(REF_PROCESS_ID) from FG_PROCESS_MACH_MAP where REF_FG_ID in (fgid)) AND rdate >= '"+formatFromDate ()+"' AND rdate <= '"+formatToDate ()+"'"        
                result = DB_Operations.executeSingle ( "select ( select PART_NAME from finished_goods where FG_ID in ( "+productId+" )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in ("+customerId+") ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in ("+productId+") AND M_CUST_ID in ("+customerId+")) as 'Sales Rate',  SUM(qtyout) as 'Produced Qty' , SUM(qtyout*"+rate+") as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = "+lastProcess+" AND rdate >= '"+formatFromDate ()+"' AND rdate <= '"+formatToDate ()+"'" );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select ( select PART_NAME from finished_goods where FG_ID in ( fgid )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in (customer_id) ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id)) as 'Sales Rate',  SUM(qtyout) as 'Produced Qty' , SUM(qtyout*"+rate+") as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = "+lastProcess+" AND rdate >= '"+formatFromDate ()+"' AND rdate <= '"+formatToDate ()+"' group by rdate"  );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    
                    result = DB_Operations.executeSingle ( "select ( select PART_NAME from finished_goods where FG_ID in ( fgid )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in (customer_id) ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id)) as 'Sales Rate',  SUM(qtyout*"+rate+") as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = "+lastProcess+" AND rdate >= '"+weeklyDates.get(i)+"' AND rdate <= '"+weeklyDates.get(j)+"'" );
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select ( select PART_NAME from finished_goods where FG_ID in ( fgid )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in (customer_id) ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id)) as 'Sales Rate',  SUM(qtyout) as 'Produced Qty' , SUM(qtyout*"+rate+") as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = "+lastProcess+" AND rdate >= '"+ months.get(i) +"' AND rdate <= '"+months.get(j)+"'" );
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select ( select PART_NAME from finished_goods where FG_ID in ( fgid )) as 'Product', ( select CUSTOMER_NAME from customer where CUSTOMER_ID in (customer_id) ) as 'Customer', (select SALES_RATE from FG_CUSTOMER_MAP where M_FG_ID in (fgid) AND M_CUST_ID in (customer_id)) as 'Sales Rate',  SUM(qtyout) as 'Produced Qty' , SUM(qtyout*"+rate+") as 'Value' from dailyreport where fgid = "+productId+" AND customer_id = "+customerId+" AND processid = "+lastProcess+" AND rdate >= '"+quarters.get(i)+"' AND rdate <= '"+quarters.get(j)+"'");
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }
     
     public void loadEntriesSingleCustomers ( String reportType , int product, int customer ) {

        ResultSet result;

        try {

            String xAxisLabel;

           
            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product);
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product + " group by rdate" );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j ) + "' AND fgid=" + product );
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) + "' AND fgid=" + product );
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j ) + "' AND fgid=" + product );
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }
     
     public void loadEntriesAllMachine ( String reportType , int product,  int employee ) {

        ResultSet result;

        try {

            String xAxisLabel;

           

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',    SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product+ " AND empid=" + employee );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product + " AND empid=" + employee+ " group by rdate" );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j ) + "' AND fgid=" + product +  " AND empid=" + employee);
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) + "' AND fgid=" + product + " AND empid=" + employee);
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j ) + "' AND fgid=" + product +  " AND empid=" + employee);
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }
    
    public void loadEntriesMachineWise ( String reportType , int product, int process, int employee) {

        ResultSet result;

        try {

            String xAxisLabel;

         

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence', machineid from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () +"' AND fgid=" + product +" AND processid=" + process +" AND empid=" + employee  );
                    jTable1.setModel ( buildTableModel( result , reportType , "xAxisLabel" ) );
            
                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

            
                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence', machineid from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () +" AND fgid=" + product +" AND processid=" + process +"' AND empid=" + employee+" group by rdate" );
                    jTable1.setModel ( buildTableModel( result , reportType , "xAxisLabel" ) );
            
                    generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                 
                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        // result = DB_Operations.executeSingle ("select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '"+weeklyDates.get ( i)+"' AND rdate <= '"+weeklyDates.get(j)+"' AND fgid="+product+" group by fgid");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence', machineid from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j )  +" AND fgid=" + product +" AND processid=" + process +"' AND empid=" + employee );
                        fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                        toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                        jTable1.setModel ( buildTableModel( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }
                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence', machineid from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) +" AND fgid=" + product +" AND processid=" + process +"' AND empid=" + employee  );
                        jTable1.setModel ( buildTableModel( result , reportType , monthsTitles[ k ] ) );

                }
                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence', machineid from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j )  + " AND fgid=" + product +" AND processid=" + process +"' AND empid=" + employee  );
                        jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );

                }
                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {
                System.out.println("sdfsdfd");
        }
    }
    
    
    public void loadEntriesProductWise ( String reportType ) {

        ResultSet result;

        int product = 0;

        try {

            String xAxisLabel;

          
            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

             //   for ( int i = 0 ; i < products.size () ; i ++ ) {
             //       product = products.get ( i ).FG_ID;

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () +"'" );
                    jTable1.setModel ( buildTableModel( result , reportType , "xAxisLabel" ) );
            //        previousReportValue = 0;
            //    }

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

             //   for ( int i = 0 ; i < products.size () ; i ++ ) {
                 //   product = products.get ( i ).FG_ID;

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () +"' group by rdate" );
                    jTable1.setModel ( buildTableModel( result , reportType , "xAxisLabel" ) );
             //       previousReportValue = 0;
            //    }
                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                  //  for ( int k = 0 ; k < products.size () ; k ++ ) {
                   //     product = products.get ( k ).FG_ID;

                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        // result = DB_Operations.executeSingle ("select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '"+weeklyDates.get ( i)+"' AND rdate <= '"+weeklyDates.get(j)+"' AND fgid="+product+" group by fgid");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j )  +"'" );
                        fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                        toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                        jTable1.setModel ( buildTableModel( result , reportType , fromWeekDate + " to " + toWeekDate ) );
               //     }
                //    previousReportValue = 0;
                }
                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                //    for ( int l = 0 ; l < products.size () ; l ++ ) {
                 //       product = products.get ( l ).FG_ID;

                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) +"'"  );
                        jTable1.setModel ( buildTableModel( result , reportType , monthsTitles[ k ] ) );

                //    }
                }
                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                //    for ( int l = 0 ; l < products.size () ; l ++ ) {
                //        product = products.get ( l ).FG_ID;

                        //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                        result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j )  +"'" );
                        jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );

                //    }
                 //   previousReportValue = 0;
                }
                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {
                System.out.println("sdfsdfd");
        }
    }
    
    public void loadEntriesOnlyProcess ( String reportType , int product,  int process ) {

        ResultSet result;

        try {

            String xAxisLabel;

            

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product+ " AND processid=" + process );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',     SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product + " AND processid=" + process+ " group by rdate" );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j ) + "' AND fgid=" + product + " AND processid=" + process);
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) + "' AND fgid=" + product + " AND processid=" + process);
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j ) + "' AND fgid=" + product + " AND processid=" + process);
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }
    
    public void loadEntriesProcessAndMachine ( String reportType , int product,  int process, int machine ) {

        ResultSet result;

        try {

            String xAxisLabel;

          

            if ( reportType.equalsIgnoreCase ( "Yearly" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',     SUM(qtyout) as 'Output Qty',     rdate,        SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '"+"01-04-2018"+"' AND rtdate <= '"+"31-03-2018"+"' AND fgid="+product);
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',    showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   SUM(qtyout/(stoptime-starttime)) as 'Speed', SUM(stoptime-starttime) as 'Hrs', SUM(qtyin-qtyout) as 'Balence' from dailyreport where rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product+ " AND processid=" + process+ " AND machineid=" + machine );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Year to Date Production " , "YEAR" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Daily" ) ) {

                //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value', SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + formatFromDate () + "' AND rdate <= '" + formatToDate () + "' AND fgid=" + product + " AND processid=" + process+ " AND machineid=" + machine+ " group by rdate" );
                jTable1.setModel ( buildTableModel ( result , reportType , "xAxisLabel" ) );

                generateBarChart ( "Daily production value report for period " + sdfTS.format ( c1.getTime () ) + " to " + sdfTS.format ( c2.getTime () ) , "DAYS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {

                showWeek ();

                xAxisLabel = "Week ";
                String fromWeekDate, toWeekDate;

                for ( int i = 0, j = i + 1 ; j < weeklyDates.size () ; i = i + 2 , j += 2 ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',  SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + weeklyDates.get ( i ) + "' AND rdate <= '" + weeklyDates.get ( j ) + "' AND fgid=" + product + " AND processid=" + process+ " AND machineid=" + machine);
                    fromWeekDate = weeklyDates.get ( i ).split ( " " )[ 0 ].substring ( 5 );
                    toWeekDate = weeklyDates.get ( j ).split ( " " )[ 0 ].substring ( 5 );
                    jTable1.setModel ( buildTableModel ( result , reportType , fromWeekDate + " to " + toWeekDate ) );
                }

                generateBarChart ( "Weekly production value report for period " + weeklyDates.get ( 0 ).split ( " " )[ 0 ] + " to " + sdfTS.format ( c2.getTime () ) , "WEEKS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {

                String year = showMonths ();

                String[] monthsTitles = new String[] { "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < months.size () && k < 12 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date',   SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',   SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + months.get ( i ) + "' AND rdate <= '" + months.get ( j ) + "' AND fgid=" + product + " AND processid=" + process+ " AND machineid=" + machine);
                    jTable1.setModel ( buildTableModel ( result , reportType , monthsTitles[ k ] ) );
                }

                generateBarChart ( "Monthly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "MONTHS" , "VALUE" );

            } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {

                String year = showQuarters ();

                String[] quarterTitles = new String[] { "April - June" , "July - September" , "October - December" , "January - March" };

                for ( int i = 0, j = i + 1, k = 0 ; j < quarters.size () && k < 4 ; i = i + 2 , j += 2 , k ++ ) {

                    //result = DB_Operations.executeSingle ("select (select PROCESS_NAME from PROCESS_MASTER where PROCESS_ID in (processid)) as 'Process' , (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout) as 'Output Qty',    rdate,                  (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where fgid="+product+" group by rdate");
                    result = DB_Operations.executeSingle ( "select (select PART_NAME from finished_goods where FG_ID in (fgid)) as 'Product',  showrdate  as 'Start Date', SUM(qtyout * (select SALES_RATE from finished_goods where FG_ID in (fgid))) as 'Total Value',  SUM(qtyout) as 'Output Qty',   (qtyout/(stoptime-starttime)) as 'Speed', (stoptime-starttime) as 'Hrs', (qtyin-qtyout) as 'Balence' from dailyreport where  rdate >= '" + quarters.get ( i ) + "' AND rdate <= '" + quarters.get ( j ) + "' AND fgid=" + product + " AND processid=" + process+ " AND machineid=" + machine);
                    jTable1.setModel ( buildTableModel ( result , reportType , quarterTitles[ k ] ) );
                }

                generateBarChart ( "Quarterly production value report for year " + year + " - " + ( Integer.parseInt ( year ) + 1 ) , "Quarters" , "VALUE" );

                //generateBarChart ( "Quarterly production value report for period "+sdfTS.format(c1.getTime())+" to "+sdfTS.format(c2.getTime()),   "QUARTERS", "VALUE" );
            }

        } catch ( SQLException e ) {

        }
    }

    public DefaultTableModel buildTableModel ( ResultSet rs , String reportType , String label )
            throws SQLException {

        //columnCount = 0;
        if ( rs != null ) {
            ResultSetMetaData metaData = rs.getMetaData ();

            // names of columns
            columnCount = metaData.getColumnCount ();
            if ( columnNames.size () < columnCount ) {
                for ( int column = 1 ; column <= columnCount ; column ++ ) {
                    columnNames.add ( metaData.getColumnName ( column ) );
                }
            }

            int j = 1;
            // data of the table
            while ( rs.next () ) {

                String[] ab = new String[ 2 ];

                Vector<Object> vector = new Vector<Object> ();
                for ( int columnIndex = 1 ; columnIndex <= columnCount ; columnIndex ++ ) {

                    Object s = rs.getObject ( columnIndex );

                    if ( s != null ) {
                        vector.add ( s );
                    } else {
                        vector.add ( new String ( "--" ) );
                    }

                    String s1;

                    if ( columnIndex == 2 ) {

                        if ( reportType.equalsIgnoreCase ( "Weekly" ) ) {
                            ab[ 0 ] = label;
                        } else if ( reportType.equalsIgnoreCase ( "Monthly" ) ) {
                            ab[ 0 ] = label;
                        } else if ( reportType.equalsIgnoreCase ( "Quarterly" ) ) {
                            ab[ 0 ] = label;
                        } else {
                            ab[ 0 ] = rs.getString ( columnIndex );
                        }

                    } else if ( columnIndex == 3 ) {
                        s1 = rs.getString ( columnIndex );

                        if ( s1 != null ) {
                            //ab[ 1 ] = rs.getString ( columnIndex );
                            ab[ 1 ] =    df.format(  Float.parseFloat(rs.getString ( columnIndex )));
                        } else {
                            ab[ 1 ] = "0";
                        }

                    }
                }
                data.add ( vector );
                abc.add ( ab );
            }
            //    generateBarChart ();
        }else{
            
            data = null ;
            columnNames = null;
        }

        return new DefaultTableModel ( data , columnNames );

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo4 = new datechooser.beans.DateChooserCombo();
        jComboBox5 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jCheckBox5 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(204, 255, 0));
        setForeground(new java.awt.Color(0, 204, 204));
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(1366, 760));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("From Date");
        add(jLabel1);
        jLabel1.setBounds(40, 70, 80, 20);

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Product");
        add(jLabel2);
        jLabel2.setBounds(40, 290, 70, 20);

        jComboBox1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(40, 310, 160, 30);

        jButton1.setBackground(new java.awt.Color(204, 255, 51));
        jButton1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jButton1.setText("Generate Report");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(40, 470, 160, 40);

        jButton2.setBackground(new java.awt.Color(204, 255, 51));
        jButton2.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jButton2.setText("Close");
        add(jButton2);
        jButton2.setBounds(40, 520, 160, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(330, 90, 950, 200);

        jLabel11.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("To Date");
        add(jLabel11);
        jLabel11.setBounds(40, 120, 80, 20);

        dateChooserCombo3.setCalendarBackground(new java.awt.Color(204, 255, 51));
        dateChooserCombo3.setCalendarPreferredSize(new java.awt.Dimension(330, 230));
        dateChooserCombo3.setFormat(2);
        add(dateChooserCombo3);
        dateChooserCombo3.setBounds(40, 90, 155, 30);

        dateChooserCombo4.setCalendarPreferredSize(new java.awt.Dimension(330, 230));
        try {
            dateChooserCombo4.setDefaultPeriods(dateChooserCombo3.getDefaultPeriods());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        add(dateChooserCombo4);
        dateChooserCombo4.setBounds(40, 140, 155, 30);

        jComboBox5.setBackground(new java.awt.Color(195, 238, 66));
        jComboBox5.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly", "Quarterly", "Yearly" }));
        add(jComboBox5);
        jComboBox5.setBounds(40, 210, 160, 30);

        jCheckBox1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Select All");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        add(jCheckBox1);
        jCheckBox1.setBounds(210, 310, 76, 24);

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Production Value Report");
        add(jLabel5);
        jLabel5.setBounds(30, 10, 440, 40);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customer");
        add(jLabel3);
        jLabel3.setBounds(40, 350, 100, 16);

        jComboBox6.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jComboBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox6MouseClicked(evt);
            }
        });
        add(jComboBox6);
        jComboBox6.setBounds(40, 370, 160, 30);

        jCheckBox5.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox5.setText("Select All");
        add(jCheckBox5);
        jCheckBox5.setBounds(210, 370, 76, 24);
    }// </editor-fold>//GEN-END:initComponents

    SimpleDateFormat sdf2 = new SimpleDateFormat ( "yyyy-MM-dd" );

    public String formatFromDate () {

        String[] dateIP = dateChooserCombo3.getText ().split ( " " );

        String fromDate = null;

        if ( dateIP[ 1 ].length () == 3 ) {
            fromDate = dateIP[ 1 ].substring ( 0 , 2 ) + "-";
            c1.set ( Calendar.DATE , Integer.parseInt ( dateIP[ 1 ].substring ( 0 , 2 ) ) );
        } else if ( dateIP[ 1 ].length () == 2 ) {
            fromDate = "0" + dateIP[ 1 ].substring ( 0 , 1 ) + "-";
            c1.set ( Calendar.DATE , Integer.parseInt ( dateIP[ 1 ].substring ( 0 , 1 ) ) );
        }

        String mon = dateIP[ 0 ];

        switch ( mon ) {

            case "Jan":
                fromDate = fromDate + "01-";
                c1.set ( Calendar.MONTH , 0 );
                break;
            case "Feb":
                fromDate = fromDate + "02-";
                c1.set ( Calendar.MONTH , 1 );
                break;
            case "Mar":
                fromDate = fromDate + "03-";
                c1.set ( Calendar.MONTH , 2 );
                break;
            case "Apr":
                fromDate = fromDate + "04-";
                c1.set ( Calendar.MONTH , 3 );
                break;
            case "May":
                fromDate = fromDate + "05-";
                c1.set ( Calendar.MONTH , 4 );
                break;
            case "Jun":
                fromDate = fromDate + "06-";
                c1.set ( Calendar.MONTH , 5 );
                break;
            case "Jul":
                fromDate = fromDate + "07-";
                c1.set ( Calendar.MONTH , 6 );
                break;
            case "Aug":
                fromDate = fromDate + "08-";
                c1.set ( Calendar.MONTH , 7 );
                break;
            case "Sep":
                fromDate = fromDate + "09-";
                c1.set ( Calendar.MONTH , 8 );
                break;
            case "Oct":
                fromDate = fromDate + "10-";
                c1.set ( Calendar.MONTH , 9 );
                break;
            case "Nov":
                fromDate = fromDate + "11-";
                c1.set ( Calendar.MONTH , 10 );
                break;
            case "Dec":
                fromDate = fromDate + "12-";
                c1.set ( Calendar.MONTH , 11 );
                break;

        }

        // fromDate = fromDate + dateIP[2];
        c1.set ( Calendar.YEAR , Integer.parseInt ( dateIP[ 2 ] ) );
        fromDate = sdf2.format ( c1.getTime () ) + " 00:00:00";

        //   c1.setFirstDayOfWeek ( Calendar.SUNDAY);
        //   System.out.println( "This is #"+  c1.get ( Calendar.WEEK_OF_MONTH) +" week in the month");
        //  System.out.println( "This is #"+  (c1.get ( Calendar.MONTH) +1)+"  month in the year");
        SimpleDateFormat sdf = new SimpleDateFormat ( "EEEE" );
        SimpleDateFormat sdf1 = new SimpleDateFormat ( "MMM" );
        SimpleDateFormat sdf2 = new SimpleDateFormat ( "yyyy" );
        SimpleDateFormat sdf3 = new SimpleDateFormat ( "dd" );

        /*
         * // System.out.println( "This is #"+ (c1.get (
         * Calendar.DAY_OF_WEEK_IN_MONTH) )+" day of month in the year");
         * System.out.println( sdf.format(c1.getTime() )+" name of the day");
         * System.out.println( sdf1.format(c1.getTime() )+" name of the month");
         * System.out.println( sdf2.format(c1.getTime() )+" name of the year");
         * System.out.println( sdf3.format(c1.getTime() )+" name of the date");
         */
//          c1.add ( Calendar.DATE, 20);
//          System.out.println(  sdf.format(c1.getTime() )+" name of the day");
//          System.out.println(  sdf1.format(c1.getTime() )+"  month");
//          System.out.println(  sdf2.format(c1.getTime() )+"  year");
//          System.out.println(  sdf3.format(c1.getTime() )+"  date");
//          
        return fromDate;
    }

    public String formatToDate () {

        String[] dateIP2 = dateChooserCombo4.getText ().split ( " " );
        String toDate = null;

        if ( dateIP2[ 1 ].length () == 3 ) {
            toDate = dateIP2[ 1 ].substring ( 0 , 2 ) + "-";
            c2.set ( Calendar.DATE , Integer.parseInt ( dateIP2[ 1 ].substring ( 0 , 2 ) ) );
        } else if ( dateIP2[ 1 ].length () == 2 ) {
            toDate = "0" + dateIP2[ 1 ].substring ( 0 , 1 ) + "-";
            c2.set ( Calendar.DATE , Integer.parseInt ( dateIP2[ 1 ].substring ( 0 , 1 ) ) );
        }
        String mon2 = dateIP2[ 0 ];

        switch ( mon2 ) {

            case "Jan":
                toDate = toDate + "01-";
                c2.set ( Calendar.MONTH , 0 );
                break;
            case "Feb":
                toDate = toDate + "02-";
                c2.set ( Calendar.MONTH , 1 );
                break;
            case "Mar":
                toDate = toDate + "03-";
                c2.set ( Calendar.MONTH , 2 );
                break;
            case "Apr":
                toDate = toDate + "04-";
                c2.set ( Calendar.MONTH , 3 );
                break;
            case "May":
                toDate = toDate + "05-";
                c2.set ( Calendar.MONTH , 4 );
                break;
            case "Jun":
                toDate = toDate + "06-";
                c2.set ( Calendar.MONTH , 5 );
                break;
            case "Jul":
                toDate = toDate + "07-";
                c2.set ( Calendar.MONTH , 6 );
                break;
            case "Aug":
                toDate = toDate + "08-";
                c2.set ( Calendar.MONTH , 7 );
                break;
            case "Sep":
                toDate = toDate + "09-";
                c2.set ( Calendar.MONTH , 8 );
                break;
            case "Oct":
                toDate = toDate + "10-";
                c2.set ( Calendar.MONTH , 9 );
                break;
            case "Nov":
                toDate = toDate + "11-";
                c2.set ( Calendar.MONTH , 10 );
                break;
            case "Dec":
                toDate = toDate + "12-";
                c2.set ( Calendar.MONTH , 11 );
                break;

        }

        //toDate = toDate + dateIP2[2];
        c2.set ( Calendar.YEAR , Integer.parseInt ( dateIP2[ 2 ] ) );
        toDate = sdf2.format ( c2.getTime () ) + " 00:00:00";

        c2.setFirstDayOfWeek ( Calendar.SUNDAY );
        //   System.out.println( "This is #"+  c2.get ( Calendar.WEEK_OF_MONTH) +" week in the month");
        //  System.out.println( "This is #"+  (c2.get ( Calendar.MONTH) +1)+" month in the year");

        return toDate;
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        int  product,  _customer               ;

        product = products.get ( jComboBox1.getSelectedIndex () ).FG_ID;
       
        _customer = customer.get ( jComboBox6.getSelectedIndex () ).CUS_ID;

        String reportType = jComboBox5.getSelectedItem ().toString ();

        //   System.out.println(  "The From date is "+formatFromDate ()  );
        //   System.out.println(  "The To date is "+formatToDate ()  );
//        System.out.println ( "Total no of days " + ( ( ( ( ( c2.getTimeInMillis () - c1.getTimeInMillis () ) / 1000 ) / 60 ) / 60 ) / 24 ) );

        //    WeekCalculation week = new WeekCalculation (formatFromDate (), formatToDate ());
        data = new Vector<Vector<Object>> ();
        columnNames = new Vector<String> ();
        abc = new ArrayList<String[]> ();

        
      
        if (!jCheckBox1.isSelected () &&  !jCheckBox5.isSelected () ) {

             // LoadReportfor all products and all customers
             loadEntries (reportType, product, _customer );           

        } else  if ( !jCheckBox1.isSelected () && jCheckBox5.isSelected ()  ) {

            // Load reports for selected customers and all products
             loadEntriesSelectedProduct (reportType , product );           

        }else  if ( jCheckBox1.isSelected ()  && !jCheckBox5.isSelected () ) {

            // Load reports for selected products and all customers
       //      loadEntriesAllMachine (reportType  );           

        }else  if ( jCheckBox1.isSelected ()  && jCheckBox5.isSelected () ) {

            // Load reports for selected customer and selected products
       //      loadEntriesAllMachine (reportType  );           

        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        JCheckBox c = ( JCheckBox ) evt.getSource ();
        if ( c.isSelected () ) {
            jComboBox1.setEnabled ( false );
            
            
            
//            jComboBox2.setEnabled ( true );
//            jComboBox3.setEnabled ( true );
//            jComboBox4.setEnabled ( true );
//             jCheckBox3.setSelected ( false);
//            jCheckBox2.setSelected ( false);
//            jCheckBox4.setSelected ( false);
        } else {
            jComboBox1.setEnabled ( true );
//            jComboBox2.setEnabled ( false );
//            jComboBox3.setEnabled ( false );
//            jComboBox4.setEnabled ( false );
           // jCheckBox2.setSelected ( false);
           // jCheckBox3.setSelected ( false);
           // jCheckBox4.setSelected ( false);
            
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
if(jComboBox1.getItemCount()==0){
    JOptionPane.showMessageDialog(null, "<html><size='06'>First add product from FinishedGood Master ");
}             // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseClicked
if(jComboBox6.getItemCount()==0){
    JOptionPane.showMessageDialog(null, "<html><size='06'>First add Customer from Customer Master ");
}             // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6MouseClicked

    ArrayList<String> weeklyDates = new ArrayList<String> ();
    ArrayList<String> months = new ArrayList<String> ();
    ArrayList<String> quarters = new ArrayList<String> ();
    SimpleDateFormat sdfTS = new SimpleDateFormat ( "yyyy-MM-dd" );

    SimpleDateFormat sdf = new SimpleDateFormat ( "EEEE" );
    SimpleDateFormat sdf1 = new SimpleDateFormat ( "MMM" );
    SimpleDateFormat sdf4 = new SimpleDateFormat ( "yyyy" );
    SimpleDateFormat sdf3 = new SimpleDateFormat ( "dd" );

    long totalNoOfDays = 0;

    public void showWeek () {

//        int i=0;
//        while( i < weeklyDates.size ()){
//            weeklyDates.remove ( i );
//            i++ ;
//        }
        weeklyDates = new ArrayList<String>();
        
        
        formatFromDate ();
        formatToDate ();

        totalNoOfDays = ( ( ( ( ( c2.getTimeInMillis () - c1.getTimeInMillis () ) / 1000 ) / 60 ) / 60 ) / 24 ) + 1;

        System.out.println ( "Total no of days " + totalNoOfDays );

        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );

        // calculation for first week of fromDate does not fall on Sunday
        String day = sdf.format ( c1.getTime () );

        //if(! (day.equalsIgnoreCase ( "Sunday"))){
        switch ( day ) {

//                case "Saturday" :      c1.add ( Calendar.DATE, 6);  totalNoOfDays=totalNoOfDays-5;  break ;
//                case "Monday" :        c1.add ( Calendar.DATE, 5);  totalNoOfDays=totalNoOfDays-4;  break ;
//                case "Tuesday" :       c1.add ( Calendar.DATE, 4);  totalNoOfDays=totalNoOfDays-3; break ;
//                case "Wednesday" :  c1.add ( Calendar.DATE, 3);  totalNoOfDays=totalNoOfDays-2; break ;
//                case "Thursday" :      c1.add ( Calendar.DATE, 2);  totalNoOfDays=totalNoOfDays-1; break ;
//                case "Friday" :          c1.add ( Calendar.DATE, 1);  totalNoOfDays=totalNoOfDays-0; break ;
            case "Sunday":
                c1.add ( Calendar.DATE , 0 );
                totalNoOfDays = totalNoOfDays - 0;
                break;
            case "Saturday":
                c1.add ( Calendar.DATE , 6 );
                totalNoOfDays = totalNoOfDays - 1;
                break;
            case "Monday":
                c1.add ( Calendar.DATE , 5 );
                totalNoOfDays = totalNoOfDays - 6;
                break;
            case "Tuesday":
                c1.add ( Calendar.DATE , 4 );
                totalNoOfDays = totalNoOfDays - 5;
                break;
            case "Wednesday":
                c1.add ( Calendar.DATE , 3 );
                totalNoOfDays = totalNoOfDays - 4;
                break;
            case "Thursday":
                c1.add ( Calendar.DATE , 2 );
                totalNoOfDays = totalNoOfDays - 3;
                break;
            case "Friday":
                c1.add ( Calendar.DATE , 1 );
                totalNoOfDays = totalNoOfDays - 2;
                break;

        }
        //}

        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );

        System.out.println ( "" );

        // Calculation for the week if fromDate fall on Sunday and all subsequent weeks till toDate does not fall on friday
        // Calculation for the last week when toDate fall on Friday
        // Calculation for inbetween weeks
        while ( totalNoOfDays > 1 ) {

            c1.add ( Calendar.DATE , 1 );
            weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );

            if ( ( int ) totalNoOfDays < 7 ) {
                switch ( ( int ) totalNoOfDays ) {
                    case 0:
                        c1.add ( Calendar.DATE , 0 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 0;
                        break;
                    case 1:
                        c1.add ( Calendar.DATE , 0 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 1;
                        break;
                    case 2:
                        c1.add ( Calendar.DATE , 1 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 2;
                        break;
                    case 3:
                        c1.add ( Calendar.DATE , 2 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 3;
                        break;
                    case 4:
                        c1.add ( Calendar.DATE , 3 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 4;
                        break;
                    case 5:
                        c1.add ( Calendar.DATE , 4 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 5;
                        break;
                    case 6:
                        c1.add ( Calendar.DATE , 5 );
                        weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                        totalNoOfDays = totalNoOfDays - 5;
                        break;
                }
            } else {

                c1.add ( Calendar.DATE , 6 );
                weeklyDates.add ( sdfTS.format ( c1.getTime () ) + " 00:00:00" );
                totalNoOfDays = totalNoOfDays - 7;

            }
        }

        //   for( int i=0, j=i+1; j<weeklyDates.size ();  i=i+2, j+=2 ){
        //    System.out.println( weeklyDates.get ( i) +" "+  weeklyDates.get(j));
        //    }
    }

    public String showMonths () {

        formatFromDate ();
        Calendar c = Calendar.getInstance ();

        // System.out.println( "From month is  "+ c1.getActualMaximum ( Calendar.DAY_OF_MONTH));
        // System.out.println( "From month is  "+sdf1.format(c1.getTime()));
        for ( int i = 2 ; i <= 13 ; i ++ ) {

            c.set ( Calendar.DATE , 1 );
            c.set ( Calendar.MONTH , i + 1 );
            c.set ( Calendar.YEAR , Integer.parseInt ( sdf4.format ( c1.getTime () ) ) );

            c.set ( Calendar.HOUR , 0 );
            c.set ( Calendar.MINUTE , 0 );
            c.set ( Calendar.SECOND , 0 );

            // System.out.print( sdf2.format ( c.getTime ()) +" 00:00:00" );
            months.add ( sdf2.format ( c.getTime () ) + " 00:00:00" );

            c.set ( Calendar.DATE , c.getActualMaximum ( Calendar.DAY_OF_MONTH ) );
            c.set ( Calendar.MONTH , i + 1 );
            c.set ( Calendar.YEAR , Integer.parseInt ( sdf4.format ( c1.getTime () ) ) );

            c.set ( Calendar.HOUR , 0 );
            c.set ( Calendar.MINUTE , 0 );
            c.set ( Calendar.SECOND , 0 );

            //  System.out.println( "    "+sdf2.format ( c.getTime ())  +" 00:00:00" );
            months.add ( sdf2.format ( c.getTime () ) + " 00:00:00" );

        }

        // System.out.println( "");
        // Calculation for the week if fromDate fall on Sunday and all subsequent weeks till toDate does not fall on friday
        // Calculation for the last week when toDate fall on Friday
        return sdf4.format ( c1.getTime () );
    }

    public String showQuarters () {

        formatFromDate ();
        Calendar c = Calendar.getInstance ();

        // System.out.println( "From month is  "+ c1.getActualMaximum ( Calendar.DAY_OF_MONTH));
        // System.out.println( "From month is  "+sdf1.format(c1.getTime()));
        for ( int i = 3 ; i <= 14 ; i += 3 ) {

            c.set ( Calendar.DATE , 1 );
            c.set ( Calendar.MONTH , i );
            c.set ( Calendar.YEAR , Integer.parseInt ( sdf4.format ( c1.getTime () ) ) );

            c.set ( Calendar.HOUR , 0 );
            c.set ( Calendar.MINUTE , 0 );
            c.set ( Calendar.SECOND , 0 );

            // System.out.print( sdf2.format ( c.getTime ()) +" 00:00:00" );
            quarters.add ( sdf2.format ( c.getTime () ) + " 00:00:00" );

            c.set ( Calendar.MONTH , i + 2 );
            c.set ( Calendar.DATE , c.getActualMaximum ( Calendar.DAY_OF_MONTH ) );
            c.set ( Calendar.YEAR , Integer.parseInt ( sdf4.format ( c1.getTime () ) ) );

            c.set ( Calendar.HOUR , 0 );
            c.set ( Calendar.MINUTE , 0 );
            c.set ( Calendar.SECOND , 0 );

            //  System.out.println( "    "+sdf2.format ( c.getTime ())  +" 00:00:00" );
            quarters.add ( sdf2.format ( c.getTime () ) + " 00:00:00" );

        }

        for ( int i = 0, j = i + 1 ; j < quarters.size () ; i = i + 2 , j += 2 ) {

            System.out.println ( quarters.get ( i ) + " " + quarters.get ( j ) );
        }

        // System.out.println( "");
        // Calculation for the week if fromDate fall on Sunday and all subsequent weeks till toDate does not fall on friday
        // Calculation for the last week when toDate fall on Friday
        return sdf4.format ( c1.getTime () );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserCombo dateChooserCombo4;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox5;
    private static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
