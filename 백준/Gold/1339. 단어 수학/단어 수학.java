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

    private static int n;
    private static String[] words;

    private static void input() {
        n = inputProcessor.nextInt();

        words = new String[n];
        for(int i = 0; i < n; i++) {
            words[i] = inputProcessor.nextLine();
        }
    }

    private static void pro() {
        // 알파벳에 대한 가중치를 구한다
        int[] weights = new int[27];
        for(String word : words) {
            int len = word.length();
            for(char c : word.toCharArray()) {
                weights[c - 'A'] += weight(len);
                len -= 1;
            }
        }

        List<Alphabet> alphabetList = new ArrayList<>();
        for(int i = 0; i <= 26; i++) {
            if(weights[i] == 0) continue;

            alphabetList.add(new Alphabet(i, weights[i]));
        }

        Collections.sort(alphabetList);

        int[] alphabets = new int[26];
        int c = 9;
        for(Alphabet a : alphabetList) {
            alphabets[a.idx] = c;
            c -= 1;
        }

        int result = 0;
        for(String word : words) {
            int num = 0;
            for(char w : word.toCharArray()) {
                num *= 10;
                num += alphabets[w - 'A'];
            }

            result += num;
        }

        sb.append(result);
    }

    private static class Alphabet implements Comparable<Alphabet> {
        private final int idx;
        private final int weight;

        public Alphabet(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        // 내림차순
        public int compareTo(Alphabet o) {
            return o.weight - this.weight;
        }
    }


    private static int weight(int len) {
        int result = 1;
        for(int i = 1; i <= len; i++) {
            result *= 10;
        }

        return result;
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