import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final String SHARP = "#";

    private static int N, Q;
    private static List<List<String>> LOGS;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        Q = inputProcessor.nextInt();

        LOGS = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            LOGS.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            String line = inputProcessor.nextLine();
            String[] tokens = line.split(SHARP);

            String date = tokens[0];
            int level = Integer.parseInt(tokens[1]);

            LOGS.get(level).add(date);
        }
    }

    private static void pro() {
        for (int i = 1; i <= Q; i++) {
            String query = inputProcessor.nextLine();
            String[] tokens = query.split(SHARP);

            String startDate = tokens[0];
            String endDate = tokens[1];
            int level = Integer.parseInt(tokens[2]);

            int count = 0;
            for (int l = level; l <= 6; l++) {
                count += countByRange(LOGS.get(l), startDate, endDate);
            }

            sb.append(count).append("\n");
        }
    }

    private static int countByRange(List<String> logs, String startDate, String endDate) {
        if (logs.isEmpty()) return 0;

        int lower = lowerBound(logs, 0, logs.size(), startDate);
        int upper = upperBound(logs, 0, logs.size(), endDate);

        return upper - lower;
    }

    private static int lowerBound(List<String> logs, int start, int end, String startDate) {
        int L = start;
        int R = end;

        while (L < R) {
            int mid = (L + R) / 2;

            if (logs.get(mid).compareTo(startDate) < 0) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
    }

    private static int upperBound(List<String> logs, int start, int end, String endDate) {
        int L = start;
        int R = end;

        while (L < R) {
            int mid = (L + R) / 2;

            if (logs.get(mid).compareTo(endDate) <= 0) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
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