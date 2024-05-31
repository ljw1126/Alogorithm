import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int N;
    private static List<List<Integer>> ADJ;
    private static int[] DP;

    private static void input() {
        N = inputProcessor.nextInt();

        ADJ = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int parent = inputProcessor.nextInt();

            if (parent == -1) continue;

            ADJ.get(parent).add(i);
        }

        DP = new int[N];
    }

    private static void pro() {
        sb.append(topDown(0));
    }

    private static class Node implements Comparable<Node> {
        private int no;
        private int time;

        public Node(int no, int time) {
            this.no = no;
            this.time = time;
        }

        // 오름차순
        @Override
        public int compareTo(Node o) {
            return o.time - this.time;
        }
    }

    private static int topDown(int node) {
        List<Integer> childs = ADJ.get(node);
        if (childs.isEmpty()) return 0;

        Queue<Node> pq = new PriorityQueue<>();
        for (int child : childs) {
            topDown(child);
            pq.add(new Node(child, DP[child]));
        }

        int time = 1;
        int max = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            max = Math.max(max, cur.time + time);
            time += 1;
        }

        DP[node] = max;
        return DP[node];
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