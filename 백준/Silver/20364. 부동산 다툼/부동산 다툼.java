import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, q;
    private static int[] wanted;
    private static boolean[] land;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 땅의 개수
        q = Integer.parseInt(st.nextToken()); // 오리의 수

        wanted = new int[q];
        for (int i = 0; i < q; i++) {
            wanted[i] = Integer.parseInt(br.readLine());
        }

        land = new boolean[n + 1];
    }

    private static void pro() {
        for (int w : wanted) {
            int result = rec(w, Integer.MAX_VALUE);
            if (result == -1) {
                land[w] = true;
                sb.append(0).append("\n");
            } else {
                sb.append(result).append("\n");
            }
        }
    }

    private static int rec(int node, int owner) {
        if (node < 1) {
            return owner == Integer.MAX_VALUE ? -1 : owner;
        }

        return rec(node / 2, land[node] ? node : owner);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}