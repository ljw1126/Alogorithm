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
            Deque<String> stack = new ArrayDeque<>();
            boolean isPossible = true;
            for(char c : input.trim().toCharArray()) {
                if(c == '.') break;
                if(Character.isAlphabetic(c)) {
                    continue;
                }

                if(c == '(') {
                    stack.push("(");
                }

                if(c == ')') {
                    if(!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        isPossible = false;
                        break;
                    }
                }

                if(c == '[') {
                    stack.push("[");
                }

                if(c == ']') {
                    if(!stack.isEmpty() && stack.peek().equals("[")) {
                        stack.pop();
                    } else {
                        isPossible = false;
                        break;
                    }
                }
            }

            sb.append(isPossible && stack.isEmpty() ? "yes" : "no").append("\n");
        }
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}