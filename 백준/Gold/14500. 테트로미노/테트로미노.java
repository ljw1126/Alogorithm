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

    private static int n, m;
    private static int[][] fields;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        fields = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                fields[i][j] = inputProcessor.nextInt();
            }
        }
    }

    // 기준점 좌표는 생략
    private static int[][][] blocks = {
        {
            {0, 1}, {0, 2}, {0, 3}
        }, // --
        {
            {1, 0}, {2, 0}, {3, 0}
        }, // |
        {
            {0, 1}, {1, 0}, {1, 1}
        }, // 네모
        {
            {1, 0}, {1, 1}, {2, 0}
        }, // ㅏ (4개)
        {
            {0, 1}, {0, 2}, {1, 1}
        }, // ㅜ
        {
            {1, 0}, {1, -1}, {2, 0}
        }, // ㅓ
        {
            {0, 1}, {0, 2}, {-1, 1}
        }, // ㅗ
        {
            {0, 1}, {1, 0}, {2, 0}
        }, // r (4개), ㄴ (4개)
        {
            {0, 1}, {1, 1}, {2, 1}
        },
        {
            {1, 0}, {2, 0}, {2, 1}
        },
        {
            {1, 0}, {2, 0}, {2, -1}
        },
        {
            {1, 0}, {1, 1}, {1, 2}
        },
        {
            {0, 1}, {0, 2}, {1, 0}
        },
        {
            {0, 1}, {0, 2}, {-1, 2}
        },
        {
            {0, 1}, {0, 2}, {1, 2}
        },
        {
            {1, 0}, {1, 1}, {2, 1}
        }, // 마지막 블록
        {
            {0, 1}, {-1, 1}, {-1, 2}
        },
        {
            {0, 1}, {1, 1}, {1, 2}
        },
        {
            {1, 0}, {1, -1}, {2, -1}
        }
    };

    private static void pro() {
        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                result = Math.max(result, calculate(i, j));
            }
        }

        sb.append(result);
    }

    private static int calculate(int i, int j) {
        int result = 0;
        for(int[][] block : blocks) {
            int sum = fields[i][j];
            for(int[] b : block) {
                int dx = i + b[0];
                int dy = j + b[1];

                if(dx < 0 || dy < 0 || dx >= n || dy >= m) {
                    sum = -1;
                    break;
                }

                sum += fields[dx][dy];
            }

            result = Math.max(result, sum);
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