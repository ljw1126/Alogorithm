import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static int[][] matrix;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            String line = br.readLine();
            for(int j = 1; j <= m; j++) {
                matrix[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    private static void pro() {
        int[][] dist = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        int[][] dir = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        Deque<Integer> que = new ArrayDeque<>();
        que.add(1);
        que.add(1);
        
        dist[1][1] = 1;

        while(!que.isEmpty()){
            int x = que.poll();
            int y = que.poll();

            if(x == n && y == m) {
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int dx =  x + dir[i][0];
                int dy =  y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > n || dy > m) continue;
                if(matrix[dx][dy] == 0) continue;
                if(dist[dx][dy] != Integer.MAX_VALUE) continue;

                dist[dx][dy] = dist[x][y] + 1;
                que.add(dx);
                que.add(dy);
            }
        }

        sb.append(dist[n][m]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}