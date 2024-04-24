import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int EMPTY = -1;
    private static final int FRESH_TOMATO = 0;
    private static final int OLD_TOMATO = 1;
    private static final int MAX_VALUE = 1000001;
    private static int[][] DIR = {
            {0, 1, 0},
            {0, -1, 0},
            {1, 0, 0},
            {-1, 0, 0},
            {0, 0, 1},
            {0, 0, -1}
    };

    private static int M, N, H;
    private static int[][][] BOXES;
    private static int[][][] DIST;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        M = inputProcessor.nextInt(); // 가로(열)
        N = inputProcessor.nextInt(); // 세로(행)
        H = inputProcessor.nextInt(); // 높이

        BOXES = new int[N][M][H];
        DIST = new int[N][M][H];
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    BOXES[i][j][k] = inputProcessor.nextInt();
                    DIST[i][j][k] = MAX_VALUE;
                }
            }
        }
    }

    // 1: 익은 토마토, 0 : 익지 않은 토마토, -1 : 빈칸
    private static void pro() {
        bfs();

        int result = 0;
        loop:
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (BOXES[i][j][k] == FRESH_TOMATO && DIST[i][j][k] == MAX_VALUE) {
                        result = -1;
                        break loop;
                    }

                    if (BOXES[i][j][k] != EMPTY && DIST[i][j][k] > result) {
                        result = DIST[i][j][k];
                    }
                }
            }
        }

        sb.append(result);
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (BOXES[i][j][k] == OLD_TOMATO) {
                        DIST[i][j][k] = 0;
                        que.add(i);
                        que.add(j);
                        que.add(k);
                    }
                }
            }
        }

        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            int z = que.poll();

            for (int i = 0; i < 6; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];
                int dz = z + DIR[i][2];

                if (dx < 0 || dy < 0 || dx >= N || dy >= M || dz < 0 || dz >= H) continue;
                if (BOXES[dx][dy][dz] == EMPTY) continue;
                if (DIST[dx][dy][dz] > DIST[x][y][z] + 1) {
                    DIST[dx][dy][dz] = DIST[x][y][z] + 1;
                    que.add(dx);
                    que.add(dy);
                    que.add(dz);
                }
            }
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}