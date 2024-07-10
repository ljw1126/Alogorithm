import java.util.stream.*;

class Solution {
    public int[] solution(int start_num, int end_num) {
        return IntStream.rangeClosed(-start_num, -end_num)
            .map(v -> -v)
            .toArray();
    }
    
    public int[] solve(int start_num, int end_num) {
        int length = start_num - end_num + 1;
        int[] answer = new int[length];
        for(int i = 0; i < length; i++) {
            answer[i] = start_num - i;
        }
        
        return answer;
    }
}