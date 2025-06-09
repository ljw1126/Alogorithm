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

    private static int n, m, x, y, k;
    private static int[][] fields;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();
        x = inputProcessor.nextInt();
        y = inputProcessor.nextInt();
        k = inputProcessor.nextInt();

        fields = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                fields[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static int[][] dir = {
            {},
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };

    private static void pro() {
        Dice dice = new Dice(x, y);
        for(int i = 1; i <= k; i++) {
            int d = inputProcessor.nextInt();

            int dx = dice.x + dir[d][0];
            int dy = dice.y + dir[d][1];

            if(dx < 0 || dy < 0 || dx >= n || dy >= m) continue;

            dice = dice.roll(dx, dy, d);

            sb.append(dice.top()).append("\n");

            if(fields[dx][dy] == 0) {
                fields[dx][dy] = dice.floor();
            } else {
                dice.setFloor(fields[dx][dy]);
                fields[dx][dy] = 0;
            }
        }
    }

    private static class Dice {
        private int x;
        private int y;
        private int[] numbers; // 1 ~ 6번면

        public Dice(int x, int y) {
            this(x, y, new int[7]);
        }

        public Dice(int x, int y, int[] numbers) {
            this.x = x;
            this.y = y;
            this.numbers = numbers;
        }

        public int top() {
            return numbers[1];
        }

        public int floor() {
            return numbers[6];
        }

        public void setFloor(int num) {
            numbers[6] = num;
        }

        // 동 : 1, 서 : 2, 북 : 3, 남 : 4
        public Dice roll(int dx, int dy, int d) {
            if(d == 1) return new Dice(dx, dy, east());
            else if(d == 2) return new Dice(dx, dy, west());
            else if(d == 3) return new Dice(dx, dy, north());

            return new Dice(dx, dy, south());
        }

        public int[] east() {
            int[] result = new int[7];
            result[2] = numbers[2];
            result[5] = numbers[5];

            result[3] = numbers[1];
            result[6] = numbers[3];
            result[4] = numbers[6];
            result[1] = numbers[4];

            return result;
        }

        public int[] west() {
            int[] result = new int[7];
            result[2] = numbers[2];
            result[5] = numbers[5];

            result[4] = numbers[1];
            result[6] = numbers[4];
            result[3] = numbers[6];
            result[1] = numbers[3];

            return result;
        }

        public int[] north() {
            int[] result = new int[7];
            result[3] = numbers[3];
            result[4] = numbers[4];

            result[2] = numbers[1];
            result[6] = numbers[2];
            result[5] = numbers[6];
            result[1] = numbers[5];

            return result;
        }

        public int[] south() {
            int[] result = new int[7];
            result[3] = numbers[3];
            result[4] = numbers[4];

            result[1] = numbers[2];
            result[5] = numbers[1];
            result[6] = numbers[5];
            result[2] = numbers[6];

            return result;
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