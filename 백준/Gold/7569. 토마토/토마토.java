import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int M, N, H;
    private static int[][][] BOXES;

    private static void input() {
        M = inputProcessor.nextInt();
        N = inputProcessor.nextInt();
        H = inputProcessor.nextInt();
        BOXES = new int[N + 1][M + 1][H + 1];

        for (int h = 1; h <= H; h++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    BOXES[i][j][h] = inputProcessor.nextInt();
                }
            }
        }
    }

    private static final int[][] DIR = {
            {1, 0, 0},
            {-1, 0, 0},
            {0, 1, 0},
            {0, -1, 0},
            {0, 0, 1},
            {0, 0, -1},
    };

    // 1: 익은 토마토, 0 : 익지 않은 토마토, -1은 토마토가 들어있지 않은 칸
    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();

        for (int h = 1; h <= H; h++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (BOXES[i][j][h] == 1) {
                        que.add(i);
                        que.add(j);
                        que.add(h);
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

                if (dx < 1 || dy < 1 || dz < 1 || dx > N || dy > M || dz > H) continue;
                if (BOXES[dx][dy][dz] != 0) continue;

                BOXES[dx][dy][dz] = BOXES[x][y][z] + 1;
                que.add(dx);
                que.add(dy);
                que.add(dz);
            }
        }

        boolean remain = false;
        int result = 0;
        for (int h = 1; h <= H; h++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (BOXES[i][j][h] == 0) {
                        remain = true;
                    }
                    if (BOXES[i][j][h] != -1) {
                        result = Math.max(result, BOXES[i][j][h]);
                    }
                }
            }
        }

        if (remain) {
            sb.append(-1);
        } else {
            sb.append(result - 1);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
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
                } catch (Exception e) {
                    e.printStackTrace();
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