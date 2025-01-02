import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] food_times, long k) {
        List<Food> foods = new ArrayList<>();
        for(int i = 0; i < food_times.length; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        Collections.sort(foods);

        int answer = -1;
        int w = foods.size();
        int prevTime = 0;
        int i = 0;
        for(Food f : foods) {
            long diff = f.time - prevTime; // 높이

            if(diff > 0) {
                long area = diff * w; // 넓이 = 높이 * 너비
                if(area <= k) {
                    k -= area;
                    prevTime = f.time;
                } else {
                    List<Food> sub = foods.subList(i, food_times.length);
                    sub.sort(Comparator.comparingInt(a -> a.no));
                    
                    long idx = k % w;
                    answer = sub.get((int) idx).no;
                    break;
                }
            }

            i += 1;
            w -= 1;
        }

        return answer;
    }
    
    private static class Food implements Comparable<Food> {
        private final int no;
        private final int time;

        public Food(int no, int time) {
            this.no = no;
            this.time = time;
        }

        public int compareTo(Food o) {
            if(this.time != o.time) {
                return this.time - o.time;
            }
            
            return this.no - o.no;
        }
    }
}