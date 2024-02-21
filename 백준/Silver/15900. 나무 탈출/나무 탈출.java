import java.util.*;
import java.io.*;

public class Main {
    
    static FastReader scan = new FastReader();
   
    static int n,ans;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input(){
        n = scan.nextInt();
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
   
        visit = new boolean[n+1];
    }

    static void dfs(int node, int cnt){

        visit[node] = true;
        
        for(int child : adj[node]){
            if(visit[child]) continue;
            dfs(child,cnt+1);
        }

        if(node != 1 && adj[node].size() == 1){
            ans += cnt;
        }

    }

    static void pro() {
        dfs(1,0);

        if(ans %2 == 0){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }
    
    }

    public static void main(String[] args) throws IOException {
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
