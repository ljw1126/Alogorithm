import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static char[][] fields;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        fields = new char[n][n];
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                fields[i][j] = line.charAt(j);
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
        boolean[][] visited = new boolean[n][n];

        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) continue;

                count += 1;
                bfs1(visited, i, j);
            }
        }
        sb.append(count).append(" ");

        visited = new boolean[n][n];
        count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) continue;

                count += 1;
                bfs2(visited, i, j);
            }
        }

        sb.append(count);
    }

    private static void bfs1(boolean[][] visited, int i, int j) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {i, j});
        visited[i][j] = true;

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            for(int k = 0; k < 4; k++) {
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
                if(visited[dx][dy]) continue;
                if(fields[dx][dy] != fields[x][y]) continue;

                que.add(new int[] {dx, dy});
                visited[dx][dy] = true;
            }
        }
    }

    private static void bfs2(boolean[][] visited, int i, int j) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {i, j});
        visited[i][j] = true;

        boolean isRG = fields[i][j] == 'R' || fields[i][j] == 'G';

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            for(int k = 0; k < 4; k++) {
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
                if(visited[dx][dy]) continue;
                if(isRG && (fields[dx][dy] == 'R' || fields[dx][dy] == 'G')) {
                    que.add(new int[] {dx, dy});
                    visited[dx][dy] = true;
                } else if(fields[dx][dy] == fields[x][y]) {
                    que.add(new int[] {dx, dy});
                    visited[dx][dy] = true;
                }
            }
        }
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}