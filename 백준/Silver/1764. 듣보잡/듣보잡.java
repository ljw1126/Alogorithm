import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static String[] A, B;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        A = new String[N + 1];
        B = new String[M + 1];

        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextLine();
        }

        for(int i = 1; i <= M; i++) {
            B[i] = inputProcessor.nextLine();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1); // 정렬 보장

        List<String> result = new LinkedList<>();
        for(int i = 1; i <= M; i++) {
            if(binarySearch(A, B[i], 1, N)) {
                result.add(B[i]);
            }
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(String name : result) {
            sb.append(name).append("\n");
        }
    }

    private static boolean binarySearch(String[] arr, String target, int start, int end) {
        int L = start;
        int R = end;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(arr[mid].equals(target)) return true;
            else if(arr[mid].compareTo(target) > 0) R = mid - 1;
            else L = mid + 1;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }
    }
    
}