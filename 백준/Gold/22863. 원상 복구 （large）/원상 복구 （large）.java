import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N;
    private static long K;
    private static int[] Si, Di;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 카드의 개수
        K = inputProcessor.nextLong(); // 카드 섞은 횟수

        Si = new int[N + 1]; // K번 카드 섞은 후 결과
        for (int i = 1; i <= N; i++) {
            Si[i] = inputProcessor.nextInt();
        }

        Di = new int[N + 1]; // Di번재 카드를 i번재로 이동하기
        for (int i = 1; i <= N; i++) {
            Di[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int[] result = new int[N + 1];
        int[] cycle = new int[N + 1];
        boolean[] finished = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (finished[i]) continue;

            int size = 0;
            int j = i;
            while (true) {
                cycle[size++] = j;
                finished[j] = true;
                j = Di[j];

                if (i == j) break;
            }

            for (int x = 0; x < size; x++) {
                int from = cycle[x];
                int to = cycle[(int) ((x + K) % size)];
                result[to] = Si[from];
            }

        }

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
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