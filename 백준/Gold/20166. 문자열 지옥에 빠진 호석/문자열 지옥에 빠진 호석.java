import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static String[][] wordMatrix;
    static List<String> queries = new ArrayList<>();

    static Map<String, Integer> wordMap = new HashMap<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 신이 좋아하는 문자열의 길이

        wordMatrix = new String[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] words = line.split("");
            for(int j = 0; j < words.length; j++) {
                wordMatrix[i][j] = words[j];
            }
        }

        for(int i = 1; i <= K; i++) {
            queries.add(br.readLine()); // 신이 좋아하는 문자열
        }
    }

    static int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
    static void rec(int x, int y, int depth, String word) {
        
        if(wordMap.containsKey(word)) {
            wordMap.put(word, wordMap.get(word) + 1);
        } else {
            wordMap.put(word, 1);
        }

        if(depth == 5) return;
        
        for(int i = 0; i < 8; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if(dx < 0) dx = N - 1;
            if(dx >= N) dx = 0;
            if(dy < 0) dy = M - 1;
            if(dy >= M) dy = 0;

            rec(dx, dy, depth + 1, word + wordMatrix[dx][dy]);
        }
    }

    static void pro() throws IOException {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                rec(i, j, 1, wordMatrix[i][j]);
            }
        }

        for(String query : queries) {
            sb.append(wordMap.getOrDefault(query, 0)).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}