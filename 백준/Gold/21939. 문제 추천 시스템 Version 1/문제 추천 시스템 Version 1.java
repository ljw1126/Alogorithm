import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();
    private static final String ADD = "add";
    private static final String RECOMMEND = "recommend";
    private static final String SOLVED = "solved";

    private static int N, M;
    private static Problems PROBLEMS;
    private static Map<Integer, Integer> PROBLEM_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static class Problem implements Comparable<Problem> {
        private int no;
        private int level;

        public Problem(int no, int level) {
            this.no = no;
            this.level = level;
        }

        public boolean isSameNo(int no) {
            return this.no == no;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level != o.level) return this.level - o.level;

            return this.no - o.no;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Problem problem = (Problem) other;
            return no == problem.no && level == problem.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(no, level);
        }
    }

    private static class Problems {
        private static final int HARD = 1; // 가장 어려운 문제, 번호 큰 순
        private static final int EASY = -1; // 가장 쉬운 문제, 번호 작은 순
        private final TreeSet<Problem> problems;

        public Problems() {
            this(new TreeSet<>());
        }

        public Problems(TreeSet<Problem> problems) {
            this.problems = problems;
        }

        public void add(Problem problem) {
            this.problems.add(problem);
        }

        public Problem recommend(int x) {
            if (this.problems.isEmpty()) {
                return null;
            }
            
            if (x == EASY) {
                return this.problems.first();
            }

            return this.problems.last();
        }

        public void solved(Problem p) {
            this.problems.remove(p);
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();

        PROBLEMS = new Problems();
        for (int i = 1; i <= N; i++) {
            int p = inputProcessor.nextInt(); // 문제 번호
            int l = inputProcessor.nextInt(); // 문제 난이도

            PROBLEMS.add(new Problem(p, l));
            PROBLEM_MAP.put(p, l);
        }
    }


    private static void pro() {
        M = inputProcessor.nextInt();
        while (M > 0) {
            M -= 1;
            String cmd = inputProcessor.next();

            if (ADD.equals(cmd)) {
                int p = inputProcessor.nextInt();
                int l = inputProcessor.nextInt();
                PROBLEMS.add(new Problem(p, l));
                continue;
            }

            if (RECOMMEND.equals(cmd)) {
                int x = inputProcessor.nextInt();
                Problem recommend = PROBLEMS.recommend(x);
                sb.append(recommend.no).append("\n");
                continue;
            }

            if (SOLVED.equals(cmd)) {
                int p = inputProcessor.nextInt();
                int l = PROBLEM_MAP.getOrDefault(p, -1);

                PROBLEMS.solved(new Problem(p, l));
                PROBLEM_MAP.remove(p);
            }
        }

    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
    
}