import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int m, n;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
    }

    private static void pro() {
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        for(int i = 2; i <= n; i++) {
            if(visited[i]) continue;

            for(int j = 2 * i; j <= n; j += i) {
                visited[j] = true;
            }
        }

        for(int i = m; i <= n; i++) {
            if(visited[i]) continue;

            sb.append(i).append("\n");
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}