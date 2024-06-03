import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int N, M;
    private static int[] SELECTED;

    private static void input() {
        N = inputProcessor.nextInt(); // 1 ~ N
        M = inputProcessor.nextInt(); // 개수

        SELECTED = new int[M];
    }

    private static void pro() {
        rec(0, 0);

    }

    private static void rec(int cnt, int flag) {
        if (cnt == M) {
            appendResult();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((flag & (1 << i)) != 0) continue;

            SELECTED[cnt] = i;
            rec(cnt + 1, flag | 1 << i);
            SELECTED[cnt] = 0;
        }
    }

    private static void appendResult() {
        for (int i = 0; i < M; i++) {
            sb.append(SELECTED[i]).append(" ");
        }
        sb.append("\n");
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