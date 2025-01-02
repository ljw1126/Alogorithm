class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        Board board = new Board(n);

        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int a = bf[2]; // 0 : 기둥, 1 : 보
            int b = bf[3]; // 0 : 삭제, 1 : 설치 

            board.execute(x, y, a, b);
        }

        int[][] answer = new int[board.count][3];
        int idx = 0;
        boolean[][] pole = board.pole;
        boolean[][] bo = board.bo;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pole[i][j]) {
                    answer[idx++] = new int[]{i, j, 0};
                }

                if(bo[i][j]) {
                    answer[idx++] = new int[]{i, j, 1};
                }
            }
        }
        return answer;
    }
    
     private static class Board {
        private final int n;
        private final boolean[][] pole;
        private final boolean[][] bo;
        private int count;

        public Board(int n) {
            this.n = n;
            this.pole = new boolean[n + 2][n + 2];
            this.bo = new boolean[n + 2][n + 2];
        }

        public void execute(int x, int y, int a, int b) {
            if(a == 0) { // 기둥
                if(b == 0) destroyPole(x, y);
                else if(b == 1) buildPole(x, y);
            } else if(a == 1){ // 보
                if(b == 0) destroyBo(x, y);
                else if(b == 1) buildBo(x, y);
            }
        }

        private void buildPole(int x, int y) {
            if(buildablePole(x, y)) {
                pole[x][y] = true;
                count += 1;
            }
        }

        // 바닥 위에 있거나, 다른 기등 위에 있거나, 보의 한쪽 끝에 있거나
        private boolean buildablePole(int x, int y) {
            return y == 0
                  || (0 < y && pole[x][y - 1])
                  || (0 < x && bo[x - 1][y])
                  || (bo[x][y]);
        }

        private void destroyPole(int x, int y) {
            pole[x][y] = false;
            if(invalid(x, y)) {
                pole[x][y] = true;
            } else {
                count -= 1;
            }
        }

        private void buildBo(int x, int y) {
            if(buildableBo(x, y)) {
                bo[x][y] = true;
                count += 1;
            }
        }

        // 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝부분이 다른 보와 동시에 연결되어 있는 경우
        private boolean buildableBo(int x, int y) {
            return (0 < y && pole[x][y - 1])
                    || (0 < y && pole[x + 1][y - 1])
                    || ((0 < x && bo[x - 1][y]) && bo[x + 1][y]);
        }

        private void destroyBo(int x, int y) {
            bo[x][y] = false;
            if(invalid(x, y)) {
                bo[x][y] = true;
            } else {
                count -= 1;
            }
        }

        private boolean invalid(int x, int y) {
            int minX = Math.max(0, x - 1);
            int maxX = Math.min(x + 1, n);
            int minY = Math.max(0, y - 1);
            int maxY = Math.min(y + 1, n);
            for(int i = minX; i <= maxX; i++) {
                for(int j = minY; j <= maxY; j++) {
                    if(pole[i][j] && !buildablePole(i, j)) return true;

                    if(bo[i][j] && !buildableBo(i, j)) return true;
                }
            }

            return false;
        }
    }
}