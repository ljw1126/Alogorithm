import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        myStr = myStr.replaceAll("[abc]+", ",");
        myStr = myStr.charAt(0) == ',' ? myStr.substring(1) : myStr;
        myStr = "".equals(myStr) ? "EMPTY" : myStr;
        
        return myStr.split(",");
    }
    
    public String[] solve(String myStr) {
        String[] tokens = myStr.split("[abc]");
        
        if(tokens.length == 0) return new String[] {"EMPTY"};
       
        return Arrays.stream(tokens)
            .filter(s -> !s.isBlank())
            .toArray(String[]::new);
    }
}