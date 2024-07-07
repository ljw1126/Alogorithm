import java.util.*;

class Solution {
    private static int MOD = 1234567;
    
    public int solution(int n) {
        int answer = 0;
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        
        return rec(n, dp);
    }
    
    public int rec(int n, int[] dp) {
        if(n <= 1) {
            return dp[n];
        }
        
        if(dp[n] != Integer.MAX_VALUE) {
            return dp[n];
        }
        
        dp[n] = rec(n - 1, dp) + rec(n - 2, dp);
        dp[n] %= MOD;
        return dp[n];
    }
}