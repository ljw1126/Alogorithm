import java.util.*;
import java.io.*;

public class Main {
    
   static int N, L, R;

    static int[][] WORLD, GROUP_INFO;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N * N 크기의 땅
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        GROUP_INFO = new int[N + 1][N + 1];

        WORLD = new int[N + 1][N + 1];
        for(int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++) {
                WORLD[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    static class Country {
        int x;
        int y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int startX, int startY, int groupNumber) {
        Queue<Country> que = new LinkedList<>();
        que.add(new Country(startX, startY));
        GROUP_INFO[startX][startY] = groupNumber;

        List<Country> union = new ArrayList<>();
        union.add(new Country(startX, startY));

        int cnt = 1;
        int sum = WORLD[startX][startY];

        while(!que.isEmpty()) {
            Country c = que.poll();
            int x = c.x;
            int y = c.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(GROUP_INFO[nx][ny] != -1) continue;

                int diff = Math.abs(WORLD[x][y] - WORLD[nx][ny]);
                if(L <= diff && diff <= R) {
                    GROUP_INFO[nx][ny] = groupNumber;
                    que.add(new Country(nx, ny));

                    union.add(new Country(nx, ny));
                    cnt += 1;
                    sum += WORLD[nx][ny];
                }
            }
        }

        int avg = sum / cnt;
        for(Country c : union) {
            WORLD[c.x][c.y] = avg;
        }
    }

    static void initGroupInfo() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
               GROUP_INFO[i][j] = -1;
            }
        }
    }

    static void pro() {
        int days = 0;
        while(true) {
            initGroupInfo();

            int groupNumber = 0;
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(GROUP_INFO[i][j] == -1) {
                        bfs(i, j, groupNumber);
                        groupNumber += 1;
                    }
                }
            }

            if(groupNumber == N * N) break;
            else days += 1;
        }

        System.out.println(days);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}