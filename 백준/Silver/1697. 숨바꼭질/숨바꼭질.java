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

    private static int N, K;

    private static void input() {
        N = inputProcessor.nextInt();
        K = inputProcessor.nextInt();
    }

    private static void pro() {
        bfs();
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(N);

        int[] dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (cur == K) break;

            int next = cur - 1;
            if (0 <= next && next <= 100000 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                que.add(next);
            }

            next = cur + 1;
            if (0 <= next && next <= 100000 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                que.add(next);
            }

            next = cur * 2;
            if (0 <= next && next <= 100000 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                que.add(next);
            }
        }

        sb.append(dist[K]);
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