import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();

    static int T, N, TEAM_COUNT;

    static int[] student;

    static boolean[] visit, finish;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        while(T > 0) {
            T -= 1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            student = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[N + 1];
            finish = new boolean[N + 1];
            TEAM_COUNT = 0;

            pro();
        }

        br.close();
    }

    static void dfs(int x) {
        visit[x] = true;

        int y = student[x];
        if(!visit[y]) {
            dfs(y);
        } else if(!finish[y]) {
            int cnt = 1;
            while(x != y) {
                y = student[y];
                cnt += 1;
            }

            TEAM_COUNT += cnt;
        }

        finish[x] = true;
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) dfs(i);
        }

        sb.append(N - TEAM_COUNT).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}