import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        return IntStream.range(0, n)
            .mapToObj(i -> toSecretMap(Long.valueOf(arr1[i] | arr2[i]), n))
            .toArray(String[]::new);
    }
    
    private String toSecretMap(long value, int n) {
        String str = Long.toBinaryString(value);
        StringBuilder sb = new StringBuilder();
        
        if(n - str.length() > 0) {
            for(int i = 0; i < n - str.length(); i++) {
                sb.append(" ");
            }
        }
        
        for(int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i) == '1' ? "#" : " ");
        }
        
        return sb.toString();
    }
}