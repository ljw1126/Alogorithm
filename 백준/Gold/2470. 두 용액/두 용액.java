import java.util.*;
import java.io.*;

public class Main {
   static StringBuilder sb = new StringBuilder();

   static int N;

   static int[] A;

   static void input() throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       N = Integer.parseInt(br.readLine());

       A = new int[N];

       StringTokenizer st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) {
           A[i] = Integer.parseInt(st.nextToken());
       }

       Arrays.sort(A);
   }

   static int binarySearch(int L, int R, int target) {
       while(L < R) {
           int mid = (L + R) / 2;

           if(A[mid] < target) {
               L = mid + 1;
           } else {
               R = mid - 1;
           }
       }

       return L;
   }

   static void pro() {
        int L = 0;
        int R = N - 1;
        int bestValue = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        while(L < R) {
            int sum = A[L] + A[R];
            if(bestValue > Math.abs(sum)) {
                bestValue = Math.abs(sum);
                v1 = A[L];
                v2 = A[R];
            }

            if(sum > 0) R -= 1;
            else L += 1;
        }

        sb.append(v1).append(" ").append(v2);
        System.out.println(sb);
   }

   public static void main(String[] args) throws Exception {
       input();
       pro();
   }
}
