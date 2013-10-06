/**
 * @author              :KyleMHB
 * Project Number       :0002 V1.0
 * Project Name         :Kings
 * Project Path         :Anagramatic/SourcePackages/anagramatic/Anagramatic.java
 * IDE                  :NETBEANS
 * Goal of Project      - 
 * Capture user input, compare to a dictionary file for anagrams,
 * output number of matches and the matches.
 */

package anagramatic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane; 

public class Anagramatic {
public static void main(String[] args) throws FileNotFoundException{
    String anagram=getInput("Enter the word you would like to process");
    List<String> words=readAnagramsFromFile(anagram, new File("words.txt"));
    String output= formatOutput(anagram,words);
    displayOutput(output);
}//pvsm


private static String getInput(String prompt) {
    String input = JOptionPane.showInputDialog(null,prompt);
    return input;
}//getInput


private static List<String> readAnagramsFromFile(String word, File f) 
        throws FileNotFoundException{
    ArrayList<String> anagrams = new ArrayList<>(); 
    try(Scanner s = new Scanner(f)){
        while(s.hasNext()){
            String candidate=s.next();
            if (checkAnagram(word,candidate)==true){
                anagrams.add(candidate);
            }
        }
}
    return anagrams;
}//readFile


private static boolean checkAnagram(String a, String b) {
    if (a.length()!=b.length()){
        return false;
    }
    char[] aArray = a.toCharArray();
    char[] bArray = b.toCharArray();
    if (Arrays.equals(aArray, bArray)){
        return false;
    }
    Arrays.sort(aArray);
    Arrays.sort(bArray);
    if(Arrays.equals(aArray, bArray)){
        return true;
    }
    return false;
    
}//match

private static String formatOutput(String original, List<String> words) {
    StringBuilder output=new StringBuilder("[ ");
    int counter=0;
    Iterator<String> wordIt =words.iterator();
    while(wordIt.hasNext()){
       output.append(wordIt.next());
       if(wordIt.hasNext()){
           output.append((++counter % 8 == 0)? ",\n" : ", ");
       }
    }
    output.append(" ]");
    return ("The Anagram "+original+" has "+words.size()+" matches.\n\nThey are:\n"+output.toString());
}//formatOutput

private static void displayOutput(String output){
    JOptionPane.showMessageDialog(null,output);
}//displayOutput
}