import java.util.*;
import java.io.*;

public class Main {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static String[] fileds;
    static int R,C, o_cnt, v_cnt, to_cnt, tv_cnt;
    static boolean[][] visit;
    static int[][] points = {{-1,0},{0,-1},{1,0},{0,1}};

    public static void input(){
        R = scan.nextInt();
        C = scan.nextInt();

        fileds = new String[R];
        visit = new boolean[R][C];

        for(int i=0 ; i < R ; i++) fileds[i] = scan.nextLine();
    }

    public static void dfs(int x, int y){
        visit[x][y] = true;
        if(fileds[x].charAt(y) == 'o') o_cnt++;
        if(fileds[x].charAt(y) == 'v') v_cnt++;

        for(int k=0 ; k< 4 ; k++){
            int nx = x + points[k][0], ny = y + points[k][1];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            if(visit[nx][ny]) continue;
            if(fileds[nx].charAt(ny) == '#') continue;

            dfs(nx,ny);
        }
    }

    public static void pro(){
 
        for(int i=0 ; i < R ; i++){
            for(int j=0 ; j<C ; j++){
                if(fileds[i].charAt(j) == '#') continue;
                if(visit[i][j]) continue;
                
                o_cnt = 0; 
                v_cnt = 0;
                
                dfs(i,j);
                
                if(o_cnt > v_cnt) 
                    to_cnt += o_cnt;
                else
                    tv_cnt += v_cnt;
            }
        }
       
        sb.append(to_cnt).append(' ').append(tv_cnt);
        System.out.println(sb);
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
