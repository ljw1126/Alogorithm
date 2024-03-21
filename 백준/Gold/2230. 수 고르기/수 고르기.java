import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N, M;
    static int[] a;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = scan.nextInt();
        }
    }

    static void pro() {
        Arrays.sort(a,1,N+1);
        int R = 1, ans = Integer.MAX_VALUE;
        
        for (int L = 1; L <= N; L++) {
            while(R+1 <= N && a[R] - a[L] < M){
                R++;
            }
         
            if(a[R] - a[L] >= M){
                 ans = Math.min(ans, a[R] - a[L]);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
