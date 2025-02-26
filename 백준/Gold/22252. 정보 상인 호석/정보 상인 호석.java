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

    private static int Q;

    private static void input() {
        Q = inputProcessor.nextInt();
    }

    private static void pro() {
        long result = 0;

        Map<String, Queue<Integer>> brokerMap = new HashMap<>();

        while(Q > 0) {
            int command = inputProcessor.nextInt();
            String name = inputProcessor.next();

            if(command == 1) {
                int k = inputProcessor.nextInt();

                if(!brokerMap.containsKey(name)) {
                    brokerMap.put(name, new PriorityQueue<>(Comparator.reverseOrder()));
                }

                Queue<Integer> broker = brokerMap.get(name);
                for(int i = 1; i <= k; i++) {
                    int c = inputProcessor.nextInt();
                    broker.add(c);
                }
            } else {
                int b = inputProcessor.nextInt();
                if(brokerMap.containsKey(name)) {
                    Queue<Integer> broker = brokerMap.get(name);
                    for(int i = 1; i <= b; i++) {
                        if(broker.isEmpty()) break;

                        result += broker.poll();
                    }
                }
            }

            Q -= 1;
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