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

    private static int N, R, Q;
    private static List<List<Integer>> ADJ;
    private static int[] SUBTREE;

    private static void input() {
        N = inputProcessor.nextInt(); // 정점
        R = inputProcessor.nextInt(); // 루트 번호
        Q = inputProcessor.nextInt(); // 쿼리수

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= N - 1; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ.get(from).add(to);
            ADJ.get(to).add(from);
        }

        SUBTREE = new int[N + 1];
    }

    private static void pro() {
        dfs(R, -1);
        for (int i = 1; i <= Q; i++) {
            int node = inputProcessor.nextInt();
            sb.append(SUBTREE[node]).append("\n");
        }
    }

    private static void dfs(int node, int parent) {
        SUBTREE[node] = 1;

        for (int child : ADJ.get(node)) {
            if (child == parent) continue;

            dfs(child, node);

            SUBTREE[node] += SUBTREE[child];
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