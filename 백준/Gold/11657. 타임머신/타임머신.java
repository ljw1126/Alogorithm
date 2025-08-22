import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static final int INF = 5000001;
    private static int n, m;
    private static List<Edge> edges;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 도시의 개수(노드)
        m = Integer.parseInt(st.nextToken()); // 버스 노선의 개수(간선)

        edges = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }
    }

    private static class Edge {
        private int from;
        private int to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static void pro() {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        boolean isPossible = true;
        for(int i = 1; i <= n; i++) {
            for(Edge e : edges) {
                int from = e.from;
                int to = e.to;
                int cost = e.cost;

                if(dist[from] != Long.MAX_VALUE && dist[to] > dist[from] + cost) {
                    dist[to] = dist[from] + cost;

                    if(i == n) {
                        isPossible = false;
                        break;
                    }
                }
            }
        }

        if(isPossible) {
            for(int i = 2; i <= n; i++) {
                if(dist[i] == Long.MAX_VALUE) sb.append("-1").append("\n");
                else sb.append(dist[i]).append("\n");
            }
        } else {
            sb.append("-1");
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    
}