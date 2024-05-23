import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, K;
    private static int[][] STUFF;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 물건의 개수
        K = inputProcessor.nextInt(); // 준서가 버틸 수 있는 무게 K

        STUFF = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            int w = inputProcessor.nextInt(); // 무게
            int v = inputProcessor.nextInt(); // 가치

            STUFF[i][0] = w;
            STUFF[i][1] = v;
        }
    }

    private static void pro() {
        int[][] dp = new int[N + 1][K + 1]; // kg에 n번 물건을 담았을때 최대 가치 구함

        for (int i = 1; i <= N; i++) { // 물건 번호
            for (int j = 1; j <= K; j++) { // 가방(kg)
                if (j < STUFF[i][0]) { // j kg 가방보다 i번 물건의 무게가 큰가
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], STUFF[i][1] + dp[i - 1][j - STUFF[i][0]]);
                }
            }
        }

        sb.append(dp[N][K]);
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