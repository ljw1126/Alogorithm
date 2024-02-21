import java.util.*;
import java.io.*;

public class Main {
    
   static long S, T;

    static Set<Long> valueSet = new HashSet<>();

    static String[] ops = new String[]{"*", "+", "-", "/"};
    static class Node {
        long value;
        String operator;

        public Node(long value, String operator) {
            this.value = value;
            this.operator = operator;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());

        br.close();
    }

    static void bfs(long start) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, ""));
        valueSet.add(start);

        boolean find = false;
        while(!que.isEmpty()) {
            Node x = que.poll();
            if(x.value == T) {
                find = true;
                System.out.println(x.operator);
                break;
            }

            for(int i = 0; i < 4; i++) {
                if(i == 3 && x.value == 0) continue; // / by zero error

                long v = cal(x.value, i);
                if(!valueSet.contains(v)) {
                    valueSet.add(v);
                    que.add(new Node(v, x.operator + ops[i]));
                }
            }
        }

        if(!find) System.out.println(-1);
    }

    static long cal(long v, int op) {
        long result = 0L;
        switch (op) {
            case 0 : result = v * v; break;
            case 1 : result = v + v; break;
            case 2 : result = 0L; break;
            case 3 : result = 1; break;
        }

        return result;
    }

    static void pro() {
        if(S == T) System.out.println(0);
        else bfs(S);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}