import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M, K, X;
    private static List<List<Integer>> ADJ;

    private static void input() {
        N = inputProcessor.nextInt(); // 도시의 개수 (노드)
        M = inputProcessor.nextInt(); // 도로의 개수 (간선)
        K = inputProcessor.nextInt(); // 거리 정보
        X = inputProcessor.nextInt(); // 출발 도시

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            ADJ.get(from).add(to);
        }
    }

    private static void pro() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(X);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        boolean[] visited = new boolean[N + 1];
        visited[X] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : ADJ.get(cur)) {
                if (visited[next]) continue;

                visited[next] = true;
                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            sb.append(-1);
            return;
        }

        Collections.sort(result);
        for (int r : result) {
            sb.append(r).append("\n");
        }
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
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