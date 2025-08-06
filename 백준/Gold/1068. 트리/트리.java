import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, removed, root;
    private static List<List<Integer>> adj;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }

            adj.get(parent).add(i);
        }

        removed = Integer.parseInt(br.readLine()); // 지울 노드
    }


    private static void pro() {
        if (root == removed) {
            sb.append(0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == removed) {
                continue;
            }

            adj.get(i).remove((Integer) removed);
        }

        int[] acc = new int[n];
        rec(root, -1, acc);

        sb.append(acc[root]);
    }

    private static void rec(int node, int parent, int[] acc) {
        if (adj.get(node).isEmpty()) {
            acc[node] = 1;
            return;
        }

        for (int child : adj.get(node)) {
            if (child == parent) continue;

            rec(child, node, acc);
            acc[node] += acc[child];
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}