import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M;

    static int[][] fields;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fields = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() throws IOException {

        // up : 상승
        long[][] up = new long[N + 1][M + 1];

        up[N][1] = fields[N][1];
        for(int row = N - 1; row >= 1; row--) {
            up[row][1] = fields[row][1] + up[row + 1][1];
        }

        for(int col = 2; col <= M; col++) {
            up[N][col] = fields[N][col] + up[N][col - 1];
        }

        for(int row = N - 1; row >= 1; row--) {
            for(int col = 2; col <= M; col++) {
                up[row][col] = fields[row][col] + Math.max(up[row][col - 1], up[row + 1][col]);
            }
        }

        // down : 하강
        long[][] down = new long[N + 1][M + 1];
        down[N][M] = fields[N][M];
        for(int row = N - 1; row >= 1; row--) {
            down[row][M] = fields[row][M] + down[row + 1][M];
        }

        for(int col = M - 1; col >= 1; col--) {
            down[N][col] = fields[N][col] + down[N][col + 1];
        }

        for(int row = N - 1; row >= 1; row--) {
            for(int col = M - 1; col >= 1; col--) {
                down[row][col] = fields[row][col] + Math.max(down[row][col + 1], down[row + 1][col]);
            }
        }

        // 결과
        long result = Long.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                result = Math.max(result, up[i][j] + down[i][j]);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(result));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}