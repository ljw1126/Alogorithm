import java.util.*;
import java.io.*;

public class Main {
   static StringBuilder sb = new StringBuilder();

    static int N, K, TARGET_IDX;

    static int[] DATA, PARENT;
    
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = "";
        while(true) {
            input = br.readLine();
            if("0 0".equals(input)) break;

            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken()); // 노드의 수
            K = Integer.parseInt(st.nextToken()); // 사촌의 수

            st = new StringTokenizer(br.readLine());
            DATA = new int[N + 1];
            PARENT = new int[N + 1];

            PARENT[0] = -1;

            int parentIdx = 0;
            for(int i = 1; i <= N; i++) {
                DATA[i] = Integer.parseInt(st.nextToken());
                if(DATA[i] == K) TARGET_IDX = i;
                if(i > 1 && DATA[i] - DATA[i - 1] != 1) parentIdx += 1;

                PARENT[i] = parentIdx;
            }

            pro();
        }
    }
    static void pro() {
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(PARENT[i] != PARENT[TARGET_IDX] && PARENT[PARENT[i]] == PARENT[PARENT[TARGET_IDX]]) cnt += 1;
        }

        sb.append(cnt).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}