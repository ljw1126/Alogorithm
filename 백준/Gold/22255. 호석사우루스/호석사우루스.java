import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M;
    static int X1, Y1, X2, Y2; 

    static int[][] board;

    static int[][][] dir = {
            {
                {0, 1}, {0, -1}, {-1, 0}, {1, 0}
            },
            {
                {-1, 0}, {1, 0}
            },
            {
                {0, -1}, {0, 1}
            }
    };

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        X1 = Integer.parseInt(st.nextToken()); // 출발지
        Y1 = Integer.parseInt(st.nextToken());
        X2 = Integer.parseInt(st.nextToken()); // 도착지
        Y2 = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Dinosaur implements Comparable<Dinosaur> {
        int x;
        int y;
        int turn;
        int impact;

        public Dinosaur(int x, int y, int turn, int impact) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.impact = impact;
        }

        @Override
        public int compareTo(Dinosaur other) {
            return impact - other.impact;
        }
    }

    static void bfs(int startX, int startY, int endX, int endY) {
        Queue<Dinosaur> que = new PriorityQueue<>();
        que.add(new Dinosaur(startX, startY, 0, 0));

        // 초기화
        int[][][] dist = new int[N + 1][M + 1][3];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        dist[startX][startY][0] = 0;

        // 실행
        while(!que.isEmpty()) {
            Dinosaur cur = que.poll();
            int turn = cur.turn;

            if(dist[cur.x][cur.y][cur.turn] < cur.impact) continue;
            if(cur.x == endX && cur.y == endY) {
                break;
            }

            int nextTurn = (turn + 1) % 3;
            for(int i = 0; i < dir[nextTurn].length; i++) {
                int dx = cur.x + dir[nextTurn][i][0];
                int dy = cur.y + dir[nextTurn][i][1];

                if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if(board[dx][dy] == -1) continue;

                if(cur.impact + board[dx][dy] < dist[dx][dy][nextTurn]) {
                    dist[dx][dy][nextTurn] = cur.impact + board[dx][dy];
                    que.add(new Dinosaur(dx, dy, nextTurn, dist[dx][dy][nextTurn]));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int k = 0; k < 3; k++) {
            result = Math.min(result, dist[endX][endY][k]);
        }

        if(result == Integer.MAX_VALUE) result = -1;

        System.out.println(result);
    }

    static void pro() {
        bfs(X1, Y1, X2, Y2);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}