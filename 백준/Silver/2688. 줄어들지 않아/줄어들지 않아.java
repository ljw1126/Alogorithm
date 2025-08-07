import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int n;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t > 0) {
            t -= 1;

            n = Integer.parseInt(br.readLine());

            pro();
        }
    }

    private static void pro() {

        long[][] dp = new long[n + 1][10];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n][i];
        }

        sb.append(result).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}