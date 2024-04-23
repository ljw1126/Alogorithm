import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    
    static void pro() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<Integer, Integer> countMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            countMap.put(num, countMap.getOrDefault(num , 0) + 1);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i =1; i <= M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(countMap.getOrDefault(num, 0)).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        pro();
    }
}