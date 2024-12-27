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

    private static int N, C;
    private static int[] DATA;

    private static void input() {
        N = inputProcessor.nextInt(); // 집의 개수
        C = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        Arrays.sort(DATA, 1, N + 1);

        int result = 0;
        int L = 1;
        int R = DATA[N] + 1;

        while(L <= R) {
            int d = (L + R) / 2;

            if(isPossible(d, C)) {
                result = d;
                L = d + 1;
            } else {
                R = d - 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int d, int c) {
        int count = 1;
        int prev = DATA[1];
        for(int i = 2; i <= N; i++) {
            int cur = DATA[i];
            int diff = cur - prev;

            if(diff < d) continue;

            count += 1;
            prev = cur;
        }
        
        return count >= c;
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