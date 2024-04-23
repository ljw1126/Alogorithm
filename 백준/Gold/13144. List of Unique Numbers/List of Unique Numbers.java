import java.util.*;
import java.io.*;

public class Main {
   
    static int N;

    static int[] NUMBERS;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        NUMBERS = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            NUMBERS[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        int[] cnt = new int[100001];

        int L = 1, R = 1;
        long ans = 0L;
        while(L <= N) {
            while(R <= N && cnt[NUMBERS[R]] < 1) {
                cnt[NUMBERS[R]] += 1;
                R += 1;
            }

            ans += (R - L);

            cnt[NUMBERS[L]] -= 1;
            L += 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
