import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        List<Integer> data = new ArrayList<>();
        for(int[] s : score) {
            data.add(s[0] + s[1]);
        }
        
        Collections.sort(data, Comparator.reverseOrder());
        
        int[] answer = new int[score.length];
        for(int i = 0; i < score.length; i++) {
            answer[i] = data.indexOf(score[i][0] + score[i][1]) + 1;
        }
        
        return answer;
    }
    
     private int[] solve(int[][] score) {
        List<Node> data = new ArrayList<>();
        for(int i = 0; i < score.length; i++) {
            double avg = (double)(score[i][0] + score[i][1]) / 2;
            data.add(new Node(i, avg));
        }
        
        Collections.sort(data);
        
        int[] answer = new int[score.length];
        
        int rank = 0;
        double prev = 0;
        for(int i = 0; i < data.size(); i++){
            Node node = data.get(i);
            rank += 1;
            
            if(node.avg == prev) {
                answer[node.idx] = answer[data.get(i - 1).idx];    
                continue;
            }
            
            answer[node.idx] = rank;
            prev = node.avg;
        }
        
        return answer;
    }
    
    private class Node implements Comparable<Node> {
        private int idx;
        private double avg;
        
        public Node(int idx, double avg) {
            this.idx = idx;
            this.avg = avg;
        }
        
        public int compareTo(Node o) {
            if(this.avg < o.avg) return 1;
            else if(this.avg > o.avg) return -1;
            
            return 0;
        }
    }
}