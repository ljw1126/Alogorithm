import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M;
    static List<List<Node>> ADJ;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost; // 오름차순 정렬
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt(); // 간선의 수

        ADJ = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ.get(from).add(new Node(to, cost));
            ADJ.get(to).add(new Node(from, cost));
        }
    }

    private static void pro() {
        dijkstra(1);
    }

    private static void dijkstra(int start) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);
        parent[start] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node next : ADJ.get(cur.idx)) {
                if(dist[next.idx] <= dist[cur.idx] + next.cost) continue;

                parent[next.idx] = cur.idx;
                dist[next.idx] = dist[cur.idx] + next.cost;
                que.add(new Node(next.idx, dist[next.idx]));
            }
        }

        sb.append(N - 1).append("\n");
        for(int i = 2; i <= N; i++) {
            sb.append(i).append(" ").append(parent[i]).append("\n");
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
                st = new StringTokenizer(nextLine());
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