import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static List<Synapse> synapses;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        synapses = new ArrayList<>();
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            synapses.add(new Synapse(u, v));
        }
    }

    private static class Synapse {
        private int u;
        private int v;

        public Synapse(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }


    private static void pro() {
        int[] parents = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        int result = 0;
        for(Synapse s : synapses) {
            if(isSameParent(parents, s.u, s.v)) { // 사이클은 끊어야 한다
                result += 1;
            } else {
                union(parents, s.u, s.v);
            }
        }

        // 연결할 시냅스
        Set<Integer> unique = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            unique.add(findParent(parents, i));
        }
        
        result += unique.size() - 1;
        sb.append(result);
    }

    private static void union(int[] parents, int u, int v) {
        int pu = findParent(parents, u);
        int pv = findParent(parents, v);

        if(pu < pv) {
            parents[pv] = pu;
        } else {
            parents[pu] = pv;
        }
    }

    private static boolean isSameParent(int[] parents, int u, int v) {
        return findParent(parents, u) == findParent(parents, v);
    }

    private static int findParent(int[] parents, int node) {
        if(parents[node] == node) return node;

        return parents[node] = findParent(parents, parents[node]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}