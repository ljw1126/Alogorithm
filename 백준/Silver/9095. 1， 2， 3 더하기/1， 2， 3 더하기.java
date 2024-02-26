import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int T;

    static int[] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        DP = new int[12];
        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int i = 4; i <= 11; i++) {
            DP[i] = DP[i - 1] + DP[i - 2] + DP[i -3];
        }

        while(T > 0) {
            T -= 1;

            int N = Integer.parseInt(br.readLine());
            sb.append(DP[N]).append("\n");
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
