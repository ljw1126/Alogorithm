import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static char[][] arr;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new char[n][2 * n - 1]; // n : 높이, 2 * n - 1 : 너비
        for(int i = 0; i < n; i++) {
            Arrays.fill(arr[i], ' ');
        }
    }

    private static void pro() {
       rec(0, n - 1, n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2 * n - 1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
    }

    private static void rec(int x, int y, int n) {
        if(n == 3) {
            arr[x][y] = '*';

            arr[x + 1][y - 1] = '*';
            arr[x + 1][y + 1] = '*';

            arr[x + 2][y - 2] = '*';
            arr[x + 2][y - 1] = '*';
            arr[x + 2][y] = '*';
            arr[x + 2][y + 1] = '*';
            arr[x + 2][y + 2] = '*';

            return;
        }

        int half = n / 2;
        // 중앙
        rec(x, y, half);

        // 왼쪽
        rec(x + half, y - half, half);

        // 오른쪽
        rec(x + half, y + half, half);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}