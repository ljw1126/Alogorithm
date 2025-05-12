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

    private static int L, C;
    private static char[] alphabet;

    private static void input() {
        L = inputProcessor.nextInt(); 
        C = inputProcessor.nextInt();

        alphabet = new char[C];
        for(int i = 0; i < C; i++) {
            alphabet[i] = inputProcessor.next().charAt(0);
        }

        Arrays.sort(alphabet);
    }

    private static void pro() {
        backtracking(0, new char[L], 0, 0, 0);
    }

    private static void backtracking(int start, char[] selected, int count, int n1, int n2) {
        if(count == L) {
            if(n1 >= 1 && n2 >= 2) {
                sb.append(selected).append("\n");
            }
            return;
        }

        for(int i = start; i < C; i++) {
            boolean flag = aeiou(alphabet[i]);

            selected[count] = alphabet[i];
            backtracking(i + 1, selected, count + 1, flag ? n1 + 1 : n1, !flag ? n2 + 1 : n2);
            selected[count] = 0;
        }

    }

    private static boolean aeiou(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
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