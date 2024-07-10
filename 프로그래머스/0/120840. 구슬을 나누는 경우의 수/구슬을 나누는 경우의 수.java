class Solution {
    public int solution(int balls, int share) {
        if(balls == share) return 1;
        if(balls < share) return 0;
        if(share == 1) return balls;
        
        int min = Math.min(balls - share, share);
        long child = 1;
        long parent = 1;
        
        for(int i = 1; i <= min; i++) {
            child *= (balls - i + 1);
            parent *= i;
            
            if(child % parent == 0) {
                child /= parent;
                parent = 1;
            }
        }
        
        return (int) (child / parent);
    }
}