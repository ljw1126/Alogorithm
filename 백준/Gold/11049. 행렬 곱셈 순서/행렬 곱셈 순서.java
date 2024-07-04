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

    private static int N;
    private static int[][] MATRIX, DP;

    private static void input() {
        N = inputProcessor.nextInt();
        MATRIX = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            MATRIX[i][0] = a;
            MATRIX[i][1] = b;
        }

        DP = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(DP[i], Integer.MAX_VALUE);
            DP[i][i] = 0;
        }
    }

    private static void pro() {
        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;

                for (int k = i; k < j; k++) {
                    int result = DP[i][k] + DP[k + 1][j] + (MATRIX[i][0] * MATRIX[k][1] * MATRIX[j][1]);
                    DP[i][j] = Math.min(DP[i][j], result);
                }
            }
        }

        sb.append(DP[1][N]);
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