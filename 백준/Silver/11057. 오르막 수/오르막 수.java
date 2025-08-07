import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private final static int MOD = 10007;
    private static int n;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    private static void pro() {
        int[][] dp = new int[n + 1][10]; // 0 ~ 9
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= MOD;
                for (int k = j + 1; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= MOD;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i];
            result %= MOD;
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}