import java.util.*;
import java.io.*;

public class Main {
    
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static int[][] alphabets;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        alphabets = new int[27][2]; // 0 : 왼쪽, 1: 오른쪽
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = toInt(st.nextToken());
            int left = toInt(st.nextToken());
            int right = toInt(st.nextToken());

            alphabets[parent][0] = left;
            alphabets[parent][1] = right;
        }
    }

    private static int toInt(String s) {
        if (s.equals(".")) return -1;

        return s.charAt(0) - 'A';
    }

    private static void pro() {
        int root = 0; // 'A';

        preOrder(root);
        sb.append("\n");

        inOrder(root);
        sb.append("\n");

        postOrder(root);
    }

    private static char toChar(int node) {
        return (char) (node + 'A');
    }

    // 전위 순회 - 루트 > 왼쪽 > 오른쪽
    private static void preOrder(int node) {
        if (node == -1) return;

        sb.append(toChar(node));
        preOrder(alphabets[node][0]);
        preOrder(alphabets[node][1]);
    }

    // 중위 순회
    private static void inOrder(int node) {
        if (node == -1) return;

        inOrder(alphabets[node][0]);
        sb.append(toChar(node));
        inOrder(alphabets[node][1]);
    }

    // 후위 순회
    private static void postOrder(int node) {
        if (node == -1) return;

        postOrder(alphabets[node][0]);
        postOrder(alphabets[node][1]);
        sb.append(toChar(node));
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}