import java.util.*;
import java.io.*;

class Solution {
    private static StringBuilder sb = new StringBuilder();
    private String S;
    public int solution(String s) {
        if(s.length() == 1) return 1;
        
        int answer = 1001;
        S = s;

        int half = s.length() / 2;
        for(int len = 1; len <= half; len++) {
            String compressed = compress(len, sb);
            
            answer = Math.min(answer, compressed.length());

            sb.setLength(0);
        }
        
        return answer;
    }
    
    private String compress(int len, StringBuilder stringBuilder) {
        int L = 0;
        while(L <= S.length() - len) {
            int R = L + len;
            String base = S.substring(L, R);
            int repeated = 1 + count(base, R, len);

            if(repeated == 1) {
                sb.append(base);
            } else {
                sb.append(repeated + base);
            }

            L += repeated * len;
        }

        if(L < S.length()) {
            sb.append(S.substring(L));
        }

        return sb.toString();
    }

    private int count(String t, int from, int len) {
        if(from >= S.length()) return 0;

        String remain = S.substring(from);
        if(remain.startsWith(t)) {
            return 1 + count(t, from + len, len);
        }

        return 0;
    }
}