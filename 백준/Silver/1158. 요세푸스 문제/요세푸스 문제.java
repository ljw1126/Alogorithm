import java.util.*;
import java.io.*;

public class Main {
   public static void main(String[] args) throws IOException {
        input();
        pro();
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
        List<Integer> result = new ArrayList<>();
        while(!que.isEmpty()) {
            int cur = que.poll();
            c += 1;

            if(c == k) {
                c = 0;
                result.add(cur);
                continue;
            }

            que.add(cur);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i = 0; i < result.size(); i++) {
            if(i == result.size() - 1) {
                sb.append(result.get(i));
                continue;
            }

            sb.append(result.get(i)).append(", ");
        }
        sb.append(">");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}