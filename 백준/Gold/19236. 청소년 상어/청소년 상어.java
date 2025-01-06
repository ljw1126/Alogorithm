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

    private static final int[][] DIR = {
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
    };

    private static int[][][] maps;
    private static int result;

    private static void input() {
        maps = new int[5][5][2]; // 0 : 번호, 1 : 방향
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                int no = inputProcessor.nextInt();
                int dir = inputProcessor.nextInt();

                maps[i][j][0] = no;
                maps[i][j][1] = dir - 1;
            }
        }
    }

    private static void pro() {
        rec(maps, 1, 1, 0);

        sb.append(result);
    }

    private static void rec(int[][][] arr, int sharkX, int sharkY, int value) {
        int[][][] _arr = copy(arr);

        int total = value + _arr[sharkX][sharkY][0];
        _arr[sharkX][sharkY][0] = -1;

        moveAllFishes(_arr, sharkX, sharkY);

        List<int[]> nexts = findNextPositions(_arr, sharkX, sharkY);
        if(nexts.size() == 0) {
            result = Math.max(result, total);
            return;
        }

        _arr[sharkX][sharkY][0] = -1;
        _arr[sharkX][sharkY][1] = -1;
        for(int[] next : nexts) {
            rec(_arr, next[0], next[1], total);
        }
    }

    private static int[][][] copy(int[][][] base) {
        int[][][] temp = new int[5][5][2];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                temp[i][j][0] = base[i][j][0];
                temp[i][j][1] = base[i][j][1];
            }
        }

        return temp;
    }

    private static void moveAllFishes(int[][][] arr, int sharkX, int sharkY) {
        for(int i = 1; i <= 16; i++) {
            int[] position = findFish(arr, i);

            if(position == null) continue;

            int x = position[0];
            int y = position[1];
            int no = arr[x][y][0];
            int dir = arr[x][y][1];

            for(int j = 0; j < 8; j++) {
                int _dir = (dir + j) % 8;
                int dx = x + DIR[_dir][0];
                int dy = y + DIR[_dir][1];

                if(dx < 1 || dy < 1 || dx > 4 || dy > 4) continue;
                if(dx == sharkX && dy == sharkY) continue;

                int _no = arr[dx][dy][0];
                int __dir = arr[dx][dy][1];

                arr[dx][dy][0] = no;
                arr[dx][dy][1] = _dir;

                arr[x][y][0] = _no;
                arr[x][y][1] = __dir;

                break;
            }
        }
    }

    private static int[] findFish(int[][][] arr, int no) {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(arr[i][j][0] == no) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    private static List<int[]> findNextPositions(int[][][] arr, int sharkX, int sharkY) {
        List<int[]> result = new ArrayList<>();
        int dir = arr[sharkX][sharkY][1];
        int x = sharkX;
        int y = sharkY;
        for(int i = 0; i < 4; i++) {
            x += DIR[dir][0];
            y += DIR[dir][1];

            if(1 <= x && x <= 4 && 1 <= y && y <= 4 && arr[x][y][0] != -1) {
                result.add(new int[] {x, y});
            }
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