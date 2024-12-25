import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        int M = key[0].length; // 돌기를 찾고
        int N = lock[0].length; // 홈을 찾고

        int[][] rotated = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                rotated[i][j] = key[i][j];
            }
        }

        // 회전
        out:
        for (int r = 0; r < 4; r++) {
            rotated = rotate(rotated, M);

            // 이동
            for (int row = -N; row < N; row++) {
                for (int col = -N; col < N; col++) {
                    // 열쇠를 회전 후 이동 시킨 값을 자물쇠에 합산
                    matchKey(rotated, row, col, lock, N, M);

                    // 자물쇠가 모두 1인지 확인 (맞으면 종료)
                    if(isUnlock(lock, N)) {
                        answer = true;
                        break out;
                    }

                    removeKey(rotated, row, col, lock, N, M);
                }
            }
        }
        
        return answer;
    }
    
    private static void matchKey(int[][] rotated, int x, int y, int[][] lock, int n, int m) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                int dx = i + x;
                int dy = j + y;

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                lock[dx][dy] += rotated[i][j];
            }
        }
    }

    private static boolean isUnlock(int[][] lock, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] != 1) return false;
            }
        }

        return true;
    }

    private static void removeKey(int[][] rotated, int x, int y, int[][] lock, int n, int m) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                int dx = i + x;
                int dy = j + y;

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                lock[dx][dy] -= rotated[i][j];
            }
        }
    }

    private int[][] rotate(int[][] rotated, int m) {
        int[][] result = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                result[j][m - 1 - i] = rotated[i][j];
            }
        }

        return result;
    }
}