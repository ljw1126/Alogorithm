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

    private static int N;
    private static int[][] DP_TABLE;

    private static void input() {
        N = inputProcessor.nextInt();

        DP_TABLE = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                DP_TABLE[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static void pro() {
        bottomUp();
    }

    private static void bottomUp() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    int v1 = DP_TABLE[i - 1][0];
                    DP_TABLE[i][j] += v1;
                } else if (j == i) {
                    int v2 = DP_TABLE[i - 1][j - 1];
                    DP_TABLE[i][j] += v2;
                } else {
                    int v1 = DP_TABLE[i - 1][j - 1];
                    int v2 = DP_TABLE[i - 1][j];
                    DP_TABLE[i][j] += Math.max(v1, v2);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, DP_TABLE[N - 1][i]);
        }

        sb.append(result);
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