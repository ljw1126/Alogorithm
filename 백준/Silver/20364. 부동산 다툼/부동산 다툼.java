import java.util.*;
import java.io.*;

public class Main {
    
static StringBuilder sb = new StringBuilder();

    static int N, Q;

    static boolean[] REAL_ESTATE;

    static List<Integer> queries = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        REAL_ESTATE = new boolean[N + 1];

        for(int i = 1; i <= Q; i++) {
            queries.add(Integer.parseInt(br.readLine()));
        }
    }

    static void execute(int q) {
        int idx = q;
        int ans = 0;
        while(idx != 0) {
            if(REAL_ESTATE[idx]) {
                ans = idx;
            }

            idx /= 2;
        }

        if(ans == 0) REAL_ESTATE[q] = true;

        sb.append(ans).append("\n");
    }

    static void pro() {
        for(int query : queries) execute(query);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}