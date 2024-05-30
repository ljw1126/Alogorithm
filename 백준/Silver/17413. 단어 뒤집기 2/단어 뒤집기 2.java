import java.util.*;
import java.io.*;

public class Main {
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }


    private static void input() {

    }

    private static void pro() {
        String text = inputProcessor.nextLine();

        boolean openBracket = false;
        Deque<Character> wordStack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '<' && !openBracket) {
                while (!wordStack.isEmpty()) {
                    sb.append(wordStack.pop());
                }

                openBracket = true;
                sb.append(c);
                continue;
            }

            if (c == '>' && openBracket) {
                openBracket = false;
                sb.append(c);
                continue;
            }

            if (openBracket) {
                sb.append(c);
                continue;
            }

            if (c == ' ') {
                while (!wordStack.isEmpty()) {
                    sb.append(wordStack.pop());
                }
                sb.append(c);
                continue;
            }

            wordStack.push(c);
        }

        while (!wordStack.isEmpty()) {
            sb.append(wordStack.pop());
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
    
}