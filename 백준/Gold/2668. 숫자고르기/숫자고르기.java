import java.util.*;
import java.io.*;

public class Main {
    
     static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] arr;

    static boolean[] visit, finish;

    static List<Integer> result = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[N + 1];
        finish = new boolean[N + 1];
    }

    static void dfs(int x) {
        visit[x] = true;

        int y = arr[x];
        if(!visit[y]) {
            dfs(y);
        } else if(!finish[y]) {
            while(x != y) {
                result.add(y);
                y = arr[y];
            }
            result.add(x);
        }

        finish[x] = true;
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) dfs(i);
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(int i : result) sb.append(i).append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}