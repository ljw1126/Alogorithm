class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for(int i = 0 ; i< babbling.length; i++) {
            if(babbling[i].matches("^(aya|ye|woo|ma)+$")) {
                answer += 1;
            }
        }
        
        return answer;
    }
}