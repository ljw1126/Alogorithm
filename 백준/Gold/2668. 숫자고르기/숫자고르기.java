import java.util.*;
import java.io.*;

public class Main {
    
   static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N;
    static int[] NUMBERS;
    static boolean[] VISIT, FINISH;
    static List<Integer> RESULT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        NUMBERS = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            NUMBERS[i] = inputProcessor.nextInt();
        }

        VISIT = new boolean[N + 1];
        FINISH = new boolean[N + 1];
    }

    public static void pro() {
        for(int i = 1; i <= N; i++) {
            if(!FINISH[i]) {
                dfs(i);
            }
        }

        Collections.sort(RESULT);
        sb.append(RESULT.size()).append("\n");
        for(int v : RESULT) {
            sb.append(v).append("\n");
        }
    }

    private static void dfs(int node) {
        VISIT[node] = true;

        int next = NUMBERS[node];
        if(!VISIT[next]) {
            dfs(next);
        } else if(!FINISH[next]) {
            while(node != next) {
                RESULT.add(next);
                next = NUMBERS[next];
            }
            RESULT.add(node);
        }

        FINISH[node] = true;
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