class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        for(int i = 0; i < num_list.length; i++) {
            answer += count(num_list[i]);
        }
        return answer;
    }
    
    private int count(int value) {
        int result = 0;
        while(value > 1) {
            if(value % 2 == 0) {
                value /= 2;
            } else {
                value -= 1;
                value /= 2;
            }
            
            result += 1;
        }
        
        return result;
    }
}