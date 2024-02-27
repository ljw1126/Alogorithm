import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A;
    static int[][] DP;
    
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1][2];
    }

    static void pro() {
        DP[1][0] = A[1];

        if(N >= 2) {
            DP[2][0] = A[2];
            DP[2][1] = A[1] + A[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 2][0], DP[i - 2][1]) + A[i];
            DP[i][1] = DP[i - 1][0] + A[i];
        }

        System.out.println(Math.max(DP[N][0], DP[N][1]));
    }


    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}