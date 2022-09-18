
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private String dna;
    private HashMap<String, Integer> codonMap;
    CodonCount(){
        codonMap = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna){
        codonMap.clear();
        for(int i=start; i<dna.length()-3; i+=3){
            String cdn = dna.substring(i, i+3);
                if(codonMap.containsKey(cdn)){
                codonMap.put(cdn, codonMap.get(cdn)+1);
            }
            else{
                codonMap.put(cdn, 1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String cdn = ""; 
        int freq = 0;
        for(String s : codonMap.keySet()){
            int t = codonMap.get(s);
            if(t > freq){
                freq = t;
                cdn = s;
            }
        }
        return cdn;
    }
    
    public void printCodonCounts(int start, int end){
        for(String cdn : codonMap.keySet()){
            int freq = codonMap.get(cdn);
            if(freq >= start && freq <= end)
                System.out.println(cdn + " " + freq);
        }
    }
    
    public int uniqueCount(){
        int count = 0;
        for(String s : codonMap.keySet())
            count ++;
        return count;
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        int frames = 3;
        for(int i =0; i<frames; i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + uniqueCount() + " unique codons");
            String mostCommon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommon + " with count " + codonMap.get(mostCommon));
            int start = 1;
            int end = 7;
            System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
            printCodonCounts(start, end);
        }
    }
}
