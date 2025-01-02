import java.util.*;

class Solution {
    private int answer, len, _n;
    private int[] WEAK, DIST;
    
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        
        _n = n;
        WEAK = weak;
        len = WEAK.length;
        DIST = dist;
        Arrays.sort(DIST);
        
        for(int i = 0; i < WEAK.length; i++) {
            rec(i, 1, 0);
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void rec(int cur, int used, int visited) {
        if(used > DIST.length) return; // 모든 사람을 다 써도 안 되는 경우 
        if(used >= answer) return; // 결과값보다 크다면
        
        for(int i = 0; i < len; i++) {
            int next = (cur + i) % len;
            int diff = WEAK[next] - WEAK[cur];
            
            if(cur > next) {
                diff += _n;
            }
            
            if(DIST[DIST.length - used] < diff) {
                break;
            }            
                
            visited |= (1 << next);
        }
        
        
        // 다 방문한 경우
        if(visited == (1 << len) - 1) {
            answer = Math.min(answer, used);
            return;
        }
        
        // 아직 방문이 필요한 경우
        for(int i = 0; i < len; i++) {
            if((visited & (1 << i)) != 0) continue;
            
            rec(i, used + 1, visited);
        }
    }
}