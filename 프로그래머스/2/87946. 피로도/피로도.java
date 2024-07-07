class Solution {
    public int solution(int k, int[][] dungeons) {
        return rec(dungeons, k);
    }
    
    private int rec(int[][] dungeons, int k) {
        int cnt = 0;
        for(int[] dungeon : dungeons) {
            int require = dungeon[0];
            int decrease = dungeon[1];
            
            if(require > k) continue;
            
            dungeon[0] = 5001; 
            cnt = Math.max(1 + rec(dungeons, k - decrease), cnt);
            dungeon[0] = require;
        }
       
        return cnt;
    }
}