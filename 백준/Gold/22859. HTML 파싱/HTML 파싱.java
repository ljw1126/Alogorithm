import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        pro();
        output();
    }

    private static void pro() {
        String html = inputProcessor.nextLine();
        html = html.replaceAll("<main>|</main>", "");
        String[] tokens = html.split("</div>|<p>|</p>");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            int startTitle = token.indexOf("title=\"");
            if (startTitle != -1) {
                int endTitle = token.indexOf("\">", startTitle);
                String title = token.substring(startTitle + 7, endTitle);
                sb.append("title : ").append(title).append("\n");
            } else {
                String paragraph = token.replaceAll("<.*?>", "").replaceAll("\\s{2,}", " ");
                sb.append(paragraph).append("\n");
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