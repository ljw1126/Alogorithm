import java.util.*;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> running = new HashMap<>();
        
        for(String name : participant) {
            running.put(name, running.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion) {
            int count = running.getOrDefault(name, 0);
            count -= 1;
            
            if(count == 0) {
                running.remove(name);
            } else {
                running.put(name, count);
            }
        }
        
        Set<String> nameSet = running.keySet();
        String answer = "";
        for(String key : nameSet) {
            answer = key;
            break;
        }
        return answer;
    }
}