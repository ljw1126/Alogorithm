import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static List<List<Node>> ADJ;
    private static int[] IN_DEGREE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 부품 번호 수
        M = inputProcessor.nextInt(); // 관계 수

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        IN_DEGREE = new int[N + 1];
        for (int i = 1; i <= M; i++) {
            int x = inputProcessor.nextInt(); // x를 만드는데
            int y = inputProcessor.nextInt(); // 부품 y가
            int k = inputProcessor.nextInt(); // k개 필요하다

            ADJ.get(y).add(new Node(x, k)); // x를 만드는데 y가 k개 필요
            IN_DEGREE[x] += 1;
        }
    }

    private static class Node {
        private int idx;
        private int amount;

        public Node(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }

    private static class Material {
        private Map<Integer, Integer> partsAndCountMap = new HashMap<>();

        public void supply(int basicParts, int amount) {
            partsAndCountMap.put(basicParts, this.get(basicParts) + amount);
        }

        public void supply(Material other, int amount) {
            Set<Integer> keySet = other.keySet();
            for (int key : keySet) {
                partsAndCountMap.put(key, this.get(key) + (other.get(key) * amount));
            }
        }

        public int get(int key) {
            return partsAndCountMap.getOrDefault(key, 0);
        }

        public Set<Integer> keySet() {
            return partsAndCountMap.keySet();
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();

        Set<Integer> basicParts = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            if (IN_DEGREE[i] == 0) {
                que.add(i);
                basicParts.add(i);
            }
        }

        // 각 중간 부품별로 필요한 파트 카운팅
        Material[] materials = new Material[N + 1];
        for (int i = 0; i <= N; i++) {
            materials[i] = new Material();
        }

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (Node next : ADJ.get(cur)) { // next를 만드는데 cur이 amount만큼 필요하다
                IN_DEGREE[next.idx] -= 1;
                if (IN_DEGREE[next.idx] == 0) {
                    que.add(next.idx);
                }

                if (basicParts.contains(cur)) { // 중간 부품
                    materials[next.idx].supply(cur, next.amount);
                } else {
                    materials[next.idx].supply(materials[cur], next.amount);
                }
            }
        }

        Material result = materials[N];
        result.keySet()
                .stream()
                .sorted()
                .forEach(key -> sb.append(key).append(" ").append(result.get(key)).append("\n"));

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