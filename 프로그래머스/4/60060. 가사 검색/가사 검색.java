import java.util.*;
import java.io.*;

class Solution {
    private static StringBuilder sb = new StringBuilder();
    private List<List<String>> forward;
    private List<List<String>> reverse;
    
    public int[] solution(String[] words, String[] queries) {
        forward = new ArrayList<>();
        reverse = new ArrayList<>();
        for(int i = 0; i <= 10_000; i++) {
            forward.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for(String w : words) {
            int len = w.length();
            forward.get(len).add(w);
            reverse.get(len).add(reverseFrom(w));
        }

        for(int i = 1; i <= 10_000; i++) {
            if(forward.get(i).isEmpty() || reverse.get(i).isEmpty()) continue;

            Collections.sort(forward.get(i));
            Collections.sort(reverse.get(i));
        }

        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int len = query.length();
            if(query.startsWith("?")) {
                answer[i] = countByRange(reverse.get(len), reverseFrom(query));
            } else {
                answer[i] = countByRange(forward.get(len), query);
            }
        }
        
        return answer;
    }
    
    private static String reverseFrom(String word) {
        String result = sb.append(word).reverse().toString();
        sb.setLength(0);
        return result;
    }

    private static int countByRange(List<String> words, String query) {
        if(words.isEmpty()) return 0;

        int left = binarySearch(words, query.replaceAll("\\?", "a"));
        int right = binarySearch(words, query.replaceAll("\\?", "z"));

        return right - left;
    }

    // 음수가 되는 가장 첫번째
    private static int binarySearch(List<String> words, String query) {
        int L = 0;
        int R = words.size();

        while(L < R) {
            int mid = (L + R) / 2;

            if(query.compareTo(words.get(mid)) < 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return R;
    }
}