import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static String s, t;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();
    }

    private static void pro() {
        int sLen = s.length();

        while(sLen < t.length()) {
            boolean isEndsWithB = t.endsWith("B");

            t = t.substring(0, t.length() - 1);
            if(isEndsWithB) {
                t = reverse(t);
            }
        }

        boolean isPossible = s.equals(t);
        sb.append(isPossible ? 1 : 0);
    }

    private static String reverse(String text) {
        StringBuilder sb = new StringBuilder();
        for(int i = text.length() - 1; i >= 0; i--) {
            sb.append(text.charAt(i));
        }

        return sb.toString();
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}