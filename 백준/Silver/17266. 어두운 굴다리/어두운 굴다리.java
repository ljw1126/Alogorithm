import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] X;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        X = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValidHeight(int H) {
        int point = 0;
        for(int x : X) {
            if(x - point <= H) {
                point = x + H;
            } else {
                return false;
            }
        }

        return point >= N;
    }
    
    static void pro() {
        int L = 0, R = 100000, ans = 0;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isValidHeight(mid)) {
                R = mid - 1;
                ans = mid;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}