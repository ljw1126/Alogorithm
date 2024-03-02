import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] dist;

    static int INF = 100;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선), 가중치 1

        dist = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            dist[from][to] = 1;
            dist[to][from] = 1;
        }

        // int 범위를 초과하니 플로이드 워셜이 이상하게 나옴
        for(int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    static void pro() {
        int A = 0;
        int B = 0;
        int ans = Integer.MAX_VALUE;
        for(int i =1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int total = 0;

                for(int k = 1; k <= N; k++) {
                    int minHour = Math.min(dist[k][i], dist[k][j]);
                    total += (minHour * 2);
                }

                if(total < ans) {
                    ans = total;
                    A = i;
                    B = j;
                }
            }
        }

        sb.append(A).append(" ").append(B).append(" ").append(ans);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}