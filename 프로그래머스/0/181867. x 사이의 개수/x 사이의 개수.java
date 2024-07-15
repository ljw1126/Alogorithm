import java.util.*;
class Solution {
    public int[] solution(String myString) {
        
        List<Integer> result = new ArrayList<>();
        int length = myString.length();
        for(int L = 0; L < length; L++) {
            int R = L;
            while(R < length && myString.charAt(R) != 'x') {
                R += 1;
            }
            
            result.add(R - L);
            System.out.println(L + "," + R);
            
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