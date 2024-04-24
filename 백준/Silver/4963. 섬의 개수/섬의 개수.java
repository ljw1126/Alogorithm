import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static int W, H;
    static boolean[][] VISIT;
    static int[][] MATRIX;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void pro() {
        int count = 0;

        for(int i = 1; i <= W; i++) {
            for(int j = 1; j <= H; j++) {
                if(MATRIX[i][j] == 1 && !VISIT[i][j]) {
                    dfs(i, j);
                    count += 1;
                }
            }
        }

        sb.append(count).append("\n");
    }

    private static void dfs(int x, int y) {
        VISIT[x][y] = true;

        for(int i = 0; i < 8; i++) {
            int nx = x + DIR[i][0];
            int ny = y + DIR[i][1];

            if(nx < 1 || ny < 1 || nx > W || ny > H) continue;
            if(VISIT[nx][ny]) continue;
            if(MATRIX[nx][ny] == 0) continue;

            dfs(nx, ny);
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();

        String input = "";
        while(!(input = inputProcessor.nextLine()).equals("0 0")) {
            String[] tokens = input.split(" ");
            W = Integer.parseInt(tokens[0]);
            H = Integer.parseInt(tokens[1]);

            MATRIX = new int[W + 1][H + 1];
            for(int i = 1; i <= H; i++) {
                for(int j = 1; j <= W; j++) {
                    MATRIX[j][i] = inputProcessor.nextInt();
                }
            }

            VISIT = new boolean[W + 1][H + 1];

            pro();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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