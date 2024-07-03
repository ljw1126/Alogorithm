import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();

        output();
    }

    private static int T, N;
    private static int S_X, S_Y, E_X, E_Y;

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            T -= 1;

            N = inputProcessor.nextInt();

            S_X = inputProcessor.nextInt();
            S_Y = inputProcessor.nextInt();

            E_X = inputProcessor.nextInt();
            E_Y = inputProcessor.nextInt();

            pro();
        }
    }

    private static void pro() {
        sb.append(bfs()).append("\n");
    }

    private static final int[][] DIR = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
    };

    private static int bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(S_X);
        que.add(S_Y);
        que.add(0);

        boolean[][] visit = new boolean[N][N];
        visit[S_X][S_Y] = true;

        int result = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            int dist = que.poll();

            if (x == E_X && y == E_Y) {
                result = dist;
                break;
            }

            for (int i = 0; i < 8; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
                if (visit[dx][dy]) continue;

                visit[dx][dy] = true;
                que.add(dx);
                que.add(dy);
                que.add(dist + 1);
            }
        }

        return result;
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