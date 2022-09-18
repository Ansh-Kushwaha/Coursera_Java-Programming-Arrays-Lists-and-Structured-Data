
/**
 * Storing words from multiple files in a map together with the corresponding file name they belong to.
 * 
 * @author Ansh Kushwaha
 * @version 1.0.0
 */
import java.util.*;
    import edu.duke.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String> > wordsMap;
    
    WordsInFiles(){
        wordsMap = new HashMap<String, ArrayList<String> >();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String name = f.getName();
        
        for(String w : fr.words()){
            //w = w.toLowerCase();
            if(!wordsMap.containsKey(w)){
                ArrayList<String> newFileNameList = new ArrayList<String>();
                newFileNameList.add(name);
                wordsMap.put(w, newFileNameList);
            }
            else if(wordsMap.containsKey(w) && !wordsMap.get(w).contains(name)){
                ArrayList<String> currFileNameList = wordsMap.get(w);
                currFileNameList.add(name);
                wordsMap.put(w, currFileNameList);
            }
        }
    }
    
    public void buildWordFileMap(){
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String w : wordsMap.keySet()){
            int temp = wordsMap.get(w).size();
            if(temp > max)
                max = temp;
        }
        return max;
    }
    
    public ArrayList wordsInNumFiles(int number){
        ArrayList<String> wordsInNFiles = new ArrayList<String>();
        for(String w : wordsMap.keySet()){
            if((wordsMap.get(w)).size() == number)
                wordsInNFiles.add(w);
        }
        return wordsInNFiles;
    }
    
    public void printFilesin(String word){
        for(String w : wordsMap.get(word)){
            System.out.println(w);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        //for(String w : wordsMap.keySet()){
        //    System.out.println(w + " " + wordsMap.get(w));
        //}
        ArrayList<String> words = wordsInNumFiles(7);
        System.out.println("The number of words in all 7 files : " + words.size()); //required
        printFilesin("tree");
    }
}
