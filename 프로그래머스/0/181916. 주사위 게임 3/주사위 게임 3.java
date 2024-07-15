import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        List<Integer> data = Arrays.asList(a, b, c, d);
        Set<Integer> unique = new HashSet<>(data);
         
        if(unique.size() == 1) {
            return 1111 * a;
        } 
        
        if(unique.size() == 4) {
            return Math.min(a, Math.min(b, Math.min(c, d)));
        } 
        
        Collections.sort(data);
        int v1 = data.get(0);
        int v2 = data.get(1);
        int v3 = data.get(2);
        int v4 = data.get(3);
        if(unique.size() == 3) {
            int q = v1;
            int r = v2;
            if(v1 == v2) {
                q = v3;
                r = v4;
            } else if(v2 == v3) {
                q = v1;
                r = v4;
            }

            return q * r;
        }
       
        if(v1 == v3 || v2 == v4) {
            int p = (v1 == v3) ? v1 : v4;
            int q = (v1 == v3) ? v4 : v1;
            return (int) Math.pow(10 * p + q, 2);
        } 
        
        // 두개씩 같은 값일때
        int p = v1;
        int q = v3;
        
        return (p + q) * Math.abs(p - q);        
    }
}