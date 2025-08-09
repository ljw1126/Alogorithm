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
    private static long[] data;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        data = new long[n];
        for(int i = 0; i < n; i++) {
            data[i] = Long.parseLong(br.readLine());
        }
    }

    private static void pro() {
        Queue<Long> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++)  {
            pq.add(data[i]);
        }

        long result = 0;
        while(!pq.isEmpty()) {
            if(pq.size() < 2) break;

            long a = pq.poll();
            long b = pq.poll();
            long sum = a + b;
            result += sum;
            
            pq.add(sum);
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