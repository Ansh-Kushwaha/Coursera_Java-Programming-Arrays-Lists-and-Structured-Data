
/**
 * Counting and storing frequencies of each word in a file in array lists.
 * 
 * @author Ansh Kushwaha
 * @version 1.0.0
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> words;
    private ArrayList<Integer> freqs;
    
    public WordFrequencies(){
        words = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        words.clear();
        freqs.clear();
        FileResource resource = new FileResource();
        for(String w : resource.words()){
            w = w.toLowerCase();
            int idx = words.indexOf(w);
            if(idx == -1){
                words.add(w);
                freqs.add(1);
            }
            else{
                int freq = freqs.get(idx);
                freqs.set(idx, freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int maxIdx = 0;
        for(int i=0; i<freqs.size(); i++)
            if(freqs.get(i) > freqs.get(maxIdx))
                maxIdx = i;
        return maxIdx;
    }
    public void tester(){
        findUnique();
        System.out.println("unique words : " + words.size() + "\n");
        //for(int i=0; i<words.size(); i++){
        //    System.out.println(freqs.get(i) + " " + words.get(i));
        //}
        int maxIdx = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are : " + words.get(maxIdx) + " " + freqs.get(maxIdx));
        
    }
    
}
