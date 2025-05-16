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

    private static int n, m;
    private static int[][] board;
    private static List<int[]> babyShark;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        babyShark = new ArrayList<>();
        board = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                int v = inputProcessor.nextInt();

                board[i][j] = v;

                if(v == 1) {
                    babyShark.add(new int[]{i, j});
                }
            }
        }
    }

    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };

    private static void pro() {
        int[][] dist = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Deque<Shark> que = new ArrayDeque<>();
        for(int[] shark : babyShark) {
            que.add(new Shark(shark[0], shark[1], 0));
            dist[shark[0]][shark[1]] = 0;
        }

        while(!que.isEmpty()) {
            Shark shark = que.poll();
            int x = shark.x;
            int y = shark.y;
            int cost = shark.cost;

            if(dist[x][y] < cost) continue;

            for(int i = 0; i < 8; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > n || dy > m) continue;
                if(board[dx][dy] == 1) continue;

                if(dist[dx][dy] > cost + 1) {
                    dist[dx][dy] = cost + 1;
                    que.add(new Shark(dx, dy, dist[dx][dy]));
                }
            }
        }

        int result = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                result = Math.max(result, dist[i][j]);
            }
        }

        sb.append(result);
    }

    private static class Shark {
        private int x;
        private int y;
        private int cost;

        public Shark(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
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