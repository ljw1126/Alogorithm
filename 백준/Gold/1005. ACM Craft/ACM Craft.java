import java.util.*;
import java.io.*;

public class Main {
    // 최대 1,000 노드가 각각 100,000 걸리면 10^8
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int T, N, K, X, Y, W;
    static int[] TIME, IN_DEGREE;
    static List<Integer>[] ADJ;

    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        while(T > 0) {
            input();
            pro();
            T -= 1;
        }

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 건물 개수 (노드)
        K = inputProcessor.nextInt(); // 건물간의 건설 규칙 수

        TIME = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            TIME[i] = inputProcessor.nextInt();
        }

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[N + 1];
        for(int i = 1; i <= K; i++) {
            X = inputProcessor.nextInt();
            Y = inputProcessor.nextInt();

            ADJ[X].add(Y);
            IN_DEGREE[Y] += 1;
        }

        W = inputProcessor.nextInt(); // 건설해야 할 건물 번호
    }

    private static void pro() {
        int result = bfs();
        sb.append(result).append("\n");
    }

    private static int bfs() {
        Deque<Integer> que = new ArrayDeque<>();

        int[] tDone = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                tDone[i] = TIME[i];
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : ADJ[x]) {
                IN_DEGREE[y] -= 1;

                if(IN_DEGREE[y] == 0) {
                    que.add(y);
                }

                tDone[y] = Math.max(tDone[y], tDone[x] + TIME[y]);
            }
        }

        return tDone[W];
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
