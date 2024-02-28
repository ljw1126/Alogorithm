import java.util.*;
import java.io.*;

public class Main {
    
     static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] A;
    static boolean[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        pro();

        M = Integer.parseInt(br.readLine());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(DP[s][e] ? 1 : 0).append("\n");
        }
    }
    static void pro() {
        DP = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            DP[i][i] = true;
        }

        for(int i = 1; i < N; i++) {
            if(A[i] == A[i + 1]) DP[i][i + 1] = true;
        }

        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= (N - len + 1); i++) {
                int j = i - 1 + len;
                if(A[i] == A[j] && DP[i + 1][j - 1]) {
                    DP[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}