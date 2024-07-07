import java.util.*;

class Solution {
    public boolean solution(String s) {
        int length = s.length();
        if(!(length == 4 || length == 6)) {
            return false;
        }
        
        boolean answer = true;
        for(int i = 0; i < length; i++) {
            if(!Character.isDigit(s.charAt(i))) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}