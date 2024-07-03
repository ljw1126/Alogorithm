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

    private static int N, M;
    private static List<List<Integer>> ADJ;

    private static void input() {
        N = inputProcessor.nextInt(); // 유저 수
        M = inputProcessor.nextInt(); // 친구 관계 수

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ.get(from).add(to);
            ADJ.get(to).add(from);
        }
    }

    private static void pro() {
        int numbers = 0;
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int kevin = bfs(i);

            if (kevin < result) {
                result = kevin;
                numbers = i;
            }
        }

        sb.append(numbers);
    }

    private static int bfs(int start) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int child : ADJ.get(cur)) {
                if (dist[child] != Integer.MAX_VALUE) continue;

                if (dist[child] > dist[cur] + 1) {
                    dist[child] = dist[cur] + 1;
                    que.add(child);
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (i == start) continue;

            sum += dist[i];
        }

        return sum;
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
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}