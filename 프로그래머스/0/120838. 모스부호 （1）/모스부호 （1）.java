import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String letter) {
       
        Map<String, String> mos = new HashMap<>();
        mos.put(".-","a");
        mos.put("-...","b");
        mos.put("-.-.","c");
        mos.put("-..","d");
        mos.put(".","e");
        mos.put("..-.","f");
        mos.put("--.","g");
        mos.put("....","h");
        mos.put("..","i");
        mos.put(".---","j");
        mos.put("-.-","k");
        mos.put(".-..","l");
        mos.put("--","m");
        mos.put("-.","n");
        mos.put("---","o");
        mos.put(".--.","p");
        mos.put("--.-","q");
        mos.put(".-.","r");
        mos.put("...","s");
        mos.put("-","t");
        mos.put("..-","u");
        mos.put("...-","v");
        mos.put(".--","w");
        mos.put("-..-","x");
        mos.put("-.--","y");
        mos.put("--..","z");

        return Stream.of(letter.split(" "))
            .map(mos::get)
            .collect(Collectors.joining());
    }
}