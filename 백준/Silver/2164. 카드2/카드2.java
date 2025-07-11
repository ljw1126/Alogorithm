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

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            que.add(i);
        }

        int result = -1;
        int cnt = 1;
        while(!que.isEmpty()) {
            int cur = que.poll();
            if(que.isEmpty()) {
                result = cur;
                break;
            }

            int mod = cnt % 2;
            if(mod != 1) {
                que.add(cur);
            }

            cnt = (cnt + 1) % 2;
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