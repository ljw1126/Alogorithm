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

    private static int N, M;
    private static int[][] bucket;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        bucket = new int[N][N]; // N * N 격자

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                bucket[r][c] = inputProcessor.nextInt();
            }
        }
    }

    private static final int[][] dir = {
            {},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    private static final int[][] diagonal = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    private static void pro() {
        List<int[]> cloud = new ArrayList<>();
        cloud.add(new int[]{N - 1, 0});
        cloud.add(new int[]{N - 1, 1});
        cloud.add(new int[]{N - 2, 0});
        cloud.add(new int[]{N - 2, 1});

        boolean[][] visited;

        for(int i = 1; i <= M; i++) {
            visited = new boolean[N][N];

            int d = inputProcessor.nextInt(); 
            int s = inputProcessor.nextInt(); 

            List<int[]> next = new ArrayList<>();
            for (int[] c : cloud) {
                int dx = (c[0] + dir[d][0] * s + N * s) % N;
                int dy = (c[1] + dir[d][1] * s + N * s) % N;

                bucket[dx][dy] += 1;
                visited[dx][dy] = true;
                next.add(new int[] {dx, dy});
            }

            for(int[] c : next) {
                int x = c[0];
                int y = c[1];

                for(int k = 0; k < diagonal.length; k++) {
                    int dx = x + diagonal[k][0];
                    int dy = y + diagonal[k][1];

                    if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
                    if(bucket[dx][dy] > 0) {
                        bucket[x][y] += 1;
                    }
                }
            }

            cloud.clear();
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if(!visited[j][k] && bucket[j][k] >= 2) {
                        cloud.add(new int[]{j, k});
                        bucket[j][k] -= 2;
                    }
                }
            }
        }

        int water = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                water += bucket[i][j];
            }
        }

        sb.append(water);
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