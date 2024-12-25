import java.util.*;
import java.io.*;

public class Main {
     private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static String S;
    
    private static void input() {
        S = inputProcessor.nextLine().trim();
    }

    private static void pro() {
        char[] arr = S.toCharArray();
        int[] nums = new int[2]; // 0, 1

        nums[arr[0] - '0'] += 1;
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] == arr[i + 1]) continue;

            if(arr[i + 1] == '0') nums[0] += 1;
            else nums[1] += 1;
        }

        sb.append(Math.min(nums[0], nums[1]));
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}