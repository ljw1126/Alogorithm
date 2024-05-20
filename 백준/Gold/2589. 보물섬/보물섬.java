import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int ROW, COL, RESULT;
    private static String[] FIELDS;
    private static List<Land> LAND = new ArrayList<>();

    private static class Land {
        private int x;
        private int y;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    // 가장 긴 시간이 걸리는 육지 두곳에 나뉘어 묻혀 있다
    // 가장 긴 시간이 걸리는 육지 두곳의 최단거리
    private static void input() {
        ROW = inputProcessor.nextInt();
        COL = inputProcessor.nextInt();

        FIELDS = new String[ROW];
        for (int i = 0; i < ROW; i++) {
            FIELDS[i] = inputProcessor.nextLine();
            for (int j = 0; j < COL; j++) {
                if (FIELDS[i].charAt(j) == 'L') {
                    LAND.add(new Land(i, j));
                }
            }
        }

        RESULT = 2501;
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static void pro() {
        int lng = 0;
        for (Land land : LAND) {
            int dist = bfs(land);
            if (lng < dist) {
                lng = dist;
                RESULT = dist;
            } else if (lng == dist) {
                RESULT = Math.min(RESULT, dist);
            }
        }
        sb.append(RESULT);
    }

    private static int bfs(Land land) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(land.x);
        que.add(land.y);
        que.add(0);

        boolean[][] visited = new boolean[ROW][COL];
        visited[land.x][land.y] = true;

        int result = 0;
        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            int dist = que.poll();

            if (result < dist) {
                result = dist;
            }

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= ROW || dy >= COL) continue;
                if (FIELDS[dx].charAt(dy) == 'W') continue;
                if (visited[dx][dy]) continue;

                que.add(dx);
                que.add(dy);
                que.add(dist + 1);
                visited[dx][dy] = true;
            }
        }

        return result;
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
    
}