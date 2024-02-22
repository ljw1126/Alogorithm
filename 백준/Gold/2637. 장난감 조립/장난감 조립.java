import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String RESULT_FORMAT = "%d %d\n";
    static int N, M;
    static List<Node>[] ADJ;
    static int[] IN_DEGREE;
    static Material[] MATERIAL_ARR;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs();

        Material material = MATERIAL_ARR[N];
        Set<Integer> keys = material.keySet();
        
        for(int key : keys) {
            sb.append(String.format(RESULT_FORMAT, key, material.supply.get(key)));
        }
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();

        Set<Integer> basic = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                basic.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(Node next : ADJ[cur]) {
                IN_DEGREE[next.to] -= 1;

                if(basic.contains(cur)) {
                    MATERIAL_ARR[next.to].supply(cur, next.amount);
                } else {
                    MATERIAL_ARR[next.to].supply(MATERIAL_ARR[cur], next.amount);
                }

                if(IN_DEGREE[next.to] == 0) {
                    que.add(next.to);
                }
            }
        }
    }

    private static class Material {
        Map<Integer, Integer> supply;

        public Material() {
            supply = new TreeMap<>();
        }

        public void supply(int no, int amount) {
            supply.put(no, supply.getOrDefault(no, 0) + amount);
        }

        public Set<Integer> keySet() {
            return supply.keySet();
        }

        public int get(int key) {
            return supply.get(key);
        }
        public void supply(Material material, int amount) {
            Set<Integer> keys = material.keySet();
            for(int key : keys) {
                supply.put(key, supply.getOrDefault(key, 0) + material.get(key) * amount);
            }
        }
    }

    private static class Node {
        int to;
        int amount;
        public Node(int to, int amount) {
            this.to = to;
            this.amount = amount;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();


        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[N + 1];
        for(int i = 1; i <= M; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int k = inputProcessor.nextInt();

            ADJ[y].add(new Node(x, k)); 
            IN_DEGREE[x] += 1;
        }

        MATERIAL_ARR = new Material[N + 1];
        for(int i = 1; i <= N; i++) {
            MATERIAL_ARR[i] = new Material();
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