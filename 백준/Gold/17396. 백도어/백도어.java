import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static long MAX_VALUE = 10000000001L;
    private static int N, M, START, NEXUS;
    private static int[] VISIBLE;

    private static List<List<Node>> ADJ;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        VISIBLE = new int[N];
        for (int i = 0; i < N; i++) {
            int visible = inputProcessor.nextInt();
            VISIBLE[i] = visible; 
        }

        ADJ = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ.get(from).add(new Node(to, cost));
            ADJ.get(to).add(new Node(from, cost));
        }
        
        START = 0;
        NEXUS = N - 1;
    }

    private static class Node implements Comparable<Node> {
        private int to;
        private long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        // 오름차순
        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            } else if (this.cost > o.cost) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static void pro() {
        dijkistra(START, NEXUS);
    }

    private static void dijkistra(int start, int nexus) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));

        long[] dist = new long[N];
        Arrays.fill(dist, MAX_VALUE);
        dist[start] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.to == nexus) break;
            if (dist[cur.to] < cur.cost) continue;

            for (Node next : ADJ.get(cur.to)) {
                if (dist[cur.to] + next.cost >= dist[next.to]) continue;
                if (VISIBLE[next.to] == 0 || isNexus(next.to)) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    que.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        if (dist[nexus] == MAX_VALUE) sb.append("-1");
        else sb.append(dist[nexus]);
    }

    private static boolean isNexus(int idx) {
        return idx == N - 1;
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