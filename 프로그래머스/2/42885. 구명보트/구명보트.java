import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int i = 0;
        int j = people.length - 1;
        for(; i < j; j--) {
            if(people[i] + people[j] <= limit) {
                i += 1;
            }
        }
        
        return people.length - i;
    }
}