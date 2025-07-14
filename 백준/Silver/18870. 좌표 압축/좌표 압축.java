import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static int[] x;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        x = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int v = Integer.parseInt(st.nextToken());
            x[i] = v;
        }
    }

    private static void pro() {
        int[] sorted = IntStream.of(x).distinct().sorted().toArray();
        
        for(int i = 0; i < n; i++) {
            sb.append(Arrays.binarySearch(sorted, x[i])).append(" ");
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}