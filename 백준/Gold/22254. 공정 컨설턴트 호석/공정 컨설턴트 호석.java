import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static long X;
    static long[] time;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        time = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            time[i] = Long.parseLong(st.nextToken());
        }
    }

    static class Line implements Comparable<Line> {
        int idx;
        long time;

        public Line(int idx, long time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Line o) { // 오름차순
            if(time < o.time) return -1;
            else if(time == o.time) return 0;
            return 1;
        }
    }

    static boolean possible(int lines, long limit) {
        Queue<Line> que = new PriorityQueue<>();
        for(int i = 1; i <= lines; i++) {
            que.add(new Line(i, time[i]));
        }

        int idx = lines + 1;
        while(idx <= N) {
            Line cur = que.poll();

            if(cur.time + time[idx] > limit) return false;

            cur.time += time[idx];
            que.add(cur);
            idx += 1;
        }

        return true;
    }

    static void pro() {
        int L = 1;
        int R = N;
        int ans = 0;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(possible(mid, X)) {
                ans = mid;
                R = mid - 1;
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