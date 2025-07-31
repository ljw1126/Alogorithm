import java.util.*;

class Solution {
    private Set<Integer> unique;
    
    public int solution(String numbers) {
        boolean[] prime = new boolean[10000000];
        prime[0] = true;
        prime[1] = true;
        
        for(int i = 2; i < 10000000; i++) {
            if(prime[i]) continue;
            
            for(int j = i + i; j < 10000000; j += i) {
                prime[j] = true;
            }
        }
        
        char[] chars = numbers.toCharArray();
        int[] nums = new int[chars.length];
        for(int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - '0';
        }
        
        unique = new HashSet<>();
        rec(nums, 0, 0);
        
        int answer = 0;
        for(int n : unique) {
            if(prime[n]) continue;
            
            answer += 1;
        }
        
        return answer;
    }
    
    private void rec(int[] nums, int flag, int value) {
        if(2 <= value) {
            unique.add(value);
        }
        
        for(int i = 0; i < nums.length; i++) {
            if((flag & (1 << i)) != 0) continue;
            
            rec(nums, flag | (1 << i), value * 10 + nums[i]);
        }
    }
    
    
}