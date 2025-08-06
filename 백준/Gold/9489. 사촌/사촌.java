import java.util.*;
import java.io.*;

public class Main {
  static StringBuilder sb = new StringBuilder();

    static int N, K, ROOT, TARGET_IDX;

    static int[] TREE, PARENT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = "";
        while(!(input = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken()); // 노드의 수
            K = Integer.parseInt(st.nextToken()); // 사촌의 수

            TREE = new int[N + 1];
            PARENT = new int[N + 1];

            st = new StringTokenizer(br.readLine());

            int parentIdx = 0;
            PARENT[0] = -1;

            for(int i = 1; i <= N; i++) {
               TREE[i] = Integer.parseInt(st.nextToken());

               if(TREE[i] == K) TARGET_IDX = i;

               if(i > 1 && TREE[i] - TREE[i - 1] != 1) parentIdx += 1;

               PARENT[i] = parentIdx;
            }

            pro();
        }
    }

    static void pro() {
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(PARENT[TARGET_IDX] != PARENT[i] && PARENT[PARENT[TARGET_IDX]] == PARENT[PARENT[i]]) cnt += 1;
        }

        sb.append(cnt).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}