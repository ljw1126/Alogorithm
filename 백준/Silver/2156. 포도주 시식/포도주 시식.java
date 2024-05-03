import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    static int[] DP, DATA;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1];
    }

     static void executeBottomUp() {
        DP[1] = DATA[1];

        if(N >= 2) {
            DP[2] = DATA[1] + DATA[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i] = Math.max(Math.max(DATA[i - 1] + DP[i - 3], DP[i - 2]) + DATA[i], DP[i - 1]);
        }

        System.out.println(DP[N]);
    }

    public static void main(String[] args) throws Exception {
        input();
        executeBottomUp();
    }
    
}