import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String BLANK = " ";
    
    static int N;
    static int[] IN_ORDER, POST_ORDER, INORDER_IDX;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        IN_ORDER = new int[N + 1];
        POST_ORDER = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            IN_ORDER[i] = inputProcessor.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            POST_ORDER[i] = inputProcessor.nextInt();
        }

        INORDER_IDX = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            INORDER_IDX[IN_ORDER[i]] = i;
        }
    }

    private static void pro() {
        divide(1, N, 1, N); // 분할 정복
    }

    private static void divide(int inS, int inE, int poS, int poE) {
        if(inE < 1 || inE < inS || poE < 1 || poE < poS) return;

        int rootValue = POST_ORDER[poE];
        int rootIndex = INORDER_IDX[rootValue];

        sb.append(rootValue).append(BLANK);

        int len = rootIndex - inS;

        divide(inS, rootIndex - 1, poS, poS + len - 1);

        divide(rootIndex + 1, inE, poS + len, poE - 1);
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