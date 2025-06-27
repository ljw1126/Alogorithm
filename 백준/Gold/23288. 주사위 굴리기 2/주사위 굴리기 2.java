import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, m, k;
    private static int[][] matrix;
    private static int[] dics;

    private static void input() {
        n = inputProcessor.nextInt(); 
        m = inputProcessor.nextInt(); 
        k = inputProcessor.nextInt(); 

        matrix = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = inputProcessor.nextInt();
            }
        }

        dics = new int[6];
        dics[0] = 2;
        dics[1] = 1;
        dics[2] = 5;
        dics[3] = 6;
        dics[4] = 4;
        dics[5] = 3;
    }

    private static int[][] dir = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static void pro() {
        int d = 0; // 동쪽
        int x = 1;
        int y = 1;
        int result = 0;
        for (int i = 1; i <= k; i++) {
            int dx = x + dir[d][0];
            int dy = y + dir[d][1];

            if (!moveable(dx, dy)) {
                d = (d + 2) % 4;
                dx = x + dir[d][0];
                dy = y + dir[d][1];
            }


            rollDics(d);
            result += getScore(dx, dy);

            int a = dics[3]; 
            int b = matrix[dx][dy];
            if (a > b) {
                d = (d + 1) % 4;
            } else if (a < b) {
                d -= 1;
                if (d < 0) d = 3;
            }

            x = dx;
            y = dy;
        }

        sb.append(result);
    }

    private static void rollDics(int d) {
        switch (d) {
            case 0:
                east();
                break;
            case 1:
                south();
                break;
            case 2:
                west();
                break;
            case 3:
                north();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int getScore(int x, int y) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{x, y});

        boolean[][] visited = new boolean[n + 1][m + 1];
        visited[x][y] = true;

        int count = 1;
        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int dx = cur[0] + dir[i][0];
                int dy = cur[1] + dir[i][1];

                if (dx < 1 || dy < 1 || dx > n || dy > m) continue;
                if (visited[dx][dy]) continue;
                if (matrix[dx][dy] != matrix[x][y]) continue;

                count += 1;
                visited[dx][dy] = true;
                que.add(new int[]{dx, dy});
            }
        }

        return count * matrix[x][y];
    }

    private static boolean moveable(int dx, int dy) {
        return (dx >= 1 && dx <= n && dy >= 1 && dy <= m);
    }

    private static void north() {
        int[] temp = new int[6];

        for (int i = 0; i < 4; i++) {
            temp[i] = dics[(i + 1) % 4];
        }

        temp[4] = dics[4];
        temp[5] = dics[5];

        dics = temp;
    }

    private static void south() {
        int[] temp = new int[6];

        for (int i = 0; i < 4; i++) {
            temp[i] = dics[(3 + i) % 4];
        }

        temp[4] = dics[4];
        temp[5] = dics[5];

        dics = temp;
    }

    private static void east() {
        int[] temp = new int[6];

        temp[0] = dics[0];
        temp[2] = dics[2];

        temp[4] = dics[3];
        temp[1] = dics[4];
        temp[5] = dics[1];
        temp[3] = dics[5];

        dics = temp;
    }

    private static void west() {
        int[] temp = new int[6];

        temp[0] = dics[0];
        temp[2] = dics[2];

        temp[4] = dics[1];
        temp[1] = dics[5];
        temp[5] = dics[3];
        temp[3] = dics[4];

        dics = temp;
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}