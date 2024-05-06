import java.util.*;
import java.io.*;

public class Main {
    
    static List<Integer>[] adj;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 작업할 개수 N (노드)
        int M = Integer.parseInt(st.nextToken()); // 작업 순서 정보 개수 (간선)

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[to].add(from);
        }

        int X = Integer.parseInt(br.readLine()); // 반드시 끝내야 하는 작업 X

        boolean[] visited = new boolean[N + 1];
        int[] leaf = new int[N + 1];
        Arrays.fill(leaf, 1);
        leaf[X] = 0;
        dfs(X, -1, visited, leaf);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(leaf[X]));
        bw.flush();
        bw.close();
    }

    static void dfs(int node, int prev, boolean[] visited, int[] leaf) {
        visited[node] = true;
        if(adj[node].isEmpty()) {
            return;
        }

        for(int next : adj[node]) {
            if(next == prev) continue;
            if(visited[next]) continue;

            dfs(next, node, visited, leaf);
            leaf[node] += leaf[next];
        }
    }

    public static void main(String[] args) throws Exception {
        input();
    }
    
}