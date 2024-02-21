import java.io.*;
import java.util.*;

public class Main {
    
  static StringBuilder sb = new StringBuilder();

    static Node ROOT;

    static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public void insert(int value) {
            if(this.value > value) {
                if(this.left == null) this.left = new Node(value);
                else this.left.insert(value);
            } else {
                if(this.right == null) this.right = new Node(value);
                else this.right.insert(value);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String input = br.readLine();
            if(input == null || "".equals(input)) break;

            int value = Integer.parseInt(input);
            if(ROOT == null) ROOT = new Node(value);
            else ROOT.insert(value);
        }
    }

    static void postOrder(Node node) {
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
    }

    static void pro() {
        postOrder(ROOT);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
