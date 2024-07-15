import java.util.*;
class Solution {
    public int[] solution(String myString) {
        String[] tokens = myString.split("x", -1); // 끝 빈 문자열 포함
        
        int[] answer = new int[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
            answer[i] = tokens[i].length();
        }
        
        return answer;
    }
    
    private int[] solve(String myString) {
        
        List<Integer> result = new ArrayList<>();
        int length = myString.length();
        for(int L = 0; L < length; L++) {
            int R = L;
            while(R < length && myString.charAt(R) != 'x') {
                R += 1;
            }
            
            result.add(R - L);
            
            L = R;
        }
        
        if(myString.endsWith("x")) {
            result.add(0);
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}