import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static long K;
    static int[] foods;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 먹이 개수
        K = Long.parseLong(st.nextToken()); // 최소 만족도

        foods = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            foods[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void twoPointer() {

        long[] _dp = new long[N + 1];

        int R = 0;
        long maxleft = 0;
        int sum = 0;
        for(int L = 1; L <= N; L++) {

            maxleft = Math.max(maxleft, _dp[L - 1]);

            while(sum < K && R + 1 <= N) {
                R += 1;
                sum += foods[R];
            }

            if(sum >= K) {
                _dp[R] = Math.max(_dp[R], maxleft + (sum - K));
            }

            sum -= foods[L];
        }

        long ans = 0;
        for(int i = 1; i<= N; i++) ans = Math.max(ans, _dp[i]);

        
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        twoPointer();
    }
    
}