import java.util.*;
import java.io.*;

public class Main {
    
     static int N, M, T;
    static int[][] pixels;

    static boolean[][] visited;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pixels = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                int sum = 0;
                for(int k = 1; k <= 3; k++) {
                    sum += Integer.parseInt(st.nextToken());
                }
                pixels[i][j] = sum / 3;
            }
        }

        T = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                pixels[i][j] = pixels[i][j] >= T ? 255 : 0;
            }
        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(!visited[i][j] && pixels[i][j] == 255) {
                    marking(i, j);
                    result += 1;
                }
            }
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static void marking(int x, int y) {
        visited[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 1 || dy < 1 || dx > N || dy > M)continue;
            if(visited[dx][dy]) continue;
            if(pixels[dx][dy] == 0) continue;

            marking(dx, dy);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
    }
    
}