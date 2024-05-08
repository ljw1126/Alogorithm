import java.util.*;
import java.io.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M, K, Q;
    private static Map<String, Folder> DIRECTORY = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 폴더의 총 개수
        M = inputProcessor.nextInt(); // 파일의 총 개수

        for (int i = 1; i <= N + M; i++) {
            String parent = inputProcessor.next(); // 상위 폴더 명
            String child = inputProcessor.next(); // 폴더 또는 파일명
            int c = inputProcessor.nextInt(); // 폴더 1, 파일 0

            Folder dir = findFolder(parent);
            if (c == 1) { // 폴더
                Folder other = findFolder(child);
                dir.addFolder(other);
            } else { // 파일
                dir.addFile(child);
            }
        }
    }

    private static Folder findFolder(String name) {
        if (DIRECTORY.containsKey(name)) {
            return DIRECTORY.get(name);
        }

        Folder newFolder = new Folder(name);
        DIRECTORY.put(name, newFolder);
        return newFolder;
    }

    private static class Folder {
        private Set<Folder> childs = new HashSet<>();
        private Set<String> fildNames = new HashSet<>();
        private int fileCount;
        private String name;

        public Folder(String name) {
            this.name = name;
        }

        public void addFolder(Folder folder) {
            childs.add(folder);
        }

        public void addFile(String fileName) {
            fildNames.add(fileName);
            fileCount += 1;
        }

        public void merge(Folder other) {
            fildNames.addAll(other.fildNames);
            fileCount += other.fileCount;
        }

        public void move(Folder other) {
            this.childs.addAll(other.childs);
            this.fildNames.addAll(other.fildNames);
            this.fileCount = this.fildNames.size();
        }

        public int size() {
            return this.fildNames.size();
        }

        public void removeFolder(Folder other) {
            childs.remove(other);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Folder folder = (Folder) other;
            return Objects.equals(name, folder.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static void dfs(Folder folder) {
        for (Folder child : folder.childs) {
            dfs(child);
            folder.merge(child);
        }
    }

    private static void pro() {
        K = inputProcessor.nextInt(); // 옮기는 횟수
        for (int i = 1; i <= K; i++) {
            String from = inputProcessor.next();
            String to = inputProcessor.next();

            moveDirectory(lastDirectoryName(from), lastDirectoryName(to));
        }

        dfs(DIRECTORY.get("main"));

        Q = inputProcessor.nextInt(); // 쿼리 수
        for (int i = 1; i <= Q; i++) {
            String path = inputProcessor.next();
            String folderName = lastDirectoryName(path);
            Folder folder = DIRECTORY.get(folderName);
            sb.append(folder.size()).append(" ").append(folder.fileCount).append("\n");
        }
    }

    private static void moveDirectory(String from, String to) {
        Folder fromFolder = DIRECTORY.get(from);
        Folder toFolder = DIRECTORY.get(to);
        toFolder.move(fromFolder); // b -> a

        Set<String> keySet = DIRECTORY.keySet();
        for (String dir : keySet) {
            if (dir.equals(from)) continue;

            Folder folder = DIRECTORY.get(dir);
            folder.removeFolder(fromFolder);
        }
    }

    private static String lastDirectoryName(String path) {
        int idx = path.lastIndexOf("/");
        String folderName = path.substring(idx + 1);
        return folderName;
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