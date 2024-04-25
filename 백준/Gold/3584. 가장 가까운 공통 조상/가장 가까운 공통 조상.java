import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();

    static int T, N, A, B;

    static int[] TREE;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while(T > 0) {
            T -= 1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            TREE = new int[N + 1];
            Arrays.fill(TREE, -1);
            for(int i = 1; i <= N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                TREE[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            pro();
        }
    }

    static void pro() {
        // A의 조상을 다 찾자 a
        int idx = A;
        List<Integer> ancestorA = new ArrayList<>();
        ancestorA.add(idx);
        while(true) {
            int ancestor = TREE[idx];
            if(ancestor == -1) break;

            ancestorA.add(ancestor);
            idx = ancestor;
        }

        idx = B;
        List<Integer> ancestorB = new ArrayList<>();
        ancestorB.add(idx);
        while(true) {
            int ancestor = TREE[idx];
            if(ancestor == -1) break;

            ancestorB.add(ancestor);
            idx = ancestor;
        }

        Loop1 :
        for(int i : ancestorA) {
            for(int j : ancestorB) {
                if(i == j) {
                    sb.append(i).append("\n");
                    break Loop1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }

}
