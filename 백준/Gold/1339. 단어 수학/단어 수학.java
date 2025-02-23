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
    private static String[] texts;

    private static void input() {
        n = inputProcessor.nextInt();

        texts = new String[n];
        for(int i = 0; i < n; i++) {
            texts[i] = inputProcessor.nextLine();
        }
    }

    private static void pro() {
       int[] alphabet = new int[27];
       for(String text: texts) {
           int len = text.length();
           for(char c : text.toCharArray()) {
               alphabet[c - 'A'] += (int) Math.pow(10, --len);
           }
       }

       Arrays.sort(alphabet); // 오름차순
       int weight = 9;
       int result = 0;
       for(int i = 26; i >= 0; i--) {
           if(alphabet[i] == 0) break;

           result += (weight * alphabet[i]);
           weight -= 1;
       }

       sb.append(result);
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