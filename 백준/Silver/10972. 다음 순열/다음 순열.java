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
    private static int[] data;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        data = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

    }

    /**
     * 1. 증
     */
    private static void pro() {
        int idx = -1;
        for(int i = n - 1; i > 0; i--) {
            if(data[i - 1] < data[i]) {
                idx = i; // 우측 그룹 시작점
                break;
            }
        }

        if(idx == -1) {
            sb.append(-1);
            return;
        }

        for(int i = n - 1; i >= idx; i--) {
            if(data[idx - 1] < data[i]) {
                int temp = data[idx - 1];
                data[idx - 1] = data[i];
                data[i] = temp;
                break;
            }
        }

        Arrays.sort(data, idx, n);
        for(int i = 0; i < n; i++) {
            sb.append(data[i]).append(" ");
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}