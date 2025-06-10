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

    private static int n, m, result, maxValue;
    private static int[][] fields;
    private static boolean[][] visited;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        fields = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                fields[i][j] = inputProcessor.nextInt();
                maxValue = Math.max(maxValue, fields[i][j]);
            }
        }

        visited = new boolean[n][m];
    }

    private static void pro() {
        result = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    rec(i, j, fields[i][j], 1);
                    visited[i][j] = false;
                }
            }
        }

        sb.append(result);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static void rec(int x, int y, int sum, int depth) {
        if(depth == 4) {
            result = Math.max(result, sum);
            return;
        }
        if(sum + (4 - depth) * maxValue < result) return;

        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if (dx < 0 || dy < 0 || dx >= n || dy >= m) continue;
            if (visited[dx][dy]) continue;

            if(depth == 2) {
                visited[dx][dy] = true;
                rec(x, y, sum + fields[dx][dy], depth + 1);
                visited[dx][dy] = false;
            }

            visited[dx][dy] = true;
            rec(dx, dy, sum + fields[dx][dy], depth + 1);
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