import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    static int[] V;

    static int MOD = 1000000007;

    static List<Integer>[] adj;

    static int[][] dp;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 정점의 개수

        st = new StringTokenizer(br.readLine());
        V = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        dp = new int[N + 1][10]; // 0 ~ 9
    }

    static void dp(int idx, int prev) {
        dp[idx][V[idx]] = 1;

        for(int child : adj[idx]) {
            if(child == prev) continue;

            dp(child, idx);

            for(int i = 0; i <= 9; i++) {
                dp[idx][i] += dp[child][i];
                dp[idx][i] %= MOD;
            }

            for(int i = V[idx]; i <= 9; i++) {
                dp[idx][V[idx]] += dp[child][i];
                dp[idx][V[idx]] %= MOD;
            }
        }
    }

    static void pro() {
        dp(1, -1);

        int ans = 0;
        for(int i = 0; i <= 9; i++) {
            ans += dp[1][i];
            ans %= MOD;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}