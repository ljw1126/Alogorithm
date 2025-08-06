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
    private static List<List<Integer>> adj;
    private static int n;
    private static int[] depths;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        depths = new int[n + 1];
    }

    private static void pro() {
        rec(ROOT, -1, 0);

        if (depths[ROOT] % 2 == 0) {
            sb.append("No");
        } else {
            sb.append("Yes");
        }
    }

    private static void rec(int node, int parent, int depth) {
        List<Integer> childs = adj.get(node);
        if (childs.size() == 1 && childs.get(0) == parent) {
            depths[node] = depth;
            return;
        }

        for (int next : childs) {
            if (next == parent) continue;

            rec(next, node, depth + 1);
            depths[node] += depths[next];
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}