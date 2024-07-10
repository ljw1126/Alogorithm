class Solution {
    public int solution(String A, String B) {
        B = B.repeat(3);
        return B.indexOf(A);
    }
    
    public int solve(String A, String B) {
        if(A.equals(B)) return 0;
        
        int length = A.length();
        int right = 0;
        while(right <= length) {
            right += 1;
            
            boolean isPossible = true;
            for(int i = 0; i < length; i++) {
                char a = A.charAt(i);
                char b = B.charAt((i + right) % length);
                
                if(a != b) {
                    isPossible = false;
                    break;
                }
            }
            
            if(isPossible) {
                return right;
            }
        }
        
        return -1;
    }
}