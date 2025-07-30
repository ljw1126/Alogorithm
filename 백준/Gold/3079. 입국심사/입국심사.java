import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static int[] times;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 입국 심사대 수
        m = Integer.parseInt(st.nextToken()); // 상근이와 친구들 수

        times = new int[n]; // 심사하는데 걸리는 시간
        for(int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void pro() {
        Arrays.sort(times);

        long L = 1L;
        long R = (long) times[0] * m;
        long result = 0L;
        while(L <= R) {
            long mid = (L + R) / 2;

            if(isPossible(mid)) {
                result = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }
    
    private static boolean isPossible(long limit) {
        long count = 0L;
        for(int t : times) {
            if(count >= Long.MAX_VALUE - (limit / t)) return false;
            
            count += (limit / t);
        }

        return count >= m;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}