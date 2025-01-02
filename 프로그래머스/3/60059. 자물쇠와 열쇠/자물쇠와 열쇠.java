class Solution {
    private int[][] _key;
    private int[][] _lock;
    
    public boolean solution(int[][] key, int[][] lock) {
        _key = key;
        _lock = lock;
        int m = key.length; // key
        int n = lock.length; // lock

        boolean answer = false;
        loop1 :
        for(int i = 0; i < 4; i++) {

            // 이동한다
            for(int x = -40; x <= 40; x++) {
                for(int y = -40; y <= 40; y++) {
                    // 열쇠를 자물쇠에 끼워본다
                    pushKey(x, y, m, n);

                    if(unlock(n)) { // 모든 자물쇠 영역이 1인가
                        answer = true;
                        break loop1;
                    }

                    // 열쇠를 자물쇠에서 뺀다
                    pollKey(x, y, m, n);
                }
            }

            if(i < 3) {
                _key = rotate(_key, m); // 회전한다
            }
        }

        return answer;
    }
    
    private void pushKey(int x, int y, int m, int n) {
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < m; col++) {
                int dx = row + x;
                int dy = col + y;

                // 자물쇠의 범위를 벗어나는 경우
                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                _lock[dx][dy] += _key[row][col];
            }
        }
    }

    private boolean unlock(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(_lock[i][j] != 1) return false;
            }
        }

        return true;
    }

    private void pollKey(int x, int y, int m, int n) {
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < m; col++) {
                int dx = row + x;
                int dy = col + y;

                // 자물쇠의 범위를 벗어나는 경우
                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                _lock[dx][dy] -= _key[row][col];
            }
        }
    }

    private int[][] rotate(int[][] key, int m) {
        int[][] temp = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                temp[i][j] = key[m - j - 1][i];
            }
        }

        return temp;
    }
}