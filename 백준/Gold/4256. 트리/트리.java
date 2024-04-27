import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final String BLANK = " ";
    private static int T, N;
    private static int[] PRE_ORDER, IN_ORDER;


    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            T -= 1;

            N = inputProcessor.nextInt(); // 노드의 개수
            PRE_ORDER = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                PRE_ORDER[i] = inputProcessor.nextInt();
            }

            IN_ORDER = new int[N + 1]; // 루트 인덱스를 쉽게 찾기 위헤
            for (int i = 1; i <= N; i++) {
                int node = inputProcessor.nextInt();
                IN_ORDER[node] = i;
            }

            pro();
            sb.append("\n");
        }
    }


    private static void pro() {
        rec(1, N, 1, N);
    }

    private static void rec(int preS, int preE, int inS, int inE) {
        if (preS > preE || inS > inE) {
            return;
        }

        int root = PRE_ORDER[preS];
        int rootIdx = IN_ORDER[root];

        int len = rootIdx - inS;

        rec(preS + 1, preS + len, inS, rootIdx - 1); // 왼쪽
        rec(preS + len + 1, preE, rootIdx + 1, inE); // 오른쪽
        sb.append(root).append(BLANK); // 루트
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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