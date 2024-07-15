import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> data = new ArrayList<>();
        for(int a : arr) data.add(a);
        
        Integer target = Integer.valueOf(2);
        int first = data.indexOf(target);
        int last = data.lastIndexOf(target);
        
        if(first == -1 && last == -1) return new int[] {-1};
        
        return Arrays.copyOfRange(arr, first, last + 1);
    }
}