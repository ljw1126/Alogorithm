import java.util.*;
import java.io.*;

public class Main {
    
   private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static List<ImageFile> IMAGE_FILES = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 파일 개수
        M = inputProcessor.nextInt(); // 파일 확장자 개수

        for (int i = 1; i <= N; i++) {
            String text = inputProcessor.nextLine();
            String[] tokens = text.split("\\.");
            IMAGE_FILES.add(new ImageFile(tokens[0], tokens[1]));
        }

        Set<String> enableExtension = new HashSet<>();
        for (int i = 0; i < M; i++) {
            String ext = inputProcessor.nextLine();
            enableExtension.add(ext);
        }

        for (ImageFile imageFile : IMAGE_FILES) {
            if (enableExtension.contains(imageFile.extension)) {
                imageFile.extensionOrder = 0;
            } else {
                imageFile.extensionOrder = 1;
            }
        }
    }

    private static class ImageFile implements Comparable<ImageFile> {
        private String fileName;
        private String extension;
        private int extensionOrder;

        public ImageFile(String fileName, String extension) {
            this.fileName = fileName;
            this.extension = extension;
        }

        public void setOrder(Map<String, Integer> orders, int last) {
            this.extensionOrder = orders.getOrDefault(this.extension, last);
        }

        @Override
        public int compareTo(ImageFile o) {
            if (this.fileName.compareTo(o.fileName) != 0) return this.fileName.compareTo(o.fileName);

            if (this.extensionOrder != o.extensionOrder) return this.extensionOrder - o.extensionOrder;

            return extension.compareTo(o.extension);
        }

        public String fullName() {
            return fileName + "." + extension;
        }
    }

    private static void pro() {
        Collections.sort(IMAGE_FILES);
        for (ImageFile imageFile : IMAGE_FILES) {
            sb.append(imageFile.fullName()).append("\n");
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