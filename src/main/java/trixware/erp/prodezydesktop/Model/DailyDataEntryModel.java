/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trixware.erp.prodezydesktop.Model;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Rajesh
 */
public class DailyDataEntryModel{

    private int productId;
    private String FromDate;
    private String FromTime;
    private String ToDate;
    private String ToTime;
    private String  Processname;
    private String ProcessID;
    private String QIn;
    private String Qout;
    private String Rejected;
    public ArrayList<EmployeeDR> Employee;
    public ArrayList<MachineDR> Machine;
    
    public ArrayList<UOMDR> UOM;
    public ArrayList<Rejection_Reasons> RejRsn;
    
    public ArrayList<TimeLossModel> timeLossData;
    public  ArrayList<ShiftDR> shiftData ;
    
    private int  SelEmployee;
    private int SelMachine;
    private int SelRejRsn;
    private int SelShift ;
    private int UomIn;
    private int UomOut;
    private int UomOutRej;
    public ArrayList<TimeLossModel> selectedtimeLossData = new ArrayList<TimeLossModel>();
    public ArrayList<RejectionModel> selectedtimeRejections = new ArrayList<RejectionModel>();

    private String rdate ; 
    private String rtdate ;
           
    private String starttime ;
    private String stoptime ; 
     
    private int totalTimeLossInMinutes = 0;
    private double totalTimeLossInHours = 0.0 ;
    
    private long totalTimeInMinutes = 0 ;
    private double totalTimeInHours = 0.0 ;
    
    SimpleDateFormat sdf2 = new SimpleDateFormat ( "yyyy-MM-dd" );
    
    public DailyDataEntryModel(){
    
    }


    public DailyDataEntryModel(String fromDate, String fromTime, String toDate, String toTime, String processname, String processID, String QIn, String qout, String rejected, ArrayList<EmployeeDR> employee, ArrayList<MachineDR> machine, ArrayList<Rejection_Reasons> rejrsn) {
        FromDate = fromDate;
        FromTime = fromTime;
        ToDate = toDate;
        ToTime = toTime;
        Processname = processname;
        ProcessID = processID;
        this.QIn = QIn;
        Qout = qout;
        Rejected = rejected;
        Employee = employee;
        Machine = machine;
        RejRsn = rejrsn;
    }
    
    
    public void setProductId( int productid ){
        productId = productid ;
    }
    
    
    public int getProductId(){
    
        return productId ;
    }

     public String getPFromDate() {
        return rdate;
    }

    public String getPFromTime() {
        return starttime;
    }
    
    public void setUomIn(int uin) {
        UomIn = uin;
    }
    public int getUomIn() {
        return UomIn;
    }
    public void setUomOut(int uout) {
        UomOut = uout;
    }
    public int getUomOut() {
        return UomOut;
    }
    public void setUomOutRej(int urej) {
        UomOutRej = urej;
    }
    public int getUomOutRej() {
        return UomOutRej;
    } 

    
     public String getPToDate() {
        return rtdate;
    }

    public String getPToTime() {
        return stoptime;
    }
    
    public String getFromDate() {
        return FromDate;
    }

    public String getFromTime() {
        return FromTime;
    }

    public String getToDate() {
        return ToDate;
    }

    public String getToTime() {
        return ToTime;
    }

    public String getProcessname() {
        return Processname;
    }

    public String getProcessID() {
        return ProcessID;
    }

    public String getQIn() {
        return QIn;
    }

    public String getQout() {
        return Qout;
    }

    public String getRejected() {
        return Rejected;
    }

    public int getEmployee() {
        return SelEmployee;
    }

    public int getMachine() {
        return SelMachine;
    }
    
