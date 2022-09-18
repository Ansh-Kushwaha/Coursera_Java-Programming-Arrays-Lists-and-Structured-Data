
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author Ansh Kushwaha 
 * @version 1.0.0
 */
import java.util.*;
import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
    
    public int countUniqueIps(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr))
                uniqueIPs.add(ipAddr);
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num){
        System.out.println("Log Entries that have status code greater than " + num + " :");
        for(LogEntry le : records){
            if(le.getStatusCode() > num)
                System.out.println(le);
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs1Day = new ArrayList<String>();
        for(LogEntry le : records){
            String aTime = (le.getAccessTime()).toString();
            String date = aTime.substring(4, 10);
            //System.out.println(date);
            if(date.equals(someday)){
                String ipAddr = le.getIpAddress();
                if(!uniqueIPs1Day.contains(ipAddr))
                    uniqueIPs1Day.add(ipAddr);
            }
        }
        return uniqueIPs1Day;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode >= low && statusCode <= high){
                String ipAddr = le.getIpAddress();
                if(!uniqueIPsInRange.contains(ipAddr))
                    uniqueIPsInRange.add(ipAddr);
            }
        }
        return uniqueIPsInRange.size();
    }
    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }
}
