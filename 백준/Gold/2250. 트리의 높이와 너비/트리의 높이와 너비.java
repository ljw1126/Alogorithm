import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, ROOT;
    static int[] DEPTH;
    static int[][] BINARY_TREE;
    static int[][] COL;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        BINARY_TREE = new int[N + 1][2];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(BINARY_TREE[i], -1);
        }

        int[] p = new int[N + 1];
        Arrays.fill(p, -1);

        for(int i = 1; i <= N; i++) {
            int parent = inputProcessor.nextInt();
            int left = inputProcessor.nextInt();
            int right = inputProcessor.nextInt();

            if(left != -1) {
                BINARY_TREE[parent][0] = left;
                p[left] = parent;
            }
            if(right != -1) {
                BINARY_TREE[parent][1] = right;
                p[right] = parent;
            }
        }

        for(int i = 1; i <= N; i++) {
            if(p[i] == -1) {
                ROOT = i;
                break;
            }
        }

        DEPTH = new int[N + 1];
    }


    private static void pro() {
        dfs(ROOT, 1);

        int maxDepth = 0;
        for(int i = 1; i <= N; i++) {
            maxDepth = Math.max(maxDepth, DEPTH[i]);
        }

        COL = new int[maxDepth + 1][2];
        for(int i = 1; i <= maxDepth; i++) {
            COL[i][0] = N + 1;
        }

        inorder(ROOT, 0);

        int level = 0;
        int width = 0;
        for(int i = 1; i <= maxDepth; i++) {
            int value = COL[i][1] - COL[i][0] + 1;
            if(width < value) {
                level = i;
                width = value;
            }
        }

        sb.append(level).append(" ").append(width);
    }

    private static int inorder(int node, int col) {
        if(node == -1) return col;

        col = inorder(BINARY_TREE[node][0], col);

        col += 1;
        int depth = DEPTH[node];
        COL[depth][0] = Math.min(COL[depth][0], col);
        COL[depth][1] = Math.max(COL[depth][1], col);

        col = inorder(BINARY_TREE[node][1], col);

        return col;
    }

    private static void dfs(int node, int depth) {
        if(node == -1) return;

        DEPTH[node] = depth;

        int left = BINARY_TREE[node][0];
        dfs(left, depth + 1);

        int right = BINARY_TREE[node][1];
        dfs(right, depth + 1);
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