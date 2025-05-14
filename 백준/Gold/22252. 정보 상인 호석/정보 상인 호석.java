import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int q;

    private static void input() {
        q = inputProcessor.nextInt(); // 쿼리 수
    }

    private static void pro() {
        Map<String, Queue<Integer>> dataMap = new HashMap<>();
        long result = 0;
        for(int i = 1; i <= q; i++) {
            int query = inputProcessor.nextInt();
            String name = inputProcessor.next();
            int k = inputProcessor.nextInt();

            dataMap.computeIfAbsent(name, s -> new PriorityQueue<Integer>(Comparator.reverseOrder()));

            if(query == 1) { // 정보를 얻은 고릴라
                Queue<Integer> pq = dataMap.get(name);
                for(int j = 1; j <= k; j++) {
                    pq.add(inputProcessor.nextInt());
                }

                continue;
            }

            Queue<Integer> pq = dataMap.get(name);
            for(int j = 1; j <= k; j++) {
                if(pq.isEmpty()) break;

                result += pq.poll();
            }
        }

        sb.append(result);
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
    
}