import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int START = 1;
    private static final int MAX_DIST = 50000001;

    private static int V, E, P;
    private static List<List<Node>> ADJ;


    private static class Node implements Comparable<Node> {
        private int idx;
        private int cost;

        private Node prev;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
            this.prev = null;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        V = inputProcessor.nextInt(); // 정점의 개수
        E = inputProcessor.nextInt(); // 간선의 개수
        P = inputProcessor.nextInt(); // 건우가 위치한 정점

        ADJ = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= E; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ.get(from).add(new Node(to, cost));
            ADJ.get(to).add(new Node(from, cost));
        }
    }

    private static void pro() {
        dijkstra(START, V);
    }

    private static void dijkstra(int start, int end) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));

        int[] dist = new int[V + 1];
        Arrays.fill(dist, MAX_DIST);
        dist[start] = 0;

        boolean result = false;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (dist[cur.idx] < cur.cost) continue;
            if (cur.idx == end && dist[cur.idx] <= dist[end]) {
                if (savedGunWoo(cur)) {
                    result = true;
                    break;
                }
                continue;
            }

            for (Node next : ADJ.get(cur.idx)) {
                if (dist[cur.idx] + next.cost > dist[next.idx]) continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                Node node = new Node(next.idx, dist[next.idx]);
                node.setPrev(cur);
                que.add(node);
            }
        }

        if (result) sb.append("SAVE HIM");
        else sb.append("GOOD BYE");
    }

    private static boolean savedGunWoo(Node node) {
        Node point = node;
        while (point != null) {
            if (point.idx == P) {
                return true;
            }

            point = point.prev;
        }

        return false;
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