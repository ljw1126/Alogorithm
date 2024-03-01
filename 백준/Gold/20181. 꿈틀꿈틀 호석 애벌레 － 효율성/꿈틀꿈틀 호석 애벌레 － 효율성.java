import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static long K;
    static int[] foods;

    static class Info {
        private int start;
        private long point;

        public Info(int start, long point) {
            this.start = start;
            this.point = point;
        }

        public int start() {
            return this.start;
        }

        public long point() {
            return this.point;
        }
    }

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

    static void pro() throws Exception {
        List<Info>[] infos = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) infos[i] = new ArrayList<>();

        long acc = 0;
        for(int L = 1, R = 0; L <= N; L++) { // 초기화 실수 가능
            while(acc < K && R + 1 <= N) {
                R += 1;
                acc += foods[R];
            }

            if(K <= acc) { // 기준치 만족도 이상일 경우
                infos[R].add(new Info(L, acc - K));
            }

            acc -= foods[L];
        }

        long[] dp = new long[N + 1];
        for(int R = 1; R <= N; R++) { // isEmpty일대 continue 빼니 다 맞음
            dp[R] = dp[R - 1]; // 이걸 빼먹음..
            for(Info info : infos[R]) {
                dp[R] = Math.max(dp[R], dp[info.start() - 1] + info.point());
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}