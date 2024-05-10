import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    
    static int[] data;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

    }

    static boolean isEvenPalindrome(int i, int j) {
        if(j - i + 1 % 2 == 1) return false;
        for(int k = 0; k <= (j - i) / 2; k++) {
            if(data[i + k] != data[j - k]) return false;
        }

        return true;
    }

    static void pro() throws IOException {
        int result = 0;
        int i = 1;
        int j = 0;
        while(i < N) {
            boolean find = false;
            for(j = i + 1; j <= N; j += 2) { 
                if(isEvenPalindrome(i, j)) {
                    find = true;
                    result += 1;
                    break;
                }
            }

            if(!find) {
                result = -1;
                break;
            }

            i = j + 1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}