import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N;
    static List<Integer>[] ADJ;
    static int[] IN_DEGREE;
    static int[] TIME;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs();
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        int[] tDone = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                tDone[i] = TIME[i];
                que.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                IN_DEGREE[next] -= 1;

                if(IN_DEGREE[next] == 0) {
                    que.add(next);
                }

                tDone[next] = Math.max(tDone[next], tDone[cur] + TIME[next]);
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(tDone[i]).append("\n");
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();

        IN_DEGREE = new int[N + 1];
        TIME = new int[N + 1];
        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            int time = inputProcessor.nextInt();
            TIME[i] = time;

            while(true) {
                int prev = inputProcessor.nextInt();

                if(prev == -1) break;

                ADJ[prev].add(i); // i 를 짓기 위해서는 prev가 선행되야 한다
                IN_DEGREE[i] += 1;
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