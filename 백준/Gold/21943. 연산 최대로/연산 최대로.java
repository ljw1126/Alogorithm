import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, P, Q, RESULT;
    private static int[] Xi;
    private static int[] DATA, MULTIPLE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        Xi = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Xi[i] = inputProcessor.nextInt();
        }

        P = inputProcessor.nextInt(); // 더하기
        Q = inputProcessor.nextInt(); // 곱하기

        DATA = new int[N + 1];
        MULTIPLE = new int[Q + 1]; // 곱셈을 기준으로 그룹을 나눔
    }

    private static void pro() {
        divideMultiGroup(1, 1);
        sb.append(RESULT);
    }

    private static void divideMultiGroup(int start, int cnt) {
        if (cnt == Q + 1) {
            shuffleNumber(1, 0, 0);
            return;
        }

        for (int i = start; i <= N - 1; i++) {
            MULTIPLE[cnt] = i;
            divideMultiGroup(i + 1, cnt + 1);
            MULTIPLE[cnt] = 0;
        }
    }

    private static void shuffleNumber(int idx, int cnt, int flag) {
        if (cnt == N) {
            calculate();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((flag & (1 << i)) != 0) continue;

            DATA[idx] = Xi[i];
            shuffleNumber(idx + 1, cnt + 1, flag | 1 << i);
            DATA[idx] = 0;
        }
    }

    private static void calculate() {
        int total = 1;
        int idx = 1;
        for (int i = 1; i < Q + 1; i++) {
            int sum = 0;
            for (int j = idx; j <= MULTIPLE[i]; j++) {
                sum += DATA[j];
            }

            total *= sum;
            idx = MULTIPLE[i] + 1;
        }

        if (idx <= N) {
            int sum = 0;
            for (int i = idx; i <= N; i++) {
                sum += DATA[i];
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