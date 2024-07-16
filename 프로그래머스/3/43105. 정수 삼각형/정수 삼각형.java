class Solution {
    public int solution(int[][] triangle) {
        for(int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i - 1][i - 1];
            for(int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1] , triangle[i - 1][j]);
            }
        }
        
        int row = triangle.length;
        int answer = 0;
        for(int i = 0; i < row; i++) {
            answer = Math.max(answer, triangle[row - 1][i]);
        }
                
        return answer;
    }
    
    private int solve(int[][] triangle) {
        int row = triangle.length;
        int col = triangle[row - 1].length;
        
        int[][] dp = new int[row][col];
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < row; i++) {
            int[] line = triangle[i];
            
            int length = line.length - 1;
            for(int j = 0; j <= length; j++) {
                dp[i][j] = line[j];
                
                if(j == 0) dp[i][j] += dp[i - 1][j];
                else if(0 < j && j < length) dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                else dp[i][j] += dp[i - 1][j - 1];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < col; i++) {
            answer = Math.max(answer, dp[row - 1][i]);
        }
                
        return answer;
    }
}