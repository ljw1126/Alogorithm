import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2]; // 0: 이진 변환 횟수, 1: 변환 과정    
        while(!s.equals("1")) {
            int len = s.length();
            
            answer[0] += 1;
            answer[1] += len;
            
            String earased = s.replaceAll("0", "");
            answer[1] -= earased.length();
            
            s = Integer.toBinaryString(earased.length());
        }
        
        return answer;
    }
}