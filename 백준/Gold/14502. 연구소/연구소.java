import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    // 0 : 빈칸, 1 : 벽, 2 : 바이러스
    private static int N, M, RESULT;
    private static int[][] FACILITY;
    private static List<Virus> virusList = new ArrayList<>();
    private static List<Blank> blankList = new ArrayList<>();
    private static int[] SELECTED;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        FACILITY = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = inputProcessor.nextInt();
                FACILITY[i][j] = value;

                if (value == 0) {
                    blankList.add(new Blank(i, j));
                }

                if (value == 2) {
                    virusList.add(new Virus(i, j));
                }
            }
        }

        SELECTED = new int[3]; // 벽은 3개만 세워야 한다
    }

    // 64C3 벽을 3개 세워야한다
    // 바이러스를 퍼뜨린다
    private static void pro() {
        createWall(0, 0);
        sb.append(RESULT);
    }

    private static void createWall(int start, int count) {
        if (count == 3) {
            calculate();
            return;
        }

        for (int i = start; i < blankList.size(); i++) {
            SELECTED[count] = i;
            createWall(i + 1, count + 1);
            SELECTED[count] = -1;
        }
    }

    private static void calculate() {
        int[][] temp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                temp[i][j] = FACILITY[i][j];
            }
        }

        // 벽을 세운다
        for (int i = 0; i < 3; i++) {
            Blank blank = blankList.get(SELECTED[i]);
            temp[blank.x][blank.y] = 1;
        }

        // 바이러스를 퍼뜨린다
        for (Virus virus : virusList) {
            Deque<Integer> que = new ArrayDeque<>();
            que.add(virus.x);
            que.add(virus.y);

            while (!que.isEmpty()) {
                int x = que.poll();
                int y = que.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = x + DIR[i][0];
                    int dy = y + DIR[i][1];

                    if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
                    if (temp[dx][dy] != 0) continue;

                    temp[dx][dy] = 2;
                    que.add(dx);
                    que.add(dy);
                }
            }
        }

        // 안전 영역을 센다
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (temp[i][j] == 0) {
                    count += 1;
                }
            }
        }

        RESULT = Math.max(RESULT, count);
    }

    private static class Blank {
        private int x;
        private int y;

        public Blank(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Virus {
        private int x;
        private int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
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
                } catch (Exception e) {
                    e.printStackTrace();
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