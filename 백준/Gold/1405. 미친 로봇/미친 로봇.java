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

    // 동, 서, 남, 북
    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static int n;
    private static double[] percentage;
    private static double result;

    private static void input() {
        n = inputProcessor.nextInt();

        percentage = new double[4];
        for(int i = 0; i < 4; i++) {
            percentage[i] = inputProcessor.nextInt() * 0.01;
        }
    }

    private static void pro() {
        boolean[][] visited = new boolean[31][31];

        visited[15][15] = true;
        rec(15, 15, 0, visited, 1.0);

        sb.append(result);
    }
    
    private static void rec(int x, int y, int cnt, boolean[][] visited, double total) {
        if(cnt == n) {
            result += total;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx <= 0 || dy <= 0 || dx >= 30 || dy >= 30) continue;
            if(visited[dx][dy]) continue;

            visited[dx][dy] = true;
            rec(dx, dy, cnt + 1, visited, total * percentage[i]);
            visited[dx][dy] = false;
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