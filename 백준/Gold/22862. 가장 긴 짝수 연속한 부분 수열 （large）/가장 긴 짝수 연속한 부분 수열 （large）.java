import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, K;
    private static int[] S;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 길이
        K = inputProcessor.nextInt(); // 최대 횟수

        S = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            S[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int cnt = 0;
        int result = 0;
        for (int L = 1, R = 0; L <= N; L++) {
            while (R + 1 <= N && cnt <= K) {
                R += 1;
                if (S[R] % 2 == 1) { // 홀수 인 경우
                    cnt += 1;
                }
            }

            result = Math.max(result, R - L + 1 - cnt);
            if (S[L] % 2 == 1) {
                cnt -= 1;
            }
        }

        sb.append(result);
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