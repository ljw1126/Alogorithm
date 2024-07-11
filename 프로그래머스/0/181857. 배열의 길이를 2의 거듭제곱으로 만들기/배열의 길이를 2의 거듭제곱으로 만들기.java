import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int length = arr.length;
        int size = size(length);
        
        int[] answer = new int[size];
        Arrays.fill(answer, 0);
        
        System.arraycopy(arr, 0, answer, 0, arr.length);
        
        return answer;
    }
    
    private int size(int n) {
        if((n & (n - 1)) == 0) {
            return n;
        }
        
        
        int size = 1;
        while(size < n) {
            size <<= 1;
        }
        
        return size;
    }
}