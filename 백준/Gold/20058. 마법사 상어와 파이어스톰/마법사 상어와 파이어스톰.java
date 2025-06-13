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

    private static int n, q, size;
    private static int[][] matrix;

    private static void input() {
        n = inputProcessor.nextInt();
        q = inputProcessor.nextInt();

        size = (int) Math.pow(2, n);

        matrix = new int[size + 1][size + 1];

        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                matrix[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static final int[][] dir = {
            {-1 ,0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static void pro() {
        for(int t = 0; t < q; t++) {
            int L = inputProcessor.nextInt();

            matrix = rotate(L, matrix);

            melt();
        }

        // 남은 얼음 양을 합산
        sb.append(totalIce()).append("\n");

        // 가장 큰 얼음 덩어리의 크기를 구한다 (상하좌우로 연결된 그룹)
        sb.append(getMaxSize());
    }


    private static int[][] rotate(int l, int[][] data) {
        int groupSize = (int) Math.pow(2, l);
        int[][] result = new int[size + 1][size + 1];

        for(int si = 1; si <= size; si += groupSize) {
            for(int sj = 1; sj <= size; sj += groupSize) {
                for(int a = 0; a < groupSize; a++) {
                    for(int b = 0; b < groupSize; b++) {
                        result[si + a][sj + b] = data[si + groupSize - 1 - b][sj + a];
                    }
                }
            }
        }

        return result;
    }

    private static int totalIce() {
        int result = 0;
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                result += matrix[i][j];
            }
        }
        
        return result;
    }

    private static int getMaxSize() {
        int result = 0;
        boolean[][] visited = new boolean[size + 1][size + 1];
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                if(matrix[i][j] > 0 && !visited[i][j]) {
                    int count = 0;
                    Deque<int[]> que = new ArrayDeque<>();
                    que.add(new int[] {i, j});
                    visited[i][j] = true;

                    while(!que.isEmpty()) {
                        int[] cur = que.poll();
                        count += 1;

                        for(int k = 0; k < 4; k++) {
                            int dx = cur[0] + dir[k][0];
                            int dy = cur[1] + dir[k][1];

                            if(dx < 1 || dy < 1 || dx > size || dy > size) continue;
                            if(visited[dx][dy]) continue;
                            if(matrix[dx][dy] == 0) continue;

                            visited[dx][dy] = true;
                            que.add(new int[] {dx, dy});
                        }
                    }

                    result = Math.max(result, count);
                }
            }
        }

        return result;
    }

    private static void melt() {
        List<int[]> target = new ArrayList<>();

        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {

                int count = 0;
                for(int k = 0; k < 4; k++) {
                    int dx = i + dir[k][0];
                    int dy = j + dir[k][1];

                    if(dx < 1 || dy < 1 || dx > size || dy > size) continue;
                    if(matrix[dx][dy] > 0) count += 1;
                }

                if(count < 3 && matrix[i][j] > 0) {
                    target.add(new int[] {i, j});
                }
            }
        }

        for(int[] t : target) {
            matrix[t[0]][t[1]] -= 1;
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