import java.util.*;
import java.io.*;

public class Main {
   
     static int N;
    static int[] A;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isPossible(int targetIdx) {
        int target = A[targetIdx];
        int L = 1, R = N;
        while(L < R) {
            if(L == targetIdx) {
                L += 1;
            } else if(R == targetIdx) {
                R -= 1;
            } else {
                int sum = A[L] + A[R];
                if(sum == target) return true;

                if(sum < target) L += 1;
                else R -= 1;
            }
        }

        return false;
    }

    static void pro() {
        Arrays.sort(A, 1, N + 1);
        
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(isPossible(i)) cnt += 1;
        }

        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
