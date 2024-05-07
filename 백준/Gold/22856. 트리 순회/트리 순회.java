import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N;
    private static int[][] NODE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        NODE = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            int parent = inputProcessor.nextInt();
            int left = inputProcessor.nextInt();
            int right = inputProcessor.nextInt();

            NODE[parent][0] = left;
            NODE[parent][1] = right;
        }
    }

    private static void pro() {
        int node = 1;
        int count = 0;

        while (NODE[node][1] != -1) {
            node = NODE[node][1];
            count += 1;
        }

        int edge = N - 1;
        edge -= count;

        int result = edge * 2 + count;
        sb.append(result);
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