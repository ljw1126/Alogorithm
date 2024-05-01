import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_DIST = 251;
    private static int N, M, K;
    private static int[][] MATRIX;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 노드 수
        M = inputProcessor.nextInt(); // 간선 수

        MATRIX = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(MATRIX[i], MAX_DIST);
            MATRIX[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            int u = inputProcessor.nextInt();
            int v = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            MATRIX[u][v] = 0;

            if (b == 1) { //양방향
                MATRIX[v][u] = 0;
            } else {
                MATRIX[v][u] = 1; // 단방향일때
            }
        }
    }

    private static class Direct {
        private int from;
        private int to;

        public Direct(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static void pro() {
        preProcess();

        K = inputProcessor.nextInt();
        for (int i = 1; i <= K; i++) {
            int s = inputProcessor.nextInt();
            int e = inputProcessor.nextInt();

            sb.append(MATRIX[s][e]).append("\n");
        }
    }

    // 플로이드 워셜
    private static void preProcess() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    MATRIX[i][j] = Math.min(MATRIX[i][j], MATRIX[i][k] + MATRIX[k][j]);
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
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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