import java.util.*;
class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < 64; i++) {
            int num = Integer.parseInt(Integer.toBinaryString(i)) * 5;
            if (l <= num && num <= r)
                list.add(num);
        }

        return list.isEmpty() ? new int[] { -1 } : list.stream().mapToInt(i -> i).toArray();
    }
    
    private int[] solve(int l, int r) {
        List<Integer> result = new ArrayList<>();
        for(int i = l; i <= r; i++) {
            if(i % 5 != 0) continue;
        
            String value = String.valueOf(i);
            if(value.matches("[05]+")) {
                result.add(i);
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer.length == 0 ? new int[] {-1} : answer;
    }
}