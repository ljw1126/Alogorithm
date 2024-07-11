import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < flag.length; i++) {
            if(!flag[i]) {
                for(int j = 0; j < arr[i]; j++) {
                    result.remove(result.size() - 1);
                }    
            } else {
                for(int j = 0; j < arr[i] * 2; j++) {
                    result.add(arr[i]);
                }   
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}