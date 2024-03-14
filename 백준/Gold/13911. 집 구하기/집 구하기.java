import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int V, E;
    static int INF = 100000001;
    static int M, X;
    static int S, Y;
    static List<List<Node>> ADJ = new ArrayList<>();
    static List<Integer> MC_DONALD = new ArrayList<>();
    static List<Integer> STAR_BUCKS = new ArrayList<>();
    
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
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순
        }
    }

    private static void input() {
        V = inputProcessor.nextInt(); // 정점의 개수
        E = inputProcessor.nextInt(); // 도로의 개수

        for(int i = 0 ; i <= V; i++) {
            ADJ.add(new ArrayList<>());
        }

        for(int i = 1; i <= E; i++) {
            int u = inputProcessor.nextInt();
            int v = inputProcessor.nextInt();
            int w = inputProcessor.nextInt(); // 가중치

            ADJ.get(u).add(new Node(v, w));
            ADJ.get(v).add(new Node(u, w));
        }

        M = inputProcessor.nextInt(); // 맥도날의 수
        X = inputProcessor.nextInt(); // 맥세권일 조건

        for(int i = 1; i <= M; i++) {
            MC_DONALD.add(inputProcessor.nextInt());
        }

        S = inputProcessor.nextInt(); // 스타벅스의 수
        Y = inputProcessor.nextInt(); // 스세권 조건

        for(int i = 1; i <= S; i++) {
            STAR_BUCKS.add(inputProcessor.nextInt());
        }
    }


    private static void pro() {
        int[] mcDonaldDist = new int[V + 1];
        int[] starBucksDist = new int[V + 1];

        dijkstra(MC_DONALD, mcDonaldDist);
        dijkstra(STAR_BUCKS, starBucksDist);

        int minDist = INF;
        for(int i = 1; i <= V; i++) {
            if(mcDonaldDist[i] == 0 || starBucksDist[i] == 0) continue;
            if(mcDonaldDist[i] > X || starBucksDist[i] > Y) continue;

            int sum = mcDonaldDist[i] + starBucksDist[i];
            if(sum < minDist) {
                minDist = sum;
            }
        }

        sb.append(minDist == INF ? -1 : minDist);
    }

    private static void dijkstra(List<Integer> start, int[] dist) {
        Arrays.fill(dist, INF);

        Queue<Node> que = new PriorityQueue<>();
        for(int i : start) {
            que.add(new Node(i, 0));
            dist[i] = 0;
        }

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node next : ADJ.get(cur.idx)) {
                if(dist[next.idx] <= dist[cur.idx] + next.cost) continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                que.add(new Node(next.idx, dist[next.idx]));
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