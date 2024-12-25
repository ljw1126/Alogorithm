import java.util.*;
import java.io.*;

class Solution {
    public int solution(String s) {
        int answer = 1001;
        
        
        int half = s.length() / 2 + 1;
        StringBuilder str = new StringBuilder();
        for(int len = 1; len <= half; len++) {

            int idx = 0;
            while(idx < (s.length() - len)) {
                String target = s.substring(idx, idx + len);
                int repeated = 1 + rec(s, target, idx + len, len);

                String compress = repeated == 1 ? target : (repeated + target);
                str.append(compress);

                idx += (repeated == 1 ? len : repeated * len);
            }

            if(idx < s.length()) {
                str.append(s.substring(idx));
            }

            answer = Math.min(answer, str.length());
            str.setLength(0);
        }
        
        return answer;
    }
    
    private static int rec(String base, String t, int start, int len) {
        if(start + len > base.length()) return 0;

        String next = base.substring(start, start + len);
        if(t.equals(next)) {
            return 1 + rec(base, t, start + len, len);
        }

        return 0;
    }
}