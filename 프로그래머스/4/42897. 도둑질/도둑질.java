class Solution {
    public int solution(int[] money) {
        int length = money.length;
        
        //dpX : 첫번째 집을 선택하지 않는 경우 
        //dpO : 첫번째 집을 선택하는 경우
        int[] dpX = new int[length];
        int[] dpO = new int[length];
        
        dpX[0] = 0;
        dpX[1] = money[1];
        
        dpO[0] = money[0];
        dpO[1] = money[0];
        
        for(int i = 2; i < length; i++) {
            dpX[i] = Math.max(dpX[i - 1], dpX[i - 2] + money[i]);
            dpO[i] = Math.max(dpO[i - 1], dpO[i - 2] + money[i]);
        }
        
        return Math.max(dpX[length - 1], dpO[length - 2]);
    }
}