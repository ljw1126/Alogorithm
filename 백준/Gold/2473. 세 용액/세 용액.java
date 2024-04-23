import java.util.*;
import java.io.*;

public class Main {
   static StringBuilder sb = new StringBuilder();

    static int N;

    static long BEST, V1, V2, V3;
    static long[] A;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        BEST = Long.MAX_VALUE;
    }

    static void twoPoint(int baseIdx) {
        int L = baseIdx + 1, R = N;
        while(L < R) {
            long sum = A[baseIdx] + A[L] + A[R];

            if(Math.abs(sum) < BEST) {
                BEST = Math.abs(sum);
                V1 = A[baseIdx];
                V2 = A[L];
                V3 = A[R];
            }

            if(sum > 0) R -= 1;
            else L += 1;
        }
    }

    static void pro() {
        Arrays.sort(A, 1, N + 1);

        for(int i = 1; i <= N - 2; i++) {
            twoPoint(i);
        }

        sb.append(V1).append(" ").append(V2).append(" ").append(V3);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
