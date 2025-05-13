import java.util.*;
import java.io.*;

public class Main {
    static int n, m, a, b;

    static long c;

    static List<Node>[] adj;

    static long[] dist;

    static long INF = 100000000000001L;

    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;

        }

        @Override
        public int compareTo(Node node) {
            if(cost > node.cost) return 1;
            else if(cost == node.cost) return 0;
            return -1;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 교차로 개수 (노드)
        m = Integer.parseInt(st.nextToken()); // 골목 개수 (간선)
        a = Integer.parseInt(st.nextToken()); // 시작 교차로
        b = Integer.parseInt(st.nextToken()); // 도착 교차로
        c = Long.parseLong(st.nextToken()); // 가진 돈

        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));
        }

        dist = new long[n + 1];
    }

    static boolean dijkstra(long limit) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(a, 0L));

        Arrays.fill(dist, INF);
        dist[a] = 0L;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node next : adj[cur.idx]) {
                if(next.cost > limit) continue;
                if(dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    que.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[b] <= c;
    }

    static void pro() {
        long L = 1L;
        long R = 1000000001L;
        long ans = R;
        while(L <= R) {
            long mid = (L + R) / 2;

            if(dijkstra(mid)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans == 1000000001L ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}