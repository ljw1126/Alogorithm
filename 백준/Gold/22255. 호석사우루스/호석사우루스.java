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

    private static int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    private static int n, m, sx, sy, ex, ey;
    private static int[][] board;


    private static void input() {
        n = inputProcessor.nextInt(); // 행
        m = inputProcessor.nextInt(); // 열
        // 출발지, 도착지
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
        /*int[][][] dist = new int[n + 1][m + 1][4];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        Arrays.fill(dist[sx][sy], 0);*/
        boolean[][][] visited = new boolean[n + 1][m + 1][3];

        Queue<Dinor> pq = new PriorityQueue<>();
        pq.add(new Dinor(sx, sy, 0, 1));
        int result = -1;

        while(!pq.isEmpty()) {
            Dinor cur = pq.poll();

            if(cur.x == ex && cur.y == ey) {
                result = cur.dist;
                break;
            }

            int start = 0;
            int end = 4;
            if(cur.turn == 1) { // 위 아래
                end = 2;
            }

            if(cur.turn == 2) { // 왼쪽 오른쪽
                start = 2;
            }

            for(int i = start; i < end; i++) {
                int dx = cur.x + DIR[i][0];
                int dy = cur.y + DIR[i][1];

                if(dx < 1 || dx > n || dy < 1 || dy > m) continue;
                if(board[dx][dy] == -1) continue;
                if(visited[dx][dy][cur.turn]) continue;

                visited[dx][dy][cur.turn] = true;
                pq.add(new Dinor(dx, dy, cur.dist + board[dx][dy], (cur.turn + 1) % 3));
            }
        }


        sb.append(result);
    }

    private static class Dinor implements Comparable<Dinor>{
        private final int x;
        private final int y;
        private final int dist;
        private final int turn;

        public Dinor(int x, int y, int dist, int turn) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.turn = turn;
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