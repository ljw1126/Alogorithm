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

    private static int n, s;
    private static int[] data;

    private static void input() {
        n = inputProcessor.nextInt();
        s = inputProcessor.nextInt();

        data = new int[n];
        for(int i = 0; i < n; i++) {
            data[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int result = backTracking(0, 0);
        if(s == 0) result -= 1;
        sb.append(result);
    }

    private static int backTracking(int idx, int sum) {
        if(idx == n) {
           return (sum == s) ? 1 : 0;
        }

        int result = 0;
        // 현재 수를 선택한다
        result += backTracking(idx + 1, sum + data[idx]);

        // 현재 수를 선택하지 않는다
        result += backTracking(idx + 1, sum);

        return result;
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