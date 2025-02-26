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

    private static int[][] LED = {
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {0, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1}
    };

    private static int N, K, P, X;

    private static void input() {
        N = inputProcessor.nextInt(); // 1 ~ N층
        K = inputProcessor.nextInt(); // K 자리의 수가 보인다(각 0 ~ 9)
        P = inputProcessor.nextInt(); // P개 LED 반전
        X = inputProcessor.nextInt(); // X층에 멈춰 있음
    }

    private static void pro() {
        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(i == X) continue;

            if(isPossible(X, i)) {
                result += 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int from, int to) {
        int v1 = from;
        int v2 = to;
        int diff = 0;
        for(int i = 1; i <= K; i++) { // K 자리의 수
            int mod1 = v1 % 10;
            int mod2 = v2 % 10;
            for(int j = 0; j < 7; j++) {
                if(LED[mod1][j] != LED[mod2][j]) {
                    diff += 1;
                }
            }

            if(diff > P) return false;

            v1 /= 10;
            v2 /= 10;
        }

        return true;
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