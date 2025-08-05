import java.util.*;
import java.io.*;

public class Main {
    static int N;

    static List<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return cost - edge.cost;
        }
    }

    static class Info implements Comparable<Info>{
        int idx;
        int cost;

        public Info(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info info) {
            return cost - info.cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // 가장 최소 비용이 드는 논을 시작점으로 잡고 시작
        int[] w = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());

                if(i != j) adj[i].add(new Edge(j, cost));
                else adj[0].add(new Edge(i, w[i]));
            }
        }
    }

    static int prime(int start) {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        boolean[] visit = new boolean[N + 1];

        int result = 0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;

            for(Edge next : adj[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        return result;
    }

    static void pro() {
        System.out.println(prime(0));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}