import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static String[] data = new String[2];

    static long MAX_VALUE = Long.MAX_VALUE;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        data[0] = st.nextToken();
        data[1] = st.nextToken();
    }

    static int convert(char x) {
        if('0' <= x && x <= '9') return x - '0';

        return x - 'a' + 10;
    }

    static long convertTo(String str, int base) {
        long result = 0L;
        for(char c : str.toCharArray()) {
            if(convert(c) >= base) return -1;
            if(result > (MAX_VALUE - convert(c)) / base) return - 1;

            result *= base;
            result += convert(c);
        }

        return result;
    }

    static void pro() {
        long ans = -1;
        long ansA = 0;
        long ansB = 0;
        for(int i = 2; i <= 36; i++) {
            for(int j = 2; j <= 36; j++) {
                if(i == j) continue;

                long v1 = convertTo(data[0], i); 
                if(v1 == -1) continue;

                long v2 = convertTo(data[1], j); 
                if(v2 == -1) continue;

                if(v1 != v2) continue;

                if(ans == -1) {
                    ans = v1;
                    ansA = i;
                    ansB = j;
                } else {
                    sb.append("Multiple");
                    return;
                }
            }
        }

        if(ans == -1) sb.append("Impossible");
        else sb.append(ans).append(" ").append(ansA).append(" ").append(ansB);
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb.toString());
    }
    
    
}