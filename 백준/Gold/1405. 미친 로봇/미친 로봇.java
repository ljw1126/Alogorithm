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
    private static double[] percent;

    private static void input() {
        N = inputProcessor.nextInt();
        percent = new double[4];
        for(int i = 0; i < 4; i++) {
            int v = inputProcessor.nextInt();
            if(v == 0) continue;

            percent[i] = v * 0.01;
        }
    }

    // 0 : 동, 1 : 서, 2: 남, 3: 북
    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static void pro() {
        boolean[][] visited = new boolean[30][30];
        visited[15][15] = true;
        sb.append(backTracking(15, 15, visited, 0, 1.0));
    }

    private static double backTracking(int x, int y, boolean[][] visited, int count, double weight) {
        if(count == N) {
            return weight;
        }

        double result = 0.0;
        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(visited[dx][dy]) continue;

            visited[dx][dy] = true;
            result += backTracking(dx, dy, visited, count + 1, weight * percent[i]);
            visited[dx][dy] = false;
        }

        return result;
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