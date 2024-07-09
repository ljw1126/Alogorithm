import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];
        
        List<Integer> copied = IntStream.of(emergency).boxed().collect(Collectors.toList());
        Collections.sort(copied, Comparator.reverseOrder());
        
        for(int i = 0; i < emergency.length; i++) {
            int target = emergency[i];
            answer[i] = copied.indexOf(Integer.valueOf(target)) + 1;
        }
        
        return answer;
    }
    
   
}