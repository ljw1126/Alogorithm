import java.util.*;
import java.io.*;

class Solution {
    private static StringBuilder sb = new StringBuilder();
    private List<List<String>> forward = new ArrayList<>();
    private List<List<String>> reverse = new ArrayList<>();
    
    public int[] solution(String[] words, String[] queries) {
        for(int i = 0; i <= 100000; i++) {
            forward.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        
        for(String w : words) {
            int len = w.length();
            forward.get(len).add(w);
            reverse.get(len).add(reversed(w));
        }
        
        for(int i = 0; i <= 100000; i++) {
            if(forward.get(i).isEmpty()) continue;
            
            Collections.sort(forward.get(i));
            Collections.sort(reverse.get(i));
        }
        
        int[] answer = new int[queries.length];
        int idx = 0;
        for(String q : queries) {
            if(q.startsWith("?")) {
                answer[idx++] = countByRange(reverse, reversed(q));
            } else {
                answer[idx++] = countByRange(forward, q);
            }
        }
        
        return answer;
    }
    
    private String reversed(String s) {
        String result = sb.append(s).reverse().toString();
        sb.setLength(0);
        return result;
    }
   
    private int countByRange(List<List<String>> words, String query) {
        int left = lowerBound(words, query);
        int right = upperBound(words, query);
        
        return right - left;
    }
    
    private int lowerBound(List<List<String>> words, String query) {
        int len = query.length();
        List<String> arr = words.get(len);
        if(arr.isEmpty()) return -1;
        
        query = query.replaceAll("\\?", "a");
        int L = 0;
        int R = arr.size();
        
        while(L < R) {
            int mid = (L + R) / 2;
            
            int v = query.compareTo(arr.get(mid));
            
            if(v < 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        
        return R;
    }
    
    private int upperBound(List<List<String>> words, String query) {
        int len = query.length();
        List<String> arr = words.get(len);
        if(arr.isEmpty()) return -1;
        
        query = query.replaceAll("\\?", "z");
        int L = 0;
        int R = arr.size();
        
        while(L < R) {
            int mid = (L + R) / 2;
            int v = query.compareTo(arr.get(mid));
            
            if(v < 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
            
        return R;
    }
}