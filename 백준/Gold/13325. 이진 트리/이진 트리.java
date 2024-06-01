import java.util.*;
import java.io.*;

public class Main {
    
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int K, N, RESULT;
    private static int[] DP;

    private static void input() {
        K = inputProcessor.nextInt(); // depth
        // 2^(k+1) - 1;
        N = (int) Math.pow(2, K + 1) - 1;

        DP = new int[N];
        for (int i = 1; i < N; i++) {
            int dist = inputProcessor.nextInt();

            DP[i] = dist;
        }
    }


    private static void pro() {
        topDown(0);
        sb.append(RESULT);
    }

    private static int topDown(int node) {
        if (node * 2 + 1 >= N) {
            RESULT += DP[node]; 
            return DP[node];
        }

        int parent = node * 2;
        int left = topDown(parent + 1);
        int right = topDown(parent + 2);

        RESULT += (DP[node] + Math.abs(left - right));
        return DP[node] + Math.max(left, right); 
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