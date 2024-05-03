import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    static int MOD = 9901;
    static int[] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        DP = new int[100001];
        DP[0] = 1;
        DP[1] = 3;

        for(int i = 2; i <= 100000; i++) {
            DP[i] = (2 * DP[i - 1] + DP[i - 2]) % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
        System.out.println(DP[N]);
    }
    
}