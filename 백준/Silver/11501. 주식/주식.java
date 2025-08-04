import java.util.*;
import java.io.*;

public class Main {

  private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int t, n;
    private static int[] data;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while(t > 0) {
            t -= 1;

            n = Integer.parseInt(br.readLine());
            data = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            pro();
        }
    }

    private static void pro() {
        long earn = 0L;
        int std = data[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            if(data[i] > std) {
                std = data[i];
            } else {
                earn += (std - data[i]);
            }
        }

        sb.append(earn).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

  
}