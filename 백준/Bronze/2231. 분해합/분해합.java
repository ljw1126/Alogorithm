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

    private static int n;

    private static void input() {
        n = inputProcessor.nextInt();
    }

    private static void pro() {
        int result = Integer.MAX_VALUE;
        int num = n - (String.valueOf(n).length() * 9);
        while(num < n) {
            int v = calculate(num);
            if(v == n) {
                result = num;
                break;
            }

            num += 1;
        }

        sb.append(result == Integer.MAX_VALUE ? 0 : result);
    }

    private static int calculate(int v) {
        int sum = v;
        int base = v;
        while(base > 0) {
            sum += (base % 10);
            base /= 10;
        }

        return sum;
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