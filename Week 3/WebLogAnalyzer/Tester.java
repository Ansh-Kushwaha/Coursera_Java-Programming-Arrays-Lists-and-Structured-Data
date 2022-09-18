/**
 * Test class for LogEntry and LogAnalyzer
 * 
 * @author Ansh Kushwaha
 * @version 1.1.0
 */

import java.util.*;
public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("logEntryData/weblog1_log");
        la.printAll();
        //int num = 400;
        //la.printAllHigherThanNum(num);
        System.out.println("There are " + la.countUniqueIps()+"IPs.");
        ArrayList<String> UIPs1 = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Unique IP visits on the specified day = " + UIPs1.size());
        int low = 200;
        int high = 299;
        System.out.println("Unique IPs in range " + low + "," + high + " = " + la.countUniqueIPsInRange(low, high));
    }
}
