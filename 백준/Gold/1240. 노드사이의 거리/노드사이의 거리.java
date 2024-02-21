import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();

    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static List<Node>[] adj;

    static int N, M;

    static int[] DIST;

    static boolean[] VISIT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드 수
        M = Integer.parseInt(st.nextToken()); // 거리 알고 싶은 노도 쌍의 개수

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= (N - 1); i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, dist));
            adj[to].add(new Node(from, dist));
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            DIST = new int[N + 1];
            VISIT = new boolean[N + 1];

            dfs(from);

            sb.append(DIST[to]).append("\n");
        }
    }

    static void dfs(int node) {
        VISIT[node] = true;

        for(Node next : adj[node]) {
            if(VISIT[next.to]) continue;

            DIST[next.to] = DIST[node] + next.dist;
            dfs(next.to);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
    
}
