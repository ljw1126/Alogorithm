import java.util.*;
import java.io.*;

public class Main {
    
     static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, M;
    static List<Node> EDGES;
    static int[] PARENT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static class Node {
        int from;
        int to;
        int c; // 오르막길 : 0, 내리막길 : 1

        public Node(int from, int to, int c) {
            this.from = from;
            this.to = to;
            this.c = c;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 건물의 개수(노드)
        M = inputProcessor.nextInt(); // 도로의 개수(간선)

        EDGES = new ArrayList<>();
        for(int i = 1; i <= M + 1; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();
            int c = inputProcessor.nextInt();

            EDGES.add(new Node(a, b, c));
        }

        PARENT = new int[N + 1];
    }

    private static void pro() {
        Collections.sort(EDGES, (o1, o2) -> o2.c - o1.c); // 내리막길(1) 순으로
        int best = kruskal();

        Collections.sort(EDGES, Comparator.comparingInt(o -> o.c));
        int worst = kruskal();

        int result = (int)(pow(worst) - pow(best));
        sb.append(result);
    }

    private static double pow(int value) {
        return Math.pow(value, 2);
    }

    private static int kruskal() {
        for(int i = 0; i <= N; i++) {
            PARENT[i] = i;
        }

        int count = 0;
        for(Node next : EDGES) {
            if(unionFind(next.from, next.to)) {
                union(next.from, next.to);

                if(next.c == 0) { 
                    count += 1;
                }
            }
        }

        return count;
    }

    private static void union(int from, int to) {
        int parentA = findParent(from);
        int parentB = findParent(to);

        if(parentA < parentB) PARENT[parentB] = parentA;
        else PARENT[parentA] = parentB;
    }

    private static boolean unionFind(int a, int b) {
        return findParent(a) != findParent(b);
    }

    private static int findParent(int node) {
        if(PARENT[node] == node) return node;

        return PARENT[node] = findParent(PARENT[node]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
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