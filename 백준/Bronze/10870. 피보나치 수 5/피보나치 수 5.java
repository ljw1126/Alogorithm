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

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    private static void pro() {
        int[] dp = new int[21];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= 20; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
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