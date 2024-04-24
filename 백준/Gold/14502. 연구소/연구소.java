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
    private static List<Virus> virusList = new ArrayList<>();
    private static List<Blank> blankList = new ArrayList<>();
    private static int BLANK_NUMBER = 0;
    private static int VIRUSE_NUMBER = 2;
    private static int MAX_WALL = 3;
    private static int[][] BOARD;
    private static int N, M;
    private static int RESULT = 0;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 세로
        M = inputProcessor.nextInt(); // 가로

        BOARD = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = inputProcessor.nextInt();
                BOARD[i][j] = value;

                if (value == BLANK_NUMBER) {
                    blankList.add(new Blank(i, j));
                } else if (value == VIRUSE_NUMBER) {
                    virusList.add(new Virus(i, j));
                }
            }
        }
    }

    private static class Virus {
        private int x;
        private int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static class Blank {
        private int x;
        private int y;

        public Blank(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static void pro() {
        createWall(0, 0);
        sb.append(RESULT);
    }

    private static void createWall(int count, int idx) {
        if (count == MAX_WALL) {
            boolean[][] spreadVirus = spreadVirus();
            int cnt = countSafetyArea(spreadVirus);
            RESULT = Math.max(cnt, RESULT);
            return;
        }

        if (idx >= blankList.size()) return;

        Blank blank = blankList.get(idx);
        BOARD[blank.x][blank.y] = 1;
        createWall(count + 1, idx + 1);

        BOARD[blank.x][blank.y] = 0;
        createWall(count, idx + 1);
    }


    private static boolean[][] spreadVirus() {
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (Virus virus : virusList) {
            dfs(virus.x, virus.y, visited);
        }
        return visited;
    }

    private static void dfs(int x, int y, boolean[][] visited) {
        if (visited[x][y]) return;

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 1 || dx > N || dy < 1 || dy > M) continue;
            if (visited[dx][dy]) continue;
            if (BOARD[dx][dy] != BLANK_NUMBER) continue;

            dfs(dx, dy, visited);
        }
    }

    private static int countSafetyArea(boolean[][] spreadVirus) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (BOARD[i][j] == 0 && !spreadVirus[i][j]) {
                    count += 1;
                }
            }
        }

        return count;
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