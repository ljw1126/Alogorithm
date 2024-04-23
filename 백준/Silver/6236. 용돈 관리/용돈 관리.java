import java.util.*;
import java.io.*;

public class Main {

    static int N, M, MIN;
    static int[] A;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            MIN = Math.max(MIN, A[i]);
        }
    }

    static boolean isValid(int limit) {
        int wallet = limit;
        int cnt = 1;
        for(int a : A) {
            if(wallet - a < 0) {
                cnt += 1;
                wallet = limit;
            }

            wallet -= a;
        }

        return cnt <= M;
    }

    static void pro() {
        int K = 0;
        int L = MIN, R = (int)1e9;
        while(L <= R) {
            int limit = (L + R) / 2;
            
            if(isValid(limit)) {
                R = limit - 1;
                K = limit;
            } else {
                L = limit + 1;
            }
        }

        System.out.println(K);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}