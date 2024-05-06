import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수

        int[][] cities = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) Arrays.fill(cities[i], 200001);

        for(int i = 1; i <= N; i++) cities[i][i] = 0;

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            cities[from][to] = time;
        }

        for(int k = 1; k <= N; k++) { // 플로이드
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    cities[i][j] = Math.min(cities[i][j], cities[i][k] + cities[k][j]);
                }
            }
        }

        int K = Integer.parseInt(br.readLine()); // 친구 인원
        st = new StringTokenizer(br.readLine());
        List<Integer> friends = new ArrayList<>();
        for(int i = 1; i <= K; i++) {
            friends.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> candidate = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        for(int c = 1; c <= N; c++) {
            int total = 0;
            for(int f : friends) {
                total = Math.max(total, cities[f][c] + cities[c][f]);
            }

            if(total < result) {
                result = total;

                candidate.clear();
                candidate.add(c);
            } else if(total == result) {
                candidate.add(c);
            }
        }

        for(int i : candidate) sb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws Exception {
        input();
    }
}