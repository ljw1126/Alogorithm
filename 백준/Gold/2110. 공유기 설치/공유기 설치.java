import java.util.*;
import java.io.*;

public class Main {
    
    static int N, C;
    static int[] houses;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집의 개수
        C = Integer.parseInt(st.nextToken()); // 공유기 개수

        houses = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            houses[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 그리드 기법 사용하여 정렬 후 1번 기준으로 조건 만족하는지 조회 : O(N)
    static boolean isValidDistance(int D) {
        int cnt = 1, last = houses[1];

        for(int i = 2; i <= N; i++) {
            if(houses[i] - last >= D) {
                cnt += 1;
                last = houses[i];
            }
        }

        return cnt >= C;
    }

    static void pro() {
        Arrays.sort(houses, 1, N + 1);

        int result = 0;
        int L = 0, R = 1000000000;
        while(L <= R) {
            int distance = (L + R) / 2;
            if(isValidDistance(distance)) {
                L = distance + 1;
                result = distance;
            } else {
                R = distance - 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
