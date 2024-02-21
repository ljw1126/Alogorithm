import java.util.*;
import java.io.*;

public class Main {
    
static StringBuilder sb = new StringBuilder();

    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, Q;
    static boolean[] VISIT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        N = inputProcessor.nextInt(); // 땅의 개수(노드)
        Q = inputProcessor.nextInt(); // 오리 수(쿼리)
        VISIT = new boolean[N + 1];
    }

    private static void pro() {
        for(int i = 1; i <= Q; i++) {
            int duck = inputProcessor.nextInt();

            int result = dfs(duck, 0);

            if(result == 0) {
                VISIT[duck] = true;
            }

            sb.append(result).append("\n");
        }
    }

    // 예외. 자기 자신이 점유되고 있는 경우 1 1 1 => 0 1 1
    private static int dfs(int node, int occ) {
        if(node < 1) {
            return occ;
        }

        if(VISIT[node]) {
            occ = node;
        }

        return dfs(node >> 1, occ);
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
