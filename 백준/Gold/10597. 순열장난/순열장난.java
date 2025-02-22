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

    private static int len;
    private static int[] nums;
    private static boolean finished;

    private static void input() {
        String text = inputProcessor.nextLine();

        len = text.length();
        nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = text.charAt(i) - '0';
        }
    }

    private static void pro() {
        boolean[] visited = new boolean[51];
        rec(0, 0, visited, new ArrayList<>());
    }

    private static void rec(int idx, int max, boolean[] visited, List<Integer> selected) {
        if(finished) return;
        if(idx == len) {
            for(int i = 1; i <= max; i++) {
                if(!visited[i]) return;
            }

            finished = true;
            for(int n : selected) {
                sb.append(n).append(" ");
            }

            return;
        }

        int v1 = nums[idx];
        if(!visited[v1] && v1 > 0) {
            selected.add(v1);
            visited[v1] = true;

            rec(idx + 1, Math.max(max, v1), visited, selected);

            selected.remove(selected.size() - 1);
            visited[v1] = false;
        }

        if(idx + 1 < len) {
            int v2 = v1 * 10;
            v2 += nums[idx + 1];

            if(v2 > 50) return;
            if(visited[v2]) return;

            selected.add(v2);
            visited[v2] = true;

            rec(idx + 2, Math.max(max, v2), visited, selected);

            visited[v2] = false;
            selected.remove(selected.size() - 1);
        }

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