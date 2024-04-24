import java.util.*;
import java.io.*;

public class Main {

    static FastReader scan = new FastReader();
  
    static int N,M;
    static ArrayList<Integer>[] arr;
    static int[] dist;

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        arr = new ArrayList[N+1];
        dist = new int[N+1];
        
        for(int i = 1 ; i <= N ; i++) arr[i] = new ArrayList<>(); 

        for(int i = 1 ; i <= M ; i++){ 
            int a = scan.nextInt();
            int b = scan.nextInt();
            arr[a].add(b);
            arr[b].add(a);
        } 
    }

    public static int bfs(int start){

        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        
        for(int i=1; i <= N; i++){
            dist[i] = -1;
        }
        
        dist[start] = 0;
        int ret = 0;

        while(!Q.isEmpty()){
            int x = Q.poll();
            ret += dist[x];

            for(int y : arr[x]){
                if(dist[y] != -1) continue; 

                dist[y] = dist[x]+1;
                Q.add(y);
            }
        }

        return ret;
    }

    public static void pro(){
        int minV = bfs(1), minIdx = 1;
        for(int i = 2; i <= N ; i++){
            int v = bfs(i);
            if(minV > v){
                minV = v ;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
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
