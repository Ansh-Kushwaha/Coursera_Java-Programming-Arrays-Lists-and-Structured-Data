 /**
 * Determining the characters in one of Shakespeare’s plays that have the most speaking parts.
 * 
 * @author Ansh Kushwaha
 * @version 1.0.0
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
    private ArrayList<String> words;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay(){
        words = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int idx = words.indexOf(person);
        if(idx == -1){
            words.add(person);
            freqs.add(1);
        }
        else{
            int freq = freqs.get(idx);
            freqs.set(idx, freq+1);
        }
    }
    
    public void findAllCharacters(){
        words.clear();
        freqs.clear();
        FileResource f = new FileResource();
        for(String line : f.lines()){
            int endIdx = line.indexOf('.');
            if(endIdx != -1){
                String person = line.substring(0, endIdx);
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("characters with speaking parts between" + num1 + "&" + num2);
        for(int i=0; i<words.size(); i++){
            if(freqs.get(i) >= num1 && freqs.get(i) <= num2)
                System.out.println(freqs.get(i) + " " + words.get(i));
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i=0; i<words.size(); i++)
            if(freqs.get(i) > 1)
                System.out.println(freqs.get(i) + " " + words.get(i));
        charactersWithNumParts(10, 15);
    }
}
