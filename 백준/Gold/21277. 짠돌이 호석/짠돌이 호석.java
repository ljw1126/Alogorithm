import java.util.*;
import java.io.*;

public class Main {
 static int N1, M1;
    static int N2, M2;

    static int[][] board;
    static int[][] puzzle;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[51][51];
        st = new StringTokenizer(br.readLine());

        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N1; i++) {
            String line = br.readLine();
            for(int j = 1; j <= M1; j++) {
                board[i][j] = line.charAt(j - 1) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        puzzle = new int[N2 + 1][M2 + 1];
        for(int i = 1; i <= N2; i++) {
            String line = br.readLine();
            for(int j = 1; j <= M2; j++) {
                puzzle[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    static int[][] rotate(int[][] p, int row, int col) {
        int[][] temp = new int[col + 1][row + 1]; 

        for(int r = 1; r <= row; r++) {
            for(int c = 1; c <= col; c++) { 
                temp[c][row - r + 1] = p[r][c];
            }
        }
        
        return temp;
    }

    static boolean possible(int shiftX, int shiftY) { // (2, 1)

        for(int x = 1; x <= N1; x++) {
            for(int y = 1; y <= M1; y++) {
                if(board[x][y] == 0) continue;

                int dx = x - shiftX + 1;
                int dy = y - shiftY + 1;
                if(dx >= 1 && dx <= N2 && dy >= 1 && dy <= M2 && puzzle[dx][dy] == 1) {
                    return false;
                }

            }
        }

        return true;
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;
        for(int rot = 1; rot <= 4; rot++) {

            for(int shiftX = -50; shiftX <= 50; shiftX++) {
                for(int shiftY = -50; shiftY <= 50; shiftY++) {

                    int row = Math.max(N1, shiftX + N2 - 1) - Math.min(1, shiftX) + 1;
                    int col = Math.max(M1, shiftY + M2 - 1) - Math.min(1, shiftY) + 1;

                    if(row * col >= ans) {
                        continue;
                    } else if(possible(shiftX, shiftY)) {
                        ans = Math.min(ans, row * col);
                    }
                }
            }

            puzzle = rotate(puzzle, N2, M2);
            int temp = M2;
            M2 = N2;
            N2 = temp;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}