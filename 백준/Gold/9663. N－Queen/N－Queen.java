import java.util.*;
import java.io.*;

public class Main {
    
    static int N, RESULT;
    static int[] column;
    static StringBuilder sb;

    public static void input() {
        sb = new StringBuilder();

        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        column = new int[N + 1];
    }

    public static void output() throws IOException {
        sb.append(RESULT);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        input();

        rec(1);

        output();
    }

    private static void rec(int row) {
        if(row == N + 1) {
            RESULT += 1;
            return;
        }

        for(int c = 1; c <= N; c++) {
            if(isPossible(row, c)) {
                column[row] = c;
                rec(row + 1);
                column[row] = 0;
            }
        }
    }

    private static boolean attackable(int r1, int c1, int r2, int c2) {
        if(c1 == c2) return true;
        if(r1 + c1 == r2 + c2) return true;
        if(r1 - c1 == r2 - c2) return true;

        return false;
    }

    private static boolean isPossible(int row, int col) { // 이번에 놓으려는 위치 (row, col)
        for(int r = 1; r < row; r++) {
            if(attackable(r, column[r], row, col)) {
                return false;
            }
        }

        return true;
    }


    public static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}