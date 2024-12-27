import java.util.*;
import java.io.*;

class Solution {
    private static final int INF = 9;
    
    private int N, RESULT, LEN;
    private int[] WEAK, DIST;
    
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        WEAK = weak;
        DIST = dist;
        RESULT = INF;
        LEN = dist.length;
        
        Arrays.sort(DIST); // 오름차순 정렬
        for(int i = 0; i < WEAK.length; i++) {
            rec(1, i, 0);
        }
        
        return RESULT == INF ? -1 : RESULT;
    }
    
    private void rec(int count, int position, long visited) {
        if(count > DIST.length) return;
        if(count >= RESULT) return;
        
        for(int i = 0; i < WEAK.length; i++) {
            int next = (position + i) % WEAK.length;
            int diff = WEAK[next] - WEAK[position];
            
            if(next < position) {
                diff += N;
            }
            
            if(diff > DIST[DIST.length - count]) {
                break;
            }
            
            visited |= (1 << next); // 방문 표시
        }
        
        if(visited == ((1 << WEAK.length) - 1)) {
            RESULT = count;
            return;
        }
        
        // 아직 다 방문 하지 않았다면
        for(int i = 0; i < WEAK.length; i++) {
            if((visited & (1 << i)) != 0) continue;
            
            rec(count + 1, i, visited);
        }
    }
    
}