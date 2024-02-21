import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] tree;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        tree = new int[27][2];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A';

            for(int j = 0; j < 2; j++) {
                char child = st.nextToken().charAt(0);

                if(child != '.') tree[parent][j] = (child - 'A');
                else tree[parent][j] = -1;
            }
        }

    }

    // 전위 순회 - 루트, 왼쪽, 오른쪽
    static void preorder(int node) {
        if(node == -1) return;

        sb.append((char)(node + 'A'));
        preorder(tree[node][0]);
        preorder(tree[node][1]);
    }

    // 중위 순회 - 왼쪽, 루트, 오른쪽
    static void inorder(int node) {
        if(node == -1) return;

        inorder(tree[node][0]);
        sb.append((char)(node + 'A'));
        inorder(tree[node][1]);
    }

    // 후위 순회 - 왼쪽, 오른족, 루트
    static void postorder(int node) {
        if(node == -1) return;

        postorder(tree[node][0]);
        postorder(tree[node][1]);
        sb.append((char)(node + 'A'));
    }

    static void pro() {
        int rootNode = 0;

        preorder(rootNode);
        sb.append("\n");

        inorder(rootNode);
        sb.append("\n");

        postorder(rootNode);
        sb.append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}