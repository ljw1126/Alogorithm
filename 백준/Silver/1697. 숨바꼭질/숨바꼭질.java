import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] dist;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        visit = new boolean[100005];
        dist = new int[100005];
    }

    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(N);
        visit[N] = true;
        dist[N] = 0;
 
        while(!Q.isEmpty()){
            int x = Q.poll();
            int nx1 = x +1, nx2 = x - 1, nx3 = x * 2;
          
            if(0 <= nx1 && nx1 <= 100000 && !visit[nx1]){
                visit[nx1] = true;
                dist[nx1] = dist[x]+1;
                Q.add(nx1);
            }

            if(0 <= nx2 && nx2 <= 100000 && !visit[nx2]){
                visit[nx2] = true;
                dist[nx2] = dist[x]+1;
                Q.add(nx2);
            }

            if(0 <= nx3 && nx3 <= 100000 && !visit[nx3]){
                visit[nx3] = true;
                dist[nx3] = dist[x]+1;
                Q.add(nx3);
            }
        }
    }

    static void pro() {
        bfs();
        System.out.println(dist[K]);
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
