import java.util.*;
import java.io.*;

public class Main {
   public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    private static int n, k;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    private static void pro() throws IOException {
        List<Integer> arr = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            arr.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int idx = 0;
        while(arr.size() > 1) {
            int next = (idx + k - 1) % arr.size();
            sb.append(arr.get(next));
            sb.append(", ");
            arr.remove(next);
            idx = next;
        }
        sb.append(arr.get(0));
        sb.append(">");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}