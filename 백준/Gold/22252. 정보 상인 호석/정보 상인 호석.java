import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int Q;
    private static long RESULT;
    private static Map<String, Queue<Integer>> GORIL = new HashMap<>();

    private static void input() {
        Q = inputProcessor.nextInt(); // 쿼리의 개수

        for (int i = 1; i <= Q; i++) {
            pro();
        }

        sb.append(RESULT);
    }

    private static void pro() {
        int query = inputProcessor.nextInt();
        String name = inputProcessor.next(); // 고릴라 이름
        int kb = inputProcessor.nextInt(); // k개 정보를 가짐

        if (!GORIL.containsKey(name)) {
            GORIL.put(name, new PriorityQueue<>(Comparator.reverseOrder()));
        }

        Queue<Integer> pq = GORIL.get(name);
        if (query == 1) { // 정보를 얻은 고릴라
            for (int i = 1; i <= kb; i++) {
                int c = inputProcessor.nextInt();
                pq.add(c);
            }
        } else { // 정보 구매
            for (int i = 1; i <= kb; i++) {
                if (pq.isEmpty()) break;

                RESULT += pq.poll();
            }
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private StringTokenizer st;
        private BufferedReader br;

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