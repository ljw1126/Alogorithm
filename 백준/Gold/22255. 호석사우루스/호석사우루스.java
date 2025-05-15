import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, m, sx, sy, ex, ey;
    private static int[][] board;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        sx = inputProcessor.nextInt();
        sy = inputProcessor.nextInt();
        ex = inputProcessor.nextInt();
        ey = inputProcessor.nextInt();

        board = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                board[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static void pro() {
        sb.append(dijkstra());
    }

    private static final int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    private static int dijkstra() {
        Queue<Dinor> pq = new PriorityQueue<>();
        pq.add(new Dinor(sx, sy, 1, 0));

        int[][][] dist = new int[n + 1][m + 1][4];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        int result = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Dinor cur = pq.poll();

            if(dist[cur.x][cur.y][cur.turn] < cur.dist) continue;
            if(cur.x == ex && cur.y == ey) {
                result = Math.min(result, cur.dist);
                continue;
            }

            int start = 0;
            int end = 3;
            if(cur.turn == 1) end = 1;
            else if(cur.turn == 2) start = 2;

            for(int i = start; i <= end; i++) {
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > n || dy > m) continue;
                if(board[dx][dy] == -1) continue;

                int turn = (cur.turn + 1) % 3;
                if(dist[dx][dy][turn] > cur.dist + board[dx][dy]) {
                    dist[dx][dy][turn] = cur.dist + board[dx][dy];
                    Dinor next = new Dinor(dx, dy, (cur.turn + 1) % 3, dist[dx][dy][turn]);
                    pq.add(next);
                }
            }

        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static class Dinor implements Comparable<Dinor>{
        private int x;
        private int y;
        private int turn;
        private int dist;

        public Dinor(int x, int y, int turn, int dist) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.dist = dist;
        }

        public int compareTo(Dinor o) {
            return this.dist - o.dist;
        }
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}