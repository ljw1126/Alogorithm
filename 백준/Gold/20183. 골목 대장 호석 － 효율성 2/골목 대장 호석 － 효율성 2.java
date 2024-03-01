import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M, A, B;
    static long C;

    static long INF = 100000000000001L;

    static List<Info>[] adj;

    static long[] dist;

    static class Info implements Comparable<Info> { // 시작 점에서 to까지 dist만큼 걸린다
        int idx;
        long dist;

        public Info(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            if(dist > o.dist) return 1;
            if(dist == o.dist) return 0;
            return -1;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 교차로 개수 (노드 수)
        M = Integer.parseInt(st.nextToken()); // 골목 개수 (간선 수)
        A = Integer.parseInt(st.nextToken()); // 시작 노드
        B = Integer.parseInt(st.nextToken()); // 노착 노드
        C = Long.parseLong(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long dist = Long.parseLong(st.nextToken());

            adj[from].add(new Info(to, dist));
            adj[to].add(new Info(from, dist));
        }

        dist = new long[N + 1];
    }

    static boolean dijkstra(long limit) {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(A, 0));

        Arrays.fill(dist, INF);
        dist[A] = 0;

        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(dist[cur.idx] != cur.dist) continue;

            for(Info next : adj[cur.idx]) {
                if(limit < next.dist) continue;
                if(dist[next.idx] > dist[cur.idx] + next.dist) {
                    dist[next.idx] = dist[cur.idx] + next.dist;
                    que.add(new Info(next.idx, dist[next.idx]));
                }
            }
        }


        return dist[B] <= C;
    }

    static void pro() { // 매개변수 탐색
        long left = 1L;
        long right = 1000000001L;
        long ans = right;
        while(left <= right) {
           long mid = (left + right) / 2;

           if(dijkstra(mid)) { // 최대 골목 비용을 만족하나
               ans = mid;
               right = mid - 1;
           } else {
               left = mid + 1;
           }
        }

        System.out.println(ans == 1000000001L ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}