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

    private static final int[][] dir = {
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1}
    };

    private static int[][][] sea;

    private static void input() {
        sea = new int[4][4][2];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                int a = inputProcessor.nextInt(); // 물고기 번호
                int b = inputProcessor.nextInt(); // 방향

                sea[i][j][0] = a;
                sea[i][j][1] = b - 1;
            }
        }
    }

    private static void pro() {
        int fishNo = sea[0][0][0];
        sea[0][0][0] = -1;
        sb.append(backTracking(0, 0, fishNo , sea));
    }

    // 현재 상어의 위치와 방향이 중요하지 번호는 의미가 없음
    private static int backTracking(int x, int y, int sum, int[][][] field) {
        int[][][] copied = copy(field);

        int curDir = copied[x][y][1];

        moveFish(copied, x, y);

        List<int[]> targets = searchNextTarget(x, y, curDir, copied);

        if(targets.isEmpty()) {
            return sum;
        }

        copied[x][y][1] = -1;
        int result = 0;
        for(int[] next : targets) {
            int fishNo = copied[next[0]][next[1]][0];

            copied[next[0]][next[1]][0] = -1;

            result = Math.max(result, backTracking(next[0], next[1], sum + fishNo, copied));

            copied[next[0]][next[1]][0] = fishNo;
        }

        return result;
    }

    private static int[][][] copy(int[][][] field) {
        int[][][] result = new int[4][4][2];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                result[i][j][0] = field[i][j][0];
                result[i][j][1] = field[i][j][1];
            }
        }

        return result;
    }

    private static int[][][] moveFish(int[][][] field, int sharkX, int sharkY) {
        for(int k = 1; k <= 16; k++) {
            int[] fish = findFish(k, field);

            if(fish == null) continue;

            int x = fish[0];
            int y = fish[1];
            int fishNo = field[x][y][0];
            int fishDir = field[x][y][1];
            for(int i = 0; i < 8; i++) {
                int nextDir = (fishDir + i) % 8;
                int dx = x + dir[nextDir][0];
                int dy = y + dir[nextDir][1];

                if(dx < 0 || dy < 0 || dx >= 4 || dy >= 4) continue;
                if(sharkX == dx && sharkY == dy) continue;

                // 빈칸이나 다른 물고기
                field[x][y][0] = field[dx][dy][0];
                field[x][y][1] = field[dx][dy][1];

                field[dx][dy][0] = fishNo;
                field[dx][dy][1] = nextDir;

                break;
            }
        }

        return field;
    }

    private static int[] findFish(int no, int[][][] field) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(field[i][j][0] == no) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    private static List<int[]> searchNextTarget(int x, int y, int direction, int[][][] moved) {
        List<int[]> next = new ArrayList<>();

        int dx = x;
        int dy = y;
        for (int i = 1; i <= 4; i++) {
            dx += dir[direction][0];
            dy += dir[direction][1];

            if(dx < 0 || dy < 0 || dx >= 4 || dy >= 4) break;
            if(moved[dx][dy][0] >= 1) {
                next.add(new int[]{dx, dy});
            }
        }

        return next;
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