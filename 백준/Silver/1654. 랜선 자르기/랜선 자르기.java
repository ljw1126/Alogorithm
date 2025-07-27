import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int k, n;
    private static int[] lines;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken()); 

        lines = new int[k];
        for(int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void pro() {
        long L = 0;
        long R = Integer.MAX_VALUE;

        long result = -1;

        while(L <= R) {
            long mid = (L + R) / 2;

            if(isPossible(mid)) {
                L = mid + 1;
                result = mid;
            } else {
                R = mid - 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(long limit) {
        long result = 0;
        for(int l : lines) {
            result += (l / limit);
        }

        return result >= n;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}