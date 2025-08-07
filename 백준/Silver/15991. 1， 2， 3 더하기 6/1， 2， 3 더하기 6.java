import java.util.*;
import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static final int MOD = 1000_000_009;
    private static int n;
    private static long[] dp;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new long[100001];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;

        int t = Integer.parseInt(br.readLine());
        while (t > 0) {
            t -= 1;

            n = Integer.parseInt(br.readLine());

            sb.append(topDown(n)).append("\n");
        }
    }

    // 양쪽에 1을 추가하는 경우, 2를 추가하는 경우, 3을 추가하는 경우
    private static long topDown(int node) {
        if (dp[node] != -1) return dp[node];

        dp[node] = (topDown(node - 2) + topDown(node - 4) + topDown(node - 6)) % MOD;

        return dp[node];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}