import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] newBoard = new int[row + 2][col + 2];
        for(int i = 0; i <= row + 1; i++) {
            Arrays.fill(newBoard[i], 1);
        }
        
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                newBoard[i][j] = board[i - 1][j - 1];
            }
        }
        
        Deque<Robot> que = new ArrayDeque<>();
        Robot start = new Robot(1, 1, 1, 2, 0);
        que.add(start);
        
        Set<Robot> visited = new HashSet<>();
        visited.add(start);
        
        int answer = -1;
        while(!que.isEmpty()) {
            Robot cur = que.poll();
            
            if(cur.isFinish(row, col)) {
                answer = cur.dist;
                break; 
            }
            
            List<Robot> nexts = findNexts(cur, newBoard);
            for(Robot next : nexts) {         
                boolean isPossible = true;
                for(Robot v : visited) {
                    if(v.visited(next)) {
                        isPossible = false;
                        break;
                    }
                }
                
                if(isPossible) {
                  que.add(next);
                  visited.add(next);
                }
            }
        }
        
        return answer;
    }
    
    private static class Robot {
        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;
        private final int dist;
        
        public Robot(int x1, int y1, int x2, int y2, int dist) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dist = dist;
        }
        
        public boolean isFinish(int x, int y) {
            return (x1 == x && y1 == y)
                || (x2 == x && y2 == y);
        }
       
        public boolean visited(Robot o) {
            return this.x1 == o.x1
                && this.y1 == o.y1
                && this.x2 == o.x2
                && this.y2 == o.y2;
        }
        
        public boolean isHosizontal() {
            return this.x1 == this.x2;
        }
        
        public boolean isVertical() {
            return this.y1 == this.y2;
        }
    }
    
    private final int[][] DIR = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    
    private List<Robot> findNexts(Robot robot, int[][] maps) {
        List<Robot> result = new ArrayList<>();
        
        int x1 = robot.x1;
        int y1 = robot.y1;
        int x2 = robot.x2;
        int y2 = robot.y2;
        int dist = robot.dist;
        for(int i = 0; i < 4; i++) {
            int dx1 = x1 + DIR[i][0];
            int dy1 = y1 + DIR[i][1];
            int dx2 = x2 + DIR[i][0];
            int dy2 = y2 + DIR[i][1];
            
            if(maps[dx1][dy1] == 1 || maps[dx2][dy2] == 1) continue;
            
            result.add(new Robot(dx1, dy1, dx2, dy2, dist + 1));
        }
        
        if(robot.isHosizontal()) { 
            // 위, 아래로 회전 가능(4가지 경우)
            if(maps[x1 - 1][y1] == 0 && maps[x2 - 1][y2] == 0) {
                result.add(new Robot(x1 - 1, y1, x1, y1, dist + 1));
                result.add(new Robot(x2 - 1, y2, x2, y2, dist + 1));
            }
            
            if(maps[x1 + 1][y1] == 0 && maps[x2 + 1][y2] == 0) {
                result.add(new Robot(x1, y1, x1 + 1, y1, dist + 1));
                result.add(new Robot(x2, y2, x2 + 1, y2, dist + 1));
            }
        }
        
        if(robot.isVertical()) {
            if(maps[x1][y1 - 1] == 0 && maps[x2][y2 - 1] == 0) {
                result.add(new Robot(x1, y1 - 1, x1, y1, dist + 1));
                result.add(new Robot(x2, y2 - 1, x2, y2, dist + 1));
            }
            
            if(maps[x1][y1 + 1] == 0 && maps[x2][y2 + 1] == 0) {
                result.add(new Robot(x1, y1, x1, y1 + 1, dist + 1));
                result.add(new Robot(x2, y2, x2, y2 + 1, dist + 1));
            }
        }
        
        return result;
    }
}