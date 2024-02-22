import java.io.*;
import java.util.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] adj;

    static void input(){    

        N = scan.nextInt();
        M = scan.nextInt();

        indeg = new int[N+1];
        adj = new ArrayList[N+1];

        for(int i=1;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=0; i< M ; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            indeg[b]++;
        }

    }

    static void pro(){
        Deque<Integer> que = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(indeg[i] == 0) que.add(i);
        }

        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append(' ');
            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0) que.add(y);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br;
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

        String nextLing(){
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
