import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
       return Arrays.stream(arr)
           .boxed()
           .flatMap(v -> Collections.nCopies(v, v).stream())
           .mapToInt(Integer::intValue)
           .toArray();
    }
    
    public int[] solve(int[] arr) {
        int length = 0;
        for(int i = 0; i < arr.length; i++) {
            length += arr[i];
        }
        
        int[] answer = new int[length];
        int start = 0;
        for(int i = 0; i < arr.length; i++) {
            Arrays.fill(answer, start, start + arr[i], arr[i]);
            start += arr[i];
        }
        
        return answer;
    }
}