import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int K, N;

    static long[] LINE;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        K = inputProcessor.nextInt(); // 보유한 랜선의 개수
        N = inputProcessor.nextInt(); // 필요한 랜선의 개수

        LINE = new long[K + 1];
        for(int i = 1; i <= K; i++) {
            LINE[i] = inputProcessor.nextLong();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        long L = 0;
        long R = Integer.MAX_VALUE;

        long result = 0;
        while(L <= R) {
            long mid = (L + R) / 2;

            if(validHeight(mid)) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        sb.append(result);
    }

    private static boolean validHeight(long height) {
        long sum = 0L;

        for(int i = 1; i <= K; i++) {
            if(LINE[i] >= height) {
                sum += (int)(LINE[i] / height);
            }
        }

        return sum >= N;
    }

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static class InputProcessor {
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