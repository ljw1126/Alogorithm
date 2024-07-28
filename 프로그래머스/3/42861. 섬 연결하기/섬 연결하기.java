import java.util.*;

class Solution {
    private static int[] PARENT;
    
    public int solution(int n, int[][] costs) {
        List<Edge> adj = new ArrayList<>();
        
        for(int[] c : costs) {
            int from = c[0];
            int to = c[1];
            int dist = c[2];
            
            adj.add(new Edge(from, to, dist));
        }
        
        Collections.sort(adj);
        
        PARENT = new int[n];
        for(int i = 0; i < n; i++) {
            PARENT[i] = i;
        }
        
        int answer = 0; // 최소 가중치
        for(Edge edge : adj) {
            if(!sameParent(edge.from, edge.to)) {
                union(edge.from, edge.to);
                answer += edge.cost;
            }
        }
        
        return answer;
    }
    
    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int cost;
        
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    private boolean sameParent(int a, int b) {
        return findParent(a) == findParent(b);
    }
    
    private void union(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);
        
        if(parentA > parentB) {
            PARENT[parentA] = parentB;
        } else {
            PARENT[parentB] = parentA;
        }
    }
    
    private int findParent(int node) {
        if(PARENT[node] == node) return node;
        
        return findParent(PARENT[node]);
    }
} 