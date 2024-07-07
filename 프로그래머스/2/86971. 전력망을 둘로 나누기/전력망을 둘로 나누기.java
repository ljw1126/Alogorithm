import java.util.*;

class Solution {
    
    public int solution(int n, int[][] wires) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] wire : wires) {
            adj.get(wire[0]).add(wire[1]);
            adj.get(wire[1]).add(wire[0]);
        }
        
        int result = n;
        for(int[] wire : wires) {
            int parent = wire[0];
            int child = wire[1];
            
            adj.get(parent).remove(Integer.valueOf(child));
            adj.get(child).remove(Integer.valueOf(parent));
            
            result = Math.min(result, diff(adj, n));
            
            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }
        
        return result;
    }
    
    private int diff(List<List<Integer>> adj, int n) {
        int group = dfs(1, -1, adj);
        
        return Math.abs(group - (n - group));
    }
    
    private int dfs(int node, int parent, List<List<Integer>> adj) {
        List<Integer> childs = adj.get(node);
        if(childs.size() == 1 && childs.get(0) == parent) {
            return 1;
        }
        
        int subtree = 1;
        for(int child : childs) {
            if(child == parent) continue;
            
            subtree += dfs(child, node, adj);
        }
        
        return subtree;
    }
}