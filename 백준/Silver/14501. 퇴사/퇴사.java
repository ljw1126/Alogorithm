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
        DATA = new int[N + 2][2];

        for (int i = 1; i <= N; i++) {
            int t = inputProcessor.nextInt(); // 상담 완료 걸리는 기간
            int p = inputProcessor.nextInt(); // 상담 완료시 받을 수 있는 금액

            DATA[i][0] = t;
            DATA[i][1] = p;
        }
    }

    private static void pro() {
        int[] dp = new int[N + 2];
        int max = 0;

        for (int i = N; i > 0; i--) {
            int time = DATA[i][0] + i;
            if (time <= N + 1) {
                dp[i] = Math.max(max, DATA[i][1] + dp[time]);
                max = dp[i];
            } else {
                dp[i] = max;
            }
        }

        sb.append(max);
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