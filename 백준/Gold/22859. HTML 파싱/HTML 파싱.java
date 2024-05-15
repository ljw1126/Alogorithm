import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {

    }

    private static void pro() {
        String html = inputProcessor.nextLine();
        html = html.replaceAll("<main>|</main>", "");

        int left = 0;
        int right = html.length();
        while (left < right) {
            int divOpen = html.indexOf("title=\"", left);
            if (divOpen == -1) break;

            int titleEnd = html.indexOf("\">", divOpen);
            String title = html.substring(divOpen + 7, titleEnd);
            sb.append("title : " + title).append("\n");

            int divClose = html.indexOf("</div>", divOpen);

            int cursor = titleEnd;
            while (true) {
                int paragraphOpen = html.indexOf("<p>", cursor);

                if (paragraphOpen == -1) break;
                if (paragraphOpen > divClose) break;

                int paragraphClose = html.indexOf("</p>", paragraphOpen);

                String paragraphBody = html.substring(paragraphOpen + 3, paragraphClose);
                sb.append(erased(paragraphBody)).append("\n");

                cursor = paragraphClose;
            }

            left = divClose;
        }
    }

    private static String erased(String text) {
        String result = "";
        boolean openTag = false;
        boolean closeTag = true;
        boolean blank = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '<') {
                openTag = true;
                closeTag = false;
                continue;
            }

            if (c == '>') {
                openTag = false;
                closeTag = true;
                continue;
            }

            if (openTag | !closeTag) continue;

            if (c == ' ') {
                if (blank) continue;

                blank = true;
            } else {
                blank = false;
            }

            result += c;
        }

        return result;
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