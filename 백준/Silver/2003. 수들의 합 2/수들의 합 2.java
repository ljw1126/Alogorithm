import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;

    private static int[] A;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // N개의 수로 된수열
        M = inputProcessor.nextInt(); // M이 되는 경우의 수

        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int cnt = 0;
        int sum = 0;
        for (int L = 1, R = 1; L <= N; L++) {
            while (R <= N && sum < M) {
                sum += A[R];
                R += 1;
            }

            if (sum == M) {
                cnt += 1;
            }

            sum -= A[L];
        }

        sb.append(cnt);
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
            br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}