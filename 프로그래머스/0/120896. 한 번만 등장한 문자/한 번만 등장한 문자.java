import java.lang.StringBuilder;

class Solution {
    public String solution(String s) {
        int[] alphabet = new int[27];
        for(char c : s.toCharArray()) {
            alphabet[c - 'a'] += 1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= 26; i++) {
            if(alphabet[i] == 1) {
                char c = (char) (i + 'a');
                sb.append(c);
            }   
        }
        
        return sb.toString();
    }
}