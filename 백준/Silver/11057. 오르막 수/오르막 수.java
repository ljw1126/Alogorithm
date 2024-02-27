import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int MOD = 10007;
    static int[][] DP;
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DP = new int[N + 1][10];
        Arrays.fill(DP[1], 1); // 한 자리 경우 1로 초기화
    }

    private static void pro() {
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int z = 0; z <= j; z++) {
                    DP[i][j] += DP[i - 1][z];
                    DP[i][j] %= MOD;
                }
            }
        }

        int result = 0;
        for(int i = 0; i <= 9; i++) {
            result += DP[N][i];
            result %= MOD;
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