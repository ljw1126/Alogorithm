import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int n, m;
    private static Deque<Node> que;
    private static List<Integer> priority;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t =  Integer.parseInt(br.readLine());
        while(t > 0) {
            t -= 1;
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 문서의 개수
            m = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는

            priority = new ArrayList<>();
            que = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int w = Integer.parseInt(st.nextToken());

                Node node = new Node(i, w);
                que.add(node);
                priority.add(w);
            }

            pro();
        }

    }

    private static class Node {
        private int idx;
        private int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        public boolean isGreaterThanOrEqual(int o) {
            return weight >= o;
        }
    }

    private static void pro() {
        Collections.sort(priority, Collections.reverseOrder());

        int idx = 0;
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(cur.isGreaterThanOrEqual(priority.get(idx))) {
                idx += 1;

                if(cur.idx == m) break;

                continue;
            }

            que.add(cur);
        }

        sb.append(idx).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}