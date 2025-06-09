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
    private static List<Student> students;
    private static int[] idx;
    private static int[][] result;

    private static void input() {
        N = inputProcessor.nextInt();
        result = new int[N + 1][N + 1];

        int studentCount = N * N;
        students = new ArrayList<>();
        idx = new int[studentCount + 1];

        for(int i = 0; i < studentCount; i++) {
            int no = inputProcessor.nextInt();
            Student student = new Student(no, new boolean[studentCount + 1]);
            for(int j = 0; j < 4; j++) {
                int like = inputProcessor.nextInt();
                student.mark(like);
            }

            students.add(student);
            idx[no] = i;
        }
    }

    private static class Student {
        private int no;
        private boolean[] likes;

        public Student(int no, boolean[] likes) {
            this.no = no;
            this.likes = likes;
        }

        public void mark(int like) {
            likes[like] = true;
        }

        public boolean isLike(int no) {
            return likes[no];
        }
    }


    private static int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static void pro() {
        // 탐색한다
        for(Student student : students) {
            find(student);
        }

        // 계산한다
        int score = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int no = result[i][j];
                Student student = students.get(idx[no]);

                int like = 0;
                for(int k = 0; k < 4; k++) {
                    int dx = i + dir[k][0];
                    int dy = j + dir[k][1];

                    if(dx < 1 || dy < 1 || dx > N || dy > N) continue;

                    if(student.isLike(result[dx][dy])) like += 1;
                }

                score += convertToScore(like);
            }
        }

        sb.append(score);
    }

    private static int convertToScore(int like) {
        if(like <= 1) return like;

        return (int) Math.pow(10, like - 1);
    }

    private static void find(Student student) {
        int x = N + 1;
        int y = N + 1;
        int maxLike = 0;
        int maxEmpty = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (result[i][j] != 0) continue;

                int empty = 0;
                int like = 0;
                for(int k = 0; k < 4; k++) {
                    int dx = i + dir[k][0];
                    int dy = j + dir[k][1];

                    if(dx < 1 || dy < 1 || dx > N || dy > N) continue;

                    if(result[dx][dy] == 0) empty += 1;
                    else if(student.isLike(result[dx][dy])) like += 1;
                }

                // 1. 좋아하는 학생이 인접한 칸이 가장 많은 칸
                if(maxLike < like) {
                    x = i;
                    y = j;
                    maxLike = like;
                    maxEmpty = empty;
                } else if(maxLike == like && maxEmpty < empty) { // 여러개라면 인접한 칸 중에 비어있는 칸이 가장 많은 칸
                    x = i;
                    y = j;
                    maxEmpty = empty;
                } else if(maxLike == like && maxEmpty == empty && i < x && j < y) {
                    x = i;
                    y = j;
                }
            }
        }

        result[x][y] = student.no;
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