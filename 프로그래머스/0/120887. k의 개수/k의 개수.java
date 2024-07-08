class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        
        for(int x = i; x <= j; x++) {
            answer += count(x, k);
        }
        
        return answer;
    }
    
    private int count(int value, int k) {
        int result = 0;
        while(value > 0) {
            int mod = value % 10;
            
            if(mod == k) result += 1;
            
            value /= 10;
        }
        
        return result;
    }
}