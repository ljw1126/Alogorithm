import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        
        int row = maps.length;
        int col = maps[0].length;
        
        int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };
        
        Deque<Integer> que = new ArrayDeque<>();
        que.add(0);
        que.add(0);
        
        int[][] dist = new int[row][col];
        for(int i = 0; i < row; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        dist[0][0] = 1;
        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            
            for(int i = 0; i < 4; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];
                
                if(dx < 0 || dy < 0 || dx >= row || dy >= col) continue;
                if(maps[dx][dy] == 0) continue;
                if(dist[dx][dy] != Integer.MAX_VALUE) continue;
                
                
                dist[dx][dy] = dist[x][y] + 1;
                que.add(dx);
                que.add(dy);
            }
        }
        
        int answer = dist[row - 1][col - 1];
        if(answer == Integer.MAX_VALUE) return -1;
        
        return answer;
    }
}