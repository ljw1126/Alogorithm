import java.util.*;
import java.io.*;

public class Main {
    
    static int N, C;

    static List<Stone>[] X, Y;

    static long CNT;

    static long SUM;

    static class Stone {
        int x;
        int y;
        int v;

        public Stone(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 광물의 개수
        C = Integer.parseInt(st.nextToken()); // 호석이가 가진 돈

        X = new ArrayList[100002];
        Y = new ArrayList[100002];
        for(int i = 0; i <= 100001; i++) {
            X[i] = new ArrayList<>();
            Y[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            X[x].add(new Stone(x, y, v)); // 너비 기준
            Y[y].add(new Stone(x, y, v)); // 높이 기준
        }
    }

    static void del(int x, int y) {
        for(Stone stone : Y[y]) {
            if(stone.x <= x) {
                CNT -= 1;
                SUM -= stone.v;
            }
        }
    }

    static void add(int x, int y) {
        for(Stone stone : X[x]) {
            if(stone.y <= y) {
                CNT += 1;
                SUM += stone.v;
            }
        }
    }

    static void pro() {
        long ans = 0L;
        int W = 0;
        int H = 100000;

        CNT = 0L;
        SUM = 0L;

        while(W <= 100000 && H >= 0) {
            if(CNT <= C) {
                add(W, H);
                W += 1;
            } else {
                del(W, H);
                H -= 1;
            }

            if(CNT <= C && ans < SUM) ans = SUM;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}