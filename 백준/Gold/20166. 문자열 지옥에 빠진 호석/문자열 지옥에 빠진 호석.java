import java.util.*;
import java.io.*;

public class Main {
  static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static char[][] wordMatrix;
    static List<String> queries = new ArrayList<>();

    static Map<String, Integer> wordMap = new HashMap<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 신이 좋아하는 문자열의 길이

        wordMatrix = new char[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] words = st.nextToken().toCharArray();
            for(int j = 0; j < M; j++) {
                wordMatrix[i][j] = words[j];
            }
        }

        for(int i = 1; i <= K; i++) {
            queries.add(br.readLine()); // 신이 좋아하는 문자열
        }
    }

    static int getX(int fromX, int toX) {
        int result = fromX + toX;
        if(result < 0) result = N - 1;
        else if(result >= N) result = 0;

        return result;
    }

    static int getY(int fromY, int toY) {
        int result = fromY + toY;
        if(result < 0) result = M - 1;
        else if(result >= M) result = 0;

        return result;
    }

    static int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
    static void rec(int x, int y, int len, String word) {
        if(wordMap.containsKey(word)) {
            wordMap.put(word, wordMap.get(word) + 1);
        } else {
            wordMap.put(word, 1);
        }

        if(len == 5) return;

        for(int i = 0; i < 8; i++) {
            int dx = getX(x, DIR[i][0]);
            int dy = getY(y, DIR[i][1]);

            rec(dx, dy, len + 1, word + wordMatrix[dx][dy]);
        }
    }

    static void pro() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                rec(i, j, 1, wordMatrix[i][j] + "");
            }
        }

        for(String query : queries) {
            sb.append(wordMap.getOrDefault(query, 0)).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}