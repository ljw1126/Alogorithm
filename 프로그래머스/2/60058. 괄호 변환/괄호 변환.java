import java.util.*;
class Solution {
    public String solution(String p) {
        return dfs(p);
    }
    
    private String dfs(String s) {
        if(s.isEmpty()) return s;
        
        int cnt = 0;
        int idx = 0;
        for(; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            
            if(c == '(') cnt += 1;
            else cnt -= 1;
            
            if(cnt == 0) {
                break;
            }
        }
        
    
        String u = "";
        String v = "";
        if(idx < s.length()) {
            u = s.substring(0, idx + 1);
            v = s.substring(idx + 1);
        } else {
            u = s;
        }
        
        if(isValid(u)) {
            return u + dfs(v);
        } 
        
        String next = "(";
        next += dfs(v);
        next += ")";
        for(int i = 1; i <= u.length() - 2; i++) {
            next += (u.charAt(i) == ')' ? '(' : ')');
        }
        
        return next;
    }
    
    private boolean isValid(String t) {
        if(t.isEmpty()) return true;
        
        int result = 0;
        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(c == '(') {
                result += 1;
            } else if(c == ')' && result > 0) {
                result -= 1;
            } else {
                return false;
            }
        }
        
        return result == 0;
    }
}