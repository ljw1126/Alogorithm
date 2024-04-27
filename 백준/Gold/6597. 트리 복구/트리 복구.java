import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();

    static int[] PRE_ORDER, IN_ORDER;

    public static void main(String[] args) throws IOException {
        while(true) {
            String input = inputProcessor.nextLine();
            if(input == null || "".equals(input)) break;

            input(input.trim());
            pro();
            sb.append(NEW_LINE);
        }

        output();
    }

    private static void input(String data) {
        String[] orders = data.split(" ");
        String preorder = orders[0];
        String inorder = orders[1];

        PRE_ORDER = new int[preorder.length()];
        IN_ORDER = new int[inorder.length()];

        for(int i = 0; i < preorder.length(); i++) {
            PRE_ORDER[i] = preorder.charAt(i) - 'A';
        }

        for(int i = 0; i < inorder.length(); i++) {
            IN_ORDER[i] = inorder.charAt(i) - 'A';
        }

    }

    private static void divide(int preS, int preE, int inS, int inE) {
        if(preE < 0 || inE < 0 || preE < preS || inE < inS) return;

        int root = PRE_ORDER[preS];
        int rootIdx = find(root, inS, inE);

        int len = rootIdx - inS;

        divide(preS + 1, preS + len, inS, rootIdx - 1);

        divide(preS + len  + 1, preE, rootIdx + 1, inE);

        sb.append((char)(root + 'A'));
    }

    private static int find(int root, int inS, int inE) {
        for(int i = inS; i <= inE; i++) {
            if(IN_ORDER[i] == root) {
                return i;
            }
        }

        return -1;
    }

    private static void pro() {
        int len = PRE_ORDER.length - 1;
        divide(0, len, 0, len);
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