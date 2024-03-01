import java.util.*;
import java.io.*;

public class Main {
    static int N, M, A, B, C;

    static List<Info>[] adj;

    static class Info { // 시작 점에서 to까지 dist만큼 걸린다
        int to;
        long dist;

        public Info(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 교차로 개수 (노드 수)
        M = Integer.parseInt(st.nextToken()); // 골목 개수 (간선 수)
        A = Integer.parseInt(st.nextToken()); // 시작 노드
        B = Integer.parseInt(st.nextToken()); // 노착 노드
        C = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            adj[from].add(new Info(to, dist));
            adj[to].add(new Info(from, dist));
        }

    }

    static boolean dijkstra(int limit) {  // 완전 탐색, 최단 거리
        Queue<Info> que = new LinkedList<>();
        que.add(new Info(A, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[A] = 0;

        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(dist[cur.to] != cur.dist) continue;

            for(Info next : adj[cur.to]) {
                if(limit < next.dist) continue;
                if(C < dist[cur.to] + next.dist) continue; // 요거 이상해 ..

                dist[next.to] = dist[cur.to] + next.dist;
                que.add(new Info(next.to, dist[next.to]));
            }
        }


        return dist[B] <= C;
    }

    static void pro() {
        int left = 1;
        int right = 1000000000;
        int ans = right;
        while(left <= right) {
           int mid = (left + right) / 2;

           if(dijkstra(mid)) { // 최대 골목 비용을 만족하나
               ans = mid;
               right = mid - 1;
           } else {
               left = mid + 1;
           }
        }

        System.out.println(ans == 1000000000 ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}