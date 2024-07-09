class Solution {
    public int solution(int order) {
        int answer = 0;
        while(order > 0) {
            int mod = order % 10;
            
            if(mod != 0 && mod % 3 == 0) {
                answer += 1;
            }
            
            order /= 10;
        }
        
        return answer;
    }
}