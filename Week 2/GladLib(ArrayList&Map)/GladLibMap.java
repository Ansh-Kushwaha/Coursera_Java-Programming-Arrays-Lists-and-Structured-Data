
/**
 * Map based version of GlabLb
 * 
 * @author Ansh Kushwaha
 * @version 1.0.0
 */
import java.util.*;
import edu.duke.*;
public class GladLibMap {
    private HashMap<String, ArrayList<String> > wordsListMap;
    private ArrayList<String> alreadyUsed = new ArrayList<String>();
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        wordsListMap = new HashMap<String, ArrayList<String> >();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        wordsListMap = new HashMap<String, ArrayList<String> >();        
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String categories[] = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for(String s: categories){
            ArrayList<String> currList = readIt(source + "/" + s + ".txt");
            wordsListMap.put(s, currList);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(wordsListMap.containsKey(label))
            return randomFrom(wordsListMap.get(label));
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(alreadyUsed.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        alreadyUsed.add(sub);
        return prefix+sub+suffix;
    }
    
    public void printAlreadyUsedWords(){
        System.out.println(alreadyUsed.size());
        for(String a : alreadyUsed){
            System.out.print(a + " , ");
        }
        System.out.println();
        alreadyUsed.clear();    
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int tWords = 0;
        for(String s : wordsListMap.keySet()){
            tWords += (wordsListMap.get(s)).size();
        }
        return tWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printAlreadyUsedWords();
        printOut(story, 60);
        System.out.println("Total number of word in wordsListMap = " + totalWordsInMap());
        
    }
}
