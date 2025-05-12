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

    private static int N, M;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();
    }

    private static void pro() {
        int[] selected = new int[M];
        rec(0, selected, 0);
    }

    private static void rec(int idx, int[] selected, int flag) {
        if(idx == M) {
            for(int i = 0; i < M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            if((flag & (1 << i)) != 0) continue;

            selected[idx] = i;
            rec(idx + 1, selected, (flag | (1 << i)));
            selected[idx] = 0;
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