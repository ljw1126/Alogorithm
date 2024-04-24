import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<Integer>[] adj;

    static int[] distance;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 1) {
                    adj[i].add(j);
                }
            }
        }
    }

    static void dfs(int x, int value) {
        distance[x] = value;

        for(int y : adj[x]) {
            if(distance[y] == 0) {
                dfs(y, value + 1);
            }
        }
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                distance[j] = 0;
            }

            dfs(i, 0);

            for(int k = 1; k <= N; k++) {
                if(distance[k] > 0) sb.append(1).append(" ");
                else sb.append(0).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
