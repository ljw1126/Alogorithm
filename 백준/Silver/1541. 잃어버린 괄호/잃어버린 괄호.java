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

        String input = br.readLine();
        String[] subtraction = input.split("-");

        int result = 0;

        String first = subtraction[0];
        result += calculate(first.split("\\+"));
        for(int i = 1; i < subtraction.length; i++){
            result -= calculate(subtraction[i].split("\\+"));
        }

        sb.append(result);
    }

    private static int calculate(String... nums) {
        int result = 0;
        for(String n : nums) {
            result += Integer.parseInt(n);
        }

        return result;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}