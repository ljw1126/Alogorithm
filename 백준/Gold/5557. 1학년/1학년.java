import java.util.*;
import java.io.*;

public class Main {
    static int N;

    static int[] DATA;

    static long[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = Integer.parseInt(st.nextToken());
        }

        DP = new long[21][101]; // sum, depth
        for(long[] d : DP) Arrays.fill(d, -1);
    }

    static long rec(int sum, int depth) {
        if(sum < 0 || sum > 20) return 0L;
        if(depth == N - 1) {
            return sum == DATA[N] ? 1L : 0L;
        }

        if(DP[sum][depth] != -1) return DP[sum][depth];

        return DP[sum][depth] = rec(sum + DATA[depth + 1], depth + 1) + rec(sum - DATA[depth + 1], depth + 1);
    }

    static void pro() {
        System.out.println(rec(DATA[1], 1));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}