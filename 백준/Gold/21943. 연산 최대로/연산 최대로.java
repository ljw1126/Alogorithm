import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, P, Q, RESULT;
    private static int[] DATA;
    private static int[] SELECTED;
    private static int[] MULTIPLE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }

        P = inputProcessor.nextInt(); // 더하기
        Q = inputProcessor.nextInt(); // 곱하기

        MULTIPLE = new int[Q + 1];
        SELECTED = new int[N + 1];
    }

    private static void pro() {
        divideByMultiple(1, 1);
        sb.append(RESULT);
    }

    private static void divideByMultiple(int start, int cnt) {
        if (cnt == Q + 1) {
            shuffleNumber(1, 0, 0);
            return;
        }

        for (int i = start; i <= N - 1; i++) { // 1 2 나 2 1 이나 똑같다
            MULTIPLE[cnt] = i;
            divideByMultiple(i + 1, cnt + 1);
            MULTIPLE[cnt] = 0;
        }
    }

    private static void shuffleNumber(int idx, int cnt, int flag) {
        if (cnt == N) {
            calculate();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }

            SELECTED[idx] = DATA[i];
            shuffleNumber(idx + 1, cnt + 1, (flag | (1 << i)));
            SELECTED[idx] = 0;
        }
    }

    private static void calculate() {
        int total = 1;
        int idx = 1;
        for (int i = 1; i < Q + 1; i++) {
            int multiple = MULTIPLE[i];

            int sum = 0;
            for (int j = idx; j <= multiple; j++) {
                sum += SELECTED[j];
            }

            total *= sum;
            idx = multiple + 1;
        }

        if (idx <= N) {
            int sum = 0;
            for (int i = idx; i <= N; i++) {
                sum += SELECTED[i];
            }

            total *= sum;
        }

        RESULT = Math.max(RESULT, total);
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