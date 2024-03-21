import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int T, N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        T = inputProcessor.nextInt();
        N = inputProcessor.nextInt();
        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        M = inputProcessor.nextInt();
        B = new int[M + 1];
        for(int i =1; i <= M; i++) {
            B[i] = inputProcessor.nextInt();
        }
    }

    private static long[] preprocess(int size, int[] arr) {
        int len = size * (size + 1) / 2;
        long[] subArray = new long[len + 1];

        int idx = 1;
        for(int i = 1; i <= size; i++) {
            long sum = 0;
            for(int j = i; j <= size; j++) {
                sum += arr[j];
                subArray[idx++] = sum;
            }
        }

        return subArray;
    }

    private static void pro() {
        long[] subArrayA = preprocess(N, A);
        long[] subArrayB = preprocess(M, B);

        Arrays.sort(subArrayA, 1, subArrayA.length);
        Arrays.sort(subArrayB, 1, subArrayB.length);


        long count = 0;

        int L = 1;
        int R = subArrayB.length - 1;
        while(L < subArrayA.length && R > 0) {
            long sum = subArrayA[L] + subArrayB[R];

            if (sum == T) {
                int toL = L;
                int fromR = R;

                while(toL <= subArrayA.length - 1 && subArrayA[L] == subArrayA[toL]) {
                    toL += 1;
                }

                while(fromR > 0 && subArrayB[R] == subArrayB[fromR]) {
                    fromR -= 1;
                }

                count += ((long)(toL - L) * (R - fromR));
                L = toL;
                R = fromR;
                continue;
            }

            if (sum > T) {
                R -= 1;
            } else {
                L += 1;
            }
        }


        sb.append(count);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
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
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}