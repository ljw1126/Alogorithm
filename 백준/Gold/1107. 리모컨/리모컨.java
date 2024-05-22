import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_CHANNEL = 999_999;
    
    private static int N, M, RESULT;
    private static boolean[] BROKEN;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = Integer.parseInt(inputProcessor.nextLine().trim()); // 이동하려고 하는 채널
        M = inputProcessor.nextInt(); // 고장난 버튼 수

        BROKEN = new boolean[10]; // 0 ~ 9;
        for (int i = 1; i <= M; i++) {
            int no = inputProcessor.nextInt();
            BROKEN[no] = true;
        }

        RESULT = Integer.MAX_VALUE;
    }

    private static void pro() {
        RESULT = Math.abs(N - 100);
        for (int i = 0; i <= MAX_CHANNEL; i++) {
            String channel = String.valueOf(i);

            boolean isPossible = true;
            for (int j = 0; j < channel.length(); j++) {
                if (BROKEN[channel.charAt(j) - '0']) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                RESULT = Math.min(RESULT, Math.abs(N - i) + channel.length());
            }
        }

        sb.append(RESULT);
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