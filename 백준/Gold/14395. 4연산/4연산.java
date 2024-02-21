import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int MAX_VALUE = 1000000000;
    static int N, C;
    
    public static void main(String[] args) throws IOException {
        input();

        if(N == C) sb.append("0");
        else pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        C = inputProcessor.nextInt();
    }

    private static void pro() {
        bfs(N);
    }

    private static void bfs(long start) {
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(start, ""));

        Set<Long> unique = new HashSet<>();
        unique.add(start);

        String result = "-1";
        while(!que.isEmpty()) {
            Node node = que.poll();
            if(node.s == C) {
                result = node.operator;
                break;
            }

            for(int i = 0; i < 4; i++) {
                if(i == 3 && node.s == 0) continue;

                long value = cal(i, node.s);
                if(1 <= value && value <= MAX_VALUE && !unique.contains(value)) {
                    unique.add(value);
                    que.add(new Node(value, node.operator + operator(i)));
                }
            }
        }

        sb.append(result);
    }

    private static String operator(int i) {
        if(i == 0) return "*";
        else if(i == 1) return "+";
        else if(i == 2) return "-";
        else return "/";
    }
    private static long cal(int operator, long s) {
        long result = 0L;
        if(operator == 0) { // *
            result = s * s;
        } else if(operator == 1) { // +
            result = s + s;
        } else if(operator == 2) { // -
            result = 0L;
        } else if(operator == 3 && s != 0L) { // /
            result = 1L;
        }
        return result;
    }

    private static class Node {
        long s;
        String operator;

        public Node(long s, String operator) {
            this.s = s;
            this.operator = operator;
        }
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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