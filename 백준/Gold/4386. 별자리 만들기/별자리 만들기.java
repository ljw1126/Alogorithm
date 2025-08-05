import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static List<List<Edge>> adj;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 별의 개수

        // 별의 좌표값을 받는다
        double[][] points = new double[n + 1][2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double distance = between(points[i], points[j]);

                adj.get(i).add(new Edge(j, distance));
                adj.get(j).add(new Edge(i, distance));
            }
        }
    }

    private static double between(double[] from, double[] to) {
        double x = Math.pow((from[0] - to[0]), 2);
        double y = Math.pow(from[1] - to[1], 2);
        return Math.sqrt(x + y);
    }

    private static class Edge {
        private int to;
        private double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static void pro() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0.0));

        boolean[] visited = new boolean[n + 1];
        double total = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.idx]) continue;

            visited[cur.idx] = true;
            total += cur.dist;
            
            for (Edge next : adj.get(cur.idx)) {
                if (visited[next.to]) continue;

                pq.add(new Node(next.to, next.cost));
            }
        }

        sb.append(total);
    }

    private static class Node implements Comparable<Node> {
        private int idx;
        private double dist;

        public Node(int idx, double dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}