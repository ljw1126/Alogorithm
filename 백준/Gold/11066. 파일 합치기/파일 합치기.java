import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int T, K;

    static int[] A;

    static int[][] SUM, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            K = Integer.parseInt(br.readLine());

            A = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= K; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            SUM = new int[K + 1][K + 1];
            DP = new int[K + 1][K + 1];

            pro();
        }
    }

    static void preprocess() { // i ~ j 까지의 합을 미리 구함
        for(int i = 1; i <= K; i++) {
            for(int j = i; j <= K; j++) {
                SUM[i][j] = SUM[i][j - 1] + A[j]; // 실수
            }
        }
    }

    static void pro() {
        preprocess();

        for(int len = 2; len <= K; len++) { // 구간 길이
            for(int i = 1; i <= (K - len + 1); i++) { // 시작 지점
                int j = i - 1 + len; // 종료 지점
                DP[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + SUM[i][j]);
                }
            }
        }

        sb.append(DP[1][K]).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}