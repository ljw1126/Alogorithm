import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int[][] DIR = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static String[] TEXT;
    static boolean[][] VISIT;
    static int[][] DIST;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs(0, 0);
        sb.append(DIST[N - 1][M - 1]);
    }

    private static void bfs(int startX, int startY) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(startX);
        que.add(startY);
        DIST[startX][startY] = 1;
        VISIT[startX][startY] = true;

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(TEXT[nx].charAt(ny) == '0') continue;
                if(VISIT[nx][ny]) continue;

                if(DIST[nx][ny] >= DIST[x][y] + 1) {
                    VISIT[nx][ny] = true;
                    DIST[nx][ny] = DIST[x][y] + 1;
                    que.add(nx);
                    que.add(ny);
                }
            }
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // row
        M = inputProcessor.nextInt(); // col

        TEXT = new String[N];
        for(int i = 0; i < N; i++) {
            TEXT[i] = inputProcessor.nextLine();
        }

        DIST = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(DIST[i], Integer.MAX_VALUE);
        }

        VISIT = new boolean[N][M];
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