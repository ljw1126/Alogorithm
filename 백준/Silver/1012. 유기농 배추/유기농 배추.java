import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int n, m, k;
    private static boolean[][] fields;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while(t > 0) {
            t -= 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken()); 

            fields = new boolean[m][n];
            for(int i = 0; i < k; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());

                fields[x][y] = true;
            }

            pro();
        }
    }

    private static void pro() {
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!fields[i][j]) continue;

                dfs(i, j);
                count += 1;
            }
        }

        sb.append(count).append("\n");
    }

    private static int[][] dir = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static void dfs(int x, int y) {
        fields[x][y] = false;

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= m || dy >= n) continue;
            if(!fields[dx][dy]) continue;

            dfs(dx, dy);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}