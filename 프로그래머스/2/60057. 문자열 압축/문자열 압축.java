import java.util.*;
import java.io.*;

class Solution {
    
    private StringBuilder sb = new StringBuilder();
    
    public int solution(String s) {
        int answer = s.length();
        int half = s.length() / 2;
        for(int len = 1; len <= half; len++) {
            String compressed = rec(s, len, 1);
            
            answer = Math.min(answer, compressed.length());  
            
            sb.setLength(0);
        }
        
        return answer;
    }
    
    private String rec(String s, int len, int repeat) {
        if(s.length() < len) {
            sb.append(s);
            return sb.toString();
        }
        
        String pre = s.substring(0, len);
        String post = s.substring(len);
        
        // 반복된다면
        if(post.startsWith(pre)) {
            return rec(post, len, repeat + 1);
        }
        
        // 반복되지 않는다면
        if(repeat == 1) sb.append(pre);
        else sb.append(String.valueOf(repeat).concat(pre));
        
        return rec(post, len, 1);
    }
}