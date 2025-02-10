import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2]; // 0: 이진 변환 횟수, 1: 변환 과정
        
        int v1 = 0;
        int v2 = 0;
        
        while(!s.equals("1")) {
            v1 += 1;

            int len = s.length();
        
            s = erase(s); 
            v2 += len - s.length();
            
            s = Integer.toBinaryString(s.length());
        }
        
        answer[0] = v1;
        answer[1] = v2;
        
        return answer;
    }
    
    private String erase(String s) {
        StringBuilder result = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c == '1') result.append(c);
        }
        
        return result.toString();
    }
}