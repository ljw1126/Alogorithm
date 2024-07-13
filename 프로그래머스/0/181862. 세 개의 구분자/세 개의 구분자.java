import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] tokens = myStr.split("[abc]");
        
        if(tokens.length == 0) return new String[] {"EMPTY"};
       
        return Arrays.stream(tokens)
            .filter(s -> !s.isBlank())
            .toArray(String[]::new);
    }
}