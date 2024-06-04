import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        List<String> reported = Arrays.stream(report)
            .distinct()
            .collect(Collectors.toList());
        
        // 제보 카운팅
        Map<String, Integer> countMap = new HashMap<>();
        for(String r : reported) {
            String target = r.split("\\s")[1];
            countMap.put(target, countMap.getOrDefault(target, 0) + 1);
        }
        
        return Arrays.stream(id_list)
            .map(user -> {
                List<String> userReport = reported.stream()
                    .filter(text -> text.startsWith(user + " "))
                    .collect(Collectors.toList());
                
                return userReport.stream()
                    .filter(s -> countMap.getOrDefault(s.split("\\s")[1], 0) >= k)
                    .count();
            })
            .mapToInt(Long::intValue)
            .toArray();
    }
}