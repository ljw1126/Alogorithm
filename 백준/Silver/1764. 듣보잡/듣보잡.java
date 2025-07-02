import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static String[] A, B;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        A = new String[N + 1];
        B = new String[M + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextLine();
        }

        for (int i = 1; i <= M; i++) {
            B[i] = inputProcessor.nextLine();
        }
    }

    private static void pro() {
        List<String> result = new ArrayList<>();
        Arrays.sort(B, 1, M + 1);

        for (int i = 1; i <= N; i++) {
            if (binarySearch(B, 1, M, A[i])) {
                result.add(A[i]);
            }
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        result.forEach(v -> sb.append(v).append("\n"));
    }

    private static boolean binarySearch(String[] arr, int start, int end, String target) {
        int L = start;
        int R = end;

        while (L <= R) {
            int mid = (L + R) / 2;
            int result = arr[mid].compareTo(target);

            if (result == 0) return true;
            else if (result < 0) L = mid + 1;
            else R = mid - 1;
        }

        return false;
    }


    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}