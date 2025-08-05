import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int m, n, total;
    private static List<Edge> edges;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("0 0")) {
            String[] tokens = line.split(" ");
            m = Integer.parseInt(tokens[0]); // 집의 수
            n = Integer.parseInt(tokens[1]); // 길의 수

            total = 0;
            edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                edges.add(new Edge(x, y, z));
                edges.add(new Edge(y, x, z));

                total += z;
            }

            pro();
        }

    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void pro() {
        Collections.sort(edges);

        int[] parent = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
        }

        int sum = 0;
        for (Edge e : edges) {
            if (isSameParent(parent, e.from, e.to)) continue;

            sum += e.cost;
            union(parent, e.from, e.to);
        }

        sb.append(total - sum).append("\n");
    }

    private static void union(int[] parent, int from, int to) {
        int parentA = findParent(parent, from);
        int parentB = findParent(parent, to);
        if (parentA > parentB) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }
    }

    private static boolean isSameParent(int[] parent, int from, int to) {
        return findParent(parent, from) == findParent(parent, to);
    }

    private static int findParent(int[] parent, int idx) {
        if (parent[idx] == idx) return idx;

        return parent[idx] = findParent(parent, parent[idx]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}