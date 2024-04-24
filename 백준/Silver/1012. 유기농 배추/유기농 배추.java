import java.io.*;
import java.util.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int T, M, N, K, CNT;

    static int[][] FIELD;
    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken()); // 테스트 케이스
        while(T > 0) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); // 가로 길이
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            K = Integer.parseInt(st.nextToken()); // 배추 심어진 위치 개수

            FIELD = new int[M][N];
            VISIT = new boolean[M][N];

            for(int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                FIELD[x][y] = 1;
            }

            pro();

            T -= 1;
        }
    }

    static void dfs(int x, int y) {
        VISIT[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + DIRECTION[i][0];
            int ny = y + DIRECTION[i][1];

            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(VISIT[nx][ny]) continue;
            if(FIELD[nx][ny] == 0) continue;

            dfs(nx, ny);
        }
    }

    static void pro() {
        CNT = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(!VISIT[i][j] && FIELD[i][j] == 1) {
                    CNT += 1;
                    dfs(i, j);
                }
            }
        }
        sb.append(CNT).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
    
}