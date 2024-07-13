import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] query) {
        int start = 0;
        int end = arr.length - 1;
        
        for(int i = 0; i < query.length; i++) {
            int idx = query[i];
            if(i % 2 == 0) {
                end = start + idx;
            } else {
                start += idx;
            }
        }
        
        return Arrays.copyOfRange(arr, start, end + 1);
    }
}