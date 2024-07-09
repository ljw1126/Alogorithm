import java.util.*;

class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);
        
        int answer = 0;
        
        // 가장 긴변
        int max = sides[1];
        for(int i = 1; i <= max; i++) {
            if(i + sides[0] > max) answer += 1;
        }
        
        // 나머지 한 변이 가장 긴변인 경우
        int sum = sides[0] + sides[1];
        for(int i = max; i < sum; i++) {
            if(sum > i) answer += 1;
        }
        
        return answer - 1;
    }
}