import java.util.stream.*;

class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        return IntStream.range(0, queries.length)
            .map(q -> IntStream.rangeClosed(queries[q][0], queries[q][1])
                 .map(i -> arr[i])
                 .filter(v -> queries[q][2] < v)
                 .min()
                 .orElse(-1)
            ).toArray();
    }
    
    public int[] solve(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int idx = 0;
        for(int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int s = query[0];
            int e = query[1];
            int k = query[2];
            
            int result = 1_000_001;
            for(int j = s; j <= e; j++) {
                if(k < arr[j]) {
                    result = Math.min(result, arr[j]);
                }
            }
            
            answer[idx++] = result == 1_000_001 ? -1 : result;
        }
        
        return answer;
    }
}