import java.util.*;
import java.io.*;

public class Main {
     static StringBuilder sb = new StringBuilder();

    static int N, M;

    static long totalCost;
    
    static List<Info> information = new ArrayList<>();

    static int[] parent;

    static class Info implements Comparable<Info> {
        int from;
        int to;
        int cost;

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info other) {
            return cost - other.cost; // 오름차순 (-1)
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물의 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            information.add(new Info(from, to, cost));
            totalCost += cost;
        }
    }

    static int getParent(int node) {
        if(parent[node] == node) return parent[node];

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    static boolean unionFindParent(int a, int b) {
        return getParent(a) == getParent(b);
    }

    static void pro() {
        Collections.sort(information); // 비용 오름 차순

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i]= i;

        long minTotalCost = 0L;
        int edgeCnt = 0;
        for(Info info : information) {
            if(!unionFindParent(info.from, info.to)) {
                minTotalCost += info.cost;
                edgeCnt += 1;

                union(info.from, info.to);
            }
        }

        if(edgeCnt == N - 1) {
            sb.append(totalCost - minTotalCost);
        } else {
            sb.append("-1");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}