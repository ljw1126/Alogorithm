import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    static int MOD = 1000000000;

    static long[][][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DP = new long[N + 1][10][1 << 10];
         for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }

    }

    static long executeByTopDown(int depth, int lastNumber, int bit) {
        if(depth == N) return bit == (1 << 10) - 1 ? 1 : 0;

        if(DP[depth][lastNumber][bit] != -1) return DP[depth][lastNumber][bit];

        int cnt = 0;
        if(lastNumber > 0) cnt += executeByTopDown(depth + 1, lastNumber - 1, bit | (1 << (lastNumber - 1)));
        if(lastNumber < 9) cnt += executeByTopDown(depth + 1, lastNumber + 1, bit | (1 << (lastNumber + 1)));

        return DP[depth][lastNumber][bit] = cnt % MOD;
    }

    static void pro() {
        long result = 0;
        for(int i = 1; i <= 9; i++) {
            result += executeByTopDown(1, i, 1 << i);
            result %= MOD;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}