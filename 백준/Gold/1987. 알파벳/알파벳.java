import java.util.*;
import java.io.*;

public class Main {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int R,C, ans;
    static int[] alpabet_cnt;
    static int[][] map;
    static boolean[] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void dfs(int x, int y, int cnt){
        visit[map[x][y]] = true;

        for(int i = 0 ; i < 4 ; i++){
            int dx = x + dir[i][0] , dy = y + dir[i][1];
            
            if(dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
            if(visit[map[dx][dy]]) continue;
            
            dfs(dx, dy, cnt+1);
        }

        visit[map[x][y]] = false;
        ans = Math.max(ans, cnt); 
    }


    static void pro(){
        dfs(0,0, 1);
        System.out.println(ans);
    }

    static void input(){
        R = scan.nextInt();
        C = scan.nextInt();

        visit = new boolean[26];
        alpabet_cnt = new int[26];
    
        map = new int[R][C];
        for(int i=0;i<R;i++){
            String str = scan.nextLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j) - 'A';
            }
        } 
        
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
