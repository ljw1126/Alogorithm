class Solution {
    public String[] solution(String my_string) {
        my_string = my_string.trim();
        return my_string.split("\\s+");
    }
}