import java.util.*;
import java.io.*;

public class Main {
    
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MOD = 1000_000;
    private static final int LOWER = 1;
    private static final int UPPER = 26;

    private static int N;
    private static String PASSWORD;
    private static long[] DP;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        PASSWORD = inputProcessor.nextLine();
        DP = new long[PASSWORD.length() + 1];
        N = PASSWORD.length();
    }

    private static void pro() {
        DP[0] = 1;

        for (int i = 1; i <= N; i++) {
            int first = PASSWORD.charAt(i - 1) - '0';
            if (isAlphabet(first)) {
                DP[i] += DP[i - 1];
                DP[i] %= MOD;
            }

            if (i == 1) continue;

            int ten = PASSWORD.charAt(i - 2) - '0';

            if (ten == 0) continue;

            int second = ten * 10 + first;
            if (isAlphabet(second)) {
                DP[i] += DP[i - 2];
                DP[i] %= MOD;
            }
        }


        sb.append(DP[N]);
    }

    private static boolean isAlphabet(int num) {
        return LOWER <= num && num <= UPPER;
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