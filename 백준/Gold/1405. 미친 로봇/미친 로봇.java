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

    private static int N;
    private static double[] percentage; // 동서남북
    private static double result;

    private static void input() {
        N = inputProcessor.nextInt();
        percentage = new double[4];
        for(int i = 0; i < 4; i++) {
            int p = inputProcessor.nextInt();
            percentage[i] = (double) p / 100;
        }
    }

    private static void pro() {
        boolean[][] maps = new boolean[31][31];
        maps[15][15] = true;

        rec(15, 15, 0, 1.0, maps);

        sb.append(result);
    }

    private static int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static void rec(int x, int y, int cnt, double value, boolean[][] maps) {
        if(cnt == N) {
            result += value;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 1 || dy < 1 || dx >= 30 || dy >= 30) continue;
            if(maps[dx][dy]) continue;

            maps[dx][dy] = true;
            rec(dx, dy, cnt + 1, value * percentage[i], maps);
            maps[dx][dy] = false;
        }
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