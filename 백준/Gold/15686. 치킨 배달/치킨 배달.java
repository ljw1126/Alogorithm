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

    private static List<Chicken> chickenList;
    private static List<House> houseList;
    private static int n, m, result;
    private static void input() {
        n = inputProcessor.nextInt(); // N * N 도시
        m = inputProcessor.nextInt(); // 폐업하지 않을 치킨집 최대 M개

        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int v = inputProcessor.nextInt();

                if(v == 1) { // 집
                    houseList.add(new House(i, j));
                } else if(v == 2) { // 치킨집
                    chickenList.add(new Chicken(i, j));
                }
            }
        }

        result = Integer.MAX_VALUE;
    }

    private static class Chicken {
        private final int x;
        private final int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class House {
        private final int x;
        private final int y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void pro() {
        combination(0, 0, new int[m]);

        sb.append(result);
    }

    private static void combination(int idx, int count, int[] selected) {
        if(count == m) {
            int distance = 0;
            for(House h : houseList) {
                int minDiff = Integer.MAX_VALUE;

                for(int i : selected) {
                    Chicken c = chickenList.get(i);
                    int diff = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
                    if(diff < minDiff) {
                        minDiff = diff;
                    }
                }

                distance += minDiff;
            }

            result = Math.min(result, distance);
            return;
        }
        if(idx >= chickenList.size()) return;

        selected[count] = idx;
        combination(idx + 1, count + 1, selected);
        selected[count] = -1;

        combination(idx + 1, count, selected);
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