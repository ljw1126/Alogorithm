import java.util.*;

class Solution {
    public String solution(String s) {
        String[] tokens = s.split(" ");
        int[] nums = new int[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        
        Arrays.sort(nums);
        
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0])
            .append(" ")
            .append(nums[nums.length - 1]);
        
        return sb.toString();
    }
}