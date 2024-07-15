import java.util.*;

class Solution {
    public String[] solution(String[] str_list) {
        
        for(int i = 0; i < str_list.length; i++) {
            String w = str_list[i];
            if("l".equals(w)) {
                return Arrays.copyOfRange(str_list, 0, i);
            } else if("r".equals(w)) {
                return Arrays.copyOfRange(str_list, i + 1, str_list.length);
            }
        }
        
        return new String[] {};
    }
}