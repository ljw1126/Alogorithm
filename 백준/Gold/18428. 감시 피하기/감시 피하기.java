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

    private static int N;
    private static String[][] school;
    private static List<Blank> blanks;
    private static List<Teacher> teachers;
    private static boolean result;

    private static void input() {
        N = inputProcessor.nextInt();
        school = new String[N + 1][N + 1];

        blanks = new ArrayList<>();
        teachers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                String v = inputProcessor.next();
                school[i][j] = v;

                if ("T".equals(v)) {
                    teachers.add(new Teacher(i, j, -1));
                } else if ("X".equals(v)) {
                    blanks.add(new Blank(i, j));
                }
            }
        }
    }

    private static class Blank {
        private final int x;
        private final int y;

        public Blank(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Teacher {
        private final int x;
        private final int y;
        private final int dir;

        public Teacher(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static void pro() {
        createDummy(0, 0);
        sb.append(result ? "YES" : "NO");
    }

    private static void createDummy(int idx, int cnt) {
        if (result) return;
        if (cnt == 3) {
            result = survive();
            return;
        }

        if (idx >= blanks.size()) return;

        Blank blank = blanks.get(idx);
        school[blank.x][blank.y] = "O";
        createDummy(idx + 1, cnt + 1);

        school[blank.x][blank.y] = "X";
        createDummy(idx + 1, cnt);
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static boolean survive() {
        Deque<Teacher> queue = new ArrayDeque<>();
        for (Teacher t : teachers) {
            for (int i = 0; i < 4; i++) {
                queue.add(new Teacher(t.x, t.y, i));
            }
        }

        while (!queue.isEmpty()) {
            Teacher cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int i = cur.dir;

            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 1 || dy < 1 || dx > N || dy > N) continue;
            if ("S".equals(school[dx][dy])) return false;
            if ("O".equals(school[dx][dy])) continue;

            queue.add(new Teacher(dx, dy, i));
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