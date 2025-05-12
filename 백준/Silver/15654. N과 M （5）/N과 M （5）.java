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
    private static int[] data;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();
        data = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            data[i] = inputProcessor.nextInt();
        }

        Arrays.sort(data);
    }

    private static void pro() {
        int[] selected = new int[M];
        rec(selected, 0, 0);
    }

    private static void rec(int[] selected, int count, int flag) {
        if(count == M) {
            for(int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            if((flag & (1 << i)) != 0) continue;

            selected[count] = data[i];
            rec(selected, count + 1, (flag | 1 << i));
            selected[count] = 0;
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