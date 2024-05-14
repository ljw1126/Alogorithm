import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int[] DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
    private static int N, F, MINUTE;
    private static String L;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 대여장에 작성된 정보의 개수
        L = inputProcessor.next(); // 대여기간 DDD/hh:mm
        F = inputProcessor.nextInt(); // 벌금 (원/분당)

        String[] tokens = L.split("/");
        int days = Integer.parseInt(tokens[0]);

        String[] times = tokens[1].split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        MINUTE += days * 24 * 60;
        MINUTE += hour * 60;
        MINUTE += minute;
    }

    private static void pro() {
        Map<String, Long> resultMap = new HashMap<>(); // 최대치
        Map<String, Map<String, Long>> bookInfoMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String line = inputProcessor.nextLine();
            String[] tokens = line.split(" ");
            String date = tokens[0];
            String time = tokens[1];
            String parts = tokens[2];
            String nickname = tokens[3];

            long times = convert(date, time); // 대여 시간 or 반납 시간
            if (!bookInfoMap.containsKey(nickname)) {
                bookInfoMap.put(nickname, new HashMap<>());
                bookInfoMap.get(nickname).put(parts, times);
            } else {
                Map<String, Long> bookMap = bookInfoMap.get(nickname);
                if (bookMap.containsKey(parts)) {
                    long borrowTime = bookMap.remove(parts);
                    long diff = times - borrowTime - MINUTE;
                    
                    if (diff > 0) {
                        long fee = diff * F;
                        resultMap.put(nickname, resultMap.getOrDefault(nickname, 0L) + fee);
                    }
                } else {
                    bookMap.put(parts, times);
                }
            }
        }

        if (resultMap.isEmpty()) {
            sb.append(-1);
        } else {
            List<String> keySet = new ArrayList<>(resultMap.keySet());
            Collections.sort(keySet);

            keySet.forEach(nickname -> sb.append(nickname).append(" ").append(resultMap.get(nickname)).append("\n"));
        }
    }

    private static long convert(String date, String time) {
        String[] dates = date.split("-");
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        for (int i = 0; i < month; i++) {
            day += DAYS[i];
        }

        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        // 당일인 경우 -1
        return ((long) (day - 1) * 24 * 60 + (hour * 60) + minute) ;
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