import java.util.*;
import java.io.*;

public class Main {
    
  private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int T, K, M, P;
    private static List<List<Integer>> ADJ;
    private static int[] IN_DEGREE;
    private static Node[] ORDERS;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            T -= 1;

            K = inputProcessor.nextInt(); // 테스트 케이스 번호
            M = inputProcessor.nextInt(); // 노드 수, 항상 바다와 만나는 노드이다
            P = inputProcessor.nextInt(); // 간선의 수

            ORDERS = new Node[M + 1];
            IN_DEGREE = new int[M + 1];
            ADJ = new ArrayList<>();
            for (int i = 0; i <= M; i++) {
                ADJ.add(new ArrayList<>());
            }

            for (int i = 1; i <= P; i++) {
                int from = inputProcessor.nextInt();
                int to = inputProcessor.nextInt();

                ADJ.get(from).add(to); // 부모 -> 자식
                IN_DEGREE[to] += 1; // 들어오는 간선
            }

            pro();
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();

        for (int i = 1; i <= M; i++) {
            if (IN_DEGREE[i] == 0) {
                que.add(i);
                ORDERS[i] = new Node(1, 1);
            }
        }

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : ADJ.get(cur)) {
                IN_DEGREE[next] -= 1;

                if (ORDERS[next] == null || ORDERS[next].isGraterThan(ORDERS[cur])) {
                    ORDERS[next] = ORDERS[cur];
                } else if (ORDERS[next].equals(ORDERS[cur])) {
                    ORDERS[next].countUp();
                }

                if (IN_DEGREE[next] == 0) {
                    if (ORDERS[next].overTwo()) {
                        ORDERS[next] = ORDERS[next].orderUp();
                    }

                    que.add(next);
                }
            }
        }

        sb.append(K).append(" ").append(ORDERS[M].order).append("\n");
    }

    private static class Node {
        private int order;
        private int count;

        public Node(int order, int count) {
            this.order = order;
            this.count = count;
        }

        public boolean isGraterThan(Node order) {
            return this.order < order.order;
        }

        public void countUp() {
            this.count += 1;
        }

        public Node orderUp() {
            return new Node(this.order + 1, 1);
        }

        public boolean overTwo() {
            return this.count >= 2;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Node node = (Node) other;
            return order == node.order && count == node.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(order, count);
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
            br = new BufferedReader(new InputStreamReader(System.in));
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