import java.util.stream.*;

class Solution {
    public String[] solution(String[] strArr) {
        return IntStream.range(0, strArr.length)
            .mapToObj(i -> i % 2 == 1 ? strArr[i].toUpperCase() : strArr[i].toLowerCase())
            .toArray(String[]::new);
    }
    
    public String[] solve(String[] strArr) {
        for(int i = 0; i < strArr.length; i++) {
            strArr[i] = converted(i, strArr[i]);
        }
        
        return strArr;
    }
    
    private String converted(int idx, String str) {
        if(idx % 2 == 1) {
            return str.toUpperCase();
        }
        
        return str.toLowerCase();
    }
}