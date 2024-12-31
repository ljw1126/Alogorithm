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

    private static int n;
    private static List<Edge> edges;

    private static void input() {
        n = inputProcessor.nextInt(); // 행성의 개수

        List<Plant> plants = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int z = inputProcessor.nextInt();

            plants.add(new Plant(i, x, y, z));
        }

        edges = new ArrayList<>();
        Collections.sort(plants, Comparator.comparingInt(a -> a.x));
        for(int i = 0; i < plants.size() - 1; i++) {
            Plant from = plants.get(i);
            Plant to = plants.get(i + 1);
            edges.add(new Edge(from.no, to.no, Math.abs(from.x - to.x)));
        }

        Collections.sort(plants, Comparator.comparingInt(a -> a.y));
        for(int i = 0; i < plants.size() - 1; i++) {
            Plant from = plants.get(i);
            Plant to = plants.get(i + 1);
            edges.add(new Edge(from.no, to.no, Math.abs(from.y - to.y)));
        }

        Collections.sort(plants, Comparator.comparingInt(a -> a.z));
        for(int i = 0; i < plants.size() - 1; i++) {
            Plant from = plants.get(i);
            Plant to = plants.get(i + 1);
            edges.add(new Edge(from.no, to.no, Math.abs(from.z - to.z)));
        }
    }

    private static class Plant {
        private final int no;
        private final int x;
        private final int y;
        private final int z;

        public Plant(int no, int x, int y, int z) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Edge implements Comparable<Edge>{
        private final int from;
        private final int to;
        private final int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void pro() {
        Collections.sort(edges);

        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        long result = 0L;
        for(Edge e : edges) {
            if(compare(e.from, e.to, parent)) {
                union(e.from, e.to, parent);
                result += e.cost;
            }
        }

        sb.append(result);
    }

    private static boolean compare(int a, int b, int[] parent) {
        return findParent(a, parent) != findParent(b, parent);
    }

    private static void union(int a, int b, int[] parent) {
        int _a = findParent(a, parent);
        int _b = findParent(b, parent);

        if(_a < _b) {
            parent[_b] = _a;
        } else {
            parent[_a] = _b;
        }
    }

    private static int findParent(int n, int[] parent) {
        if(n == parent[n]) return n;

        return parent[n] = findParent(parent[n], parent);
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