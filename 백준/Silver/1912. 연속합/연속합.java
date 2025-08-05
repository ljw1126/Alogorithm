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
    private static int[] data;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        data = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void pro() {
        int[] dp = new int[n + 1];

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            dp[i] = Math.max(data[i], dp[i - 1] + data[i]);
            result = Math.max(result, dp[i]);
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