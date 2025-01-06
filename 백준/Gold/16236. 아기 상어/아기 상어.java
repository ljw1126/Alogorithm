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

    private static int n, sharkX, sharkY, sharkLv, ate;

    private static int[][] maps;
    private static final int[] NOT_FOUND = new int[3];

    private static void input() {
        n = inputProcessor.nextInt();

        maps = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int v = inputProcessor.nextInt();
                if(v == 9) { // 상어인 경우
                    sharkX = i;
                    sharkY = j;
                    continue;
                }

                maps[i][j] = v;
            }
        }

        sharkLv = 2;
        ate = 0;
    }

    private static void pro() {
        int time = 0;
        while(true) {
            int[] target = find(bfs());

            if(target == null) break;
            
            sharkX = target[0];
            sharkY = target[1];
            maps[target[0]][target[1]] = 0;
            ate += 1;
            time += target[2];

            if(ate >= sharkLv) {
                sharkLv += 1;
                ate = 0;
            }
        }

        sb.append(time);
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static int[][] bfs() {
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(sharkX, sharkY));

        int[][] dist = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[sharkX][sharkY] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            for(int i = 0; i < 4; i++) {
                int dx = cur.x + DIR[i][0];
                int dy = cur.y + DIR[i][1];

                if(dx < 1 || dy < 1 || dx > n || dy > n) continue;
                if(dist[dx][dy] != -1) continue;
                if(sharkLv < maps[dx][dy]) continue;

                dist[dx][dy] = dist[cur.x][cur.y] + 1;
                que.add(new Node(dx, dy));
            }
        }

        return dist;
    }

    private static class Node {
        private final int x;
        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] find(int[][] dist) {
        int minDist = Integer.MAX_VALUE;
        int x = 0;
        int y = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(maps[i][j] == 0 || dist[i][j] == -1) continue;
                if(sharkLv <= maps[i][j]) continue;

                if(dist[i][j] < minDist) {
                    minDist = dist[i][j];
                    x = i;
                    y = j;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? null : new int[] {x, y, minDist};
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