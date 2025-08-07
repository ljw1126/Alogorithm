import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static int[] data;
    private static int[][] query;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        data = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        query = new int[m][2];
        for(int i = 0; i < m; i++) {
            StringTokenizer points = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(points.nextToken());
            query[i][1] = Integer.parseInt(points.nextToken());
        }
    }

    private static void pro() {
        boolean[][] phallendrome = new boolean[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            phallendrome[i][i] = true;
        }

        for(int i = 1; i <= n - 1; i++) {
            if(data[i] == data[i + 1]) {
                phallendrome[i][i + 1] = true;
            }
        }


        for(int len = 3; len <= n; len++) {
            for(int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                if(data[i] == data[j] && phallendrome[i + 1][j - 1]) {
                    phallendrome[i][j] = true;
                }
            }
        }

        for(int[] q : query) {
            sb.append(phallendrome[q[0]][q[1]] ? "1" : "0").append("\n");
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}