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

    private static int n, k;
    private static int[] belt;

    private static void input() {
        n = inputProcessor.nextInt();
        k = inputProcessor.nextInt();

        int lng = (2 * n);
        belt = new int[lng];
        for(int i = 0; i < lng; i++) {
            belt[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int result = 0;
        int count = 0;

        boolean[] robots = new boolean[n];

        while(true) {
            result += 1;

            int[] temp = new int[2 * n];
            for(int i = 0; i < 2 * n; i++) {
                temp[(i + 1) % (2 * n)] = belt[i];
            }
            belt = temp;

            for(int i = n - 1; i > 0; i--) {
                robots[i] = robots[i - 1];
            }
            robots[n - 1] = false;
            robots[0] = false;

            for(int i = n - 1; i > 0; i--) {
                if(robots[i - 1] && !robots[i] && belt[i] >= 1) {
                    belt[i] -= 1;
                    robots[i] = true;
                    robots[i - 1] = false;

                    if(belt[i] == 0) count += 1;
                }
            }

            if(belt[0] > 0 && !robots[0]) {
                belt[0] -= 1;
                robots[0] = true;

                if(belt[0] == 0) count += 1;
            }

            if(count >= k) break;
        }



        sb.append(result);

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