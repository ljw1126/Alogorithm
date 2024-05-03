import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MOD = 1000_000_009;

    private static final int MAX_N = 1000_000;
    private static int T;
    private static long[] DP = new long[MAX_N + 1];

    public static void main(String[] args) throws IOException {
        preProcess();
        input();
        output();
    }

    private static void preProcess() {
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for (int i = 4; i <= MAX_N; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2] + DP[i - 3]) % MOD;
        }
    }

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            T -= 1;
            int n = inputProcessor.nextInt();
            sb.append(DP[n]).append("\n");
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