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
    private static int[][] DATA;

    private static void input() {
        N = inputProcessor.nextInt();

        DATA = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                DATA[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static void pro() {
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = DATA[N - 1][i];
        }

        sb.append(topDown(0, 0, dp));
    }

    private static int topDown(int row, int col, int[][] dp) {
        if (row == N - 1) return dp[row][col];
        if (dp[row][col] != -1) return dp[row][col];

        int v1 = topDown(row + 1, col, dp);
        int v2 = topDown(row + 1, col + 1, dp);
        int max = Math.max(v1, v2);

        return dp[row][col] = DATA[row][col] + max;
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