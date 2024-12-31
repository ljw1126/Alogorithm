import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        
        int[] stoped = new int[N + 2];
        for(int s : stages) {
            stoped[s] += 1;
        }
        
        List<Stage> list = new ArrayList<>();
        int players = stages.length;
        for(int i = 1; i <= N; i++) {
            int stop = stoped[i];
            double failRate = (double) stop / players;
            
            list.add(new Stage(i, failRate));
            players -= stop;
            
        }
        
        Collections.sort(list);
        
        int[] answer = new int[N];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).no;
        }
        
        return answer;
    }
    
    private static class Stage implements Comparable<Stage>{
        private final int no;
        private final double failRate;
        
        public Stage(int no, double failRate) {
            this.no = no;
            this.failRate = failRate;
        }

        public int compareTo(Stage o) {
            if(this.failRate < o.failRate) {
                return 1;
            } else if(this.failRate > o.failRate) {
                return -1;
            } 
            
            return this.no - o.no; 
        }
    }
}