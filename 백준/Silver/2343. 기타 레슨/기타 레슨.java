import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int N, M, MIN, TOTAL;
    static int[] VIDEO;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static void pro() {
        int L = MIN;
        int R = TOTAL;
        int result = 0;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isPossible(mid)) {
                result = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int limit) {
        int count = 0;
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            if(sum + VIDEO[i] > limit) {
                count += 1;
                sum = 0;
            }

            sum += VIDEO[i];
        }

        if(sum != 0) count += 1;

        return count <= M; // 블루레이 M개에 다 담을 수 있는가
    }

    public static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 강의의 수
        M = inputProcessor.nextInt(); // 블루레이 개수

        VIDEO = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            VIDEO[i] = inputProcessor.nextInt();
            MIN = Math.max(MIN, VIDEO[i]);
            TOTAL += VIDEO[i];
        }
    }

    public static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
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

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}