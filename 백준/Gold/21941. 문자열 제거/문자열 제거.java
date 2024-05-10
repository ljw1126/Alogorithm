import java.util.*;
import java.io.*;

public class Main {
    
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static String S;
    private static int M;
    private static Map<String, Integer> KEYWORD_SCORE = new HashMap<>();
    private static int[] DP;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        S = inputProcessor.nextLine();
        M = inputProcessor.nextInt();

        for (int i = 1; i <= M; i++) {
            String keyword = inputProcessor.next();
            int score = inputProcessor.nextInt();

            KEYWORD_SCORE.put(keyword, score);
        }
    }

    private static void pro() {
        DP = new int[S.length() + 1];
        DP[0] = 0;
        String first = S.substring(0, 1);
        if (KEYWORD_SCORE.containsKey(first)) {
            DP[1] = KEYWORD_SCORE.get(first);
        } else {
            DP[1] = 1;
        }

        for (int i = 2; i <= S.length(); i++) {
            DP[i] = Math.max(DP[i], DP[i - 1] + 1);
            for (int j = 0; j < i; j++) {
                String word = S.substring(j, i);
                if (KEYWORD_SCORE.containsKey(word)) {
                    DP[i] = Math.max(DP[i], DP[j] + KEYWORD_SCORE.get(word));
                }
            }
        }

        sb.append(DP[S.length()]);
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