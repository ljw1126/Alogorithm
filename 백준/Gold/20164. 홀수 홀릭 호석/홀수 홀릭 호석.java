import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static String input;
    static int MIN_VALUE, MAX_VALUE;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        input = br.readLine();

        MIN_VALUE = Integer.MAX_VALUE;
        MAX_VALUE = Integer.MIN_VALUE;
    }

    static void rec(String data, int count) {
        //길이가 1인 경우
        if(data.length() == 1) {
            count += countOdd(data);
            MIN_VALUE = Math.min(MIN_VALUE, count);
            MAX_VALUE = Math.max(MAX_VALUE, count);
            return;
        }

        //길이가 2인 경우
        if(data.length() == 2) {
            count += countOdd(data);

            String n1 = data.substring(0, 1);
            String n2 = data.substring(1);
            String next = String.valueOf(sum(n1, n2));

            rec(next, count);
            return;
        }

        //길이가 3인 경우
        if(data.length() >= 3) {
            count += countOdd(data);

            for (int i = 0; i < data.length() - 2; i++) {
                for (int j = i + 1; j < data.length() - 1; j++) {
                    String n1 = data.substring(0, i + 1); // to : exclusive
                    String n2 = data.substring(i + 1, j + 1);
                    String n3 = data.substring(j + 1);

                    String next = String.valueOf(sum(n1, n2, n3));
                    rec(next, count);
                }
            }
        }
    }

    static int countOdd(String v) {
        int result = 0;
        int value = Integer.parseInt(v);

        while(value != 0) {
            int mod = value % 10;
            if(mod % 2 != 0) result += 1;

            value /= 10;
        }

        return result;
    }
    static int sum(String... arr) {
        int sum = 0;
        for(String v : arr) sum += Integer.parseInt(v);

        return sum;
    }


    static void pro() {
        rec(input, 0);
        sb.append(MIN_VALUE).append(" ").append(MAX_VALUE);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}