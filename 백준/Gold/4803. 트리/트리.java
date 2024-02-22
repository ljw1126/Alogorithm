import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static String MANY_TREE = "Case %d: A forest of %d trees.";
    static String ONE_TREE = "Case %d: There is one tree.";
    static String NO_TREE = "Case %d: No trees.";
    static int N, M;
    static List<Integer>[] ADJ;
    static int[] PARENT;
    static boolean[] VISIT;
    static boolean[] CYCLE;

    public static void main(String[] args) throws IOException {
        String input = "";
        int testCase = 1;
        while(!(input = inputProcessor.nextLine()).equals("0 0")) {
            input(input);

            int treeCount = pro();

            result(testCase, treeCount);

            testCase += 1;
        }

        output();
    }

    private static void result(int testCase, int treeCount) {
        if(treeCount == 0) {
            sb.append(String.format(NO_TREE, testCase)).append("\n");
        } else if(treeCount == 1) {
            sb.append(String.format(ONE_TREE, testCase)).append("\n");
        } else if(treeCount > 1) {
            sb.append(String.format(MANY_TREE, testCase, treeCount)).append("\n");
        }
    }

    private static void input(String text) {
        String[] tokens = text.split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        PARENT = new int[N + 1];
        Arrays.fill(PARENT, -1);

        VISIT = new boolean[N + 1];

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            ADJ[to].add(from);
        }
    }

    public static int pro() {
        int treeCount = 0;
        for(int i = 1; i <= N; i++) {
            if(!VISIT[i] && !isCycle(i, -1)) {
                treeCount += 1;
            }
        }

        return treeCount;
    }

    private static boolean isCycle(int node, int prev) {
        VISIT[node] = true;

        for(int next : ADJ[node]) {
            if(next == prev) continue;

            if(!VISIT[next]) {
                if(isCycle(next, node)) return true;
            } else if(next != prev) {
                return true;
            }
        }

        return false;
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
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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