import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int T, N, A, B, ROOT;
    static List<Integer>[] ADJ;
    static int[] PARENT, DEPTH;

    public static void main(String[] args) throws IOException {
        input();

        output();
    }
    private static void input() {
        T = inputProcessor.nextInt();
        while(T > 0) {
            N = inputProcessor.nextInt();

            ADJ = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++) {
                ADJ[i] = new ArrayList<>();
            }

            DEPTH = new int[N + 1];

            PARENT = new int[N + 1];
            Arrays.fill(PARENT, -1);

            // 트리의 간선 = 노드 수 - 1
            for(int i = 1; i <= N - 1; i++) {
                int parent = inputProcessor.nextInt();
                int child = inputProcessor.nextInt();

                PARENT[child] = parent;
                ADJ[parent].add(child);
            }

            // 가장 가까운 공통 조상을 구할 두 노드
            A = inputProcessor.nextInt();
            B = inputProcessor.nextInt();

            pro(PARENT, A, B);

            T -= 1;
        }
    }

    private static void pro(int[] _parent, int _a, int _b) {
        // ROOT 구하기
        for(int i = 1; i <= N; i++) {
            if(_parent[i] == -1) {
                ROOT = i;
                break;
            }
        }

        // depth 구하기
        dfs(ROOT, 1);

        lca(_parent, _a, _b);
    }

    private static void lca(int[] _parent, int _a, int _b) {
        int depthA = DEPTH[_a];
        int depthB = DEPTH[_b];
        int tempA = _a;
        int tempB = _b;
        while (depthA > depthB) {
            tempA = PARENT[tempA];
            depthA = DEPTH[tempA];
        }

        while (depthA < depthB) {
            tempB = PARENT[tempB];
            depthB = DEPTH[tempB];
        }

        while(tempA != tempB) {
            tempA = PARENT[tempA];
            tempB = PARENT[tempB];
        }

        sb.append(tempA).append("\n");
    }

    private static void dfs(int node, int depth) {
        DEPTH[node] = depth;

        for(int child : ADJ[node]) {
            dfs(child, depth + 1);
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
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
