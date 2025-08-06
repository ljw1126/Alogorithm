import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int t, n;
    private static int[] preOrder, inOrder;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        while (t > 0) {
            t -= 1;

            n = Integer.parseInt(br.readLine());
            preOrder = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            inOrder = new int[n + 1]; // 루트 인덱스를 빠르게 찾기 위해 노하우를 적용
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int v = Integer.parseInt(st.nextToken());
                inOrder[v] = i;
            }

            pro();
        }
    }

    private static void pro() {
        rec(1, n, 1, n);
        sb.append("\n");
    }

    private static void rec(int preS, int preE, int inS, int inE) {
        if (preS > preE || inS > inE) return;

        int root = preOrder[preS];
        int rootIdx = inOrder[root];

        rec(preS + 1, (preS + 1) + (rootIdx - 1 - inS), inS, rootIdx - 1);
        rec((preS + 1) + (rootIdx - 1 - inS) + 1, preE, rootIdx + 1, inE);
        sb.append(root).append(" ");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}