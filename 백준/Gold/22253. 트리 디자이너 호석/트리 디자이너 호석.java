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

    private static final int MOD = 1_000_000_007;

    private static int N;

    private static int[] POINT;
    private static int[][] DP;
    private static List<List<Integer>> ADJ;

    // 각 정점의 숫자는 0 ~ 9 까지
    private static void input() {
        N = inputProcessor.nextInt();

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        // 1 ~ N 노드까지 0 ~ 9 사이의 숫자를 가진다
        DP = new int[N + 1][10];
        POINT = new int[N + 1];  // 0 ~ 9 까지 존재
        for (int i = 1; i <= N; i++) {
            POINT[i] = inputProcessor.nextInt();
            DP[i][POINT[i]] = 1;
        }

        for (int i = 1; i <= N - 1; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ.get(from).add(to);
            ADJ.get(to).add(from);
        }
    }

    private static void pro() {
        int root = 1;
        rec(root, -1);

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += DP[root][i];
            result %= MOD;
        }

        sb.append(result);
    }

    private static void rec(int node, int parent) {
        List<Integer> childs = ADJ.get(node);
        if (childs.size() == 1 && childs.get(0) == parent) {
            return;
        }

        for (int child : childs) {
            if (child == parent) continue;

            rec(child, node);

            acc(node, child);
        }
    }

    // 자신을 선택하지 않는 경우와 자신을 선택하는 경우
    private static void acc(int node, int child) {
        int point = POINT[node];
        for (int i = 0; i <= 9; i++) {
            DP[node][i] += DP[child][i];
            DP[node][i] %= MOD;

            if (point <= i) {
                DP[node][point] += DP[child][i];
                DP[node][point] %= MOD;
            }
        }
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
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
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