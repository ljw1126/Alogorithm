class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        Result base = new Result(n);

        for(int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2]; // 0: 기둥, 1: 보
            int b = frame[3]; // 0: 삭제, 1: 설치

            base.process(x, y, a, b);
        }

        return base.getResult();
    }
    
     private static class Result {
        private final boolean[][] pole;
        private final boolean[][] bar;
        private final int n;
        private int count = 0;

        public Result(int n) {
            this(new boolean[n + 2][n + 2], new boolean[n + 2][n + 2], n);
        }

        public Result(boolean[][] pole, boolean[][] bar, int n) {
            this.pole = pole;
            this.bar = bar;
            this.n = n;
        }

        public void process(int x, int y, int a, int b) {
            if(a == 0 && b == 1) {
                buildPole(x, y);
            } else if(a == 0 && b == 0) {
                deletePole(x, y);
            } else if(a == 1 && b == 1) {
                buildBar(x, y);
            } else if(a == 1 && b == 0) {
                deleteBar(x, y);
            }
        }

        //바닥이거나, 아래에 기둥이 있다면, 왼쪽에 보가 있다면, 오른쪽에 보가 있다면
        private void buildPole(int x, int y) {
            if(checkPole(x, y)) {
                pole[x][y] = true;
                count += 1;
            }
        }

        private boolean checkPole(int x, int y) {
            return y == 0
                    || (0 < y && pole[x][y - 1])
                    || (0 < x && bar[x - 1][y])
                    || bar[x][y];
        }

        // 위에 기둥이 있는 경우, x == 0 (n)에 기둥이 있을대 , 양옆에 기둥없이 보가 있을대
        private void deletePole(int x, int y) {
            pole[x][y] = false;
            if(canDelete(x, y)) {
                count -= 1;
            } else {
                pole[x][y] = true;
            }
        }

        // 왼쪽 아래 기둥이 있거나, 오른쪽 아래에 기둥이 있다면, 양옆에 보가 있다면
        private void buildBar(int x, int y) {
            if(checkBar(x, y)) {
                bar[x][y] = true;
                count += 1;
            }
        }

        // 왼쪽, 오른쪽에 기둥이 있거나, 양옆에 보가 있는 경우
        private boolean checkBar(int x, int y) {
            return (0 < y && pole[x][y - 1])
                    || (0 < y && pole[x + 1][y - 1])
                    || (0 < x && bar[x - 1][y] && bar[x + 1][y]);
        }

        private void deleteBar(int x, int y) {
            bar[x][y] = false;
            if(canDelete(x, y)) {
                count -= 1;
            } else {
                bar[x][y] = true;
            }
        }

        private boolean canDelete(int x, int y) {
            int minX = Math.max(0, x - 1);
            int maxX = Math.min(n, x + 1);
            int minY = Math.max(0, y - 1);
            int maxY = Math.min(n, y + 1);
            
            for(int i = minX; i <= maxX; i++) {
                for(int j = minY; j <= maxY; j++) {
                    if(pole[i][j] && !checkPole(i, j)) {
                        return false;
                    }

                    if(bar[i][j] && !checkBar(i, j)) {
                        return false;
                    }
                }
            }

            return true;
        }

        public int[][] getResult() {
            int[][] result = new int[count][3];
            int idx = 0;
            for(int i = 0; i <= n; i++) {
                for(int j = 0; j <= n; j++) {
                    if(pole[i][j]) {
                        result[idx++] = new int[] {i, j, 0};
                    }

                    if(bar[i][j]) {
                        result[idx++] = new int[] {i, j, 1};
                    }
                }
            }
            return result;
        }
    }
}