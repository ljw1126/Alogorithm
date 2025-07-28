import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static int[] liquid;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        liquid = new int[n]; // 정렬된 상태
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void pro() {
        int L = 0;
        int R = n - 1;

        int min = Integer.MAX_VALUE;
        int a = -1;
        int b = -1;

        while(L < R) {
            int sum = liquid[L] + liquid[R];

            if(Math.abs(sum) <= min) {
                min = Math.abs(sum);
                a = liquid[L];
                b = liquid[R];
            }

            if(sum < 0) {
                L += 1;
            } else {
                R -= 1;
            }
        }

        sb.append(a).append(" ").append(b);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}