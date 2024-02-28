import java.util.*;
import java.io.*;

public class Main {
    
     static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static int[][] MATRIX;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        preprocess();
        bottomUp();
        sb.append(DP[1][N]);

        output();
    }


    private static void input() {
        N = inputProcessor.nextInt();

        MATRIX = new int[N + 1][2];
        for(int i = 1; i <= N; i++) {
            MATRIX[i][0] = inputProcessor.nextInt();
            MATRIX[i][1] = inputProcessor.nextInt();
        }
    }

    private static void preprocess() {
        DP = new int[N + 1][N + 1];
        for(int i = 1; i < N; i++) {
            DP[i][i] = 0;
            DP[i][i + 1] = MATRIX[i][0] * MATRIX[i][1] * MATRIX[i + 1][1];
        }
    }

    private static void bottomUp() {
        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;

                DP[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + (MATRIX[i][0] * MATRIX[k][1] * MATRIX[j][1]));
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
            while(st == null || !st.hasMoreElements()) {
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