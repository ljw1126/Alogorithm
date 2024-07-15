class Solution {
    public String solution(String a, String b) {
        int L = a.length() - 1;
        int R = b.length() - 1;
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(L >= 0 && R >= 0) {
            char c1 = a.charAt(L);
            char c2 = b.charAt(R);
            
            int sum = (c1 - '0') + (c2 - '0') + carry;
            if(sum >= 10) carry = 1;
            else carry = 0;
            
            sb.append(sum % 10);
            
            L -= 1;
            R -= 1;
        }
        
        while(L >= 0) {
            char c = a.charAt(L--);
            int sum = (c - '0') + carry;
            sb.append(sum % 10);
            
            if(sum >= 10) carry = 1;
            else carry = 0;
        }
        
        System.out.println(carry);
        while(R >= 0) {
            char c = b.charAt(R--);
            int sum = (c - '0') + carry;
            sb.append(sum % 10);
            
            if(sum >= 10) carry = 1;
            else carry = 0;
        }
        
        if(carry != 0) {
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
}