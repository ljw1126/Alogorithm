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

    // 북동남서
    private static final int[][] dir = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private static int n, m, x, y, d;
    private static int[][] room;
    private static Robot robot;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        x = inputProcessor.nextInt();
        y = inputProcessor.nextInt();
        d = inputProcessor.nextInt();

        room = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                room[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static class Robot {
        private int x;
        private int y;
        private int d; // 방향

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public Robot back(int dx, int dy) {
            return new Robot(dx, dy, d);
        }
    }

    private static void pro() {
        Deque<Robot> que = new ArrayDeque<>();
        que.add(new Robot(x, y, d));

        int count = 0;
        while(!que.isEmpty()){
            Robot cur = que.poll();

            if(room[cur.x][cur.y] == 0) {
                count += 1;
                room[cur.x][cur.y] = 2;
            }

            boolean find = false;
            int d = cur.d;
            for(int i = 0; i < 4; i++) {
                d -= 1;

                if(d < 0) d = 3;

                int dx = cur.x + dir[d][0];
                int dy = cur.y + dir[d][1];

                if(dx < 0 || dy < 0 || dx >= n || dy >= m) continue;
                if(room[dx][dy] == 0) {
                    find = true;
                    que.add(new Robot(dx, dy, d));
                    break;
                }
            }

            if(!find) {
                int dx = cur.x - dir[cur.d][0];
                int dy = cur.y - dir[cur.d][1];

                if(room[dx][dy] == 1) break; // 벽이라면

                que.add(new Robot(dx, dy, cur.d));
            }
        }

        sb.append(count);
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