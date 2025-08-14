import java.util.*;
import java.io.*;

public class Main {
    
    static MyReader scan = new MyReader();
    
    static int N,X,ans;
    static boolean[] visit;

    static class Node {
        int idx, time;

        public Node(int _idx, int _time){
            this.idx = _idx;
            this.time = _time;
        }
    }

    static void dijkstra(int start){

        Queue<Node> que = new LinkedList();
        que.add(new Node(start, 0));
        visit[start] = true;
  
        while(!que.isEmpty()){
            Node n = que.poll();
          
            if(n.idx == X){
                ans = n.time;
                break;
            }

            if(0 <= n.idx  * 2 && n.idx  * 2 <= 100000 && !visit[n.idx  * 2]){
                visit[n.idx  * 2] = true;
                que.add(new Node(n.idx  * 2, n.time));
            }

            if(0 <= n.idx -1 && n.idx -1 <= 100000 && !visit[n.idx -1]){
                visit[n.idx -1] = true;
                que.add(new Node(n.idx -1, n.time +1 ));
            }
        
             if(0 <= n.idx + 1 && n.idx + 1 <= 100000 && !visit[n.idx + 1]){
                visit[n.idx + 1] = true;
                que.add(new Node(n.idx + 1, n.time +1));
            }
    
           
        }

        System.out.println(ans);
    }

    static void input(){
        N = scan.nextInt();
        X = scan.nextInt();
        visit = new boolean[100001];
    }

    static void pro(){
        dijkstra(N);
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
