import java.util.*;
import java.io.*;

public class Main {
    
   private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, k;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    private static void pro() throws IOException {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            que.add(i);
        }

        int c = 0;
        StringJoiner joiner = new StringJoiner(", ", "<", ">");
        while(!que.isEmpty()) {
            int cur = que.poll();
            c += 1;

            if(c == k) {
                c = 0;
                joiner.add(cur + "");
                continue;
            }

            que.add(cur);
        }

        sb.append(joiner);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}