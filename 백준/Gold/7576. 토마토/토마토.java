import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int m, n, count;
    private static int[][] boxes;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        boxes = new int[n][m];
        for(int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                boxes[i][j] = Integer.parseInt(st2.nextToken());

                if(boxes[i][j] == 0) {
                    count += 1;
                }
            }
        }
    }

    private static final int[][] dir = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static void pro() {
        if(count == 0) {
            sb.append(0);
            return;
        }

        Deque<int[]> que = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(boxes[i][j] == 1) {
                    que.add(new int[] {i, j});
                }
            }
        }

        while(!que.isEmpty()) {
            int[] cur = que.poll();

            for(int i = 0; i < 4; i++) {
                int dx = cur[0] + dir[i][0];
                int dy = cur[1] + dir[i][1];

                if(dx < 0 || dy < 0 || dx >= n || dy >= m) continue;
                if(boxes[dx][dy] != 0) continue;

                boxes[dx][dy] = boxes[cur[0]][cur[1]] + 1;
                que.add(new int[] {dx, dy});
                count -= 1;
            }
        }

        if(count > 0) {
            sb.append(-1);
            return;
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(boxes[i][j] == -1) continue;

                result = Math.max(result, boxes[i][j]);
            }
        }

        sb.append(result - 1);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}