import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        pro();
        output();
    }

    private static void pro() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while(!(input = br.readLine()).equals(".")) {
            sb.append(isBalanced(input) ? "yes" : "no").append("\n");
        }
        
        br.close();
    }

    private static boolean isBalanced(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : input.toCharArray()) {
            if(c == '(' || c == '[') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }

                stack.pop();
            } else if(c == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    return false;
                }

                stack.pop();
            }

        }

        return stack.isEmpty();
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}