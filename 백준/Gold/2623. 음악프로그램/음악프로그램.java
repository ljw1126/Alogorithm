import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb =new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indegree;
    
    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        
        indegree = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            adj[i] = new ArrayList<>();

        for(int i=0; i < M;i++){

            int size = scan.nextInt();
            int parent = scan.nextInt();
            for(int j = 1; j < size ; j++){
                int child = scan.nextInt();
                adj[parent].add(child);
                indegree[child]++;
                parent = child;
            }
        }
    }

    static int topologicalSort(){
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> que = new LinkedList();
        for(int i=1;i<=N;i++){
            if(indegree[i] == 0) que.add(i);
        }

        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append('\n');
            result.add(x);

            for(int y : adj[x]){
                indegree[y]--;
                if(indegree[y] == 0) que.add(y);
            }
        }
        return result.size();
    }


    static void pro(){
        if(topologicalSort() != N) System.out.println(0);
        else System.out.println(sb);
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
