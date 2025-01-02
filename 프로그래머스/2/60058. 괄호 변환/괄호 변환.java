import java.io.*;

class Solution {
    public String solution(String p) {
        return rec(p);
    }
    
    private String rec(String w) {
        if(w.equals("")) return w;
        
        int idx = 0;
        int parentheses = 0;
        for(char c : w.toCharArray()) {
            if(c == '(') parentheses += 1;
            else parentheses -= 1;
            
            if(parentheses == 0) {
                break;
            }
            
            idx += 1;
        }
        
        String u = w.substring(0, idx + 1);
        String v = rec(w.substring(idx + 1));
        
        // 문자열 u가 올바른 괄호 문자열이라면
        if(check(u)) {
            return u + v;
        } 
        
        // 문자열 u가 올바른 괄호 문자열이 아니라면
        return convertText(u, v);
    }
    
    private boolean check(String u) {
        if(u.isEmpty()) return true;
        
        int count = 0;
        for(char c : u.toCharArray()) {
            if(c == '(') count += 1;
            else if(--count < 0) return false;
        }
        
        return true;
    }
    
    private String convertText(String u, String v) {
        StringBuilder sb = new StringBuilder();
        sb.append("(")
            .append(v)
            .append(")");
    
        for(int i = 1; i < u.length() - 1; i++) {
            char c = u.charAt(i);
            sb.append(c == '(' ? ")" : "(");
        }
        
        return sb.toString();
    }
}