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

    private static int N, L, R;
    private static int[][] country;
    
    private static void input() {
        N = inputProcessor.nextInt();
        L = inputProcessor.nextInt();
        R = inputProcessor.nextInt();

        country = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                country[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static void pro() {
        int result = 0;
        while(true) {
            int groupNo = 0;
            int[][] groups = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(groups[i][j] == 0) {
                        groupNo += 1;
                        bfs(i, j, groupNo, groups);
                    }
                }
            }

            if(groupNo == N * N) break;

            result += 1;
        }

        // 결과 출력
        sb.append(result);
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    private static void bfs(int startX, int startY, int groupNo, int[][] groups) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {startX, startY});

        groups[startX][startY] = groupNo;

        List<int[]> union = new ArrayList<>();
        union.add(new int[] {startX, startY});

        int sum = country[startX][startY];
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if(dx < 1 || dy < 1 || dx > N || dy > N) continue;
                if(groups[dx][dy] != 0) continue;

                int diff = Math.abs(country[x][y] - country[dx][dy]);
                if(L <= diff && diff <= R) {
                    int[] next = new int[] {dx, dy};
                    que.add(next);

                    union.add(next);
                    sum += country[dx][dy];
                    groups[dx][dy] = groupNo;
                }
            }
        }

        if(union.size() == 1) return;

        int avg = sum / union.size();
        for(int[] u : union) {
            country[u[0]][u[1]] = avg;
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