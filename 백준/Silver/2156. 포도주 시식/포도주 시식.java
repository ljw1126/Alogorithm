import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static int[] liquid;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        liquid = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            liquid[i] = Integer.parseInt(br.readLine());
        }

    }

    private static void pro() {
        int[] dp = new int[n + 1];
        dp[1] = liquid[1];
        if (n >= 2) {
            dp[2] = liquid[1] + liquid[2];
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1]; // 마시지 않는다

            int drunk = Math.max(liquid[i - 1] + dp[i - 3], dp[i - 2]) + liquid[i];
            dp[i] = Math.max(dp[i], drunk);
        }

        sb.append(dp[n]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}