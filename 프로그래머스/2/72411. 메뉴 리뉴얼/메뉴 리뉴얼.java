import java.util.*;
import java.util.stream.*;

class Solution {
    
    private static Map<String, Integer> menuMap = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        for(int i = 0; i < orders.length; i++) {
            String order = orders[i];
            char[] c = order.toCharArray();
            Arrays.sort(c);
            orders[i] = String.valueOf(c);
        }
        
        List<String> result = new ArrayList<>();
        for(int c : course) {
            for(String order : orders) {
                combination("", order, c);
            }
            
            if(!menuMap.isEmpty()) {
                int max = menuMap.values().stream().mapToInt(Integer::intValue).max().getAsInt();
                if(max > 1) {
                    for(String key : menuMap.keySet()) {
                        if(menuMap.get(key) == max) {
                            result.add(key);
                        }
                    }
                }
            }
            
            menuMap.clear();
        }
    
        
        for(String menu : menuMap.keySet()) {
            if(menuMap.get(menu) < 2) continue;
            
            result.add(menu);
        }
        
        Collections.sort(result);
        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    private static void combination(String menu, String order, int count) {
        if(count == 0) {
            menuMap.put(menu, menuMap.getOrDefault(menu, 0) + 1);
            return;
        }
        
        for(int i = 0; i < order.length(); i++) {
            combination(menu + order.substring(i, i + 1), order.substring(i + 1), count - 1);
        }
    }
}