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

    private static int n, m, k;
    private static int[][] food, land;
    private static Queue<Tree> aliveTree;
    private static Deque<Tree> deadTree;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();
        k = inputProcessor.nextInt();

        food = new int[n + 1][n + 1];
        land = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                food[i][j] = inputProcessor.nextInt();
                land[i][j] = 5;
            }
        }

        aliveTree = new PriorityQueue<>();
        for(int i = 1; i <= m; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int z = inputProcessor.nextInt();
            aliveTree.add(new Tree(x, y, z));
        }

        deadTree = new ArrayDeque<>();

    }

    private static class Tree implements Comparable<Tree>{
        private int x;
        private int y;
        private int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        public boolean isPossibleAge() {
            return age % 5 == 0;
        }

        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    private static void pro() {
        for(int y = 1; y <= k; y++) {
            spring();
            summer();
            autumn();
            winter();
        }

        sb.append(aliveTree.size());
    }

    private static void spring() {
        Queue<Tree> next = new PriorityQueue<>();
        while(!aliveTree.isEmpty()) {
            Tree cur = aliveTree.poll();

            if(land[cur.x][cur.y] < cur.age) {
                deadTree.add(cur);
                continue;
            }

            land[cur.x][cur.y] -= cur.age;
            cur.age += 1;
            next.add(cur);
        }

        aliveTree = next;
    }

    private static void summer() {
        while(!deadTree.isEmpty()) {
            Tree cur = deadTree.poll();
            land[cur.x][cur.y] += (cur.age / 2);
        }
    }

    private static final int[][] dir = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    private static void autumn() {
        Queue<Tree> next = new PriorityQueue<>();
        while(!aliveTree.isEmpty()) {
            Tree cur = aliveTree.poll();
            next.add(cur);

            if(!cur.isPossibleAge()) continue;

            int x = cur.x;
            int y = cur.y;

            for(int i = 0; i < 8; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > n || dy > n) continue;

                next.add(new Tree(dx, dy, 1));
            }
        }

        aliveTree = next;
    }

    private static void winter() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                land[i][j] += food[i][j];
            }
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