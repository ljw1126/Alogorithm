import java.util.*;
import java.io.*;

public class Main {
   private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static Node preOrder;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        preOrder = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String input = br.readLine();

            if (input == null || input.isBlank()) break;

            preOrder.add(Integer.parseInt(input));
        }
    }

    private static class Node {
        private final int no;
        private Node left;
        private Node right;

        public Node(int no) {
            this.no = no;
            this.left = null;
            this.right = null;
        }

        public void add(int value) {
            if (value < no) {
                if (left == null) {
                    left = new Node(value);
                } else {
                    left.add(value);
                }
            } else if (value > no) {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.add(value);
                }
            }
        }
    }

    private static void pro() {
        postOrder(preOrder);
    }

    private static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.no).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}