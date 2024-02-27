import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();

    static int T, N;

    static int MOD = 1000000009;

    static long[][] DP;

    static void inputForBottomUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 1; i <= 3; i++) {
                result += DP[N][i];
                result %= MOD;
            }
            
            sb.append(result).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void executeByBottomUp() {
        DP = new long[100001][4];
        DP[1][1] = 1;
        DP[2][2] = 1;
        DP[3][1] = 1;
        DP[3][2] = 1;
        DP[3][3] = 1;

        for(int i = 4; i <= 100000; i++) {
            DP[i][1] = (DP[i - 1][2] + DP[i - 1][3]) % MOD;
            DP[i][2] = (DP[i - 2][1] + DP[i - 2][3]) % MOD;
            DP[i][3] = (DP[i - 3][1] + DP[i - 3][2]) % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        executeByBottomUp();
        inputForBottomUp();
    }
}