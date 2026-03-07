package TestCases;

import java.util.HashMap;
import java.util.Map;

public class test123 {

public static void main(String[] args) {
   /*
   A pangram is a string that contains every letter of the alphabet (A-Z) at least once, such as:
            "The quick brown fox jumps over the lazy dog."


            import java.util.*;

public class PangramCheck {
    public static void main(String[] args) {
        String input = "The quick brown fox jumps over the lazy dog";
        System.out.println("Is Pangram: " + isPangram(input));
    }

    public static boolean isPangram(String str) {
        // Convert to lowercase to ignore case sensitivity
        str = str.toLowerCase();

        Set<Character> alphabetSet = new HashSet<>();

        for (char c : str.toCharArray()) {
            // Only add if the character is a letter [cite: 161]
            if (c >= 'a' && c <= 'z') {
                alphabetSet.add(c);
            }
        }

        // If the set contains 26 unique letters, it's a pangram
        return alphabetSet.size() == 26;
    }
}

    */

    String s1=   "The quick brown fox jumps over the lazy dog.";


    s1=s1.toUpperCase();
    HashMap<Character, Integer> map = new HashMap<>();

    for (char ch = 'A' ; ch <= 'Z'; ch++) {
        map.put(ch, 0);
    }

    for(int i=0;i<s1.length();i++)
    {
        char c1=s1.charAt(i);
        if(map.containsKey(c1))
        {
            map.put(c1,map.getOrDefault(c1,0)+1);
        }

    }

    for(Map.Entry<Character,Integer>entry : map.entrySet())
            {
                System.out.println(entry.getKey()+"       "+entry.getValue());
            }





}



}