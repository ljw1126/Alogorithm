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

    private static int r, c, t;
    private static int[][] matrix;

    private static List<int[]> airMachine;

    private static void input() {
        r = inputProcessor.nextInt();
        c = inputProcessor.nextInt();
        t = inputProcessor.nextInt();

        matrix = new int[r + 1][c + 1];
        airMachine = new ArrayList<>();

        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                int v = inputProcessor.nextInt();
                matrix[i][j] = v;

                if(v == -1) { // 공기 청정기
                    airMachine.add(new int[] {i, j});
                }
            }
        }
    }

    private static final int[][] dustDir = {
            {0, -1},
            {0, 1},
            {1, 0},
            {-1, 0}
    };

    private static final int[][] topDir = {
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 0}
    };

    private static final int[][] downDir = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static void pro() {
        while(t > 0) {
            t -= 1;

            int[][] dust = spreadDust();

            int[][] temp = new int[r + 1][c + 1];
            int[] topAir = airMachine.get(0);
            int[] downAir = airMachine.get(1);

            temp[topAir[0]][topAir[1]] = -1;
            temp[downAir[0]][downAir[1]] = -1;
            for(int i = 2; i <= r - 1; i++) {
                for(int j = 2; j <= c - 1; j++) {
                    if(topAir[0] == i || downAir[0] == i) continue;

                    temp[i][j] = dust[i][j];
                }
            }

            int x = topAir[0];
            int y = topAir[1];
            int d = 0;
            while(true) {
                int dx = x + topDir[d][0];
                int dy = y + topDir[d][1];

                if(dx == topAir[0] && dy == topAir[1]) break;
                if(dx < 1 || dy < 1 || dx > r || dy > c) {
                    d += 1;
                    continue;
                }

                if(dust[x][y] > 0) {
                    temp[dx][dy] = dust[x][y];
                }

                x = dx;
                y = dy;
            }

            x = downAir[0];
            y = downAir[1];
            d = 0;
            while(true) {
                int dx = x + downDir[d][0];
                int dy = y + downDir[d][1];

                if(dx == downAir[0] && dy == downAir[1]) break;
                if(dx < 1 || dy < 1 || dx > r || dy > c) {
                    d += 1;
                    continue;
                }

                if(dust[x][y] > 0) {
                    temp[dx][dy] = dust[x][y];
                }

                x = dx;
                y = dy;
            }

            matrix = temp;
        }

        int result = 0;
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                if(matrix[i][j] < 1) continue;

                result += matrix[i][j];
            }
        }

        sb.append(result);
    }

    private static int[][] spreadDust() {
        int[][] temp = new int[r + 1][c + 1];
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                if(matrix[i][j] <= 0) continue;

                int count = 0;
                for(int k = 0; k < 4; k++) {
                    int dx = i + dustDir[k][0];
                    int dy = j + dustDir[k][1];

                    if(dx < 1 || dy < 1 || dx > r || dy > c) continue;
                    if(matrix[dx][dy] == -1) continue;

                    temp[dx][dy] += (matrix[i][j] / 5);
                    count += 1;
                }

                temp[i][j] += matrix[i][j] - ((matrix[i][j] / 5) * count);
            }
        }

        return temp;
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