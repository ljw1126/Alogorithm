import java.util.*;
import java.io.*;

public class Main {
    
    static InputProcessor inputProcessor = new InputProcessor();

    static long INF = Long.MAX_VALUE;
    static int N, M;
    static List<Node>[] ADJ;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    private static class Node implements Comparable<Node> {
        int idx;
        long cost;

        int state; 

        public Node(int idx, long cost) {
            this(idx, cost, 0);
        }
        public Node(int idx, long cost, int state) {
            this.idx = idx;
            this.cost = cost;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
           if(this.cost < o.cost) {
               return -1;
           } else if(this.cost > o.cost) {
               return 1;
           } else {
               return 0;
           }
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ADJ = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();
            int d = inputProcessor.nextInt() * 2; 

            ADJ[a].add(new Node(b, d));
            ADJ[b].add(new Node(a, d));
        }
    }

    private static void pro() throws IOException {
        long[] distFox = new long[N + 1];
        long[][] distWolf = new long[N + 1][2];

        int start = 1;
        dijkstraFox(start, distFox);
        dijkstraWolf(start, distWolf);

        int result = 0;
        for(int i = 2; i <= N; i++) {
            if(distFox[i] == INF) continue;
            
            if(distFox[i] < Math.min(distWolf[i][0], distWolf[i][1])) {
                result += 1;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static void dijkstraFox(int start, long[] dist) {
        Arrays.fill(dist, INF);

        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));

        dist[start] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node next : ADJ[cur.idx]) {
                if(dist[next.idx] <= dist[cur.idx] + next.cost) continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                que.add(new Node(next.idx, dist[next.idx]));
            }
        }
    }

    private static void dijkstraWolf(int start, long[][] dist) {
        for(int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0, 0));

        dist[start][0] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            int idx = cur.idx;
            int state = cur.state;
            long cost = cur.cost;

            if(dist[idx][state] < cost) continue;

            int nextState = 1 - state;
            for(Node next : ADJ[idx]) {
                long nextCost = cost + (state == 0 ? (next.cost / 2) : (next.cost * 2));
                if(dist[next.idx][nextState] <= nextCost) continue;

                dist[next.idx][nextState] = nextCost;
                que.add(new Node(next.idx, nextCost, nextState));
            }
        }
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