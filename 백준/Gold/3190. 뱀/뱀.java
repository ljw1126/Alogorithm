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

    private static int N, K, L;
    private static int[][] BOARD;
    private static Map<Integer, String> COMMAND_MAP;

    private static void input() {
        N = inputProcessor.nextInt(); // 보드의 크기
        K = inputProcessor.nextInt(); // 사과의 개수
        BOARD = new int[N + 1][N + 1];
        for(int i = 1; i <= K; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();

            BOARD[x][y] = 1;
        }

        L = inputProcessor.nextInt();
        COMMAND_MAP = new HashMap<>();
        for(int i = 1; i <= L; i++) {
            int x = inputProcessor.nextInt();
            String c = inputProcessor.next();

            COMMAND_MAP.put(x, c);
        }
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static void pro() {
        Deque<Integer> snake = new LinkedList<>();
        snake.add(1);
        snake.add(1);

        BOARD[1][1] = 2; // 뱀 표시

        int i = 0; // 오른쪽 이동
        int time = 0;
        int dx = 1;
        int dy = 1;
        while(true) {
            dx += DIR[i][0];
            dy += DIR[i][1];

            if(dx < 1 || dy < 1 || dx > N || dy > N || BOARD[dx][dy] == 2) {
                time += 1;
                break;
            }

            if(BOARD[dx][dy] == 1) { // 사과가 있는 경우
                BOARD[dx][dy] = 2;
            } else { // 빈 공간
                int nx = snake.poll();
                int ny = snake.poll();
                BOARD[nx][ny] = 0; // 꼬리 제거
            }

            BOARD[dx][dy] = 2;
            snake.add(dx);
            snake.add(dy);

            time += 1;
            // 방향 전환*
            if(COMMAND_MAP.containsKey(time)) {
                String command = COMMAND_MAP.remove(time);
                i = nextDir(i, command);
            }
        }

        sb.append(time);
    }

    private static int nextDir(int i, String command) {
        if(command.equals("D")) { // 오른쪽 90도
            return (i + 1) % 4;
        }

        return (i - 1) < 0 ? 3 : (i - 1); // 왼쪽 90도
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