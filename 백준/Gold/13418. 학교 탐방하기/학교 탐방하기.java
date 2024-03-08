import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, M;
    static List<Node>[] ADJ;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static class Node {
        int idx;
        int c; // 오르막길 : 0, 내리막길 : 1

        public Node(int idx, int c) {
            this.idx = idx;
            this.c = c;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 건물의 개수(노드)
        M = inputProcessor.nextInt(); // 도로의 개수(간선)

        ADJ = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++)  {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M + 1; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();
            int c = inputProcessor.nextInt();

            ADJ[a].add(new Node(b, c));
            ADJ[b].add(new Node(a, c));
        }
    }

    private static void pro() {
        // best
        int best = mst(0, (o1, o2) -> o2.c - o1.c);

        // worst (1000^2)
        int worst = mst(0, Comparator.comparingInt(o -> o.c));

        double result = pow(worst) - pow(best);
        sb.append((int) result);
    }

    private static double pow(int value) {
        return Math.pow(value, 2);
    }

    private static int mst(int start, Comparator<Node> comparator) {
        Queue<Node> que = new PriorityQueue<>(comparator);
        que.add(new Node(start, -1));

        boolean[] visit = new boolean[N + 1];

        int count = 0; //오른막길 0, 내리막길 1
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            if(cur.c == 0) {
                count += 1;
            }

            for(Node next : ADJ[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        return count;
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
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