import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N;
    static boolean[] visit;
    static int[] parent;
    static ArrayList<Integer>[] adj;
    
    static void input(){
        N = scan.nextInt();
        visit = new boolean[N+1];
        adj = new ArrayList[N+1];
        parent = new int[N+1];

        for(int i=1; i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=0; i<N-1;i++){ 
            int x = scan.nextInt();
            int y = scan.nextInt();

            adj[x].add(y);
            adj[y].add(x);    
        }
    }

    static void dfs(int start, int parnt){

        visit[start] = true;
        parent[start] = parnt;

        for(int i : adj[start]){
            if(visit[i]) continue;
            dfs(i, start);            
        }
    }


    static void pro(){
        dfs(1,0);
        for(int i=2; i <= N;i++) sb.append(parent[i]).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br ;
        StringTokenizer st;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){

            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }


    }
}
