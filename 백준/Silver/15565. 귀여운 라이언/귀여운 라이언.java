import java.util.*;
import java.io.*;

public class Main {
    
    static int N, K;
    static int[] A;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        for(int L = 1, R = 1; L < A.length; L++) {
            while(R <= N && cnt < K) {
                if(A[R] == 1) cnt += 1;

                R += 1;
            }

            if(cnt == K) ans = Math.min(ans, R - L);

            if(A[L] == 1) cnt -=1;
        }


        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
