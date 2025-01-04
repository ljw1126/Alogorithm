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

    // 위, 아래, 왼쪽, 오른쪽
    private static final int[][] DIR = {
            {},
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    private static int n, m, k;
    private static int[][][] fields;
    private static int[][][] priority;
    private static Shark[] sharks;

    private static void input() {
        n = inputProcessor.nextInt(); // 맵 크기 n * n
        m = inputProcessor.nextInt(); // 상어 수
        k = inputProcessor.nextInt(); // 상어 냄새 턴

        sharks = new Shark[m + 1];

        fields = new int[n + 1][n + 1][2]; // 0 : 상어 번호, 1 : 냄새
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int v = inputProcessor.nextInt();

                if(v == 0) continue;

                fields[i][j][0] = v;
                fields[i][j][1] = k;

                sharks[v] = new Shark(i, j, v, -1);
            }
        }

        // 초기 상어 방향
        for(int i = 1; i <= m; i++) {
            sharks[i].dir = inputProcessor.nextInt();
        }

        // 상어 방향별 우선 순위
        priority = new int[m + 1][5][5];
        for(int i = 1; i <= m; i++) { // i번 상어의 우선 순위
            for(int j = 1; j <= 4; j++) { // j = 1 : 위, 2 : 아래, 3: 왼쪽, 4: 오른쪽일때 우선순위
                int d1 = inputProcessor.nextInt();
                int d2 = inputProcessor.nextInt();
                int d3 = inputProcessor.nextInt();
                int d4 = inputProcessor.nextInt();

                priority[i][j] = new int[] {-1, d1, d2, d3, d4};
            }
        }
    }

    private static class Shark {
        private final int x;
        private final int y;
        private final int no;
        private int dir;

        public Shark(int x, int y, int no, int dir) {
            this.x = x;
            this.y = y;
            this.no = no;
            this.dir = dir;
        }
    }

    private static void pro() {
        int result = -1;
        for(int i = 1; i <= 1000; i++) {
           
            // 상어가 이동할 경로를 탐색한다
            for(int s = 1; s <= m; s++) {
                if(sharks[s] == null) continue;

                Shark next = nextPosition(sharks[s]);
                sharks[s] = next;
            }

            // 냄새를 하나씩 지운다
            decreaseSmell();

            // 상어가 이동한다
            for(int s= 1; s <= m; s++) {
                if(sharks[s] == null) continue;

                Shark shark = sharks[s];
                int x = shark.x;
                int y = shark.y;
                int no = shark.no;

                if(fields[x][y][0] != 0 && fields[x][y][0] < no) {
                    sharks[s] = null;
                    continue;
                }

                fields[x][y][0] = no;
                fields[x][y][1] = k;
            }
            
             if(liveOnlyOne()) {
                result = i;
                break;
            }

        }


        sb.append(result);
    }

    private static Shark nextPosition(Shark shark) {
        int x = shark.x;
        int y = shark.y;
        int no = shark.no;
        int dir = shark.dir;
        int[] prior = priority[no][dir];

        for(int i = 1; i <= 4; i++) {
            int dx = x + DIR[prior[i]][0];
            int dy = y + DIR[prior[i]][1];

            if(dx < 1 || dy < 1 || dx > n || dy > n) continue;
            if(fields[dx][dy][0] != 0 || fields[dx][dy][1] > 0) continue;

            return new Shark(dx, dy, no, prior[i]);
        }

        for(int i = 1; i <= 4; i++) {
            int dx = x + DIR[prior[i]][0];
            int dy = y + DIR[prior[i]][1];

            if(dx < 1 || dy < 1 || dx > n || dy > n) continue;
            if(fields[dx][dy][0] != no) continue;

            return new Shark(dx, dy, no, prior[i]);
        }


        return null;
    }

    private static void decreaseSmell() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(fields[i][j][0] == 0) continue;

                if(fields[i][j][1] > 0 && --fields[i][j][1] == 0) {
                    fields[i][j][0] = 0;
                    fields[i][j][1] = 0;
                }
            }
        }
    }

    private static boolean liveOnlyOne() {
        for(int i = 2; i <= m; i++) {
            if(sharks[i] != null) return false;
        }

        return true;
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