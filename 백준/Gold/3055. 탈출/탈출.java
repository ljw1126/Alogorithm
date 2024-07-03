import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int[][] DIR = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static final Character BLANK = '.';
    private static final Character HEDGEHOG = 'S';
    private static final Character BEAVER = 'D';
    private static final Character WATER = '*';
    private static final Character STONE = 'X';
    private static final int MAX_DIST = 2501;
    private static int R, C;
    private static int[][] DIST_HEDGEHOG, DIST_WATER;
    private static String[] FIELD;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        R = inputProcessor.nextInt();
        C = inputProcessor.nextInt();

        FIELD = new String[R];
        for (int i = 0; i < R; i++) {
            FIELD[i] = inputProcessor.nextLine();
        }

        DIST_WATER = new int[R][C];
        DIST_HEDGEHOG = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(DIST_WATER[i], MAX_DIST);
            Arrays.fill(DIST_HEDGEHOG[i], MAX_DIST);
        }
    }

    private static void pro() {
        fillWater();
        runHedgeHog();
    }

    private static void fillWater() {
        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (FIELD[i].charAt(j) == WATER) {
                    que.add(i);
                    que.add(j);
                    DIST_WATER[i][j] = 0;
                }
            }
        }

        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
                if (FIELD[dx].charAt(dy) != BLANK) continue;
                if (DIST_WATER[dx][dy] > DIST_WATER[x][y] + 1) {
                    DIST_WATER[dx][dy] = DIST_WATER[x][y] + 1;
                    que.add(dx);
                    que.add(dy);
                }
            }
        }
    }

    private static void runHedgeHog() {
        Deque<Integer> que = new ArrayDeque<>();

        int destX = 0;
        int destY = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char c = FIELD[i].charAt(j);
                if (c == HEDGEHOG) {
                    que.add(i);
                    que.add(j);
                    DIST_HEDGEHOG[i][j] = 0;
                }

                if (c == BEAVER) {
                    destX = i;
                    destY = j;
                }
            }
        }

        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
                if (FIELD[dx].charAt(dy) == STONE) continue;
                if (DIST_WATER[dx][dy] <= DIST_HEDGEHOG[x][y] + 1) continue;

                if (DIST_HEDGEHOG[dx][dy] > DIST_HEDGEHOG[x][y] + 1) {
                    DIST_HEDGEHOG[dx][dy] = DIST_HEDGEHOG[x][y] + 1;
                    que.add(dx);
                    que.add(dy);
                }
            }
        }

        if (DIST_HEDGEHOG[destX][destY] == MAX_DIST) {
            sb.append("KAKTUS");
        } else {
            sb.append(DIST_HEDGEHOG[destX][destY]);
        }
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