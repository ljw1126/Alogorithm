import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, T, W, M;
    private static Map<Integer, Person> PENDING_PERSON = new HashMap<>();
    private static Deque<Person> BANK = new ArrayDeque<>();

    private static class Person {
        private int id;
        private int needTime;
        private int workTime;

        public Person(int id, int needTime) {
            this.id = id;
            this.needTime = needTime;
            this.workTime = 0;
        }

        public boolean isEnd() {
            return this.needTime == 0;
        }

        public void work() {
            this.needTime -= 1;
            this.workTime += 1;
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 0초에 들어오는 고객 수
        T = inputProcessor.nextInt();
        W = inputProcessor.nextInt();

        for (int i = 1; i <= N; i++) {
            int Px = inputProcessor.nextInt();
            int Tx = inputProcessor.nextInt();
            BANK.add(new Person(Px, Tx));
        }

        M = inputProcessor.nextInt(); // 1초 이후 은행에 들어온 고객 수
        for (int i = 1; i <= M; i++) {
            int Px = inputProcessor.nextInt(); // id
            int Tx = inputProcessor.nextInt(); // 업무를 처리하는데 필요한 시간
            int Cx = inputProcessor.nextInt(); // 영업시작 Cx 초 후에 은행 입장

            if (Cx <= 200000) {
                PENDING_PERSON.put(Cx, new Person(Px, Tx));
            }
        }

    }


    private static void pro() {
        for (int i = 1; i <= W; i++) {
            if (PENDING_PERSON.containsKey(i)) { // count 초에 들어올 사람이 있는가
                Person next = PENDING_PERSON.remove(i);
                BANK.add(next);
            }

            Person cur = BANK.poll();
            sb.append(cur.id).append("\n");

            cur.work();
            if (!cur.isEnd()) {
                if (cur.workTime >= T) {
                    cur.workTime = 0;
                    BANK.add(cur);
                } else {
                    BANK.addFirst(cur);
                }
            }
        }

    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
    
}