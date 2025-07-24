import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    private static void pro() {
        final int mod = 10007;
        int[][] dp = new int[1001][2];

        dp[1][0] = 1;

        dp[2][0] = 1;
        dp[2][1] = 1;

        for(int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][1] = (dp[i - 2][0] + dp[i - 2][1]) % mod;
        }


        int result = (dp[n][0] + dp[n][1]) % mod;
        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}