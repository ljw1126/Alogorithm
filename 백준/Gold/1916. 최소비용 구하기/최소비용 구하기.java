import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m, s, e;
    private static List<List<Edge>> adj;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 도시의 개수 (노드)
        m = Integer.parseInt(br.readLine()); // 버스의 개수 (간선)

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Edge(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        br.close();
    }

    private static class Edge {
        private int idx;
        private int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private static void pro() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            if (cur.dist > dist[cur.idx]) continue;
            if (cur.idx == e) continue;

            for (Edge next : adj.get(cur.idx)) {
                if (dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    pq.add(new Info(next.idx, dist[next.idx]));
                }
            }
        }

        sb.append(dist[e]);
    }

    private static class Info implements Comparable<Info> {
        private int idx;
        private int dist;

        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public int compareTo(Info o) {
            return this.dist - o.dist;
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}