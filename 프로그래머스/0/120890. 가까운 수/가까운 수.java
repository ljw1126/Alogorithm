import java.util.*;

class Solution {
    public int solution(int[] array, int n) {
        Arrays.sort(array);
        
        int min = 101;
        int answer = 0;
        for(int i = 0; i < array.length; i++) {
            int diff = Math.abs(array[i] - n) ;
            if(diff < min) {
                min = diff;
                answer = array[i];
            }
        }
        
        return answer;
    }
}