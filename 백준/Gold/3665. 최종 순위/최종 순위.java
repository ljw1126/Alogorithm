import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        output();
    }

    private static int n, m;
    private static boolean[][] edge;
    private static int[] indegree;

    private static void input() {
        int T = inputProcessor.nextInt();
        while(T > 0) {

            n = inputProcessor.nextInt(); // 팀의 수
            edge = new boolean[n + 1][n + 1];
            indegree = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                int v = inputProcessor.nextInt(); // 작년 i 순위는 v 이다
                for(int j = 1; j <= n; j++) {
                    if(v == j) continue;
                    if(!edge[j][v]) {
                        edge[v][j] = true;
                        indegree[j] += 1;
                    }
                }
            }

            m = inputProcessor.nextInt();
            for(int i = 1; i <= m; i++) {
                int a = inputProcessor.nextInt();
                int b = inputProcessor.nextInt();
                swap(a, b);
            }

            pro();

            T -= 1;
        }
    }

    private static void swap(int a, int b) {
        if(edge[a][b]) {
            edge[a][b] = false;
            edge[b][a] = true;
            indegree[b] -= 1;
            indegree[a] += 1;
        } else {
            edge[a][b] = true;
            edge[b][a] = false;
            indegree[a] -= 1;
            indegree[b] += 1;
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                que.add(i);
            }
        }

        int[] answer = new int[n + 1];
        int idx = 1;
        for(int i = 1; i <= n; i++) {
            if(que.isEmpty()) { // 데이터에 일관성이 없어 정할 수 없는 경우
                sb.append("IMPOSSIBLE").append("\n");
                return;
            }

            if(que.size() >= 2) { // 두 명이 들어 있으면 확실한 순위를 정할 수 없다
                sb.append("?").append("\n");
                return;
            }

            int cur = que.poll();

            answer[idx++] = cur;

            for(int j = 1; j <= n; j++) {
                if(cur == j) continue;
                if(!edge[cur][j]) continue;

                indegree[j] -= 1;
                if(indegree[j] == 0) {
                    que.add(j);
                }
            }
        }

        // 순위를 정할 수 있다면
        for(int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }
        sb.append("\n");
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