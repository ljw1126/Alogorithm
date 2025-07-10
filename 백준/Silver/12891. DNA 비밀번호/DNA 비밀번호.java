import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int s, p;
    private static String dna;
    private static int[] condition;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken()); // 임의로 만든 DNA 문자열 길이
        p = Integer.parseInt(st.nextToken()); // 비밀번호로 사용할 부분 문자열의 길이

        dna = br.readLine();

        st = new StringTokenizer(br.readLine());
        condition = new int[4]; // a, c, g, t
        for(int i = 0; i < 4; i++) {
            condition[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void pro() {
        int R = 0;
        char[] arr = dna.toCharArray();
        int a = 0;
        int c = 0;
        int g = 0;
        int t = 0;

        int result = 0;

        for(int L = 0; L <= s - p; L++) {

            while(R < s && R - L + 1 <= p) {
                if(arr[R] == 'A') a += 1;
                else if(arr[R] == 'C') c += 1;
                else if(arr[R] == 'G') g += 1;
                else if(arr[R] == 'T') t += 1;

                R += 1;
            }

            if(a >= condition[0] && c >= condition[1]
            && g >= condition[2] && t >= condition[3]) {
                result += 1;
            }

            if(arr[L] == 'A') a -= 1;
            else if(arr[L] == 'C') c -= 1;
            else if(arr[L] == 'G') g -= 1;
            else if(arr[L] == 'T') t -= 1;
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}