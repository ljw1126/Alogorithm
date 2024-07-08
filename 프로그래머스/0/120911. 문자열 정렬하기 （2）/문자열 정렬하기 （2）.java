import java.util.*;
import java.io.*;

class Solution {
    public String solution(String my_string) {
        String answer = "";
        
        my_string = my_string.toLowerCase();
        String[] tokens = my_string.split("");
        
        Arrays.sort(tokens);
        
        StringBuilder sb = new StringBuilder();
        for(String t : tokens) {
            sb.append(t);
        }
        
        return sb.toString();
    }
}