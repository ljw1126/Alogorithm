import java.util.*;
import java.io.*;

public class Main {
    
 static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Integer>[] adj;

    static int[] SCORE;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 직원 수 (노드 수)
        M = Integer.parseInt(st.nextToken()); // 칭찬 횟수

        SCORE = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int parent  = Integer.parseInt(st.nextToken());

            if(i == 1) continue;

            adj[parent].add(i);
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int praise = Integer.parseInt(st.nextToken());

            SCORE[to] += praise;
        }
    }

    static void dfs(int node, int value) {
        SCORE[node] += value;

        for(int next : adj[node]) {
            dfs(next, SCORE[node]);
        }
    }

    static void pro() {
        dfs(1, 0);
        for(int i = 1; i <= N; i++) sb.append(SCORE[i]).append(" ");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
