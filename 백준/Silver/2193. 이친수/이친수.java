import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    static long[] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new long[N + 1];
    }

    static void executeBottomUp() {
        DP[0] = 0;
        DP[1] = 1;

        for(int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + DP[i -2];
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        executeBottomUp();

        System.out.println(DP[N]);
    }
    
}