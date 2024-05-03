import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();
    static int MOD = 1000000009;
    
    static int T, N;
    static long[] DP = new long[100001];

    public static void main(String[] args) throws IOException {
        preprocess();

        T = inputProcessor.nextInt();
        while(T > 0) {
            N = inputProcessor.nextInt();

            sb.append(DP[N]).append(NEW_LINE);

            T -= 1;
        }

        output();
    }

    private static void preprocess() {
        // 초기화
        DP[1] = 1; // 1
        DP[2] = 2; // 1 + 1, 2
        DP[3] = 2; // 1 + 1 + 1, 3
        DP[4] = 3; // 1 + 1 + 1 + 1, 1 + 2 + 1, 2 + 2
        DP[5] = 3; // 1 + 1 + 1 + 1 + 1, 1 + 3 + 1, 2 + 1 + 2
        DP[6] = 6; // 1 + DP[4] + 1, 2 + DP[2] + 3, 3 + 3

        for(int i = 7; i <= 100000; i++) {
            DP[i] = (DP[i - 2] + DP[i - 4] + DP[i - 6]) % MOD;
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