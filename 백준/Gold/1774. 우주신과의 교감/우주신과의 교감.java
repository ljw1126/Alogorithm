import java.util.*;
import java.io.*;

public class Main {
     private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static int[] parents;
    private static List<Edge> edges;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 우주신들의 개수
        m = Integer.parseInt(st.nextToken()); // 이미 연결된 신들과의 통로의 개수

        int[][] points = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        edges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double distance = calculate(points[i], points[j]);
                edges.add(new Edge(i, j, distance));
            }
        }

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        // 이미 연결되어 있는 경우
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            union(parents, p1, p2);
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private double dist;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    private static double calculate(int[] p1, int[] p2) {
        double v1 = Math.pow(p1[0] - p2[0], 2);
        double v2 = Math.pow(p1[1] - p2[1], 2);

        return Math.sqrt(v1 + v2);
    }

    private static int findParent(int[] parent, int idx) {
        if (parent[idx] == idx) return idx;

        return parent[idx] = findParent(parent, parent[idx]);
    }

    private static boolean isSameParent(int[] parent, int a, int b) {
        return findParent(parent, a) == findParent(parent, b);
    }

    private static void union(int[] parent, int a, int b) {
        int pa = findParent(parent, a);
        int pb = findParent(parent, b);

        if (pa < pb) {
            parents[pb] = pa;
        } else {
            parents[pa] = pb;
        }
    }

    private static void pro() {
        Collections.sort(edges);

        double result = 0.0;
        for (Edge e : edges) {
            if (isSameParent(parents, e.from, e.to)) continue;

            result += e.dist;
            union(parents, e.from, e.to);
        }

        sb.append(String.format("%.2f", result));
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}