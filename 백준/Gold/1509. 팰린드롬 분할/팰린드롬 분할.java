import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static String text;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
    }

    private static void pro() {
        int length = text.length();
        char[] arr = new char[length + 1];
        for(int i = 1; i <= length; i++){
            arr[i] = text.charAt(i - 1);
        }

        boolean[][] phallendrome = new boolean[length + 1][length + 1];
        for(int i = 1; i <= length; i++) {
            phallendrome[i][i] = true;
        }

        for(int i = 1; i <= length - 1; i++) {
            if(arr[i] == arr[i + 1]) {
                phallendrome[i][i + 1] = true;
            }
        }

        for(int len = 3; len <= length; len++) {
            for(int i = 1; i <= length - len + 1; i++) {
                int j = i + len - 1;

                if(arr[i] == arr[j] && phallendrome[i + 1][j - 1]) {
                    phallendrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= length; i++) {
            dp[i] = dp[i - 1] + 1;

            for(int j = 1; j < i; j++) {
                if(phallendrome[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        sb.append(dp[length]);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}