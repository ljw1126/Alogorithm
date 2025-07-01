class Solution {
    public int solution(int n) { 
        StringBuilder sb = new StringBuilder();
        int base = 3;
        while(n > 0) {
            sb.append(n % base);
            n /= base;
        }
       
        return Integer.parseInt(sb.toString(), base);
    }
}