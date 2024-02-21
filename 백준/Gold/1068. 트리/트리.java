import java.io.*;
import java.util.*;

public class Main {

   static int N, ROOT, REMOVE_NODE;

    static List<Integer>[] adj;

    static int[] LEAF;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) {
                ROOT = i;
                continue;
            }

            adj[parent].add(i);
        }

        st = new StringTokenizer(br.readLine());
        REMOVE_NODE = Integer.parseInt(st.nextToken());

        LEAF = new int[N];
    }

    static void removeNode() {
        for(int i = 0; i < N; i++) {
            List<Integer> child = adj[i];
            if(child.contains(REMOVE_NODE)) {
                child.remove(child.indexOf(REMOVE_NODE));
            }
        }
    }

    static void dfs(int node) {
        if(adj[node].isEmpty()) {
            LEAF[node] = 1;
            return;
        }

        for(int child : adj[node]) {
            dfs(child);
            LEAF[node] += LEAF[child];
        }
    }

    static void pro() {
        removeNode();

        if(ROOT != REMOVE_NODE) dfs(ROOT);

        System.out.println(LEAF[ROOT]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