    public int getRej_Reason() {
        return SelRejRsn;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public void setFromTime(String fromTime) {
        FromTime = fromTime;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public void setToTime(String toTime) {
        ToTime = toTime;
    }

    public void setProcessname(String processname) {
        Processname = processname;
    }

    public void setProcessID(String processID) {
        ProcessID = processID;
    }

    public void setQIn(String QIn) {
        this.QIn = QIn;
    }

    public void setQout(String qout) {
        Qout = qout;
    }

    public void setRejected(String rejected) {
        Rejected = rejected;
    }

    public void setEmployee(int employee) {
        SelEmployee = employee;
    }

    public void setMachine(int machine) {
        SelMachine = machine;
    }
    
    public void setRejReason( int rejRsn){
        SelRejRsn = rejRsn ;
    }
    
     public void setPFromDate( String rDate ) {
        rdate = rDate ;
    }

    public void setPFromTime( String startTime) {
       //rtdate = rtDate ;
       starttime = startTime;
    }
    
     public void setPToDate( String rtDate) {
        rtdate = rtDate ;
    }

    public void setPToTime( String stopTime ) {
        stoptime = stopTime ;
    }
    
    public void setTimeLossData(  ArrayList<TimeLossModel> _timeLossData ){
        selectedtimeLossData = _timeLossData;
    }
    
    public ArrayList<TimeLossModel> getTimeLossData(){
        
        return  selectedtimeLossData;
    }

    public void setRejectionData(  ArrayList<RejectionModel> _rejectionData ){
        selectedtimeRejections = _rejectionData;
    }
    
    public ArrayList<RejectionModel> getRejectionData(){
        return selectedtimeRejections ;
    }
    
    public int getShiftID() {
        return SelShift ;
    }

    public void setShiftData ( int shiftId ) {
        this.SelShift = shiftId;
    }

    public int getTotalTimeLossInMinutes () {
        return totalTimeLossInMinutes;
    }

    public void setTotalTimeLossInMinutes ( int totalTimeLossInMinutes ) {
        this.totalTimeLossInMinutes = totalTimeLossInMinutes;
    }

//    public double getTotalTimeLossInHours () {
//        return totalTimeLossInHours;
//    }

    public void setTotalTimeLossInHours ( double totalTimeLossInHours ) {
        this.totalTimeLossInHours = totalTimeLossInHours;
    }
    
    public double getTotalTimeLossInHours( ){
    
                if(totalTimeLossInMinutes > 720 ){
                    JOptionPane.showMessageDialog ( null, "Maximum time loss in minutes for single shift can be 720 minutes only");
                    totalTimeLossInMinutes = 720 ;
                }
                
                DecimalFormat df = new DecimalFormat("#.##");
                double hr = 0.0, min = 0.0 ;
                double hr2 = 0.0, min2 = 0.0 ;
                if( totalTimeLossInMinutes > 59  ){
                     hr = (totalTimeLossInMinutes / 60) ; 
                     min = Double.parseDouble(  df.format( Double.parseDouble(df.format( totalTimeLossInMinutes % 60 ) )/60 )   );                    
                }     
                else{
                    hr = Double.parseDouble(  df.format( Double.parseDouble(df.format( totalTimeLossInMinutes % 60 ) )/60 )   );  
                }
                
                return hr+min ;

        
    }

    public long getTotalTimeInMinutes () {
        return totalTimeInMinutes;
    }

    public void setTotalTimeInMinutes ( int totalTimeInMinutes ) {
        this.totalTimeInMinutes = totalTimeInMinutes;
    }

//    public double getTotalTimeInHours () {
//        return totalTimeInHours;
//    }

    public void setTotalTimeInHours ( double totalTimeInHours ) {
        this.totalTimeInHours = totalTimeInHours;
    }
    
    public double getTotalTimeInHours (  ) {

             DecimalFormat df = new DecimalFormat("#.##");
            long totalMin;
            double totalHrs , min2 = 0.0 ;

            String fromTime = "";
            String toTime = "";
            
           try{ 
            String sDate1=getFromDate ();  
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse( getPFromDate ()); 
            
              fromTime = new SimpleDateFormat("MMM dd, yyyy").format(date1)+" " + getFromTime ();
              
              date1=new SimpleDateFormat("yyyy-MM-dd").parse( getPToDate ()); 
              
             toTime = new SimpleDateFormat("MMM dd, yyyy").format(date1)+" " + getToTime () ;

           }catch( ParseException e ){
               System.out.println ( ""+e.getMessage () );
           }

           

             LocalDateTime d1 = null , d2 = null ;
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm", Locale.ENGLISH);
            //java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            
                d1 = LocalDateTime.parse(   fromTime , formatter);
                d2 = LocalDateTime.parse(   toTime, formatter);
           
            
            Duration difference = Duration.between(d1, d2);

            totalMin = difference.toMinutes ();
            totalTimeInMinutes = totalMin ;

             if( totalMin > 59  ){
                 totalHrs  = (totalMin / 60) ; 
                 min2 = Double.parseDouble(  df.format( Double.parseDouble(df.format( totalMin % 60 ) )/60 )   );                    
            }     
            else{
               totalHrs = Double.parseDouble(  df.format( Double.parseDouble(df.format( totalMin % 60 ) )/60 )   );        
            }

            return totalHrs+min2;    
    }

}