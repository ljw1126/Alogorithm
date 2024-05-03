import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    static long[][] DP;

    static long executeByTopDown(int depth, int last) {
        if(depth == 1) return 1L;

        if(DP[depth][last] != -1) return DP[depth][last];

        long value = 0;
        for(int i = last; i <= 9; i++) {
            value += executeByTopDown(depth - 1, i);
        }

        return DP[depth][last] = value;
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        DP = new long[65][10];
        for(int i = 0; i <= 64; i++) Arrays.fill(DP[i], - 1);

        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 0; i <= 9; i++) result += executeByTopDown(N, i);

            sb.append(result).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}