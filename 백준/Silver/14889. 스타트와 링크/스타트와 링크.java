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

    private static int n, half, result;
    private static int[][] data;

    private static void input() {
        n = inputProcessor.nextInt();
        data = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                data[i][j] = inputProcessor.nextInt();
            }
        }

        half = n / 2;
        result = Integer.MAX_VALUE;
    }

    private static void pro() {
        boolean[] used = new boolean[n + 1];
        rec(0, 1, used);

        sb.append(result);
    }

    private static void rec(int count, int start, boolean[] used) {
        if(count == half) {
            int[] teamA = new int[half];
            int[] teamB = new int[half];
            int i0 = 0;
            int i1 = 0;
            for(int i = 1; i <= n; i++) {
                if(used[i]) teamA[i0++] = i;
                else teamB[i1++] = i;
            }

            int accA = calculate(teamA);
            int accB = calculate(teamB);
            result = Math.min(result, Math.abs(accA - accB));
            return;
        }

        for(int i = start; i <= n; i++) {
            if(used[i]) continue;

            used[i] = true;
            rec(count + 1, i + 1, used);
            used[i] = false;
        }
    }

    private static int calculate(int[] team) {
        int result = 0;
        for(int i = 0; i < half - 1; i++) {
            for(int j = i + 1; j < half; j++) {
                result += (data[team[i]][team[j]] + data[team[j]][team[i]]);
            }
        }

        return result;
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