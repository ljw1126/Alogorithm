import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M, X;
    private static List<List<Integer>> ADJ;
    private static int[] LEAF;
    private static boolean[] VISIT;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 작업 개수 (노드)
        M = inputProcessor.nextInt(); // 작업 순서 정보 (간선)

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            ADJ.get(b).add(a);
        }

        X = inputProcessor.nextInt();

        LEAF = new int[N + 1];
        Arrays.fill(LEAF, 1);
        LEAF[X] = 0;

        VISIT = new boolean[N + 1];
    }

    private static void pro() {
        dfs(X, -1);
        sb.append(LEAF[X]);
    }

    private static void dfs(int node, int prev) {
        VISIT[node] = true;

        for (int next : ADJ.get(node)) {
            if (next == prev) continue;
            if (VISIT[next]) continue;

            dfs(next, node);
            LEAF[node] += LEAF[next];
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