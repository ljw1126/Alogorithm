import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int n;
    private static int[] data;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t > 0) {
            t -= 1;

            n = Integer.parseInt(br.readLine());
            data = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            pro();
        }
    }

    private static void pro() {
        int[][] acc = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            acc[i][i] = data[i];
        }

        for(int i = 1; i < n; i++) {
            for(int j = i + 1; j <= n; j++) {
                acc[i][j] = acc[i][j - 1] + data[j];
            }
        }

        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for(int len = 2; len <= n; len++) {
            for(int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                for(int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + acc[i][j]);
                }
            }
        }

        sb.append(dp[1][n]).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}