import java.util.*;
import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static final int ROOT = 1;
    private static int n, m;
    private static long[] score;
    private static List<List<Integer>> adj;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 회사의 직원 수
        m = Integer.parseInt(st.nextToken()); // 최초의 칭찬의 횟수

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) continue;

            adj.get(parent).add(i);
        }

        score = new long[n + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            score[a] += v;
        }
    }

    private static void pro() {
        rec(ROOT, -1);

        for (int i = 1; i <= n; i++) {
            sb.append(score[i]).append(" ");
        }
    }

    private static void rec(int node, int prev) {
        if (adj.get(node).isEmpty()) {
            return;
        }

        for (int child : adj.get(node)) {
            if (child == prev) continue;

            score[child] += score[node];
            rec(child, node);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}